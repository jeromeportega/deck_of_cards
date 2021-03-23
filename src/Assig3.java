/*
  Open Sourcerers
  Nathan Jobes, Amy Gonzales, Chris Bray, Jerome Ortega
  CST 338 Week 3
  Assignment 3: Decks of Cards
  3/17/2021
 */

import java.util.Scanner;
import java.util.Random;

public class Assig3
{
   public static void main(String[] args)
   {
     //Phase 3 
      Deck myDeck = new Deck();
      System.out.println("Test with 2 pack.\n");
      
      //loops to repeat test for 2 pack and 1 pack version
      for (int numPacks = 2; numPacks > 0; numPacks --)
      {
         //incrementor to add line breaks to print as a paragraph
         int lineBreak = 0;
         
         //a loop to switch shuffle off and on
         for (int i = 1; i <= 2; i++)
         {
            myDeck = new Deck(numPacks);
            lineBreak = 0;
            if (i%2 == 0)
            {
               myDeck.shuffle();
               System.out.println("Now with shuffle:");
            }
            while (myDeck.getTopCard() > 0)
            {
               lineBreak++;
               System.out.print(myDeck.dealCard() + " / ");
               if (lineBreak%5 == 0) System.out.print("\n"); // for readability
            }
            System.out.println("\n"); // add space between prints
         }
       //Add space between first and second test
       if (numPacks == 2) System.out.println("\n\nTest with 1 pack.\n\n");
       //Mark end of test
       else System.out.println("\nEnd of Phase 3 Test\n\n");
      }
      
      //Phase 4
      
      Scanner keyboard = new Scanner(System.in);
      boolean validInput = false;
      int numHands = 0;
      
      while (!validInput)
      {
         
         System.out.println("How many hands? (1-10, please): ");
         //What happens if they enter "george"?
         numHands = keyboard.nextInt();
         if (numHands > 0 && numHands <= 10)
         {
            validInput = true;
         }
         Deck newDeck = new Deck(); //new deck
         Hand hands[] = new Hand[numHands]; //create array 
         int firstCard = newDeck.getTopCard(); //get first card 
         
         //Populate Hands
         for (int currentHand = 0; currentHand < numHands; ++currentHand)
         {
            hands[currentHand] = new Hand(); //populates array w/ hand objects
         }
         
         //Place cards into hands
         while (newDeck.getTopCard() > 0)//should we check for bad cards here?
         {
            
            for (int currentHand = 0; currentHand < numHands; ++currentHand)
            { 
               if (newDeck.getTopCard() <= 0) //should this be firstCard == numHands?
                  break; //stop dealing cards
               hands[currentHand].takeCard(newDeck.dealCard());
               -- firstCard;
            }
         }
         
         System.out.println("Unshuffled hands: ");
         for (int currentHand = 0; currentHand < numHands; ++currentHand)
         {
            System.out.println(" Player " + (currentHand + 1) 
                  + " = ( "+ hands[currentHand].toString() + ")");//prints cards
            hands[currentHand].resetHand();
         }
         
         newDeck.init(1);
         newDeck.shuffle();
         
         firstCard = newDeck.getTopCard(); //get first card 
         for (int currentHand = 0; currentHand < numHands; ++currentHand)
         {
            hands[currentHand] = new Hand(); //populates array w/ hand objects
         }
         
         while (newDeck.getTopCard() > 0)//should we check for bad cards here?
         {
            for (int currentHand = 0; currentHand < numHands; ++currentHand)
            { 
               if (newDeck.getTopCard() <= 0) //should this be firstCard == numHands?
                  break; //stop dealing cards
               hands[currentHand].takeCard(newDeck.dealCard());
               -- firstCard;
            }

         }
         System.out.println("Shuffled hands: ");
         for (int currentHand = 0; currentHand < numHands; ++currentHand)
         {
            System.out.println(" Player " + (currentHand + 1) + " = " + hands[currentHand].toString());//prints cards
         }
         System.out.println("Thanks for playing!");
      
         
         keyboard.close();
         
      }
      
      
   }

   //Method to test Card class -- should be deleted after validation?
   //Nathan
   public static void testCard()
   {
      Card aceSpades = new Card('A', Card.Suit.SPADES);
      Card queenDiamonds = new Card('Q', Card.Suit.DIAMONDS);
      Card badCard = new Card('Z', Card.Suit.SPADES);
      
      System.out.println(aceSpades + "\n" + queenDiamonds + "\n" + badCard + "\n");
      
      aceSpades.set('X',Card.Suit.DIAMONDS);
      
      System.out.println(aceSpades + "\n" + queenDiamonds + "\n" + badCard + "\n");
   }
   
   //Method to test Hand class -- should be deleted after validation?
   //Nathan
   public static void testHand()
   {
      Card aceSpades = new Card('A', Card.Suit.SPADES);
      Card queenDiamonds = new Card('Q', Card.Suit.DIAMONDS);
      Card threeClubs = new Card('3', Card.Suit.CLUBS);
      
      Hand myHand = new Hand();
      
      //Add these cards until hand limit is reached
      for (int i = 0; i < Hand.MAX_CARDS; i++)
      {
         int mod = i%3;
         switch(mod)
         {
            case 0: myHand.takeCard(aceSpades); break;
            case 1: myHand.takeCard(queenDiamonds); break;
            case 2: myHand.takeCard(threeClubs); break;
            default: break;
         }
      }
      
      //Display hand
      System.out.println(myHand);
      
      //Play cards until hand is empty
      while(myHand.getNumCards() > 0)
      {
         System.out.println(myHand.playCard());
      }
      
      //Display empty hand
      System.out.println(myHand);
      
   }
   
   //Runs one double deal cycle for Phase 3 with a given number of packs of cards
   //Nathan
   public static void testDeck (int numPacks)
   {
      Deck myDeck = new Deck(numPacks);
      
      int lineBreak = 0;
      while (myDeck.getTopCard() > 0)
      {
         lineBreak++;
         System.out.print(myDeck.dealCard() + " / ");
         if (lineBreak%5 == 0) System.out.print("\n"); // for readability
         
         
      }
      
      System.out.println("\n\n");
      
      myDeck.init(numPacks);
      myDeck.shuffle();
      
      while (myDeck.getTopCard() > 0)
      {
         lineBreak++;
         System.out.print(myDeck.dealCard() + " / ");
         if (lineBreak%5 == 0) System.out.print("\n"); // for readability
      }
   }
  
}








class Card
{
   
   public enum Suit {CLUBS, DIAMONDS, HEARTS, SPADES}
   
   private char value;
   private Suit suit;
   private boolean errorFlag;
   
   //Default
   //Amy
   public Card()
   {
      char defaultValue = 'A';
      Suit defaultSuit = Suit.SPADES;
      set(defaultValue, defaultSuit);
   }
   
   //Overload
   //Amy
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }
   
   
     //Chris -Returns a string representation of the Card, returns invalid if (errorFlag)
   public String toString()
   {
      if (errorFlag == true)
      {
         return "[invalid]";
      }
      return (value + " " + suit);
   }
   
 
   //Chris -Sets the card's representation and modifies errorFlag based on inputs
   public boolean set(char value, Suit suit)
   {
      if (isValid(value, suit))
      {
         this.value = value;
         this.suit = suit;
         errorFlag = false;
         
      }
      else
      {
         errorFlag = true;
         
      }
      return !errorFlag;
   }
   
   //Accessors
   //Jerome
   public Suit getSuit()
   {
      return suit;
   }
   
   //Jerome
   public char getValue()
   {
      return value;
   }
   
   //Nathan
   public boolean getErrorFlag()
   {
      return errorFlag;
   }
   
   //Returns true if the cards are the value and suit
   //Nathan
   public boolean equals(Card card)
   {
      if (value == card.getValue() && suit == card.getSuit() &&
            errorFlag == card.getErrorFlag()) return true;
      else return false;
   }
   
   //Returns true if entered value is a real card value - Suit should always be valid
   //Amy
   private boolean isValid(char value, Suit suit)
   {
      String charValue = "23456789TJQKA";
      if (charValue.indexOf(value) != -1)
         return true;
      else
         return false;  
   }
   
   
}



class Hand
{
   
   public static final int MAX_CARDS = 52; // one deck
   
   Card [] myCards;
   int numCards;
   
   //Default
   //Jerome
   public Hand()
   {
      numCards = 0;
      myCards = new Card[MAX_CARDS];
   }
   
   //Removes all cards from the hand
   //Jerome
   public void resetHand()
   { 
      myCards = new Card[MAX_CARDS];
   }
   
   
   //Chris - Adds a copy of a card to the hand
   public boolean takeCard(Card card)
   {
      if (numCards < MAX_CARDS)
      {
         myCards[numCards] = new Card(card.getValue(), card.getSuit());
         numCards++;
         return true;
      }
      else return false;
   }
   
   
   //Returns and removes the card at the tail of myCards[]
   //Nathan
   public Card playCard()
   {
      if (numCards > 0)
      {
         Card playedCard = myCards[numCards -1];
         myCards[numCards-1] = null;
         numCards--;
         return playedCard;
      }
      else
      {
         Card errorCard = new Card('Z', Card.Suit.SPADES);
         return errorCard;
      }
   }
   
   //Chris -Returns a string representation of all cards in myCards[]
   public String toString()
   {
      String oneString = "";
      for (int i = 0; i < numCards; i++)
      {
         oneString = oneString + myCards[i].toString() + " ";
      }
      return oneString;
   }
   
   
   //Accessors
   //Jerome
   public int getNumCards()
   {
      return numCards;
   }
   
   //Returns card at position k
   //if position k does not exists returns a card with errorFlag == true
   //Amy
   public Card inspectCard(int k)
   {
      if (k >= 0 && k < numCards && k < MAX_CARDS)
      {
         if (myCards[k] != null)
         {
            return myCards[k];
         }
         else
         {
            Card errorCard = new Card('X', Card.Suit.SPADES);
            return errorCard;
         }
      }
      else
      {
         Card errorCard = new Card('X', Card.Suit.SPADES);
         return errorCard;
      }
   }
   
}

class Deck
{
   
   public static final int MAX_PACKS = 6;

   
   private static Card [] masterPack;
   
   private static Card[] cards;
   int topCard;
   
   
   //Default assumes 1 pack
   //Amy
   public Deck()
   {
      allocateMasterPack();
      init(1);
   }
   
   //Creates a deck with numPacks worth of standard decks
   //Amy
   public Deck(int numPacks)
   {
      if (numPacks <= MAX_PACKS)
      {
         allocateMasterPack();
         init(numPacks);
      }
   }
   
   //Reset cards[] with numPacks number of decks ordered
   //Nathan
   public void init(int numPacks)
   {
      cards = new Card [52*numPacks];
      topCard = 52*numPacks;
      
      for (int i = 0; i < topCard; i++)
      {
         cards[i] = masterPack[i%52];
      }
   }
   
  
   //Chris -Shuffles the deck
   public void shuffle()
   {
      Random randomGenerator = new Random();
      for (int passes = 0; passes < 5; passes++)
      {
         for (int i = 0; i < (topCard); i++)
         {
            int thisSwitch = randomGenerator.nextInt(topCard);
            Card temp = Deck.cards[i];
            Deck.cards[i] = Deck.cards[thisSwitch];
            Deck.cards[thisSwitch] = temp;
         }
      }
   }
   
   //Returns the top card of the deck if it exists and removes it.
   //Amy
   public Card dealCard()
   {
         if (topCard <= 0)
         {
            return null;
         }
         Card temp = cards[topCard-1];
         cards[topCard-1] = null;
         topCard--;
         return temp;  
   }
   
   //Returns the value of topCard
   //Jerome
   public int getTopCard()
   {
      return topCard;
   }
   
   //Returns the card at position k, if a card does not exist there return an illegal card
   //Amy
   public Card inspectCard(int k)
   {
      if (k >= 0 && k < topCard && k < cards.length)
      {
         if (cards[k] != null)
         {
            return cards[k];
         }
         else
         {
            Card errorCard = new Card('X', Card.Suit.SPADES);
            return errorCard;
         }
      }
      else
      {
         Card errorCard = new Card('X', Card.Suit.SPADES);
         return errorCard;
      }
   }
   
   //If masterPack has not been populated, then populate otherwise do nothing
   //Nathan
   private static void allocateMasterPack()
   {
      if (masterPack == null)
      {
         masterPack = new Card [52];
         for (int i = 0; i < 52; i++)
         {
            char value = 'X'; //Default to a bad value so card will throw errorFlag
            switch(i%13)
            {
               case 0: value =  'A'; break;
               case 1: value =  '2'; break;
               case 2: value =  '3'; break;
               case 3: value =  '4'; break;
               case 4: value =  '5'; break;
               case 5: value =  '6'; break;
               case 6: value =  '7'; break;
               case 7: value =  '8'; break;
               case 8: value =  '9'; break;
               case 9: value =  'T'; break;
               case 10: value = 'J'; break;
               case 11: value = 'Q'; break;
               case 12: value = 'K'; break;
               default: break;
            }
            
            Card.Suit suit = Card.Suit.CLUBS;
            switch((i/13))
            {
               case 0: suit = Card.Suit.CLUBS; break;
               case 1: suit = Card.Suit.DIAMONDS; break;
               case 2: suit = Card.Suit.HEARTS; break;
               case 3: suit = Card.Suit.SPADES; break;
               default: break;
            }
            
            masterPack[i] = new Card(value, suit);
         }
      }
   }
   
}