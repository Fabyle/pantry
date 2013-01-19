package edu.test.createDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main_HSQLDB_select {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection connexion = DriverManager.getConnection("jdbc:hsqldb:file:database", "sa",  "");
			
			Statement statement = connexion.createStatement() ;
			
			ResultSet resultat = statement.executeQuery("SELECT * FROM PERSON");
			
			while(resultat.next()){
				System.out.println(resultat.getString("ID")) ;
				System.out.println(resultat.getString("NOM")) ;
				System.out.println(resultat.getString("PRENOM")) ;
			}
			
			
			// pour flusher toute la base en mémoire sur le disque
			
			//statement.executeQuery("SHUTDOWN");
			statement.close();
			
			connexion.close() ;
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Connection connexion = DriverManager.getConnection("jdbc:hsqldb:mem:database", "sa",  "");
		

		
		
		
	}

}
