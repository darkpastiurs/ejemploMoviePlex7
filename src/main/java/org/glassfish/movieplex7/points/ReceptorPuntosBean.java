/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.points;

import java.util.Enumeration;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueBrowser;

/**
 *
 * @author darkpastiursSennin
 */
@Named
@RequestScoped
@JMSDestinationDefinition(name="java:global/jms/puntosQueue", interfaceName="javax.jms.Queue")
public class ReceptorPuntosBean {
    @Inject
    private JMSContext contexto;
    @Resource(lookup="java:global/jms/puntosQueue")
    private Queue puntosQueue;
    
    public String receiveMessage(){
        String mensaje = contexto.createConsumer(puntosQueue).receiveBody(String.class);
        System.out.println("Recibiendo mensaje... " + mensaje);
        return mensaje;
    }
    
    public int getQueueSize(){
        int contador = 0;
        try {
            QueueBrowser qBrowser = contexto.createBrowser(puntosQueue);
            Enumeration elementos = qBrowser.getEnumeration();
            while(elementos.hasMoreElements()){
                elementos.nextElement();
                contador++;
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return contador;
    }
}
