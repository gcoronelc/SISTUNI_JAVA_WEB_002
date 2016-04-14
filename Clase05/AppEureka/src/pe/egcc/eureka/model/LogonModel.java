package pe.egcc.eureka.model;

import pe.egcc.eureka.dao.espec.DaoEmpleadoEspec;
import pe.egcc.eureka.dao.impl.DaoEmpleadoImpl;
import pe.egcc.eureka.domain.Empleado;

/**
 *
 * @author Gustavo Coronel
 */
public class LogonModel {
  
  public Empleado validar(String usuario, String clave){
    Empleado bean = null;
    DaoEmpleadoEspec dao = new DaoEmpleadoImpl();
    bean = dao.validar(usuario, clave);
    return bean;
  }
  
}
