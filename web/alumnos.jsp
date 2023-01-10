<%-- 
    Document   : alumnos
    Created on : 20/12/2022, 11:24:19 AM
    Author     : Profesor
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Alumno"%>
<%@page import="datos.ControladorBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alumnos LPOO</title>
    </head>
    <body>
        <h1>Alumnos UAM</h1>
        <%
            ControladorBD controlador= new ControladorBD();
            controlador.conectar();
            ArrayList<Alumno> alumnos = controlador.consultarAlumnos();
         
        %>
        
        <table border=1 width="500">
            <tr>
                <th>Matricula</th><th>Nombre</th><th>Edad</th>                                   
            </tr>
            <%
                for (Alumno unAlumno : alumnos) {
                    out.println("<tr>");
                    out.println("<td>"+unAlumno.getMatricula()+"</td>");
                    out.println("<td>"+unAlumno.getNombre()+"</td>");
                    out.println("<td>"+unAlumno.getEdad()+"</td>");
                    out.println("</tr>");
                }

            %>           
        </table>
        
        <h2>Eliminar Alumno</h2>
        <form  action="eliminarAlumno.jsp" method="post">
            <select name="matricula">
                <%
                    for (Alumno unAlumno : alumnos) {
                            out.println("<option>"+unAlumno.getMatricula()+"</option>");
                        }
                 %>   
             </select>   
             <input type="submit" value="eliminar">
        </form>
        
        <h2>Agregar Alumno</h2>
        <form action="agregarAlumno.jsp" method="post">
            Matricula: <input type="text" name="matricula" size="15"><br><br>
            Nombre   : <input type="text" name="nombre" size="15"><br><br>
            Edad     : <input type="text" name="edad" size="15"><br>
            <input type="submit" value="agregar">
        </form>
        <br><br>
        <a href="index.html">Inicio</a>
    </body>
</html>
