package edu.pantry.Pantry_JMS.Message_Object;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.Session;

import com.sun.messaging.ConnectionConfiguration;

public class ConnectedObject {

	/**
	 * Cr�ation de la queueConnection 
	 * 
	 * @return a QueueConnection
	 * @throws JMSException
	 */
	protected QueueConnection createQueueConnection() throws JMSException {
		
		System.out.println("V�rifier le d�marrage d'un broker sur la machine D8209045 port 7677");
		System.out.println("1-lancer imqbrokerd");
		System.out.println("2-lancer imqbrokerd -port 7677 -name broker02");
		
		// we create a connection factory which connects to one of a cluster
		// member
		com.sun.messaging.ConnectionFactory connectionFactory = new com.sun.messaging.ConnectionFactory();

		// we define the behavior of the connection factory in relation to the
		// cluster members.
		connectionFactory.setProperty(
				com.sun.messaging.ConnectionConfiguration.imqAddressList,
				"mq://D8209045:7677,mq://D8209045:7677");
		connectionFactory.setProperty(
				ConnectionConfiguration.imqReconnectEnabled, "true");
		connectionFactory.setProperty(
				ConnectionConfiguration.imqReconnectAttempts, "5");
		connectionFactory.setProperty(
				ConnectionConfiguration.imqReconnectInterval, "500");
		connectionFactory.setProperty(
				ConnectionConfiguration.imqAddressListBehavior, "RANDOM");

		// we provide a privileged user to connect to the brokers.
		return connectionFactory.createQueueConnection("admin", "admin");
	}

	/**
	 * Cr�ation d'une session sur la queue de connexion pass�e en param�tre
	 * 
	 * @param queueConnection
	 * @return a Session
	 * @throws JMSException
	 */
	protected Session createSession(QueueConnection queueConnection)
			throws JMSException {

		// we create a session which automatically acknowledges receiving the
		// messages.
		javax.jms.Session session = queueConnection.createSession(false,
				javax.jms.Session.AUTO_ACKNOWLEDGE);
		return session;
	}
	
	
	/**
	 * @param session
	 * @param name
	 * @return
	 * @throws JMSException
	 */
	protected Queue createQueue(Session session, String name) throws JMSException{
	 
	return session.createQueue(name);
}
}
