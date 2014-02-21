package com.lukman.reactoramq.controller;

import static reactor.event.selector.Selectors.$;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import reactor.core.Reactor;

import com.google.gson.Gson;
import com.lukman.reactoramq.model.amq.MessageQueueModel;
import com.lukman.reactoramq.reactor.TestActor;
import com.lukman.reactoramq.service.AmqService;

@Controller
public class TestController {

	@Autowired Reactor reactor;
	@Autowired TestActor testActor;
	@Autowired AmqService amqService;
	
	@ResponseBody
	@RequestMapping(value="/createreactor",method = RequestMethod.GET)
	public String createReactor(){
		reactor.on($("TestReactor"), testActor);
		return "reactor is ready";
	}
	
	@ResponseBody
	@RequestMapping(value="/createlistener",method = RequestMethod.GET)
    public String createListener() {
		amqService.createListener("TestConsumer");
		return "listener created";
    }
	
	@ResponseBody
	@RequestMapping(value="/send",method = RequestMethod.GET)
    public String send() {
		MessageQueueModel model=new MessageQueueModel();
		model.setService("TestConsumer");
		model.setParameters(UUID.randomUUID().toString());
		model.setEventName("token");
		amqService.sendMessage(model);
        return "Message send: "+new Gson().toJson(model);
    }
}