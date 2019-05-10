package org.excavator.boot.reactiveview;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReactiveViewApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testCalcHeight(){
	    var chair = new Chair();
	    var height = chair.calcHeight(Size.S);

	    assertEquals(height, 10);
	}

}
