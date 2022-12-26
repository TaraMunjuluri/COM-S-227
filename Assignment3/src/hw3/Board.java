package hw3;

import static api.Direction.*;
import static api.Orientation.*;

import java.util.ArrayList;

import javax.crypto.IllegalBlockSizeException;

import api.Cell;
import api.Direction;
import api.Move;
import api.Orientation;
import api.CellType;

/*
 * @author Tara Munjuluri
 */
/**
 * /** Represents a board in the Block Slider game. A board contains a 2D grid
 * of cells and a list of blocks that slide over the cells.
 */
public class Board {
	/**
	 * 2D array of cells, the indexes signify (row, column) with (0, 0) representing
	 * the upper-left corner of the board.
	 */
	private Cell[][] grid;

	/**
	 * A list of blocks that are positioned on the board.
	 */
	private ArrayList<Block> blocks;

	/**
	 * A list of moves that have been made in order to get to the current position
	 * of blocks on the board.
	 */
	private ArrayList<Move> moveHistory = new ArrayList<Move>();
	/**
	 * An object that signifies grabbing the block
	 */
	private Block GrabBlock;
	/**
	 * An object that signifies grabbing the cell
	 */
	private Cell GrabCell;
	/**
	 * Keeps track of the count
	 */
	private int moveCount;
	/**
	 * A boolean representing whether the game is over or not
	 */
	private boolean isGameOver;

	/**
	 * Constructs a new board from a given 2D array of cells and list of blocks. The
	 * cells of the grid should be updated to indicate which cells have blocks
	 * placed over them (i.e., setBlock() method of Cell). The move history should
	 * be initialized as empty.
	 * 
	 * @param grid   a 2D array of cells which is expected to be a rectangular shape
	 * @param blocks list of blocks already containing row-column position which
	 *               should be placed on the board
	 */
	public Board(Cell[][] grid, ArrayList<Block> blocks) {
		this.grid = grid;
		this.blocks = blocks;
		update();
		moveHistory.clear();
		moveCount = 0;
		isGameOver = false;
	}

	/**
	 * Constructs a new board from a given 2D array of String descriptions.
	 * <p>
	 * DO NOT MODIFY THIS CONSTRUCTOR
	 * 
	 * @param desc 2D array of descriptions
	 */
	public Board(String[][] desc) {
		this(GridUtil.createGrid(desc), GridUtil.findBlocks(desc));
	}

	/**
	 * Models the user grabbing a block over the given row and column. The purpose
	 * of grabbing a block is for the user to be able to drag the block to a new
	 * position, which is performed by calling move GrabbedBlock(). This method
	 * records two things: the block that has been grabbed and the cell at which it
	 * was grabbed.
	 * 
	 * @param row row to grab the block from
	 * @param col column to grab the block from
	 */
	public void grabBlockAtCell(int row, int col) {
		GrabBlock = grid[row][col].getBlock();
		GrabCell = grid[row][col];
	}

	/**
	 * Set the currently grabbed block to null.
	 */
	public void releaseBlock() {
		GrabBlock = null;
		GrabCell = null;
	}

	/**
	 * Returns the currently grabbed block.
	 * 
	 * @return the current block
	 */
	public Block getGrabbedBlock() {
		return GrabBlock;
	}

	/**
	 * Returns the currently grabbed cell.
	 * 
	 * @return the current cell
	 */
	public Cell getGrabbedCell() {
		return GrabCell;
	}

	/**
	 * Returns true if the cell at the given row and column is available for a block
	 * to be placed over it. Blocks can only be placed over floors and exits. A
	 * block cannot be placed over a cell that is occupied by another block.
	 * 
	 * @param row row location of the cell
	 * @param col column location of the cell
	 * @return true if the cell is available for a block, otherwise false
	 */
	public boolean canPlaceBlock(int row, int col) {
		if (grid[row][col].hasBlock()) {
			return false;
		}
		if (((!grid[row][col].isFloor())) && ((!grid[row][col].isExit()))) {
			return false;
		}
		if (row >= grid.length || col >= grid[0].length) {
			return false;
		}
		return true;

	}

	/**
	 * Returns the number of moves made so far in the game.
	 * 
	 * @return the number of moves
	 */
	public int getMoveCount() {
		// TODO
		return moveCount;
	}

	/**
	 * Returns the number of rows of the board.
	 * 
	 * @return number of rows
	 */
	public int getRowSize() {
		// TODO
		return grid.length;
	}

	/**
	 * Returns the number of columns of the board.
	 * 
	 * @return number of columns
	 */
	public int getColSize() {
		// TODO
		return grid[0].length;
	}

	/**
	 * Returns the cell located at a given row and column.
	 * 
	 * @param row the given row
	 * @param col the given column
	 * @return the cell at the specified location
	 */
	public Cell getCell(int row, int col) {
		Cell result = grid[row][col];
		return result;
	}

	/**
	 * Returns a list of all blocks on the board.
	 * 
	 * @return a list of all blocks
	 */
	public ArrayList<Block> getBlocks() {
		// TODO
		return blocks;
	}

	/**
	 * Returns true if the player has completed the puzzle by positioning a block
	 * over an exit, false otherwise.
	 * 
	 * @return true if the game is over
	 */
	public boolean isGameOver() {
		return isGameOver;
	}

	/**
	 * Moves the currently grabbed block by one cell in the given direction. A
	 * horizontal block is only allowed to move right and left and a vertical block
	 * is only allowed to move up and down. A block can only move over a cell that
	 * is a floor or exit and is not already occupied by another block. The method
	 * does nothing under any of the following conditions:
	 * <ul>
	 * <li>The game is over.</li>
	 * <li>No block is currently grabbed by the user.</li>
	 * <li>A block is currently grabbed by the user, but the block is not allowed to
	 * move in the given direction.</li>
	 * </ul>
	 * If none of the above conditions are meet, the method does the following:
	 * <ul>
	 * <li>Moves the block object by calling its move method.</li>
	 * <li>Sets the block for the grid cell that the block is being moved into.</li>
	 * <li>For the grid cell that the block is being moved out of, sets the block to
	 * null.</li>
	 * <li>Moves the currently grabbed cell by one cell in the same moved direction.
	 * The purpose of this is to make the currently grabbed cell move with the block
	 * as it is being dragged by the user.</li>
	 * <li>Adds the move to the end of the moveHistory list.</li>
	 * <li>Increment the count of total moves made in the game.</li>
	 * </ul>
	 * 
	 * @param dir the direction to move
	 */
	public void moveGrabbedBlock(Direction dir) {
		if (isGameOver() == false) {
			if (!(GrabBlock == null)) {

				GrabCell.setBlock(GrabBlock);
				if (GrabBlock.getOrientation() == Orientation.VERTICAL) {
					if ((dir == UP) && (canPlaceBlock(GrabBlock.getFirstRow() - 1, GrabBlock.getFirstCol()))) {
						grid[GrabBlock.getFirstRow() - 1][GrabBlock.getFirstCol()].setBlock(GrabBlock);
						grid[GrabBlock.getFirstRow() + GrabBlock.getLength() - 1][GrabBlock.getFirstCol()].clearBlock();
						GrabCell = grid[GrabCell.getRow() - 1][GrabCell.getCol()];
						GrabBlock.move(dir);
						moveHistory.add(new Move(GrabBlock, UP));
						moveCount++;
						if (grid[GrabBlock.getFirstRow()][GrabBlock.getFirstCol()].isExit()) {
							isGameOver = true;
						}

					}

					else if ((dir == DOWN) && (canPlaceBlock(GrabBlock.getFirstRow() + GrabBlock.getLength(),
							GrabBlock.getFirstCol()))) {
						grid[GrabBlock.getFirstRow() + GrabBlock.getLength()][GrabBlock.getFirstCol()]
								.setBlock(GrabBlock);
						grid[GrabBlock.getFirstRow()][GrabBlock.getFirstCol()].clearBlock();
						GrabCell = grid[GrabCell.getRow() + 1][GrabCell.getCol()];
						GrabBlock.move(dir);
						moveHistory.add(new Move(GrabBlock, DOWN));
						moveCount++;
						if (grid[GrabBlock.getFirstRow() + GrabBlock.getLength() - 1][GrabBlock.getFirstCol()]
								.isExit()) {
							isGameOver = true;
						}
					}

				} else if (GrabBlock.getOrientation() == Orientation.HORIZONTAL) {
					if ((dir == LEFT) && (canPlaceBlock(GrabBlock.getFirstRow(), GrabBlock.getFirstCol() - 1))) {
						grid[GrabBlock.getFirstRow()][GrabBlock.getFirstCol() - 1].setBlock(GrabBlock);
						grid[GrabBlock.getFirstRow()][GrabBlock.getFirstCol() + GrabBlock.getLength() - 1].clearBlock();
						GrabCell = grid[GrabCell.getRow()][GrabCell.getCol() - 1];
						GrabBlock.move(dir);
						moveHistory.add(new Move(GrabBlock, LEFT));
						moveCount++;
						if (grid[GrabBlock.getFirstRow()][GrabBlock.getFirstCol()].isExit()) {
							isGameOver = true;
						}

					} else if ((dir == RIGHT) && (canPlaceBlock(GrabBlock.getFirstRow(),
							GrabBlock.getFirstCol() + GrabBlock.getLength()))) {
						grid[GrabBlock.getFirstRow()][GrabBlock.getFirstCol() + GrabBlock.getLength()]
								.setBlock(GrabBlock);
						grid[GrabBlock.getFirstRow()][GrabBlock.getFirstCol()].clearBlock();
						GrabCell = grid[GrabCell.getRow()][GrabCell.getCol() + 1];
						GrabBlock.move(dir);
						moveHistory.add(new Move(GrabBlock, RIGHT));
						moveCount++;
						if (grid[GrabBlock.getFirstRow()][GrabBlock.getFirstCol() + GrabBlock.getLength() - 1]
								.isExit()) {

							isGameOver = true;

						}

					}

				}

			}

		}
	}

	/**
	 * Resets the state of the game back to the start, which includes the move
	 * count, the move history, and whether the game is over. The method calls the
	 * reset method of each block object. It also updates each grid cells by calling
	 * their setBlock method to either set a block if one is located over the cell
	 * or set null if no block is located over the cell.
	 */
	public void reset() {
		isGameOver = false;
		for (int i = 0; i < blocks.size(); i++) {
			blocks.get(i).reset();

		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j].clearBlock();
			}
		}

		moveCount = 0;
		moveHistory.clear();
		new Board(grid, blocks);
		isGameOver = false;
		update();

	}

	/**
	 * Returns a list of all legal moves that can be made by any block on the
	 * current board. If the game is over there are no legal moves.
	 * 
	 * @return a list of legal moves
	 */
	public ArrayList<Move> getAllPossibleMoves() {

		ArrayList<Move> possibleMoves = new ArrayList<>();
		if (isGameOver) {
			return possibleMoves;
		}

		for (int i = 0; i < blocks.size(); i++) {
			if (blocks.get(i).getOrientation() == HORIZONTAL) {
				if (canPlaceBlock(blocks.get(i).getFirstRow(), blocks.get(i).getFirstCol() - 1) == true) {
					Move newMove = new Move(blocks.get(i), LEFT);
					possibleMoves.add(newMove);
				}
				if (canPlaceBlock(blocks.get(i).getFirstRow(),
						blocks.get(i).getFirstCol() + blocks.get(i).getLength()) == true) {
					Move newMove = new Move(blocks.get(i), RIGHT);
					possibleMoves.add(newMove);
				}
			} else if (blocks.get(i).getOrientation() == VERTICAL) {
				if (canPlaceBlock(blocks.get(i).getFirstRow() - 1, blocks.get(i).getFirstCol()) == true) {
					Move newMove = new Move(blocks.get(i), UP);
					possibleMoves.add(newMove);
				}
				if (canPlaceBlock(blocks.get(i).getFirstRow() + blocks.get(i).getLength(),
						blocks.get(i).getFirstCol()) == true) {
					Move newMove = new Move(blocks.get(i), DOWN);
					possibleMoves.add(newMove);
				}
			}
		}
		return possibleMoves;
	}

	/**
	 * Gets the list of all moves performed to get to the current position on the
	 * board.
	 * 
	 * @return a list of moves performed to get to the current position
	 */
	public ArrayList<Move> getMoveHistory() {
		return moveHistory;
	}

	/**
	 * EXTRA CREDIT 5 POINTS
	 * <p>
	 * This method is only used by the Solver.
	 * <p>
	 * Undo the previous move. The method gets the last move on the moveHistory list
	 * and performs the opposite actions of that move, which are the following:
	 * <ul>
	 * <li>grabs the moved block and calls moveGrabbedBlock passing the opposite
	 * direction</li>
	 * <li>decreases the total move count by two to undo the effect of calling
	 * moveGrabbedBlock twice</li>
	 * <li>if required, sets is game over to false</li>
	 * <li>removes the move from the moveHistory list</li>
	 * </ul>
	 * If the moveHistory list is empty this method does nothing.
	 */
	public void undoMove() {
		if (moveHistory.size() > 0) {
			GrabBlock = moveHistory.get(moveHistory.size() - 1).getBlock();
			Direction dir = moveHistory.get(moveHistory.size() - 1).getDirection();
			if (dir == UP) {
				moveGrabbedBlock(DOWN);
			} else if (dir == DOWN) {
				moveGrabbedBlock(UP);
			} else if (dir == LEFT) {
				moveGrabbedBlock(RIGHT);
			} else {
				moveGrabbedBlock(LEFT);
			}
			moveHistory.remove(moveHistory.size() - 1);
			moveHistory.remove(moveHistory.size() - 1);
			moveCount -= 2;
		}
	}

	/*
	 * Updates the grid and goes through the entire board
	 */
	private void update() {
		for (int i = 0; i < blocks.size(); i++) {
			for (int row = 0; row < getRowSize(); row++) {
				for (int col = 0; col < getColSize(); col++) {
					if (blocks.get(i).getFirstCol() == col && blocks.get(i).getFirstRow() == row) {
						grid[row][col].setBlock(blocks.get(i));
						int n = blocks.get(i).getLength();
						while (n > 1) {
							if (blocks.get(i).getOrientation() == HORIZONTAL) {
								grid[row][++col].setBlock(blocks.get(i));
								n--;
							} else {
								if (blocks.get(i).getOrientation() == VERTICAL) {
									grid[++row][col].setBlock(blocks.get(i));
									n--;
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		boolean first = true;
		for (Cell row[] : grid) {
			if (!first) {
				buff.append("\n");
			} else {
				first = false;
			}
			for (Cell cell : row) {
				buff.append(cell.toString());
				buff.append(" ");
			}
		}
		return buff.toString();
	}
}
