package edu.pantry.Pantry_JMS.Queue;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.sun.messaging.ConnectionConfiguration;

public class QueueClient implements MessageListener {
	
	
	public static void main(String[] args) {
		QueueClient file = new QueueClient();
		try {
			file.startClientConsumer();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// we use the QueueClient class to implement MessageListener interface

	public void startClientConsumer() throws JMSException {
		
		
		// we create a connection factory which connects to one of a cluster member
		com.sun.messaging.ConnectionFactory connectionFactory = new com.sun.messaging.ConnectionFactory();
		
		//we define the behavior of the connection factory in relation to the cluster members.
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
		//javax.jms.QueueConnection queueConnection = connectionFactory
			//	.createQueueConnection("user01", "password01");
		javax.jms.QueueConnection queueConnection = connectionFactory
		.createQueueConnection("admin", "admin");
		
		//we create a session which automatically acknowledges receiving the messages.
		
		javax.jms.Session session = queueConnection.createSession(false,
				javax.jms.Session.AUTO_ACKNOWLEDGE);
		
		javax.jms.Queue smsQueue = session.createQueue("smsQueue");
		
		//we send a text message to the queue.
		javax.jms.MessageProducer producer = session.createProducer(smsQueue);
		
		for (int i=1; i<10000; i++) {
			Message msg = session.createTextMessage("A sample sms "+i);
			producer.send(msg);

		}
			
		
		// we set QueueClient as the message listener for our consumer.
		javax.jms.Queue smsQueue2 = session.createQueue("smsQueue");
		javax.jms.MessageConsumer consumer = session.createConsumer(smsQueue2);
		consumer.setMessageListener(this);
		//we start receiving messages from the server and finally
		queueConnection.start();
	}

	// we consume the message in the onMessage method which is MessageListener interface method for processing the incoming messages.
	public void onMessage(Message sms) {
		try {
			String smsContent = ((javax.jms.TextMessage) sms).getText();
			System.out.println(smsContent);
		} catch (JMSException ex) {
			ex.printStackTrace();
		}
	}
}
