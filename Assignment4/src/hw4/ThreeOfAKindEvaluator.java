package hw4;

import api.Card;
import api.Hand;

/**
 * Evaluator for a hand containing (at least) three cards of the same rank.
 * The number of cards required is three.
 * 
 * The name of this evaluator is "Three of a Kind".
 * 
 * @author Tara M

 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class ThreeOfAKindEvaluator extends AbstractEvaluator {
	private int ranking;
	private int handSize;

	
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param handSize
   *   number of cards in a hand
   */
  public ThreeOfAKindEvaluator(int ranking, int handSize)
  {
	  super(ranking, handSize,"Three of a kind",3);
  }
@Override
public boolean canSatisfy(Card[] mainCards) {
	if (mainCards.length == cardsRequired()) {
		if (mainCards[0].compareToIgnoreSuit(mainCards[1]) == 0) {
			if (mainCards[1].compareToIgnoreSuit(mainCards[2]) == 0)
			return true;
		}
	}

	return false;
}

}
