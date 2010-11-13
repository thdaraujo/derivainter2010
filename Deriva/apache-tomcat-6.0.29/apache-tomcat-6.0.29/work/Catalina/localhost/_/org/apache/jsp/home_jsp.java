package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import deriva.neg.Autorizacao;
import deriva.neg.Usuario;
import deriva.app.AutorizacaoApp;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/Controles/Topo.html");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    if (!AutorizacaoApp.autoriza(request, response))
        return;

      out.write("\n");
      out.write("\n");
      out.write("  ");

    session = request.getSession();
    Usuario usuario = null;
    String ola = "usuário";
    String mensagemPessoal = "escreva uma mensagem pessoal!";
    String pathImage = application.getContextPath()+ "/images/";

    String imagemPerfil = pathImage + "logo.gif";

    if (session != null && session.getAttribute("usuario") != null){
            usuario = (Usuario) session.getAttribute("usuario");       
       
        
            if (usuario.getNickname() != null && !usuario.getNickname().isEmpty()) ola = usuario.getNickname();
            else if (usuario.getEmail() != null && !usuario.getEmail().isEmpty()) ola = usuario.getEmail();
            if (usuario.getmensagemPessoal() != null && !usuario.getmensagemPessoal().isEmpty()) mensagemPessoal = usuario.getmensagemPessoal();
            if (usuario.getImagemPerfil() != null && !usuario.getImagemPerfil().isEmpty()){
                //imagem vem da pasta images se não vier da web (se não for um caminho de imagem, não vai conter / no nome)
                if (!usuario.getImagemPerfil().contains("/"))
                    imagemPerfil = pathImage + usuario.getImagemPerfil();
                else imagemPerfil = usuario.getImagemPerfil();
            }
    }    
  
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n");
      out.write("   \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("  <html lang=\"pt\">\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"utf-8\" />\n");
      out.write("        <title>Deriva - Home</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />\n");
      out.write("        <!--[if IE]>\n");
      out.write("        <script src=\"http://html5shiv.googlecode.com/svn/trunk/html5.js\"></script>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"scripts/iehacks.css\" />\n");
      out.write("        <![endif]-->\n");
      out.write("\n");
      out.write("        <!--[if lte IE 7]>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"scripts/ie67hacks.css\" />\n");
      out.write("        <![endif]-->        \n");
      out.write("        <script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js\"></script>\n");
      out.write("\t<script src=\"scripts/jquery.ae.image.resize.min.js\"></script>\n");
      out.write("\t<script>\n");
      out.write("\t\t$(function() {\n");
      out.write("\t\t\t$(\".imagemPerfilBarra\").aeImageResize({height: 50, width: 50});\n");
      out.write("                        $(\".imagemPerfil\").aeImageResize({height: 100, width: 100});\n");
      out.write("\t\t});\n");
      out.write("\t</script>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        <div>\n");
      out.write("            &nbsp;\n");
      out.write("        </div>\n");
      out.write("        <header>\n");
      out.write("              ");
      out.write("<!--\n");
      out.write("To change this template, choose Tools | Templates\n");
      out.write("and open the template in the editor.\n");
      out.write("-->\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n");
      out.write("<html>\n");
      out.write("  <head>\n");
      out.write("    <title></title>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("    <div id=\"logo\">\n");
      out.write("                        <a href=\"/home.jsp\"><img src=\"images/logo.gif\" alt=\"Logo\" /></a>\n");
      out.write("                        <!--<hgroup>\n");
      out.write("                                <h1>Deriva</h1>\n");
      out.write("                                <h2>Rede Social</h2>\n");
      out.write("                        </hgroup>-->\n");
      out.write("                </div>\n");
      out.write("                <nav>\n");
      out.write("                        <ul>\n");
      out.write("                                <li><a href=\"/home.jsp\">Home</a></li>\n");
      out.write("                                <li><a href=\"/ListaUsuarios\">Lista de Usuarios</a></li>\n");
      out.write("                                <li><a href=\"/EditarPerfil\">Editar Perfil</a></li>\n");
      out.write("                                <li><a href=\"/SignOut\">Logout</a></li>\n");
      out.write("                                <li><a href=\"#\">123t</a></li>\n");
      out.write("                        </ul>\n");
      out.write("                </nav>\n");
      out.write("  </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("        </header>\n");
      out.write("\n");
      out.write("        <div id=\"content\">\n");
      out.write("                <section class=\"hfeed\">\n");
      out.write("                        <article class=\"hentry\">\n");
      out.write("                                <hgroup>\n");
      out.write("                                        <h2 class=\"entry-title\"><a href=\"/home.jsp\">Bem Vindo, ");
      out.print( ola );
      out.write("!</a></h2>\n");
      out.write("                                        <h3> <a class=\"author\" href=\"#\"> ");
      out.print( usuario.getEmail() );
      out.write("</a> </h3>\n");
      out.write("                                </hgroup>\n");
      out.write("                                <img src=\"");
      out.print(imagemPerfil );
      out.write("\" class=\"imagemPerfil\">\n");
      out.write("                                <p class=\"entry-summary\"> ");
      out.print( mensagemPessoal );
      out.write("  </p>\n");
      out.write("                                <footer><a href=\"#\">Comment on this (5)</a>&emsp;&bull;&emsp;<a href=\"#\">Tweet this</a>&emsp;&bull;&emsp;<a href=\"#\">Stumble Upon</a></footer>\n");
      out.write("                                <br /><hr /><br />\n");
      out.write("                        </article>                        \n");
      out.write("                </section>\n");
      out.write("                <aside>\n");
      out.write("                    <!-- Gambiware: chamo o servlet e o jsp - deve haver um jeito melhor de executar o servlet E DEPOIS dar\n");
      out.write("                         include no conteudo da barra, mas não descobri ainda como. Redirect explode tudo. -->\n");
      out.write("                    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/BarraTripulantes", out, false);
      out.write("\n");
      out.write("                    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/Controles/BarraTripulantes.jsp", out, false);
      out.write("\n");
      out.write("                </aside>\n");
      out.write("        </div>\n");
      out.write("                \n");
      out.write("        <footer id=\"main-footer\">\n");
      out.write("                <section id=\"footer-1\">\n");
      out.write("                        Deriva &copy; 2010\n");
      out.write("                </section>\n");
      out.write("                <section id=\"footer-2\">\n");
      out.write("\n");
      out.write("                </section>\n");
      out.write("        </footer>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
