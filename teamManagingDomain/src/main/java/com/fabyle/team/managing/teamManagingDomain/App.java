package com.fabyle.team.managing.teamManagingDomain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fabyle.team.managing.teamManagingDomain.domain.Project;
import com.fabyle.team.managing.teamManagingDomain.domain.Resource;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
    		ApplicationContext context =
    			    new ClassPathXmlApplicationContext("spring-context-ressources.xml","spring-context-projects-all.xml");
    		
    		
    		
    		Resource xavier = (Resource) context.getBean("xro");
    		System.out.println("Xavier says : "+xavier.getNom());
    		
    		Project projet1 = (Project) context.getBean("batch-portefeuille");
    		System.out.println("batch-portefeuille says : "+projet1.getDateFinContrainte());
    		
    		
    		
    }
}
