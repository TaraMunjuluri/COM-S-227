package hw3;

import static api.Direction.*;

import api.Direction;
import api.Orientation;

/*
 * @author Tara Munjuluri
 */
/**
 * Represents a block in the Block Slider game.
 */
public class Block {
	/*
	 * represents first row
	 */
	private int firstRow;
	/*
	 * represents first column
	 */
	private int firstCol;
	/*
	 * represents length
	 */
	private int length;
	/*
	 * represents either the vertical or horizontal orientation
	 */
	private Orientation orientation;
	/*
	 * represents the original location of the first row
	 */
	private int og_firstRow;
	/*
	 * represents the original location of the first column
	 */
	private int og_firstCol;
	/*
	 * represents the original length
	 */
	private int og_length;
	/*
	 * represents the original location of the first row
	 */
	private Orientation orig_orientation;

	/**
	 * Constructs a new Block with a specific location relative to the board. The
	 * upper/left most corner of the block is given as firstRow and firstCol. All
	 * blocks are only one cell wide. The length of the block is specified in cells.
	 * The block can either be horizontal or vertical on the board as specified by
	 * orientation.
	 * 
	 * @param firstRow    the first row that contains the block
	 * @param firstCol    the first column that contains the block
	 * @param length      block length in cells
	 * @param orientation either HORIZONTAL or VERTICAL
	 */
	public Block(int firstRow, int firstCol, int length, Orientation orientation) {
		this.firstRow = firstRow;
		this.firstCol = firstCol;
		this.length = length;
		this.orientation = orientation;

		og_firstRow = firstRow;
		og_firstCol = firstCol;
		og_length = length;
		orig_orientation = orientation;

	}

	/**
	 * Resets the position of the block to the original firstRow and firstCol values
	 * that were passed to the constructor during initialization of the the block.
	 */
	public void reset() {
		this.firstRow = og_firstRow;
		this.firstCol = og_firstCol;
		this.length = length;
		this.orientation = orig_orientation;

	}

	/**
	 * Move the blocks position by one cell in the direction specified. The blocks
	 * first column and row should be updated. The method will only move VERTICAL
	 * blocks UP or DOWN and HORIZONTAL blocks RIGHT or LEFT. Invalid movements are
	 * ignored.
	 * 
	 * @param dir direction to move (UP, DOWN, RIGHT, or LEFT)
	 */
	public void move(Direction dir) {
		switch (dir) {
		case UP:
			if (orientation == Orientation.VERTICAL) {
				firstRow = firstRow - 1;
			}
			break;

		case DOWN:
			if (orientation == orientation.VERTICAL) {
				firstRow = firstRow + 1;
			}
			break;
		case RIGHT:
			if (orientation == orientation.HORIZONTAL) {
				firstCol = firstCol + 1;
			}
			break;
		case LEFT:
			if (orientation == orientation.HORIZONTAL) {
				firstCol = firstCol - 1;
			}
			break;
		}
	}

	/**
	 * Gets the first row of the block on the board.
	 * 
	 * @return first row
	 */
	public int getFirstRow() {
		// TODO
		return firstRow;
	}

	/**
	 * Sets the first row of the block on the board.
	 * 
	 * @param firstRow first row
	 */
	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	/**
	 * Gets the first column of the block on the board.
	 * 
	 * @return first column
	 */
	public int getFirstCol() {
		// TODO
		return firstCol;
	}

	/**
	 * Sets the first column of the block on the board.
	 * 
	 * @param firstCol first column
	 */
	public void setFirstCol(int firstCol) {
		this.firstCol = firstCol;
	}

	/**
	 * Gets the length of the block.
	 * 
	 * @return length measured in cells
	 */
	public int getLength() {
		// TODO
		return length;
	}

	/**
	 * Gets the orientation of the block.
	 * 
	 * @return either VERTICAL or HORIZONTAL
	 */
	public Orientation getOrientation() {
		// TODO
		return orientation;
	}

	@Override
	public String toString() {
		return "(row=" + getFirstRow() + ", col=" + getFirstCol() + ", len=" + getLength() + ", ori=" + getOrientation()
				+ ")";
	}
}
