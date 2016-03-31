package pe.egcc.eureka.dao.espec;

import pe.egcc.eureka.domain.Empleado;

/**
 *
 * @author Gustavo Coronel
 */
public interface DaoEmpleadoEspec extends DaoCrudEspec<Empleado>{
  
  
  Empleado validar(String usuario, String clave);
  
}
