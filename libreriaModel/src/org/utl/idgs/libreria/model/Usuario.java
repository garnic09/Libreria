package org.utl.idgs.libreria.model;

import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author garni
 */
public class Usuario {
    private int idUsuario;
    private String usuario;
    private String contrasenia;
    private String rol;
    private String lastToken;
    private String dateLastToken;

    public Usuario() {
    }

    public Usuario(int idUsuario, String usuario, String contrasenia, String rol, String lastToken, String dateLastToken) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.lastToken = lastToken;
        this.dateLastToken = dateLastToken;
    }

    public Usuario(String usuario, String contrasenia, String rol, String lastToken, String dateLastToken) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.lastToken = lastToken;
        this.dateLastToken = dateLastToken;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getLastToken() {
        return lastToken;
    }

    public void setLastToken() {
        String u = this.getUsuario();
        String p = this.getContrasenia();
        String k = new Date().toString(); //time
        String x = (DigestUtils.sha256Hex(u+";"+p+";"+k));
        this.lastToken = x;
    }

    public String getDateLastToken() {
        return dateLastToken;
    }

    public void setDateLastToken(String dateLastToken) {
        this.dateLastToken = dateLastToken;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", rol=" + rol + ", lastToken=" + lastToken + ", dateLastToken=" + dateLastToken + '}';
    }
    
}
