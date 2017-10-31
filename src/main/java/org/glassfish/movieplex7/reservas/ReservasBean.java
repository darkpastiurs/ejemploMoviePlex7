/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.reservas;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.glassfish.movieplex7.entities.Movie;
import org.glassfish.movieplex7.entities.ShowTiming;

/**
 *
 * @author darkpastiursSennin
 */
@Named
@FlowScoped("reservas")//@RequestScoped@ViewScoped@ApplicationScoped
public class ReservasBean implements Serializable {

    //Serializable data
    private static final long serialVersionUID = 2512612413027091526L;

    //LOGGER
    private static final Logger LOG = Logger.getLogger(ReservasBean.class.getName());

    //Campos a injectar en JSF
    private int movieId;
    private String inicioFuncion;
    private int funcionId;
    //Recursos
    @PersistenceContext(unitName = "movieplex7PU")
    EntityManager em;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getInicioFuncion() {
        return inicioFuncion;
    }

    public void setInicioFuncion(String inicioFuncion) {
        StringTokenizer tokens = new StringTokenizer(inicioFuncion, ",");
        funcionId = Integer.parseInt(tokens.nextToken());
        this.inicioFuncion = tokens.nextToken();
        LOG.log(Level.INFO, "Valor de funciones de pelicula asignados Id = {0}, Funcion = {1]", new Object[]{funcionId, inicioFuncion});
    }

    public int getFuncionId() {
        return funcionId;
    }

    public void setFunctionId(int funcionId) {
        this.funcionId = funcionId;
    }

    public String getPeliculaNombre() {
        try {
            return em.createNamedQuery("Movie.findById", Movie.class)
                    .setParameter("id", movieId)
                    .getSingleResult()
                    .getName();
        } catch (NoResultException e) {
            return "";
        }
    }

    public String getSala() {
        try {
            List<ShowTiming> lista = em.createNamedQuery("ShowTiming.findByMovieAndTimingId")
                    .setParameter("movieId", this.movieId)
                    .setParameter("timingId", this.funcionId)
                    .getResultList();
            if (lista.isEmpty()) {
                return "Ninguno";
            }
            return lista.get(0).getTheaterId().getId().toString();
        } catch (NoResultException e) {
            return "Ninguno";
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.movieId;
        hash = 29 * hash + this.funcionId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReservasBean other = (ReservasBean) obj;
        if (this.movieId != other.movieId) {
            return false;
        }
        if (this.funcionId != other.funcionId) {
            return false;
        }
        if (!Objects.equals(this.inicioFuncion, other.inicioFuncion)) {
            return false;
        }
        return true;
    }
}
