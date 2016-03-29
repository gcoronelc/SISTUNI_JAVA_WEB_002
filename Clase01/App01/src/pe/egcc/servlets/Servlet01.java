package pe.egcc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Panchito", urlPatterns = { "/uno", "/dos", "/tres" })
public class Servlet01 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void service(HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {

    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Servlet Servlet01</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Bienvenidos al mundo oscuro de Java</h1>");
    out.println("</body>");
    out.println("</html>");
    
  }

}
