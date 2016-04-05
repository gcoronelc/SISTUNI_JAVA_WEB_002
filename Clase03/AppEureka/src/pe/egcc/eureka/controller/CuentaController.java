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
import pe.egcc.eureka.model.CuentaModel;

@WebServlet({ "/CuentaDeposito", "/CuentaRetiro" })
public class CuentaController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  private CuentaModel cuentaModel;

  @Override
  public void init() throws ServletException {
    cuentaModel = new CuentaModel();
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String path = request.getServletPath();

    if (path.equals("/CuentaDeposito")) {
      cuentaDeposito(request, response);
    }

  }

  private void cuentaDeposito(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      // Datos
      String cuenta = request.getParameter("cuenta");
      double importe = Double.parseDouble(request.getParameter("importe"));
      // Empleado
      Empleado bean = (Empleado) request.getSession().getAttribute("usuario");
      // Proceso
      cuentaModel.registrarDeposito(cuenta, importe, bean.getCodigo());
      String mensaje = "La operación se realizó con exito.";
      request.setAttribute("mensaje", mensaje);
    } catch (Exception e) {
      request.setAttribute("error", e.getMessage());
    }
    RequestDispatcher rd;
    rd = request.getRequestDispatcher("regDeposito.jsp");
    rd.forward(request, response);
  }

}
