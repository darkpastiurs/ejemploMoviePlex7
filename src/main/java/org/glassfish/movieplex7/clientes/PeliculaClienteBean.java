/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.clientes;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.glassfish.movieplex7.entities.Movie;

/**
 *
 * @author darkpastiursSennin
 */
@Named("peliculaCliente")
@RequestScoped
public class PeliculaClienteBean {
    private Client cliente;
    private WebTarget webObjetivo;
    
    @Inject
    private PeliculaApoyoBean peliculaApoyoBean;
    
    @PostConstruct
    public void init(){
        cliente = ClientBuilder.newClient();
        webObjetivo = cliente.target("http://localhost:8080/movieplex7/webresources/movie/");        
    }
    
    @PreDestroy
    public void destroy(){
        cliente.close();
    }
    
    public Movie[] getPeliculas(){
        return webObjetivo.request().get(Movie[].class);
    }
    
    public Movie getPeliculaDetalle(){
        Movie pelicula = webObjetivo.path("{peliculaDetalles}").resolveTemplate("peliculaDetalles", peliculaApoyoBean.getPeliculaId()).request().get(Movie.class);
        return pelicula;
    }
    
    public void eliminarPelicula(){
        webObjetivo.path("{peliculaId}").resolveTemplate("peliculaId", peliculaApoyoBean.getPeliculaId()).request().delete();
    }
}
