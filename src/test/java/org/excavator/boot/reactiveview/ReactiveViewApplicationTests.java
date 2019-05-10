package org.excavator.boot.reactiveview;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReactiveViewApplicationTests {
	@LocalServerPort
	private int port;

	WebTestClient webTestClient;

	final String API_RESOURCES = "/reactive-api/";
	final String baseUrl = "http://localhost:";

	private final static Logger logger  = LoggerFactory.getLogger(ReactiveViewApplicationTests.class);

	@BeforeAll
	public void init(){
		logger.info("port = {}", port);
	    webTestClient = WebTestClient.bindToServer().responseTimeout(Duration.ofSeconds(3)).baseUrl(baseUrl+port).build();
	}
	@Test
	public void contextLoads() {
		var name = "cmonkey";
	    var webClient = WebClient.builder().baseUrl(baseUrl+port).build();
	    var responsString = webClient.get().uri(API_RESOURCES+name).exchange().flatMapMany(clientResponse -> clientResponse.bodyToFlux(String.class));

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
	public void testGreeting(){

		var name = "cmonkey";
	    webTestClient.get().uri(API_RESOURCES+name)
				.accept(MediaType.TEXT_EVENT_STREAM)
				.exchange()
				.expectStatus().isOk()
				.returnResult(String.class).getResponseBody().subscribe(response -> {
					//TODO response is not work
					logger.info("response = {}", response);
		});
	}

	@Test
	public void testCalcHeight(){
	    var chair = new Chair();
	    var height = chair.calcHeight(Size.S);

	    assertEquals(height, 10);
	}

}
