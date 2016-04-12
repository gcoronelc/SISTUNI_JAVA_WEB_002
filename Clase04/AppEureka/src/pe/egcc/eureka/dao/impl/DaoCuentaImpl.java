package pe.egcc.eureka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import pe.egcc.eureka.dao.espec.DaoCuentaEspec;
import pe.egcc.eureka.db.AccesoDB;

/**
 *
 * @author Gustavo Coronel
 */
public class DaoCuentaImpl implements DaoCuentaEspec {

  @Override
  public List<Map<String, ?>> conMovimientos(String cuenta) {
    List<Map<String, ?>> lista;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = "select CUENCODIGO,MONENOMBRE,CUENSALDO, " 
          + "CUENESTADO,MOVINUMERO,MOVIFECHA, "
          + "MOVIIMPORTE,TIPOCODIGO,TIPONOMBRE " 
          + "from v_movimiento " 
          + "where CUENCODIGO = ?";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, cuenta);
      ResultSet rs = pstm.executeQuery();
      lista = JdbcUtil.rsToList(rs);
      rs.close();
      pstm.close();
    } catch (SQLException e) {
      throw new RuntimeException(e.getMessage());
    } catch (Exception e) {
      String mensaje = "Se ha producido un error, intentelo mas tarde.";
      if (e.getMessage() != null && !e.getMessage().isEmpty()) {
        mensaje += (" " + e.getMessage());
      }
      throw new RuntimeException(mensaje);
    } finally {
      try {
        cn.close();
      } catch (Exception e) {
      }
    }
    return lista;
  }

  @Override
  public void registrarDeposito(String cuenta, double importe, String codEmp) {
    Connection cn = null;
    try {
      // Iniciar Tx
      cn = AccesoDB.getConnection();
      cn.setAutoCommit(false);
      // Actualizar la tabla cuenta
      String sql = "update cuenta " 
          + "set dec_cuensaldo = dec_cuensaldo + ?, "
          + "int_cuencontmov = int_cuencontmov + 1 " 
          + "where chr_cuencodigo = ?";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setDouble(1, importe);
      pstm.setString(2, cuenta);
      int filas = pstm.executeUpdate();
      pstm.close();
      if (filas == 0) {
        throw new SQLException("No existe la cuenta.");
      }
      // Leer Nro. de Movimiento
      sql = "select int_cuencontmov from cuenta "
          + "where chr_cuencodigo = ?";
      pstm = cn.prepareStatement(sql);
      pstm.setString(1, cuenta);
      ResultSet rs = pstm.executeQuery();
      rs.next();
      int nroMov = rs.getInt("int_cuencontmov");
      rs.close();
      pstm.close();
      // Insertar el movimiento
      sql = "insert into movimiento(chr_cuencodigo,int_movinumero,"
          + "dtt_movifecha,chr_emplcodigo,chr_tipocodigo,dec_moviimporte) "
          + "values(?,?,SYSDATE,?,'003',?)";
      pstm = cn.prepareStatement(sql);
      pstm.setString(1, cuenta);
      pstm.setInt(2, nroMov);
      pstm.setString(3, codEmp);
      pstm.setDouble(4, importe);
      pstm.executeUpdate();
      pstm.close();
      // Confirmar Tx
      cn.commit();
    } catch (Exception e) {
      try {
        cn.rollback();
      } catch (Exception e2) {
      }
      String mensaje = "Error en el proceso.";
      if (e.getMessage() != null && !e.getMessage().isEmpty()) {
        mensaje += " " + e.getMessage();
      }
      throw new RuntimeException(mensaje);
    } finally {
      try {
        cn.close();
      } catch (Exception e2) {
      }
    }
  }

}
