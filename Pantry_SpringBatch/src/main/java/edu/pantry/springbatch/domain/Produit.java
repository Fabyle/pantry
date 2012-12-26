/**
 * 
 */
package edu.pantry.springbatch.domain;

import java.io.Serializable;

public class Produit implements Serializable {

    private static final long serialVersionUID = 6648416741847674063L;

    public Produit(String id) {
        super();
        this.id = id;
    }

    public Produit() {
    }

    private String id;
    private String name;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + description;
    }

}
