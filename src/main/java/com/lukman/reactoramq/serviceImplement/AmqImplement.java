package com.lukman.reactoramq.serviceImplement;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.lukman.reactoramq.listeneramq.AmqListener;
import com.lukman.reactoramq.model.amq.MessageQueueModel;
import com.lukman.reactoramq.service.AmqService;

@Service
public class AmqImplement implements AmqService{
	
	@Autowired private ConnectionFactory connectionFactory;
	@Autowired private JmsTemplate jmsTemplate;

	private String msg;
	@Override
	public boolean createListener(String destination) {
		try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination dest=session.createQueue(destination);
            MessageConsumer consumer = session.createConsumer(dest);
            AmqListener listener = new AmqListener();
            consumer.setMessageListener(listener);
            connection.start();
            return true;
        } catch (JMSException ex) {
            Logger.getLogger(AmqImplement.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
		
	}

	@Override
	public boolean sendMessage(MessageQueueModel queueModel) {
		msg=new Gson().toJson(queueModel);
		MessageCreator messageCreator=new MessageCreator() {
			  public Message createMessage(Session session) throws JMSException {
			      TextMessage textMessage = session.createTextMessage(msg);
			      return textMessage;
			  }
		};
		jmsTemplate.setDefaultDestinationName(queueModel.getService());
		try {
			jmsTemplate.send(messageCreator);
			return true;
		} catch (JmsException e) {
			Logger.getLogger(AmqImplement.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}
}
