package org.excavator.boot.reactiveview.producer;

import org.excavator.boot.reactiveview.entity.Greeting;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

@Component
public class GreetingProducer {
    DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

    public Flux<Greeting> greet(String name){
        return Flux.fromStream(Stream.generate(() -> new Greeting("Hi " + name + " @ " + formater.format(Instant.now()) + " !"))).delayElements(Duration.ofSeconds(1));
    }
}
