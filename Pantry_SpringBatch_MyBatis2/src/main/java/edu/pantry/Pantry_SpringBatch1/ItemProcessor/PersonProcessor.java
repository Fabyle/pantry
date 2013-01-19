package edu.pantry.Pantry_SpringBatch1.ItemProcessor;

import org.springframework.batch.item.ItemProcessor;

import edu.pantry.mybatis.modelMapper.Personne;



/**
 * Processor du batch, qui va implementer les r?gles de gestion
 * @author trainee
 *
 */
public class PersonProcessor implements ItemProcessor<Personne, Personne>{

	public Personne process(final Personne personneInput) throws Exception {
		Personne personneOutput = null;

		//si la civilite a la valeur M la personne sera ecrite en base sinon on la  rejette
		//if ("M".equals(personneInput.getCivilite())) {
			personneOutput = new edu.pantry.mybatis.modelMapper.Personne();
			//personneOutput.setCivilite(personneInput.getCivilite());
			//personneOutput.setId(personneInput.getId());
			personneOutput.setNom(personneInput.getNom());
			personneOutput.setPrenom(personneInput.getPrenom());
		//}
		return personneOutput;
	}



}