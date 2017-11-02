/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.points;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author darkpastiursSennin
 */
@Named
@RequestScoped
public class EmisorPuntosBean {
    @NotNull
    @Pattern(regexp = "^\\d{2},\\d{2}", message="El formato de ete formato debe ser ##,## ej. 15.25 ")
    private String mensaje;

    @Inject
    private JMSContext contexto;
    
    @Resource(lookup="java:global/jms/puntosQueue")
    private Queue puntosQueue;
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public void sendMessage(){
        System.out.println("Enviando mensaje... " + mensaje);
        contexto.createProducer().send(puntosQueue, mensaje);
    }
    
}
