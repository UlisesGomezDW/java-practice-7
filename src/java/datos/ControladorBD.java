/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Alumno;
import modelo.Materia;

/**
 *
 * @author josue
 */
public class ControladorBD {
    
    private static final String USUARIO="root";
    private static final String PSWD="root";
    private static final String BD="escuela00";
    private static final String URL="jdbc:mysql://localhost:3306/";
    private Connection conexion;
    
    public boolean conectar(){
        boolean estado = false;
        try {        
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion=DriverManager.getConnection(URL+BD, USUARIO, PSWD);
            if(conexion!=null)
                   estado=true;            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error "+ex.getMessage());
        } catch (SQLException ex1) {
            System.out.println("Error "+ex1.getMessage());
        }
        return estado;
    }
    
    public boolean desconectar(){
        boolean estado=false;
        try {
            conexion.close();
            estado=true;
        } catch (SQLException ex) {
            System.out.println("Error "+ex.getMessage());
        }
        return estado;
    }
    
    public ArrayList<Alumno> consultarAlumnos(){
        ArrayList<Alumno> alumnos= new ArrayList<>();
        Statement st;
        ResultSet rs;
        String q="SELECT * FROM alumnos";
        
        try {
            st= conexion.createStatement();
            rs=st.executeQuery(q);
            while(rs.next()){
                Alumno al= new Alumno();
                al.setMatricula(rs.getString("matricula"));
                al.setNombre(rs.getString(2));
                al.setEdad(rs.getInt("edad"));
                alumnos.add(al);
            }
        } catch (SQLException ex) {
            System.out.println("Error "+ex.getMessage());
        }
        return alumnos;
    }
    
    public boolean agregarAlumno(Alumno alumno){
        boolean estado=false;
        Statement st;
        String q="INSERT INTO alumnos VALUES("+"'"+alumno.getMatricula()+"',"+
                "'"+alumno.getNombre()+"',"+alumno.getEdad()+")";
        //INSERT INTO alumnos VALUES('2152232', "Braulio", 22)
        try {
            st=conexion.createStatement();
            st.execute(q);
            estado=true;
        } catch (SQLException ex) {
            System.out.println("Error "+ex.getMessage());
        }
        return estado;
    }
    
    public boolean eliminarAlumno(String matricula){
        boolean estado=false;
        Statement st;
        String q="DELETE FROM alumnos WHERE matricula="+"'"+matricula+"'";
        
        try {
            st=conexion.createStatement();
            st.executeUpdate(q);
            estado=true;
        } catch (SQLException ex) {
            System.out.println("Error "+ex.getMessage());
        }
        return estado;
    }
    
    public boolean actualizarAlumno(String matricula, String nombre){
        boolean estado=false;
        Statement st;
        String q="UPDATE alumnos SET nombre="+"'"+nombre+"'"+
                "WHERE matricula="+"'"+matricula+"'";
         //UPDATE alumnos SET nombre= 'Ramon' WHERE matricula='2152232';
        try {           
            st=conexion.createStatement();
            st.executeUpdate(q);
            estado=true;
        } catch (SQLException ex) {
            System.out.println("Error "+ex.getMessage());
        }
        return estado;
    }
    
    public ArrayList<Materia> consultarMaterias(){
        ArrayList<Materia> materias= new ArrayList<>();
        Statement st;
        ResultSet rs;
        String q="SELECT * FROM materias";
        
        try {
            st= conexion.createStatement();
            rs=st.executeQuery(q);
            while(rs.next()){
                materias.add(new Materia(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            System.out.println("Error "+ex.getMessage());
        }
        return materias;
    }
    
    public boolean agregarMateria(Materia materia){
        boolean estado=false;
        PreparedStatement ps;
        String q="INSERT INTO materias VALUES(?, ?)";
        //INSERT INTO materias VALUES('1151022', 'SD');
        try {
            ps=conexion.prepareStatement(q);
            ps.setString(1, materia.getClave());
            ps.setString(2, materia.getNombre());
            ps.execute();
            estado=true;
        } catch (SQLException ex) {
            System.out.println("Error "+ex.getMessage());
        }
        return estado;
    }
    
    public boolean eliminarMateria(String clave){
        boolean estado=false;
        PreparedStatement ps;
        String q="DELETE FROM materias WHERE clave=?";
        //DELETE FROM materias WHERE clave='1151022';
        try {
            ps=conexion.prepareStatement(q);
            ps.setString(1, clave);
            ps.execute();
            estado=true;
        } catch (SQLException ex) {
            System.out.println("Error "+ex.getMessage());
        }
        return estado;
    }
    
    public boolean actualizarMateria(String clave, String nombre){
        boolean estado=false;
        PreparedStatement ps;
        String q="UPDATE materias SET nombre=? WHERE clave=?";
        //UPDATE materias SET nombre='Programacion Estructurada' WHERE clave='1151038';
        try {
            ps=conexion.prepareStatement(q);
            ps.setString(1, nombre);
            ps.setString(2, clave);
            ps.execute();
            estado=true;
        } catch (SQLException ex) {
            System.out.println("Error "+ex.getMessage());
        }
        return estado;
    }
    
    
}
