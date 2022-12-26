package hw4;

import api.Card;
import api.Hand;

/**
 * Evaluator satisfied by any set of cards.  The number of
 * required cards is equal to the hand size.
 * 
 * The name of this evaluator is "Catch All".
 * @author Tara M
 */
public class CatchAllEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param handSize
   *   number of cards in a hand
   */
  public CatchAllEvaluator(int ranking, int handSize)
  {
	  super(ranking, handSize,"Catch All",handSize);
  }

@Override
public boolean canSatisfy(Card[] mainCards) {
	return cardsRequired()==mainCards.length;
}



}
