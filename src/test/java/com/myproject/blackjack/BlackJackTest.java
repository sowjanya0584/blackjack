package com.myproject.blackjack;

import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Test;

public class BlackJackTest {

	@Test
	public void blackJacktest() {
		
		//Arrange
		BlackJack bj = new BlackJack();
		
		//Act
		Set<String> wonList = bj.blackJack();
		
		//Assert
		assertNotNull(wonList);
	}
}
