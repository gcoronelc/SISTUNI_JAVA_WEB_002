package pe.egcc.eureka.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import pe.egcc.eureka.domain.Empleado;
import pe.egcc.eureka.dto.Mensaje;
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
    Mensaje mensaje;
    try {
      // Datos
      String cuenta = request.getParameter("cuenta");
      double importe = Double.parseDouble(request.getParameter("importe"));
      // Empleado
      Empleado bean = (Empleado) request.getSession().getAttribute("usuario");
      // Proceso
      cuentaModel.registrarDeposito(cuenta, importe, bean.getCodigo());
      mensaje = new Mensaje(1, "La operación se realizó con exito.");
    } catch (Exception e) {
      mensaje = new Mensaje(-1, e.getMessage());
    }
    // Retornando JSON
    Gson gson = new Gson();
    String textoJson = gson.toJson(mensaje);
    //response.setContentType("text/plain;charset=UTF-8");
    response.setContentType("application/json;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(textoJson);
    out.flush();
    out.close();
  }

}
