/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.rural.repository;

import br.com.project.rural.entity.Cliente;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Gustavo Hoffmann
 */
public class ClienteRepository extends BasicRepository{

    public ClienteRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public List<Cliente> findAll() {
        return getPureList(Cliente.class, "select cli from Cliente cli");
    }

    public Cliente create(Cliente cliente) {
        addEntity(Cliente.class, cliente);
        return cliente;
    }

    public void delete(Cliente cliente) {
        removeEntity(cliente);
    }

    public Cliente update(Cliente cliente) {
        return setEntity(Cliente.class, cliente);
    }

    public Cliente getCliente(int id) {
        return getEntity(Cliente.class, id);
    }
    
}
