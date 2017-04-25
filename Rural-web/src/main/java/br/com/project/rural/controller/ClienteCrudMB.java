/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.rural.controller;

import br.com.project.rural.entity.Cliente;
import br.com.project.rural.service.ClienteService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Gustavo Hoffmann
 */
@Named(value = "ClienteCrudMB")
@ViewScoped
public class ClienteCrudMB extends BasicControl {

    private Cliente entity;
    private List<Cliente> clientes;
    @EJB
    private ClienteService clienteService;

    public ClienteCrudMB() {
        entity = new Cliente();
    }

    public void salvarCliente() {
        try {
            if (entity.getId() == null) {
                clienteService.create(entity);
            } else {
                clienteService.update(entity);
            }
            createFacesInfoMessage("Dados Gravados com sucesso");
        } catch (Exception ex) {
            Logger.getLogger(ClienteCrudMB.class.getName()).log(Level.SEVERE, null, ex);
            createFacesErrorMessage(ex.getMessage());
        }
    }

    public List<Cliente> getClientes() {
        if (clientes == null) {
            clientes = clienteService.findAll();
        }
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getEntity() {
        return entity;
    }

    public void setEntity(Cliente entity) {
        this.entity = entity;
    }

}
