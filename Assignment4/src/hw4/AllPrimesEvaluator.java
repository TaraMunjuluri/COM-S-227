package hw4;

import api.Card;
import api.Hand;

/**
 * Evaluator for a hand in which the rank of each card is a prime number.
 * The number of cards required is equal to the hand size. 
 * 
 * The name of this evaluator is "All Primes".
 *  @author Tara M
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class AllPrimesEvaluator extends AbstractEvaluator {

  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param handSize
   *   number of cards in a hand
   */
  public AllPrimesEvaluator(int ranking, int handSize)
  {
    // TODO: call appropriate superclass constructor and 
    // perform other initialization
	  super(ranking,handSize,"All Primes",handSize);
  }
public boolean canSatisfy(Card[] mainCards) {
	if(mainCards.length==cardsRequired()) {
		for(int i=0;i<mainCards.length;i++) {
			for(int j=2;j<mainCards[i].getRank();j++) {
				if(mainCards[i].getRank()%2==0) {
					if(mainCards[i].getRank()%j==0) {
						return false;
					}
			}
		
	}
	
	
				
		}
		
		
}

return true;
}
}

