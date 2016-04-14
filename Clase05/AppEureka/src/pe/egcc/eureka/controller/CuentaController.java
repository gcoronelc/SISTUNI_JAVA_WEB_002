package pe.egcc.eureka.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.egcc.eureka.domain.Empleado;
import pe.egcc.eureka.dto.Mensaje;
import pe.egcc.eureka.model.CuentaModel;

@WebServlet({ "/CuentaDeposito", "/CuentaRetiro", "/CuentaConMovimientos" })
public class CuentaController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  private CuentaModel cuentaModel;
  private ResponseClient responseClient;

  @Override
  public void init() throws ServletException {
    cuentaModel = new CuentaModel();
    responseClient = new ResponseClient();
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    if (request.getSession().getAttribute("usuario") == null) {
      
      Mensaje mensaje = new Mensaje(-100, "Debe iniciar sesión.");
      responseClient.response(request, response, mensaje);
    
    } else {

      String path = request.getServletPath();

      if (path.equals("/CuentaDeposito")) {
        cuentaDeposito(request, response);
      } else if(path.equals("/CuentaConMovimientos")){
        cuentaConMovimientos(request,response);
      }
    
    }

  }

  private void cuentaConMovimientos(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Mensaje mensaje;
    try {
      String cuenta = request.getParameter("cuenta");
      List<Map<String, ?>> lista = cuentaModel.traerMovimientos(cuenta);
      if(lista.isEmpty()){
        mensaje = new Mensaje(-1, "Cuenta no existe.");
      } else {
        Gson gson = new Gson();
        mensaje = new Mensaje(1, gson.toJson(lista));
      }
    } catch (Exception e) {
      mensaje = new Mensaje(-1, e.getMessage());
    }
    responseClient.response(request, response, mensaje);
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
    /*
    // Retornando JSON
    Gson gson = new Gson();
    String textoJson = gson.toJson(mensaje);
    // response.setContentType("text/plain;charset=UTF-8");
    response.setContentType("application/json;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(textoJson);
    out.flush();
    out.close();*/
    responseClient.response(request, response, mensaje);
  }

}
