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
public class mainCreateTablePersonne_Hsqldb {

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
			
			//statement.executeUpdate("DROP TABLE PERSONNE");
			statement
					.executeUpdate("CREATE TABLE PERSONNE (ID INTEGER,	NOM VARCHAR (50),	PRENOM VARCHAR (50),	CIVILITE VARCHAR (3),PRIMARY KEY (ID));");
			
			statement.executeUpdate("INSERT INTO PERSONNE (ID) values(1)");
			
			
			ResultSet resultat = statement.executeQuery("SELECT * FROM PERSONNE");
					
			
			while(resultat.next()){
				System.out.println(resultat.getInt("ID")) ;
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
