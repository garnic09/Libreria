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
}
