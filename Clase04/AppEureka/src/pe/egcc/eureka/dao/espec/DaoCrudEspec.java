package pe.egcc.eureka.dao.espec;

import java.util.List;

/**
 *
 * @author Gustavo Coronel
 */
public interface DaoCrudEspec<T> {
  
  T readForId(String codigo);
  
  List<T> readAll();
  
  List<T> readForCriteria(T bean);
  
  void insert(T bean);
  
  void update(T bean);
  
  void delete(String codigo);
  
}
