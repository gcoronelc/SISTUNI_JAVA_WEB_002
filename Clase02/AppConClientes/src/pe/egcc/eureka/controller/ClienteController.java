package pe.egcc.eureka.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.egcc.eureka.domain.Cliente;
import pe.egcc.eureka.model.ClienteModel;

@WebServlet({ "/ClienteConsultar", "/ClienteInsertar", "/ClienteActualizar" })
public class ClienteController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  
  private ClienteModel clienteModel;
  
  @Override
  public void init() throws ServletException {
    clienteModel = new ClienteModel();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String path = request.getServletPath();
    
    if(path.equals("/ClienteConsultar")){
      clienteConsultar(request,response);
    }
    
  }

  private void clienteConsultar(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    
    // Datos
    Cliente bean = new Cliente();
    bean.setPaterno(request.getParameter("paterno"));
    bean.setMaterno(request.getParameter("materno"));
    bean.setNombre(request.getParameter("nombre"));
    
    // Procesar
    List<Cliente> lista = clienteModel.getClientes(bean);
    
    // Dispacher
    request.setAttribute("lista", lista);
    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
    rd.forward(request, response);

  }

}
