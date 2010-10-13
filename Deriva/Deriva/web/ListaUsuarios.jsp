<%@page import="java.util.List"%>
<%@page import="deriva.db.userDAO"%>
<%@page import="deriva.db.DAOFactory"%>
<%@page import="deriva.neg.Usuario"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Usuarios</title>
    </head>
    <body>

            <%
                userDAO dao = DAOFactory.getUserDAO();
                
                List<Usuario> lista = dao.ListarUsuarios();            
            
                if (lista != null){
                    for (Usuario usr : lista) {
                        if (usr.getImagemPerfil() != null && usr.getNickname() != null){
                    %>
                             <table>
                                   <tr>
                                    <td>
                                        <img src="<%= usr.getImagemPerfil().toString() %>
                                    </td>
                                    <td>
                                        <%= usr.getNickname().toString() %>
                                    </td>
                                </tr>
                             </table>
            <%           }
                    }
                }%>





    </body>
</html>
