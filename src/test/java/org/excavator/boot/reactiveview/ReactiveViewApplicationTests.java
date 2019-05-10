package org.excavator.boot.reactiveview;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
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
