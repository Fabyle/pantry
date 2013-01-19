package edu.pantry.mybatis.mainMyBatis;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.pantry.mybatis.modelMapper.Personne;


public class MainMyBatis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String resource = "/mybatis-config.xml";
		InputStream inputStream = MainMyBatis.class.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		
		Personne person =  session.selectOne("edu.pantry.mybatis.modelMapper.selectPerson", 1);
		System.out.println(person.getNom()+" "+person.getPrenom());
		
		Personne nouvellePersonne = new Personne("DURAND", "Jack");
		
		session.insert("edu.pantry.mybatis.modelMapper.insertPerson",nouvellePersonne);
		Personne nouvellePersonneRelu =  session.selectOne("edu.pantry.mybatis.modelMapper.selectPerson", 2);
		System.out.println(nouvellePersonneRelu.getNom()+" "+nouvellePersonneRelu.getPrenom());
		
		
		nouvellePersonneRelu.setPrenom("JACKY");
		session.update("edu.pantry.mybatis.modelMapper.updatePerson",nouvellePersonneRelu);
		
		Personne nouvellePersonneRelu2 =  session.selectOne("edu.pantry.mybatis.modelMapper.selectPerson", 2);
		System.out.println(nouvellePersonneRelu2.getNom()+" "+nouvellePersonneRelu2.getPrenom());
		
		
		
		
		
		

	}

}
