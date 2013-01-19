package edu.pantry.Pantry_SpringBatch1.ItemWriter;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.batch.item.ItemWriter;

import edu.pantry.mybatis.mainMyBatis.MainMyBatis;
import edu.pantry.mybatis.modelMapper.Personne;

public class MyBatisItemWriter implements ItemWriter<Personne> {

		

	@Override
	public void write(List<? extends Personne> arg0) throws Exception {
		
		String resource = "/mybatis-config.xml";
		InputStream inputStream = MainMyBatis.class.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession(false);
		for (Personne personne : arg0) {
			session.insert("edu.pantry.mybatis.modelMapper.insertPerson",personne);
		}
		
		
		session.commit();
		
		
		Statement statement = session.getConnection().createStatement();
		
		ResultSet resultat = statement.executeQuery("SELECT * FROM PERSON");
		
		while(resultat.next()){
			System.out.println(resultat.getString("ID")) ;
			System.out.println(resultat.getString("NOM")) ;
			System.out.println(resultat.getString("PRENOM")) ;
		}
		
		statement.executeQuery("SHUTDOWN");
		statement.close();
		session.close();
		//session.getConnection().close() ;
		
		
	}

}
