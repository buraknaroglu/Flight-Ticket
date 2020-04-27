package com.flight.ticket;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class TicketApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void DSfsdftextLoads() {
		Random rand = new Random();
//		Integer price =  rand.nextInt(1000);
		Integer price =  (int) (Math.random());
		System.out.println(price);
	}

}
