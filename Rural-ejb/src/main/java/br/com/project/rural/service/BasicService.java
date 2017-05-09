/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.rural.service;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gustavo Hoffmann
 * @param <T>
 */

abstract class BasicService<T> implements Serializable {

    @PersistenceContext(unitName = "RuralPU")
    protected EntityManager em;

    public abstract T getEntity(Long id) throws Exception;
    
}
