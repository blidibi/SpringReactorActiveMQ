package com.lukman.reactoramq.listeneramq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import reactor.core.Reactor;
import reactor.event.Event;

public class AmqListener implements MessageListener {
	
	@Autowired Reactor reactor;
	@Override
	public void onMessage(Message message) {
		try {
			String data = ((TextMessage) message).getText();
			reactor.notify("TestReactor", Event.wrap(data));
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
