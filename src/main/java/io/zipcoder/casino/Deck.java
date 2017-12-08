package io.zipcoder.casino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList();

        for (int i = 0; i < 4; i++) {
            Suit[] suitArray = new Suit[]{Suit.CLUB, Suit.DIAMOND, Suit.HEART, Suit.SPADE};
            for (int j = 0; j < 13; j++) {
                Rank[] rankArray = new Rank[]{Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE,
                        Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN, Rank.JACK, Rank.QUEEN,
                        Rank.KING};

                Card card = new Card(rankArray[j], suitArray[i]);

                cards.add(card);
            }
        }

    }

    public Deck(int num) {
        this.cards = new ArrayList<Card> ();
    }

    public void createFullDeck(){
        for(Suit cardSuit : Suit.values()){
            //Loop through Values
            for(Rank rankValue : Rank.values()){
                //Add new card to the mix
                this.cards.add(new Card(rankValue,cardSuit));
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Integer getDeckSize() {
        return cards.size();
    }

    public Card pop(int value) {
        return cards.get (value);
    }

    public void removeCard(int i){
        this.cards.remove(i);
    }

    public Card getCard(int i){
        return this.cards.get(i);
    }

    public void addCard(Card addCard){
        this.cards.add(addCard);
    }

    public void draw(Deck comingFrom){
        this.cards.add(comingFrom.pop(0));
        comingFrom.removeCard(0);
    }

    public String toString(){
        String str = "";
        for(int i = 0;i < 2;i ++){
           str+= "\n" +cards.get (i);
        }
        return str;
    }

    public void moveAllToDeck(Deck moveTo){
        int thisDeckSize = this.cards.size();
        //put cards in moveTo deck
        for(int i = 0; i < thisDeckSize; i++){
            moveTo.addCard(this.getCard(i));
        }
        //empty one card from deck
        for(int i = 0; i < thisDeckSize; i++){
            this.removeCard(0);
        }
    }

    public int deckSize(){
        return this.cards.size();
    }

    public int cardsValue(){
        int totalValue = 0;
        int aces = 0;
        //For every card in the deck
        for(Card aCard : this.cards){
            //Switch of possible values
            switch(aCard.getRank()){
                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN: totalValue += 10; break;
                case JACK: totalValue += 10; break;
                case QUEEN: totalValue += 10; break;
                case KING: totalValue += 10; break;
                case ACE: aces += 1; break;
            }
        }

        for(int i = 0; i < aces; i++){
            //If they're already at over 10 getting an ace valued at 11 would put them up to 22, so make ace worth one
            if (totalValue > 10){
                totalValue += 1;
            }
            else{
                totalValue += 11;
            }
        }
        return totalValue;
    }

}


