/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.rural.repository;

import javax.persistence.EntityManager;

/**
 *
 * @author Gustavo Hoffmann
 */
public class UsuarioRepository extends BasicRepository{

    public UsuarioRepository(EntityManager entityManager) {
        super(entityManager);
    }
   
}
