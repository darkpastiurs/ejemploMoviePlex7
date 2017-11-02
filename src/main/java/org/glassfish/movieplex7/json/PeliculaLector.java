/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.json;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import org.glassfish.movieplex7.entities.Movie;

/**
 *
 * @author darkpastiursSennin
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class PeliculaLector implements MessageBodyReader<Movie> {

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Movie.class.isAssignableFrom(type);
    }

    @Override
    public Movie readFrom(Class<Movie> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        Movie pelicula = new Movie();
        JsonParser conversor = Json.createParser(entityStream);
        while (conversor.hasNext()) {
            switch (conversor.next()) {
                case KEY_NAME:
                    String key = conversor.getString();
                    conversor.next();
                    switch (key) {
                        case "id":
                            pelicula.setId(conversor.getInt());
                            break;
                        case "name":
                            pelicula.setName(conversor.getString());
                            break;
                        case "actors":
                            pelicula.setActors(conversor.getString());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return pelicula;
    }

}
