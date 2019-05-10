package org.excavator.boot.reactiveview.controller;

import lombok.RequiredArgsConstructor;
import org.excavator.boot.reactiveview.entity.Greeting;
import org.excavator.boot.reactiveview.producer.GreetingProducer;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GreetingController {

    private final GreetingProducer greetingProducer;

    @GetMapping(value = "/reactive-api/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<String> greeting(@PathVariable String name){

        return greetingProducer.greet(name).map(Greeting::getMessage);
    }
}
