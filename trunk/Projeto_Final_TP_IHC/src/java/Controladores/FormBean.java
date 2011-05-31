/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Neo
 */

package Controladores;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormBean implements Serializable {

    private List<String> selectedMovies;

    private List<String> selectedOptions;

    private Map<String,String> movies;

    public FormBean() {
        movies = new HashMap<String, String>();
        movies.put("Scarface", "Scarface");
        movies.put("Goodfellas", "Goodfellas");
        movies.put("Godfather", "Godfather");
        movies.put("Carlito's Way", "Carlito's Way");
    }

    public List<String> getSelectedMovies() {
        return selectedMovies;
    }
    public void setSelectedMovies(List<String> selectedMovies) {
        this.selectedMovies = selectedMovies;
    }

    public List<String> getSelectedOptions() {
        return selectedOptions;
    }
    public void setSelectedOptions(List<String> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public Map<String, String> getMovies() {
        return movies;
    }
}