<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="deriva.app.BarraTripulantes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<!--  <h2>Contatos</h2>
        <ul>
                <li><a href="#">contato 1</a></li>
                <li><a href="#">contato 2</a></li>
                <li><a href="#">contato 3</a></li>
        </ul>
        <br /> -->
      
        <h2>${requestScope.title}</h2>
        <ul>
           <c:forEach var="usr" items="${requestScope.listaAmigos}">
                <li><a href="MostraPerfil?id=${usr.idusuario}"><img src="${usr.imagemPerfil}" class=imagemPerfilBarra>
                        <h3>${usr.nickname}</h3>
                    </a>
                </li>
           </c:forEach>
                <li><a href="/ListaTripulantes">Ver minha tripulação</a></li>
       </ul>       
       <br />

        <div id="busca" class="busca">
            <h2>Busca</h2>
                <form method="post" action="Busca">
                   <p>
                       <input type="text" name="termos" class="input" maxlength="100" />
                       <input type="submit" value="Vai!" id="botao" />
                   </p>
                </form>

        </div>
       
        <%--
        <h2>Comunidades</h2>
        <ul>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
        </ul>
        <br /> --%>
    
