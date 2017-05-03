/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.rural.controller;

import br.com.project.rural.entity.Usuario;
import br.com.project.rural.service.UsuarioService;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Gustavo Hoffmann
 */
@SessionScoped
@ManagedBean(name = "acessoManagedBean")
public class AcessoManagedBean extends BasicControl{

    @EJB
    private UsuarioService usuarioService;
    
    private String login;
    private String senha;
    
    private Usuario entity;
    
    
    
    /**
     * Creates a new instance of AcessoManagedBean
     */
    public AcessoManagedBean() {
        
    }

    
    public String logar(){
        return "/admin/index.xhtml";
    }
    
    
    public Usuario getEntity() {
        return entity;
    }

    public void setEntity(Usuario entity) {
        this.entity = entity;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    
}
