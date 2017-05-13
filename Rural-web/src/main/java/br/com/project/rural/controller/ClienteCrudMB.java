/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.rural.controller;

import br.com.project.rural.entity.Cliente;
import br.com.project.rural.service.ClienteService;
import br.com.project.rural.utils.ModelFilter;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Gustavo Hoffmann
 */
@ViewScoped
@ManagedBean(name = "ClienteCrudMB")
public class ClienteCrudMB extends BasicControl {

    private Cliente entity;
    private Cliente clienteSelected;
    private List<Cliente> clientesSelecionados;
    @EJB
    private ClienteService clienteService;
    private LazyDataModel<Cliente> lazyLista;

    @PostConstruct
    public void postConstruct() {
        System.out.println("Users Control started " + hashCode());
    }

    public ClienteCrudMB() {
        entity = new Cliente();
    }

    public Cliente getEntity() {
        if (entity == null) {
            entity = new Cliente();
        }

        if (getRequestParam("id") != null && !getRequestParam("id").isEmpty()) {
            if ((((entity).getId() == null)) || (entity).getId() != Long.parseLong(getRequestParam("id"))) {
                try {
                    entity = clienteService.getEntity(Long.parseLong(getRequestParam("id")));
                } catch (Exception ex) {
                    Logger.getLogger(ClienteCrudMB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return entity;
    }

    public String salvarCliente() {
        try {
            if (entity.getId() == null) {
                clienteService.create(entity);
            } else {
                clienteService.update(entity);
            }
            createFacesInfoMessage("Dados Gravados com sucesso");
            return "./index.xhtml";
        } catch (Exception ex) {
            Logger.getLogger(ClienteCrudMB.class.getName()).log(Level.SEVERE, null, ex);
            createFacesErrorMessage(ex.getMessage());
            return null;
        } finally {
            scrollTopMessage();
        }
    }

    public void removerCliente(Cliente cliente) {
        try {
            if (cliente != null) {
                clienteService.delete(cliente);
                createFacesInfoMessage("Dados Removidos com sucesso");
            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteCrudMB.class.getName()).log(Level.SEVERE, null, ex);
            createFacesErrorMessage(ex.getMessage());
        } finally {
            scrollTopMessage();
        }
    }

    
    public LazyDataModel<Cliente> getLazyDataModel() {

        if (lazyLista == null) {
            lazyLista = new LazyDataModel<Cliente>() {

                @Override
                public Cliente getRowData(String rowKey) {
                    List<Cliente> list = (List<Cliente>) getWrappedData();
                    for (Cliente cli : list) {
                        if (cli.getId().toString().equals(rowKey)) {
                            return cli;
                        }
                    }
                    return null;
                }

                @Override
                public Object getRowKey(Cliente object) {
                    return object.getId(); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public List<Cliente> load(int offset, int max, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    ModelFilter modelFilter = ModelFilter.getInstance();
                    if (filters != null) {
                        for (String key : filters.keySet()) {
                            modelFilter.addFilter(key, filters.get(key));
                        }
                    }
                    //
                    
                    modelFilter.addOperador(ModelFilter.Operadores.ILIKE, "nome");
                    
                    int count = clienteService.count(modelFilter);
                    this.setRowCount(count);

                    if (sortField != null) {
                        modelFilter.addOrderBy(sortField, SortOrder.ASCENDING == sortOrder ? "ASC" : "DESC");
                    }
                    modelFilter.setLimit(max);
                    modelFilter.setOffSet(offset);
                    List<Cliente> clientes = clienteService.findRange(modelFilter);
                    return clientes;
                }
            };
            //
            int count = clienteService.count();
            lazyLista.setRowCount(count);
            //
        }
        return lazyLista;
    }

   

    public void setEntity(Cliente entity) {
        this.entity = entity;
    }

    public List<Cliente> getClientesSelecionados() {
        clientesSelecionados = clienteService.findAll();
        return clientesSelecionados;
    }

    public void setClientesSelecionados(List<Cliente> clientesSelecionados) {
        this.clientesSelecionados = clientesSelecionados;
    }

    public Cliente getClienteSelected() {
        return clienteSelected;
    }

    public void setClienteSelected(Cliente clienteSelected) {
        this.clienteSelected = clienteSelected;
    }

    

}
