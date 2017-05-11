/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.rural.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.naming.ldap.HasControls;

/**
 *
 * @author Gustavo Hoffmann
 */
public class ModelFilter implements Serializable {

    private static ModelFilter instance;

    private String sqlBase;
    private String sqlCountBase;
    private int limit;
    private int offSet;
    private Map<String, Object> filtros;
    private Map<String, String> orderBy;
    private Class entidade;

    private ModelFilter() {

        filtros = new HashMap<String, Object>();
        orderBy = new HashMap<String, String>();

    }

    public void addOrderBy(String campo, String posicao) {
        orderBy.put(campo, posicao);
    }

    public void addFilter(String campo, Object value) {
        filtros.put(campo, value);
    }

    public String getSqlBase() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT (o) FROM ").append(entidade.getSimpleName()).append(" as o WHERE 1 = 1 ");

        for (String key : filtros.keySet()) {
            sb.append(getOperadorCampo(key));
        }
        
        sb.append(" ORDER BY ");
        if (orderBy == null || orderBy.isEmpty()) {
            sb.append(" o.id DESC, ");
        } else {
            for (String campo : orderBy.keySet()) {
                if (orderBy.get(campo).equalsIgnoreCase("ASC")) {
                    if (!campo.contains(".")) {
                        campo = "o." + campo;
                    }
                    sb.append(campo).append(" ASC ,  ");
                } else {
                    if (!campo.contains(".")) {
                        campo = "o." + campo;
                    }
                    sb.append(campo).append(" DESC ,  ");
                }
            }
            
            sb.append(" o.id ");

        }

        return sb.toString();
    }
    
    public String getOperadorCampo(String campo) {
        String aliasCampo = campo;

        if (!aliasCampo.contains(".")) {
            aliasCampo = "o." + aliasCampo;
        }

        return " AND" + "  " + aliasCampo + " =  '" + filtros.get(campo) + "' ";
    }
    

    public String getSqlCountBase() {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

    public static ModelFilter getInstance() {

        if (instance == null) {
            instance = new ModelFilter();
        }

        return instance;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }

    public void setEntidade(Class entidade) {
        this.entidade = entidade;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffSet() {
        return offSet;
    }

}
