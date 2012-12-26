package edu.pantry.Pantry_JMS.Message_String;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.QueueConnection;

import edu.pantry.Pantry_JMS.Message_Object.ConnectedObject;


public class Consumer extends ConnectedObject implements MessageListener {

	public static void main(String[] args) {
		Consumer consommateur = new Consumer();
		try {
			consommateur.startConsumerOn("smsQueue");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void startConsumerOn(String queueName) throws JMSException {
		QueueConnection queueConnection = this.createQueueConnection();
		
		javax.jms.Session session = this.createSession(queueConnection);

		// we set QueueClient as the message listener for our consumer.
		javax.jms.Queue smsQueue = this.createQueue(session, queueName);
		javax.jms.MessageConsumer consumer = session.createConsumer(smsQueue);
		consumer.setMessageListener(this);
		// we start receiving messages from the server and finally
		queueConnection.start();
	}

	// we consume the message in the onMessage method which is MessageListener interface method for processing the incoming messages.
	public void onMessage(Message sms) {
		try {
			String smsContent = ((javax.jms.TextMessage) sms).getText();
			System.out.println("Consumer received : "+ smsContent);
		} catch (JMSException ex) {
			ex.printStackTrace();
		}
	}
}
