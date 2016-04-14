package pe.egcc.eureka.model;

import java.util.List;

import pe.egcc.eureka.dao.espec.DaoClienteEspec;
import pe.egcc.eureka.dao.impl.DaoClienteImpl;
import pe.egcc.eureka.domain.Cliente;

/**
 *
 * @author Gustavo Coronel
 */
public class ClienteModel {

  private DaoClienteEspec dao;

  public ClienteModel() {
    dao = new DaoClienteImpl();
  }
  
  public Cliente getCliente(String codigo){
    return dao.readForId(codigo);
  }
  
  public List<Cliente> getClientes() {
    return dao.readAll();
  }
  
  public List<Cliente> getClientes(Cliente bean) {
    return dao.readForCriteria(bean);
  }
  
  public void insert(Cliente bean) {
    dao.insert(bean);
  }

  public void update(Cliente bean) {
    throw new UnsupportedOperationException("Debes programarlo."); //To change body of generated methods, choose Tools | Templates.
  }

  public void delete(Cliente bean) {
    throw new UnsupportedOperationException("Debes programarlo."); //To change body of generated methods, choose Tools | Templates.
  }
}
