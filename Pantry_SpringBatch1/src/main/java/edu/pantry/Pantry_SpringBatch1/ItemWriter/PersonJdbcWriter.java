package edu.pantry.Pantry_SpringBatch1.ItemWriter;

import java.sql.SQLException;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.pantry.Pantry_SpringBatch1.ItemModel.Personne;

/**
 * Le writer du batch qui va ecrire les donnees en BDD via JDBC
 * @author trainee
 *
 */
@Transactional (propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PersonJdbcWriter implements ItemWriter<Personne>{
	
	private JdbcTemplate jdbcTemplate;
	
	private static final String REQUEST_INSERT_PERSONNE = "insert into PERSONNE (id,nom,prenom,civilite) values (?,?,?,?)";
	private static final String REQUEST_UPDATE_PERSONNE = "update PERSONNE set nom=?, prenom=?, civilite=? where id=?";

	
	public void write(List<? extends Personne> items) throws Exception {
		System.out.println("----------- nouveau pas de commit");
		for (Personne personne : items) {
			
			System.out.println(personne.getId()+" "+personne.getNom());
			final Object object [] = {personne.getNom(),personne.getPrenom(), personne.getCivilite(),personne.getId()};
			
			//on tente un update
			int nbLigne = jdbcTemplate.update(REQUEST_UPDATE_PERSONNE, object);
					
			//si le nombre de ligne mise a jour vaut 0, on fait un insert
			if (nbLigne == 0) {
				final Object object2 [] = {personne.getId(),personne.getNom(),personne.getPrenom(), personne.getCivilite()};
				jdbcTemplate.update(REQUEST_INSERT_PERSONNE, object2);
			}	else {
				
			}
				
			
		}
		controle();
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private void controle() throws SQLException{
//		//Patch de suivi
//		Statement stat = jdbcTemplate.getDataSource().getConnection().createStatement();
//		ResultSet result = stat.executeQuery("SELECT * FROM PERSONNE");
//		while(result.next()){
//			System.out.println(result.getString("NOM")) ;
//		}
//		System.out.println(">>>");
//		
		
	}
	
	
	
	

}
