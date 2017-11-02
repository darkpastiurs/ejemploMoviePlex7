/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.batch;

import java.util.List;
import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.operations.JobSecurityException;
import javax.batch.operations.JobStartException;
import javax.batch.runtime.BatchRuntime;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.glassfish.movieplex7.entities.Sales;

/**
 *
 * @author darkpastiursSennin
 */
@Named
@RequestScoped
public class VentaBean {
    @PersistenceContext(unitName = "movieplex7PU")
    private EntityManager em;

    public void iniciarJob() {
        try {
            JobOperator operadorJob = BatchRuntime.getJobOperator();
            long jobId = operadorJob.start("eod-ventas", new Properties());
            System.out.println("Iniciado job con id: " + jobId);
        } catch (JobSecurityException | JobStartException e) {
            e.printStackTrace();
        }
    }
    
    public List<Sales> getVentas(){
        return em.createNamedQuery("Sales.findAll", Sales.class).getResultList();
    }

}
