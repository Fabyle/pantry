/*
 * mainPrepareHsqldb.java créé le 27 déc. 2012
 * Systalians
 */
package edu.pantry.Pantry_SpringBatch1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TODO ajouter un commentaire
 * 
 * @author Systalians - lemoinef
 * @author Systalians - $Author:$
 * @version $Name: $
 */
public class mainSelectTablePersonne_Hsqldb {

	/**
	 * TODO ajouter un commentaire
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
		} catch (InstantiationException ex) {
			// TODO bloc catch squelette auto-généré
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			// TODO bloc catch squelette auto-généré
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// TODO bloc catch squelette auto-généré
			ex.printStackTrace();
		}

		try {
			Connection connexion = DriverManager.getConnection(
					"jdbc:hsqldb:file:database", "sa", "");

			Statement statement = connexion.createStatement();
			
			ResultSet resultat = statement.executeQuery("SELECT * FROM PERSONNE");
					
				
			
			while(resultat.next()){
				System.out.println(resultat.getString("NOM")) ;
			}
			
			// pour flusher toute la base en mémoire sur le disque
			statement.executeQuery("SHUTDOWN");
			statement.close();

			connexion.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
