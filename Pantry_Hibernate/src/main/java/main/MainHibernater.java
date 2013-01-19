package main;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.pantry.modelMapper.Personne;


public class MainHibernater {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		listPersonne();
		
		

	}
	
	
	 private static void listPersonne() {
		    Transaction tx = null;
		    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); 
		    		// configures settings from hibernate.cfg.xml
		    Session session =sessionFactory.getCurrentSession();
		    //tx = session.beginTransaction();
		    try {
		      tx = session.beginTransaction();
		      // dans la requete le nom de la classe.
		      List honeys = session.createQuery("from Personne u").list();
		      //List list = session.createQuery("from Utilisateur where nom= ?").setString(0, "nom").list();
		      for (Iterator iter = honeys.iterator(); iter.hasNext();) {
		        Personne element = (Personne) iter.next();
		        System.out.println("element :"+ element.getNom());
		        
		      }
		    //  tx.commit();
//		    } catch (RuntimeException e) {
//		      if (tx != null && tx.isActive()) {
//		        try {
		// Second try catch as the rollback could fail as well
		        //  tx.rollback();
		        } catch (HibernateException e1) {
		          System.out.println("erreur");
		          e1.printStackTrace();
		        }
		// throw again the first exception
//		        throw e;
//		      }


//		    }
		  }

	

}
