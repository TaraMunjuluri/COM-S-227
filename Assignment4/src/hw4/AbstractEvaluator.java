package hw4;

import java.util.ArrayList;
import java.util.Arrays;

import api.Card;
import api.Hand;
import api.IEvaluator;
import api.Suit;
import util.SubsetFinder;
/**
 * 
 * The class AbstractEvaluator includes common code for all evaluator types.
 * 
 * TODO: Expand this comment with an explanation of how your class hierarchy is
 * organized.
 * 
 * The abstract evaluator is organized by the variables that are being passed in through all methods
 * The abstract evaluator also consists of the getBestHand method, createHand method, canSubsetSatisfy method which is being used 
 * across all classes 
 *
 * @author Tara M

 */
public abstract class AbstractEvaluator implements IEvaluator {
	/*
	 * Represents the ranking of the card
	 */
	private int ranking;
	/*
	 * Represents the hand size of the set of cards
	 */
	private int handSize;
	/*
	 * Represents the name of the evaluator
	 */
	private String name;
	/*
	 * Represents the number of cards that are required
	 */
	private int cardsRequired;

	protected AbstractEvaluator(int ranking, int handSize, String name, int cardsRequired) {
		this.ranking = ranking;
		this.handSize = handSize;
		this.name = name;
		this.cardsRequired = cardsRequired;

	}
	/*
	 * Gets the ranking of the card
	 * @return ranking
	 */
	@Override
	public int getRanking() {
		return ranking;
	}
	/*
	 * Returns the handsize
	 * @return hand size
	 */
	@Override
	public int handSize() {
		return handSize;
	}
	/*
	 * Returns the name
	 * @return ranking
	 */
	@Override
	public String getName() {
		return name;
	}
	/*
	 * Returns the cardsRequired
	 * @return cardsRequired
	 */
	@Override
	public int cardsRequired() {
		return cardsRequired;
	}
	/*
	 * Gets the best hand from the cards
	 * @param allCards
	 * @return the best hand 
	 */
	@Override
	public Hand getBestHand(Card[] allCards) {
		Hand max = null;
		Arrays.sort(allCards);
		ArrayList<Hand> tempList = new ArrayList<Hand>();
		if (allCards.length >= handSize()) {
			ArrayList<int[]> findPairs = SubsetFinder.findSubsets(allCards.length, cardsRequired());
			ArrayList<Hand> notNull = new ArrayList<Hand>();
			if (allCards.length >= cardsRequired()) {
				for (int i = 0; i < findPairs.size(); i++) {

					Hand hand = createHand(allCards, findPairs.get(i));
					tempList.add(hand);
				}
				for (int k = 0; k < tempList.size(); k++) {
					if (tempList.get(k) != null) {
						notNull.add(tempList.get(k));
					}
				}
				for (int j = 0; j < notNull.size(); j++) {
					// ((notNull.get(j).compareTo(notNull.get(j + 1)) < 0)
					if ((max == null) || notNull.get(j).compareTo(max) < 0) {
						max = notNull.get(j);
					}

				}

			}

		}
		return max;

	}
	/*
	 * Creates the hand from all the cards
	 * @param allCards
	 * @param subset
	 * @return hand
	 */
	@Override
	public Hand createHand(Card[] allCards, int[] subset) {
		int j = 0;
		int k = 0;
		Arrays.sort(subset);
		if (allCards.length < handSize() && subset.length != cardsRequired()) {
			return null;

		} else {
			Card[] myMainCards = new Card[cardsRequired()];
			Card[] mySideCards = new Card[allCards.length - myMainCards.length];

			for (int i = 0; i < subset.length; i++) {
				myMainCards[i] = allCards[subset[i]];

			}
			for (int i = 0; i < allCards.length; i++) {
				if (k < subset.length && subset[k] == i) {
					k++;
					continue;

				}
				mySideCards[j] = allCards[i];
				j++;

			}

			
			if (canSatisfy(myMainCards)) {
				return new Hand(myMainCards, mySideCards, this);
			} else {
				return null;
			}
		}

	
	}
	@Override
	/*
	 * Returns whether or not the subset can be satisfied
	 * @param allCards
	 * @return a boolean value
	 */
	public boolean canSubsetSatisfy(Card[] allCards) {
		int i = 0;
		int j = 0;
		ArrayList<int[]> findPairs = SubsetFinder.findSubsets(allCards.length, cardsRequired());
		if (allCards.length >= cardsRequired()) {
			for (i = 0; i < findPairs.size(); i++) {
				int temp = 0;
				Card[] card1 = new Card[cardsRequired()];
				for (j = 0; j < findPairs.get(i).length; j++) {

					card1[temp] = new Card(allCards[findPairs.get(i)[j]].getRank(), Suit.CLUBS);
					temp++;
				}
				if (canSatisfy(card1)) {
					return true;
				}
			}

		}

		return false;
	}

}
