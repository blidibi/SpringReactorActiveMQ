package com.lukman.reactoramq.service;

import com.lukman.reactoramq.model.amq.MessageQueueModel;

public interface AmqService {
	boolean sendMessage(MessageQueueModel queueModel);
	boolean createListener(String destination);
}
