package edu.pantry.Pantry_APIMail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;


import com.sun.mail.imap.IMAPFolder;

public class FolderFetchIMAP {

	public static void main(String[] args) throws MessagingException,
			IOException {
		IMAPFolder folder = null;
		Store store = null;
		String subject = null;
		Flag flag = null;
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");

			Session session = Session.getDefaultInstance(props, null);

			store = session.getStore("imaps");
			store.connect("imap.googlemail.com", "truc@gmail.com",
					"password");

			//folder = (IMAPFolder) store.getFolder("[Gmail]/Spam"); 
			folder = (IMAPFolder) store.getFolder("inbox"); 
			//folder = (IMAPFolder) store.getFolder("[Gmail]/Tous les messages");

			if (!folder.isOpen())
				folder.open(Folder.READ_WRITE);
			Message[] messages = folder.getMessages();
			System.out.println("No of Messages : " + folder.getMessageCount());
			System.out.println("No of Unread Messages : "
					+ folder.getUnreadMessageCount());
			System.out.println(messages.length);
			Map<String, ArrayList<?>> mailParSender = new HashMap<String, ArrayList<?>>();
			for (int i = 0; i < messages.length; i++) {
				System.out.println(i);
				Message msg = messages[i];

				subject = msg.getSubject();
				String from = msg.getFrom()[0].toString();
				Mail mail = new Mail(subject, from,
						/*msg.getAllRecipients()[0].toString()*/"",
						msg.getReceivedDate(), msg.getSize());

				if (!mailParSender.containsKey(from)) {
					mailParSender.put(from, new ArrayList<Mail>());
				}
				((Collection) mailParSender.get(from)).add(mail);

			}

			Set<Entry<String, ArrayList<?>>> entrySet = mailParSender
					.entrySet();
			for (Entry<String, ArrayList<?>> entry : entrySet) {
				System.out.println(entry.getKey() + ";"
						+ entry.getValue().size());
			}

		} finally {
			if (folder != null && folder.isOpen()) {
				folder.close(true);
			}
			if (store != null) {
				store.close();
			}
		}

	}

}
