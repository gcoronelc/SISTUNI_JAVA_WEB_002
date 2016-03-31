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

}
