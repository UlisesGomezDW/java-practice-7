<%-- 
    Document   : eliminarAlumno
    Created on : 20/12/2022, 12:17:10 PM
    Author     : Profesor
--%>

<%@page import="datos.ControladorBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Alumno</title>
    </head>
    <body>
        <%
            ControladorBD con= new ControladorBD();
            con.conectar();
            String mat=request.getParameter("matricula");
            con.eliminarAlumno(mat);
            response.sendRedirect("alumnos.jsp");
            con.desconectar();
        %>
    </body>
</html>
