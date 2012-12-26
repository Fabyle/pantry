package edu.pantry.springbatch.core.item;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import edu.pantry.springbatch.domain.Produit;

public class ProduitFieldSetMapper implements FieldSetMapper<Produit> {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.batch.item.file.mapping.FieldSetMapper#mapFieldSet
     * (org.springframework.batch.item.file.transform.FieldSet)
     */
    public Produit mapFieldSet(FieldSet fieldSet) {
        Produit product = new Produit();
        product.setId(fieldSet.readString("ID"));
        product.setName(fieldSet.readString("NOM"));
        product.setDescription(fieldSet.readString("DESCRIPTION"));
        return product;
    }

}
