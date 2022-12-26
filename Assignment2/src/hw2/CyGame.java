package hw2;

/**
 * Model of a Monopoly-like game. Two players take turns rolling dice to move
 * around a board. The game ends when one of the players has at least
 * MONEY_TO_WIN money or one of the players goes bankrupt (has negative money).
 * 
 * @author TaraM
 */
public class CyGame {
	/**
	 * Do nothing square type.
	 */
	public static final int DO_NOTHING = 0;
	/**
	 * Pass go square type.
	 */
	public static final int PASS_GO = 1;
	/**
	 * Cyclone square type.
	 */
	public static final int CYCLONE = 2;
	/**
	 * Pay the other player square type.
	 */
	public static final int PAY_PLAYER = 3;
	/**
	 * Get an extra turn square type.
	 */
	public static final int EXTRA_TURN = 4;
	/**
	 * Jump forward square type.
	 */
	public static final int JUMP_FORWARD = 5;
	/**
	 * Stuck square type.
	 */
	public static final int STUCK = 6;
	/**
	 * Points awarded when landing on or passing over go.
	 */
	public static final int PASS_GO_PRIZE = 200;
	/**
	 * The amount payed to the other player per unit when landing on a PAY_PLAYER
	 * square.
	 */
	public static final int PAYMENT_PER_UNIT = 20;
	/**
	 * The amount of money required to win.
	 */
	public static final int MONEY_TO_WIN = 400;
	/**
	 * The cost of one unit.
	 */
	public static final int UNIT_COST = 50;
	/**
	 * Current Player(player 1 or 2) that is currently making a move
	 */
	private int currentPlayer;
	/**
	 * Current square player one is on.
	 */
	private int currentSquare1;
	/**
	 * Current square player two is on.
	 */
	private int currentSquare2;
	/**
	 * Current money player one has.
	 */
	private int currentMoney1;
	/**
	 * Current money player two has.
	 */
	private int currentMoney2;
	/**
	 * Amount of property player one has
	 */
	private int propertyUnits1;
	/**
	 * Amount of property player two has
	 */
	private int propertyUnits2;
	/**
	 * Number of squares in the board
	 */
	private int numSquares;
	/**
	 * Starting anount of money players have
	 */
	private int startingMoney;
	/**
	 * Represent player one being stuck.
	 */
	private boolean stuck1;
	/**
	 * Represent player two being stuck.
	 */
	private boolean stuck2;
	/**
	 * public constructor that constructs a new monopoly game with numSquares and
	 * startingMoney
	 * 
	 * @param takes in numSquares
	 * @param takes in startingMoney
	 */
	public CyGame(int numSquares, int startingMoney) {
		this.numSquares = numSquares;
		this.startingMoney = startingMoney;
		currentPlayer = 1;
		currentSquare1 = 0;
		currentSquare2 = 0;
		currentMoney1 = startingMoney;
		currentMoney2 = startingMoney;
		propertyUnits1 = 1;
		propertyUnits2 = 1;
	}
	/**
	 * This method is called to indicate the current player attempting to buy a unit
	 */
	public void buyUnit() {
		if (!isGameEnded()) {
			if (currentPlayer == 1) {
				if ((getSquareType(currentSquare1) == DO_NOTHING) && (currentMoney1 >= UNIT_COST)) {
					currentMoney1 -= UNIT_COST;
					propertyUnits1 += 1;
					endTurn();
				}
			} else {
				if ((getSquareType(currentSquare2) == DO_NOTHING) && (currentMoney2 >= UNIT_COST)) {
					currentMoney2 -= UNIT_COST;
					propertyUnits2 += 1;
					endTurn();
				}
			}
		}
	}

	/**
	 * Method called to indicate the current player passes or completes their turn
	 */
	public void endTurn() {
		if (currentPlayer == 1) {
			currentPlayer = 2;
		} else {
			currentPlayer = 1;
		}
	}
	/**
	 * Returns the current player
	 *
	 * @return the current player
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	/**
	 * Returns the player ones money
	 *
	 * @return the current money of player one
	 */
	public int getPlayer1Money() {
		return currentMoney1;
	}
	/**
	 * Returns the player one square
	 *
	 * @return the player one square
	 */
	public int getPlayer1Square() {
		return currentSquare1;
	}

	/**
	 * Returns the player one property units
	 *
	 * @return the amount of units player one has
	 */
	public int getPlayer1Units() {
		return propertyUnits1;
	}

	/**
	 * Returns the player two money
	 *
	 * @return the current money of player two
	 */
	public int getPlayer2Money() {
		return currentMoney2;
	}

	/**
	 * Returns the player two square
	 *
	 * @return the player two square
	 */
	public int getPlayer2Square() {
		return currentSquare2;
	}

	/**
	 * Returns the player two property units
	 *
	 * @return the amount of units player two has
	 */
	public int getPlayer2Units() {
		return propertyUnits2;
	}
	/**
	 * Gets the square type of the square they land on
	 *
	 * @param takes in the square number they are on
	 * @return the the type of square
	 */
	public int getSquareType(int square) {
		if (square == 0) {
			return PASS_GO;
		} else if (square == (numSquares - 1)) {
			return CYCLONE;
		} else if (square % 5 == 0) {
			return PAY_PLAYER;
		} else if ((square % 7 == 0) || (square % 11 == 0)) {
			return EXTRA_TURN;
		} else if (square % 3 == 0) {
			return STUCK;
		} else if (square % 2 == 0) {
			return JUMP_FORWARD;
		} else {
			return DO_NOTHING;
		}
	}

	/**
	 * Returns the state of the game whether it has ended or not
	 *
	 * @return whether if game ended is true or false
	 */
	public boolean isGameEnded() {
		return ((currentMoney1 >= MONEY_TO_WIN) || (currentMoney2 >= MONEY_TO_WIN) || (currentMoney1 < 0)
				|| (currentMoney2 < 0));
	}

	/**
	 * Method called to indicate whether the dice has been rolled and applies an action based on current player turn
	 * 
	 * @param takes in the value that the dice is rolled
	 */
	public void roll(int value) {
		int currentValue1 = 0;
		int currentValue2 = 0;
		if (!isGameEnded()) {
			if (getCurrentPlayer() == 1) {
				if ((getSquareType((currentSquare1 + value) % numSquares) == STUCK || (stuck1 == true))) {  
					if (((value % 2 == 0) || (stuck1 == false)) && (currentSquare1 + value >= numSquares) //checks if player passes go and makes sure player only gets payed once if they do
							&& (currentSquare1 + value != 0)) {
						currentMoney1 += PASS_GO_PRIZE;
					}
					if (value % 2 == 0) {
						currentSquare1 = (currentSquare1 + value) % numSquares;
						stuck1 = false;
						currentValue1 = getSquareType(currentSquare1);
						if (currentValue1 == PASS_GO) {
							currentMoney1 += PASS_GO_PRIZE;
							endTurn();
						} else if (currentValue1 == CYCLONE) {
							currentSquare1 = currentSquare2;
							endTurn();
						} else if (currentValue1 == PAY_PLAYER) {
							currentMoney1 -= ((PAYMENT_PER_UNIT * propertyUnits2));
							currentMoney2 += ((PAYMENT_PER_UNIT * propertyUnits2));
							endTurn();
						} else if (currentValue1 == EXTRA_TURN) {
							currentPlayer = 1;
						} else if (currentValue1 == JUMP_FORWARD) {
							if (currentSquare1 + 4 > numSquares) { //assigns player PASS_GO_PRIZE money if the current value of the square is larger than the number of squares in the board
								currentMoney1 += PASS_GO_PRIZE;
							}
							currentSquare1 = (currentSquare1 + 4) % numSquares;
							if (getSquareType(currentSquare1) == STUCK) { 
								stuck1 = true;
							}
							endTurn();
						} else {
							endTurn();
						}

					} else {
						if (stuck1 != true) {
							stuck1 = true;
							currentSquare1 = (currentSquare1 + value) % numSquares;
						}
						endTurn();
					}

				} else {
					if (currentSquare1 + value > numSquares
							&& getSquareType((currentSquare1 + value) % numSquares) != PASS_GO) {
						currentMoney1 += PASS_GO_PRIZE;
					}
					currentSquare1 = (currentSquare1 + value) % numSquares;
					currentValue1 = getSquareType(currentSquare1);

					if (currentValue1 == PASS_GO) {
						currentMoney1 += PASS_GO_PRIZE;
						endTurn();
					} else if (currentValue1 == CYCLONE) {
						currentSquare1 = currentSquare2;
						endTurn();
					} else if (currentValue1 == PAY_PLAYER) {
						currentMoney1 -= ((PAYMENT_PER_UNIT * propertyUnits2));
						currentMoney2 += ((PAYMENT_PER_UNIT * propertyUnits2));
						endTurn();
					} else if (currentValue1 == EXTRA_TURN) {
						currentPlayer = 1;
					} else if (currentValue1 == JUMP_FORWARD) {
						if (currentSquare1 + 4 > numSquares) {
							currentMoney1 += PASS_GO_PRIZE;
						}
						currentSquare1 = (currentSquare1 + 4) % numSquares;
						if (getSquareType(currentSquare1) == STUCK) {
							stuck1 = true;
						}
						endTurn();
					} else if (currentValue1 == DO_NOTHING) {
						endTurn();
					}
				}

			} else {
				if ((getSquareType((currentSquare2 + value) % numSquares) == STUCK || (stuck2 == true))) {
					if (value % 2 == 0) {
						currentSquare2 = (currentSquare2 + value) % numSquares;
						stuck2 = false;
						currentValue1 = getSquareType(currentSquare1);

						if (currentValue2 == PASS_GO) {
							currentMoney2 += PASS_GO_PRIZE;
							endTurn();
						} else if (currentValue2 == CYCLONE) {
							currentSquare2 = currentSquare1;
							endTurn();
						} else if (currentValue2 == PAY_PLAYER) {
							currentMoney2 -= ((PAYMENT_PER_UNIT * propertyUnits1));
							currentMoney1 += ((PAYMENT_PER_UNIT * propertyUnits1));
							endTurn();
						} else if (currentValue2 == EXTRA_TURN) {
							currentPlayer = 2;
						} else if (currentValue2 == JUMP_FORWARD) {
							if (currentSquare2 + 4 > numSquares) {
								currentMoney2 += PASS_GO_PRIZE;
							}
							currentSquare2 = (currentSquare2 + 4) % numSquares;
							if (getSquareType(currentSquare2) == STUCK) {
								stuck2 = true;
							}
							endTurn();
						} else if (currentValue2 == DO_NOTHING) {
							endTurn();
						}

					} else {
						if (stuck1 != true) {
							stuck1 = true;
							currentSquare1 = (currentSquare1 + value) % numSquares;
						}
						endTurn();
					}
				} else {
					if (currentSquare2 + value > numSquares
							&& getSquareType((currentSquare2 + value) % numSquares) != PASS_GO) {
						currentMoney2 += PASS_GO_PRIZE;
					}
					currentSquare2 = (currentSquare2 + value) % numSquares;
					currentValue2 = getSquareType(currentSquare2);

					if (currentValue2 == PASS_GO) {
						currentMoney2 += PASS_GO_PRIZE;
						endTurn();
					} else if (currentValue2 == CYCLONE) {
						currentSquare2 = currentSquare1;
						endTurn();
					} else if (currentValue2 == PAY_PLAYER) {
						currentMoney2 -= (PAYMENT_PER_UNIT * propertyUnits1);
						currentMoney1 += (PAYMENT_PER_UNIT * propertyUnits1);
						endTurn();
					} else if (currentValue2 == EXTRA_TURN) {
						currentPlayer = 2;
					} else if (currentValue2 == JUMP_FORWARD) {
						if (currentSquare2 + 4 > numSquares) {
							currentMoney2 += PASS_GO_PRIZE;
						}
						currentSquare2 = (currentSquare2 + 4) % numSquares;
						if (getSquareType(currentSquare2) == STUCK) {
							stuck2 = true;
						}
						endTurn();
					} else if (currentValue2 == DO_NOTHING) {
						endTurn();
					}
				}
			}
		}
	}

	/**
	 * This method is called to indicate the current player attempting to sell a
	 * unit
	 *
	 */
	public void sellUnit() {
		if (!isGameEnded()) {
			if (currentPlayer == 1) {
				if ((propertyUnits1 >= 1)) {
					propertyUnits1 -= 1;
					currentMoney1 += UNIT_COST;
					endTurn();
				}
			}
			else {
				if ((propertyUnits2 >= 1)) {
					propertyUnits2 -= 1;
					currentMoney2 += UNIT_COST;
					endTurn();
				}
			}
		}
	}
	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player 1*: (0, 0, $0) Player 2: (0, 0, $0)</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which players turn it is.
	 * The numbers (0, 0, $0) indicate which square the player is on, how many units
	 * the player has, and how much money the player has respectively.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString() {
		String fmt = "Player 1%s: (%d, %d, $%d) Player 2%s: (%d, %d, $%d)";
		String player1Turn = "";
		String player2Turn = "";
		if (getCurrentPlayer() == 1) {
			player1Turn = "*";
		} else {
			player2Turn = "*";
		}
		return String.format(fmt, player1Turn, getPlayer1Square(), getPlayer1Units(), getPlayer1Money(), player2Turn,
				getPlayer2Square(), getPlayer2Units(), getPlayer2Money());
	}
}
