/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import javax.batch.api.chunk.AbstractItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author darkpastiursSennin
 */
@Named
@Dependent
public class VentaLector extends AbstractItemReader {

    private BufferedReader lector;

    public void open(Serializable checkpoint) {
        try {
            lector = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/sales.csv")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object readItem() {
        String lectura = null;
        try {
            lectura = lector.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lectura;
    }

}
