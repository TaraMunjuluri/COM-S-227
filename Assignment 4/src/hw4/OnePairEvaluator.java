package hw4;

import java.util.ArrayList;
import java.util.Arrays;

import api.Card;
import api.Hand;
import api.Suit;
import util.SubsetFinder;

/**
 * Evaluator for a hand containing (at least) two cards of the same rank. The
 * number of cards required is two.
 * 
 * The name of this evaluator is "One Pair".
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
/* @author Tara M
 * 
 */

public class OnePairEvaluator extends AbstractEvaluator {
	/**
	 * Constructs the evaluator.
	 * 
	 * @param ranking  ranking of this hand
	 * @param handSize number of cards in a hand
	 * 
	 */

	public OnePairEvaluator(int ranking, int handSize) {
		super(ranking, handSize, "One Pair", 2);
	}

	@Override
	public boolean canSatisfy(Card[] mainCards) {
		if (mainCards.length == cardsRequired()) {
			if (mainCards[0].compareToIgnoreSuit(mainCards[1]) == 0) {
				return true;
			}
		}

		return false;
	}

}
