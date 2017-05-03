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

    public List<Cliente> findAll() throws Exception{
        return getPureList(Cliente.class, "select cli from Cliente cli");
    }

    public Cliente create(Cliente cliente) throws Exception {
        addEntity(Cliente.class, cliente);
        return cliente;
    }

    public void delete(Cliente cliente) throws Exception{
        removeEntity(cliente);
    }

    public Cliente update(Cliente cliente) throws Exception{
        return setEntity(Cliente.class, cliente);
    }

    public Cliente getCliente(Long id) throws Exception{
        return getEntity(Cliente.class, id);
    }
    
}
