package edu.pantry.Pantry_JMS.Message_Object;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import edu.pantry.Pantry_JMS.BusinessMessage;



public class ProducerObject extends ConnectedObject {

	public static void main(String[] args) {
		ProducerObject producteur = new ProducerObject();
		try {
			producteur.sendMessageOn("smsQueueObject");
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

		for (int i = 1; i < 10; i++) {
			Message msg = session.createObjectMessage(new BusinessMessage("Robert","Xavier"));
			producer.send(msg);

		}
	}
}
