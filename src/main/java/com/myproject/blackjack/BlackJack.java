package com.myproject.blackjack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class BlackJack {
	
	 List<Integer> dealer_cards = new ArrayList<Integer>(); 
	 List<Integer> player1_cards = new ArrayList<Integer>();
	 List<Integer> player2_cards = new ArrayList<Integer>();
	 List<Integer> player3_cards = new ArrayList<Integer>();
	
	 
	private int getRandomValue(int sum) {

		int max =10;
		int min = 1;
		 Random random = new Random();
		 
		 int value = random.nextInt(max + min) + min;
		 if(value == 11 && (sum+value) > 21) {
			 value = 1;
		 }
		 return value;
	}
	private void shuffling() {
		
		System.out.println("shuffling");
		dealer_cards.add(getRandomValue(getSum(dealer_cards)));
		player1_cards.add(getRandomValue(getSum(player1_cards)));
		player2_cards.add(getRandomValue(getSum(player2_cards)));
		player3_cards.add(getRandomValue(getSum(player3_cards)));
		
	}
	
	private int getSum(List<Integer> party) {
		return party.stream()
        .reduce(0,Integer::sum);
	}
	private void getCardsforPlayer(String player,boolean player_status) {
		
		if(!player_status) {
			System.out.println(player+" busted");
			;
		}
		else {
		Scanner sc = new Scanner(System.in);
			System.out.println("Hit or Stand for "+player+"\n");
			String status=sc.nextLine();
			
			if(status.equalsIgnoreCase("Hit")) {
				int player1_sum = getSum(player1_cards);
				int player2_sum = getSum(player2_cards);
				int player3_sum = getSum(player3_cards);
				int dealer_sum = getSum(dealer_cards);
				 player_status = player.equalsIgnoreCase("player1")?(player1_sum <21)?player1_cards.add(getRandomValue(player1_sum)):false
					:player.equalsIgnoreCase("player2")?(player2_sum<21)?player2_cards.add(getRandomValue(player2_sum)):false
						:player.equalsIgnoreCase("player3")?(player3_sum<21)?player3_cards.add(getRandomValue(player3_sum)):false
							:player.equalsIgnoreCase("dealer")?(dealer_sum<21)?dealer_cards.add(getRandomValue(dealer_sum)):false:false;
				
				getCardsforPlayer(player,player_status);
			}else if(status.equalsIgnoreCase("Stand")) {
				System.out.println("Stand for player "+player);
				
			}
		}
		
			 
	}
	
	public Set<String> blackJack() {
		Scanner sc = new Scanner(System.in);
		 System.out.println("Enter number of players:");
		 Integer noOfPlayers = Integer.parseInt(sc.nextLine());
		 
		 System.out.println("Starting game with "+ noOfPlayers +" players.");
		 BlackJack bj = new BlackJack();
		 bj.shuffling();
		 
		 for(int i=1;i<=noOfPlayers;i++) {
			 bj.getCardsforPlayer("player"+i,true);
			 
		 }
		 bj.getCardsforPlayer("dealer",true);
		 
		 System.out.println(bj.dealer_cards.toString());
		 System.out.println(bj.player1_cards.toString());
		 System.out.println(bj.player2_cards.toString());
		 System.out.println(bj.player3_cards.toString());
		 
		 Map<String,Integer> playerScore = new HashMap<String,Integer>();
		
		 int dealer_score = bj.dealer_cards.stream()
			        .reduce(0,Integer::sum);
		 playerScore.put("dealer",dealer_score);
		
		 int player1_score = bj.player1_cards.stream()
			        .reduce(0,Integer::sum);
		
		 playerScore.put("player1",player1_score);
		 
		 int player2_score = bj.player2_cards.stream()
			        .reduce(0,Integer::sum);
		
		 playerScore.put("player2",player2_score);
		 
		 int player3_score = bj.player3_cards.stream()
			        .reduce(0,Integer::sum);
		
		 playerScore.put("player3",player3_score);
		 
		 System.out.println(dealer_score);
		 System.out.println(player1_score);
		 System.out.println(player2_score);
		 System.out.println(player3_score);
		 Entry<String, Integer> maxEntry = null;
		 Set<String> bustedKeys = new HashSet<>();
		 int max = 0;
		 
		   for(Entry<String, Integer> entry : playerScore.entrySet()) {
		        Integer value = entry.getValue();
		        if(null != value && value <=21 && value >= max) {
		            maxEntry = entry;
		            max = value;
		        } if (null != value && value >21) {
		        	bustedKeys.add(entry.getKey());
		        }
		    }
		   
		   if(bustedKeys != null ) {
			   System.out.println("bustedKeys "+bustedKeys);
		   }
		   
		  // System.out.println(maxEntry.getKey()+ " won"+" with score "+maxEntry.getValue());
		   Set<String> keys = new HashSet<>();
		   int maxWin = maxEntry.getValue();
		   playerScore.forEach((key,value) -> {
	            if(value==maxWin){
	                keys.add(key);
	            }
	        });
		   
		   System.out.println("Players won "+keys);
		   return keys;
	
	 }
	
	 public static void main(String[] args)
	 {
		BlackJack bj = new BlackJack();
		bj.blackJack();
	 }

}
