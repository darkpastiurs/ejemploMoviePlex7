/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.batch;

import java.util.StringTokenizer;
import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import org.glassfish.movieplex7.entities.Sales;

/**
 *
 * @author darkpastiursSennin
 */
@Named
@Dependent
public class VentaProcesador implements ItemProcessor {

    @Override
    public Object processItem(Object item) throws Exception {
        Sales venta = new Sales();
        StringTokenizer tokens = new StringTokenizer((String)item, ",");
        venta.setId(Integer.parseInt(tokens.nextToken()));
        venta.setAmount(Float.parseFloat(tokens.nextToken()));
        return venta;
    }
    
}
