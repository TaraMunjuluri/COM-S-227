package hw4;

import api.Card;

/**
 * Evaluator for a hand containing (at least) four cards of the same rank.
 * The number of cards required is four.
 * 
 * The name of this evaluator is "Four of a Kind".
 * @author Tara M
 */
public class FourOfAKindEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param handSize
   *   number of cards in a hand
   */

  public FourOfAKindEvaluator(int ranking, int handSize)
  {
		super(ranking, handSize, "Four of a Kind", 4);
  }
@Override
public boolean canSatisfy(Card[] mainCards) {
	if (mainCards.length == cardsRequired()) {
		if (mainCards[0].compareToIgnoreSuit(mainCards[3]) == 0) {
			return true;
		}
	}

	return false;
}
}
  

  
