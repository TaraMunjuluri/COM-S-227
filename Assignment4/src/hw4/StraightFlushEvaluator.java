package hw4;

import java.util.Arrays;

import api.Card;
import api.Suit;

/**
 * Evaluator for a hand consisting of a "straight" in which the card ranks are
 * consecutive numbers AND the cards all have the same suit. The number of
 * required cards is equal to the hand size. An ace (card of rank 1) may be
 * treated as the highest possible card or as the lowest (not both) To evaluate
 * a straight containing an ace it is necessary to know what the highest card
 * rank will be in a given game; therefore, this value must be specified when
 * the evaluator is constructed. In a hand created by this evaluator the cards
 * are listed in descending order with high card first, e.g. [10 9 8 7 6] or [A
 * K Q J 10], with one exception: In case of an ace-low straight, the ace must
 * appear last, as in [5 4 3 2 A]
 * 
 * The name of this evaluator is "Straight Flush".
 * 
 * @author Tara M

 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class StraightFlushEvaluator extends StraightEvaluator {
	/**
	 * Constructs the evaluator. Note that the maximum rank of the cards to be used
	 * must be specified in order to correctly evaluate a straight with ace high.
	 * 
	 * @param ranking     ranking of this hand
	 * @param handSize    number of cards in a hand
	 * @param maxCardRank largest rank of any card to be used
	 */
	public StraightFlushEvaluator(int ranking, int handSize, int maxCardRank) {
		// TODO: call appropriate superclass constructor and
		// perform other initialization
		super(ranking, handSize, maxCardRank);

	}

	public String getName() {
		return "Straight Flush";
	}

	public int cardsRequired() {
		return handSize();
	}

	@Override
	public boolean canSatisfy(Card[] mainCards) {
		boolean satisfies = super.canSatisfy(mainCards);
		if (satisfies == true) {
			for (int i = 0; i < mainCards.length - 1; i++) {
				if (mainCards[i].getSuit() != (mainCards[i + 1].getSuit())) {
					satisfies = false;
					break;
				}
			}
		}
		return satisfies;
	}

}
