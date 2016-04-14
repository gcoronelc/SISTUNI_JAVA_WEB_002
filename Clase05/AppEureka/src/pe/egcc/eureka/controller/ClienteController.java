package pe.egcc.eureka.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.egcc.eureka.domain.Cliente;
import pe.egcc.eureka.dto.Mensaje;
import pe.egcc.eureka.model.ClienteModel;
import pe.egcc.eureka.util.EurekaUtil;

@WebServlet({ "/ClienteConsultar", "/ClienteInsertar", "/ClienteActualizar",
  "/ClienteNuevo", "/ClienteEditar", "/ClienteEliminar", "/ClienteProcesar"})
public class ClienteController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  
  private ClienteModel clienteModel;
  private ResponseClient responseClient;

  @Override
  public void init() throws ServletException {
    clienteModel = new ClienteModel();
    responseClient = new ResponseClient();
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    if (request.getSession().getAttribute("usuario") == null) {

      Mensaje mensaje = new Mensaje(-100, "Debe iniciar sesi�n.");
      responseClient.response(request, response, mensaje);

    } else {

      String path = request.getServletPath();

      if (path.equals("/ClienteConsultar")) {
        clienteConsultar(request, response);
      } else if (path.equals("/ClienteNuevo")) {
        clienteNuevo(request, response);
      } else if (path.equals("/ClienteEliminar")) {
        clienteEliminar(request, response);
      }

    }
  }

  private void clienteEliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String codigo = request.getParameter("codigo");
    Cliente bean = clienteModel.getCliente(codigo);
    request.setAttribute("cliente", bean);
    request.setAttribute("accion", EurekaUtil.CRUD_ACCION_ELIMINAR);
    RequestDispatcher rd = request.getRequestDispatcher("editarCliente.jsp");
    rd.forward(request, response);
  }

  private void clienteNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Cliente bean = new Cliente();
    bean.setCodigo("NONE");
    request.setAttribute("cliente", bean);
    request.setAttribute("accion", EurekaUtil.CRUD_ACCION_NUEVO);
    RequestDispatcher rd = request.getRequestDispatcher("editarCliente.jsp");
    rd.forward(request, response);
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

    Mensaje mensaje;
    if(lista.isEmpty()){
      mensaje = new Mensaje(-50, "No hay coincidencias.");
    } else {
      Gson gson = new Gson();
      mensaje = new Mensaje(1, gson.toJson(lista));
    }
    
    responseClient.response(request, response, mensaje);
    
    /* Dispacher
    request.setAttribute("lista", lista);
    RequestDispatcher rd = request.getRequestDispatcher("conClientes.jsp");
    rd.forward(request, response);**/

  }

}
