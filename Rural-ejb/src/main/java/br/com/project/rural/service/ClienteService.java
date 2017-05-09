/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.rural.service;

import br.com.project.rural.entity.Cliente;
import br.com.project.rural.repository.ClienteRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gustavo Hoffmann
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ClienteService extends BasicService<Cliente>{
    
    private ClienteRepository clienteRepository;
    
    @PostConstruct
    @PostActivate
    private void postConstruct() {
        clienteRepository = new ClienteRepository(em);
    }
    
    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Cliente getEntity(Long id) throws Exception {
        return clienteRepository.getCliente(id);
    }
    
    
    public Cliente update(Cliente cliente) throws Exception {
        return clienteRepository.update(cliente);
    }
    
    public void delete(Cliente cliente) throws Exception{
        clienteRepository.delete(cliente);
    }
    
    
    public Cliente create(Cliente cliente) throws Exception {
        return clienteRepository.create(cliente);
    }
    
    public List<Cliente> findAll() throws Exception{
        return clienteRepository.findAll();
    }

   

    

    
    
    
}
