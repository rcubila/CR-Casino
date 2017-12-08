package io.zipcoder.casino;

import java.util.Scanner;

public class BlackConsole {

    public static void starBlackJackGame() {

        Deck playingDeck = new Deck (1);
        playingDeck.createFullDeck ();
        playingDeck.shuffle ();

        Deck playerCards = new Deck (1);

        double playerMoney = 100.0;

        Deck dealerCards = new Deck (1);

        System.out.println ("Welcome to the Black Jack table !!!!");

        Scanner userInput = new Scanner (System.in);

        //Play the game while the player has money
        while (playerMoney > 0) {
            //Take Bet
            System.out.println ("You have $" + playerMoney + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble ();
            boolean endRound = false;
            if (playerBet > playerMoney) {
                //Break if they bet too much
                System.out.println ("You cannot bet more than you have.");
                break;
            }

            System.out.println ("Dealing...");

            playerCards.draw (playingDeck); // player Card 1
            playerCards.draw (playingDeck); // player Card 2

            //Dealer gets two cards
            dealerCards.draw (playingDeck); // dealer Card 1
            dealerCards.draw (playingDeck); // dealer Card 2


            while (true) {

                System.out.println ("Your Hand:" + playerCards.toString ());

                System.out.println ("Your hand is currently valued at: " + playerCards.cardsValue ());

                System.out.println ("Dealer Hand: " + dealerCards.getCard (0).toString () + " and [hidden]");

                System.out.println ("Would you like to (1)Hit or (2)Stand");
                int response = userInput.nextInt ();

                if (response == 1) {
                    playerCards.draw (playingDeck);
                    System.out.println ("You draw a:" + playerCards.getCard (playerCards.deckSize () - 1).toString ());

                    if (playerCards.cardsValue () > 21) {
                        System.out.println ("Bust. Currently valued at: " + playerCards.cardsValue ());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }

                if (response == 2) {
                    break;
                }
            }

            System.out.println ("Dealer Cards:" + dealerCards.toString ());

            if ((dealerCards.cardsValue () > playerCards.cardsValue ()) && !endRound) {
                System.out.println ("Dealer beats you " + dealerCards.cardsValue () + " to " + playerCards.cardsValue ());
                playerMoney -= playerBet;
                endRound = true;
            }
            while ((dealerCards.cardsValue () < 17) && !endRound) {
                dealerCards.draw (playingDeck);
                System.out.println ("Dealer draws: " + dealerCards.getCard (dealerCards.deckSize () - 1).toString ());
            }
            System.out.println ("Dealers hand value: " + dealerCards.cardsValue ());

            if ((dealerCards.cardsValue () > 21) && !endRound) {
                System.out.println ("Dealer Busts. You win!");
                playerMoney += playerBet;
                endRound = true;
            }

            if ((dealerCards.cardsValue () == playerCards.cardsValue ()) && !endRound) {
                System.out.println ("Push.");
                endRound = true;
            }

            if ((playerCards.cardsValue () > dealerCards.cardsValue ()) && !endRound) {
                System.out.println ("You win the hand.");
                playerMoney += playerBet;
                endRound = true;
            } else if (!endRound) //dealer wins
            {
                System.out.println ("Dealer wins.");
                playerMoney -= playerBet;
            }

            playerCards.moveAllToDeck (playingDeck);
            dealerCards.moveAllToDeck (playingDeck);
            System.out.println ("End of Hand.");

        }

        System.out.println ("Sorry, you ran out of money!!");

        userInput.close ();
    }
}