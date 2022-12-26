package hw4;

import java.util.Arrays;

import api.Card;
import api.Hand;

/**
 * Evaluator for a generalized full house. The number of required cards is equal
 * to the hand size. If the hand size is an odd number n, then there must be (n
 * / 2) + 1 cards of the matching rank and the remaining (n / 2) cards must be
 * of matching rank. In this case, when constructing a hand, the larger group
 * must be listed first even if of lower rank than the smaller group</strong>
 * (e.g. as [3 3 3 5 5] rather than [5 5 3 3 3]). If the hand size is even, then
 * half the cards must be of matching rank and the remaining half of matching
 * rank. Any group of cards, all of which are the same rank, always satisfies
 * this evaluator.
 * 
 * The name of this evaluator is "Full House".
 *  @author Tara M
 */
public class FullHouseEvaluator extends AbstractEvaluator {
	/**
	 * Constructs the evaluator.
	 * 
	 * @param ranking  ranking of this hand
	 * @param handSize number of cards in a hand
	 */
	public FullHouseEvaluator(int ranking, int handSize) {
		super(ranking, handSize, "Full House", handSize);

	}

	@Override
	public boolean canSatisfy(Card[] mainCards) {

		boolean answer=false;
		if (mainCards.length != handSize()) {
			return false;
		}
		if (mainCards.length <= 2) {
			return true;
		} 
		int k = 0;
		int firstCount = 1;
		int secondCount = 0;
		Card first = mainCards[0];
		Card second = null;
		for (int i = 1; i < mainCards.length; i++) {
			k++;
			if (mainCards[i].compareToIgnoreSuit(first) != 0) {
				second = mainCards[i];
				break;
			}
		}

		for (int j = 1; j < mainCards.length; j++) {
			if (mainCards[j].compareToIgnoreSuit(first) == 0) {
				firstCount++;
			} else if (mainCards[j].compareToIgnoreSuit(second) == 0) {
				secondCount++;
			} else {
				return answer;
			}
		}

		if (handSize() % 2 == 1) {
			if (firstCount > secondCount) {
				if (handSize() / 2 + 1 == firstCount ||handSize()==firstCount) {
					return true;
				}
			} else if (firstCount < secondCount) {
				if (handSize() / 2 + 1 == secondCount) {
					return true;
				}

			}
		}

		else if (handSize() % 2 == 0) {
			if (firstCount == secondCount||handSize()==firstCount) {
				return true;
			}
		} else {
			return false;
		}

		return false;

	}


	@Override
	public Hand createHand(Card[] allCards, int[] subset) {
		Hand currentHand = super.createHand(allCards, subset);
		int currHandSize = handSize();
		if (handSize() % 2 == 1 && currentHand != null) {
			Card[] currMainCards = currentHand.getMainCards();
			if (currMainCards[0].getRank() != currMainCards[handSize() / 2].getRank()) {
				Card[] temp = new Card[currMainCards.length];
				for (int i = 0; i < currMainCards.length / 2; i++) {
					temp[i + currMainCards.length / 2 + 1] = currMainCards[i];
					temp[i] = currMainCards[i + currMainCards.length / 2];
				}
				temp[currMainCards.length / 2] = currMainCards[handSize() - 1];
				return new Hand(temp, null, this);
			}
		} else {
			return currentHand;
		}

		return currentHand;

	}
}
