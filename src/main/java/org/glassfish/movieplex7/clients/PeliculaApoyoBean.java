/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.clients;

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
    private String peliculaNombre;
    private String peliculaActores;

    public int getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(int peliculaId) {
        this.peliculaId = peliculaId;
    }

    public String getPeliculaNombre() {
        return peliculaNombre;
    }

    public void setPeliculaNombre(String peliculaNombre) {
        this.peliculaNombre = peliculaNombre;
    }

    public String getPeliculaActores() {
        return peliculaActores;
    }

    public void setPeliculaActores(String peliculaActores) {
        this.peliculaActores = peliculaActores;
    }        
}
