/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.batch;

import java.util.List;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author darkpastiursSennin
 */
@Named
@Dependent
public class VentaEscritor extends AbstractItemWriter{
    @PersistenceContext(unitName="movieplex7PU")
    private EntityManager em;


    @Override
    @Transactional
    public void writeItems(List<Object> items) throws Exception {
        items.stream().forEach(item -> {            
            em.persist(item);
            em.flush();
            em.clear();
        });
    }
    
}
