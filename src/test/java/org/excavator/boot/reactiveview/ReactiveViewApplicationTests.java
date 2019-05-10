package org.excavator.boot.reactiveview;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReactiveViewApplicationTests {
	@LocalServerPort
	private int port;

	private final static Logger logger  = LoggerFactory.getLogger(ReactiveViewApplicationTests.class);

	@Test
	public void contextLoads() {
		var name = "cmonkey";
		var baseUrl = "http://localhost:" + port;
	    var webClient = WebClient.builder().baseUrl(baseUrl).build();
	    var responsString = webClient.get().uri("/reactive-api/"+name).exchange().flatMapMany(clientResponse -> clientResponse.bodyToFlux(String.class));

	    responsString.subscribe(msg -> {
	        logger.info("msg = {}", msg);
		});

		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
		    logger.error("Exception = {}", e);
		}
	}

	@Test
	public void testCalcHeight(){
	    var chair = new Chair();
	    var height = chair.calcHeight(Size.S);

	    assertEquals(height, 10);
	}

}
