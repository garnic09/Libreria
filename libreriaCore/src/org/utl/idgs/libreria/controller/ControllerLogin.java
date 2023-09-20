/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.idgs.libreria.controller;

import org.utl.idgs.libreria.db.ConexionMySQL;
import org.utl.idgs.libreria.model.Empleado;
import org.utl.idgs.libreria.model.Persona;
import org.utl.idgs.libreria.model.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author garni
 */
public class ControllerLogin {
    public Empleado entrar(Usuario u) throws Exception {
        String query = "SELECT * FROM vista_e WHERE usuario = '" + u.getUsuario() + "' AND contrasenia = '" + u.getContrasenia() + "'";

        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        Empleado e = new Empleado();
        if (rs.next()) {
            e = fill(rs);
        }
        rs.close();
        stmt.close();
        conn.close();
        conexion.close();
        System.out.println(query);
        return e;

    }

    public Empleado fill(ResultSet rs) throws Exception {
        Empleado e = new Empleado();
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("IDU"));
        u.setUsuario(rs.getString("Usuario"));
        u.setContrasenia(rs.getString("Contrasenia"));
        u.setRol(rs.getString("Rol"));
        e.setUsuario(u);
        Persona p = new Persona();
        p.setIdPersona(rs.getInt("IDP"));
        p.setApellidoM(rs.getString("Apellido_Materno"));
        p.setApellidoP(rs.getString("Apellido_Paterno"));
        p.setCorreo(rs.getString("Correo"));
        p.setGenero(rs.getString("Genero"));
        p.setNombre(rs.getString("Nombre"));
        e.setPersona(p);
        e.setNumeroUnico(rs.getString("Numero_Unico"));
        e.setIdEmpleado(rs.getInt("IDE"));

        return e;
    }
    
    public void guardarToken(Empleado empleado) throws SQLException{
        String query = "UPDATE usuario SET lastToken=?,dateLastToken=NOW() WHERE idUsuario=?";
        
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        PreparedStatement pstmt = conn.prepareCall(query);
        
        pstmt.setString(1,empleado.getUsuario().getLastToken());
        pstmt.setInt(2,empleado.getUsuario().getIdUsuario());
        
        pstmt.execute();
        
        pstmt.close();
        conn.close();
        conexion.close();
    }
    
    public boolean eliminarToken(Empleado e) throws Exception{
        boolean r = false;
        String query = "UPDATE usuario SET lastToken='' WHERE idUsuario=?";
        
        ConexionMySQL conexionMySQL = new ConexionMySQL();
        
        Connection connection = conexionMySQL.open();
        
        PreparedStatement pstmt = connection.prepareCall(query);
        
        pstmt.setInt(1, e.getUsuario().getIdUsuario());
        
        pstmt.execute();
        r = true;
        
        pstmt.close();
        connection.close();
        conexionMySQL.close();
        
        return r;
    }
    
    public boolean validarToken(String t) throws Exception{
        boolean r = false;
        String query = "SELECT * FROM vista_e WHERE lastToken='" + t + "'";
        ConexionMySQL conexionMySQL = new ConexionMySQL();
        Connection connection = conexionMySQL.open();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        if(rs.next())
            r = true;
        
        stmt.close();
        connection.close();
        conexionMySQL.close();
        
        return r;
    }
}
