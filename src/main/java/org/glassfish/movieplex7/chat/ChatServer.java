/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author darkpastiursSennin
 */
@ServerEndpoint("/websocket")
public class ChatServer {

    private static final Logger LOG = Logger.getLogger(ChatServer.class.getName());
    private static final List<Session> CLIENTES = Collections.synchronizedList(new ArrayList<Session>());

    @OnOpen
    public void onOpen(Session cliente) {
        LOG.log(Level.INFO, "[ServerEndpoint][ChatServer][onOpen]");
        CLIENTES.add(cliente);
    }

    @OnClose
    public void onClose(Session cliente) {
        LOG.log(Level.INFO, "[ServerEndpoint][ChatServer][onClose]");
        CLIENTES.remove(cliente);
    }

    @OnMessage
    public void onMessage(String mensaje, Session cliente) {
        LOG.log(Level.INFO, "[ServerEndpoint][ChatServer][onMessage] -> {0}", mensaje);
        CLIENTES.stream().forEach(peer -> {
            try {
                peer.getBasicRemote().sendObject(mensaje);
            } catch (IOException | EncodeException ex) {
                LOG.log(Level.SEVERE, "[ServerEndpoint][ChatServer][onMessage][Exception] -> {0}", ex.getMessage());
            }
        });
    }
}
