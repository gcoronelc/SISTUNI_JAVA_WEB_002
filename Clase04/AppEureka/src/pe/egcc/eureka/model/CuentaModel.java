package pe.egcc.eureka.model;

import java.util.List;
import java.util.Map;

import pe.egcc.eureka.dao.espec.DaoCuentaEspec;
import pe.egcc.eureka.dao.impl.DaoCuentaImpl;


/**
 *
 * @author Gustavo Coronel
 */
public class CuentaModel {

  private DaoCuentaEspec daoCuenta;

  public CuentaModel() {
    daoCuenta = new DaoCuentaImpl();
  }
  
  public List<Map<String, ?>> traerMovimientos(String cuenta) {
    return daoCuenta.conMovimientos(cuenta);
  }

  public void registrarDeposito(String cuenta, double importe, String codEmp) {
    if(cuenta.isEmpty()){
      throw new RuntimeException("Cuenta no puede estar vacía");
    }
    if(codEmp == null || codEmp.isEmpty()){
      throw new RuntimeException("Debe iniciar sesión");
    }
    if(importe <= 0.0){
      throw new RuntimeException("Importe incorrecto.");
    }
    daoCuenta.registrarDeposito(cuenta, importe, codEmp);
  }
}
