package pe.egcc.eureka.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import pe.egcc.eureka.model.CuentaModel;

@WebServlet({ "/RepoMovimientos" })
public class RepoController extends HttpServlet {

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

    if (path.equals("/RepoMovimientos")) {
      repoMovimientos(request, response);
    }

  }

  private void repoMovimientos(HttpServletRequest request, HttpServletResponse response) {
    try { 
      // Parámetros
      String cuenta = request.getParameter("cuenta");
      // Obteniendo Datos
      List<Map<String, ?>> lista = cuentaModel.traerMovimientos(cuenta);
      System.err.println("Filas: " + lista.size());
      JRMapCollectionDataSource jrData;
      jrData = new JRMapCollectionDataSource(lista);
      // Parametros
      Map pars = new HashMap<>();
      // El reporte
      String fileRepo = "/pe/egcc/eureka/reports/repoMovimientos.jasper";
      InputStream inRepo = getClass().getResourceAsStream(fileRepo);
      byte[] bytes = JasperRunManager.runReportToPdf(inRepo, pars, jrData);     
      // Enviar el reporte al browser
      response.setContentType("application/pdf");
      response.setContentLength(bytes.length);
      ServletOutputStream out = response.getOutputStream();
      out.write(bytes, 0, bytes.length);
      out.flush();
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}