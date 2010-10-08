package deriva.app;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet1 extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
		
        try {
            int n = Integer.parseInt(request.getParameter("n"));
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet1 at " + request.getContextPath () + "</h1>");
            out.println("<p>");
            
            for (int i = 1; i <= n; i++)
                out.println(i + ";");

            out.println("</p>");
            out.println("</body>");
            out.println("</html>");
        } finally { 
            out.close();
        }
		
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

}
