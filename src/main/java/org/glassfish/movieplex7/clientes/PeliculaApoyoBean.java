/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.clientes;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author darkpastiursSennin
 */
@Named
@RequestScoped
public class PeliculaApoyoBean {
    private int peliculaId;

    public int getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(int peliculaId) {
        this.peliculaId = peliculaId;
    }
    
    
}
