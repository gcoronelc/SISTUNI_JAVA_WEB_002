package pe.egcc.eureka.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pe.egcc.eureka.domain.Empleado;
import pe.egcc.eureka.model.LogonModel;

@WebServlet({ "/LogonSalir", "/LogonIngresar" })
public class LogonController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LogonModel  logonModel;

	@Override
	public void init() throws ServletException {
  	logonModel = new LogonModel();
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String path = request.getServletPath();

    if (path.equals("/LogonIngresar")) {
      logonIngresar(request, response);
    }
	}


  private void logonIngresar(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    String destino;
    try {
      // Datos
      String usuario = request.getParameter("usuario");
      String clave = request.getParameter("clave");
      // Proceso
      Empleado bean = logonModel.validar(usuario, clave);
      if(bean == null){
        throw new Exception("Datos incorrectos.");
      }
      // Guardar datos del empleado en sesión
      HttpSession session = request.getSession();
      session.setAttribute("usuario", bean);
      destino = "mainPage.jsp";
    } catch (Exception e) {
      request.setAttribute("error", e.getMessage());
      destino = "index.jsp";
    }
    RequestDispatcher rd;
    rd = request.getRequestDispatcher(destino);
    rd.forward(request, response);
  }

}
