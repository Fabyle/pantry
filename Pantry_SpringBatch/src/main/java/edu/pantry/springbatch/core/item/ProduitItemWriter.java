package edu.pantry.springbatch.core.item;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import edu.pantry.springbatch.domain.Produit;

public class ProduitItemWriter implements ItemWriter<Produit> {

    protected static final Logger logger = LoggerFactory.getLogger(ProduitFieldSetMapper.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.batch.item.ItemWriter#write(java.util.List)
     */
    public void write(List<? extends Produit> items) throws Exception {
        for (Produit produit : items) {
            logger.info(produit.toString());
        }
    }

}
