package edu.pantry.Pantry_JMS.Message_String;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import edu.pantry.Pantry_JMS.Message_Object.ConnectedObject;


public class Producer extends ConnectedObject {

	public static void main(String[] args) {
		Producer producteur = new Producer();
		try {
			producteur.sendMessageOn("smsQueue");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendMessageOn(String queueName) throws JMSException {
		Session session = this.createSession(this.createQueueConnection());
		javax.jms.Queue smsQueue = this.createQueue(session, queueName);

		// we send a text message to the queue.
		javax.jms.MessageProducer producer = session.createProducer(smsQueue);

		for (int i = 1; i < 10000; i++) {
			Message msg = session.createTextMessage("A sample sms " + i);
			producer.send(msg);

		}
	}
}
