<%-- 
    Document   : agregarAlumno
    Created on : 20/12/2022, 12:35:44 PM
    Author     : Profesor
--%>

<%@page import="modelo.Alumno"%>
<%@page import="datos.ControladorBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ControladorBD con = new ControladorBD();
            con.conectar();
            if (!request.getParameter("matricula").equals("") && !request.getParameter("nombre").equals("") && !request.getParameter("edad").equals("")) {
                String mat = request.getParameter("matricula");
                String nombre = request.getParameter("nombre");
                int edad = Integer.parseInt(request.getParameter("edad"));
                Alumno alumno = new Alumno(mat, nombre, edad);
                con.agregarAlumno(alumno);

            }

            response.sendRedirect("alumnos.jsp");
            con.desconectar();
        %>
    </body>
</html>
