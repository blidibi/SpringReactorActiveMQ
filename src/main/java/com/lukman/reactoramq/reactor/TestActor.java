package com.lukman.reactoramq.reactor;

import org.springframework.stereotype.Service;

import reactor.event.Event;
import reactor.function.Consumer;

@Service
public class TestActor implements Consumer<Event<String>>{

	@Override
	public void accept(Event<String> arg) {
		System.out.println(arg.getData());
	}

}
