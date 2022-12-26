package hw3;

import org.junit.Before;
import org.junit.Test;
import static api.CellType.*;
import static api.Orientation.*;
import static api.Direction.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import api.Cell;
import api.DescriptionUtil;
import api.Orientation;

import static org.junit.Assert.*;


public class Hw3JUnit {

	private static final Cell[][] testGrid1 = {
			{ new Cell(WALL, 0, 0), new Cell(WALL, 0, 1), new Cell(WALL, 0, 2), new Cell(WALL, 0, 3),
					new Cell(WALL, 0, 4) },
			{ new Cell(WALL, 1, 0), new Cell(FLOOR, 1, 1), new Cell(FLOOR, 1, 2), new Cell(FLOOR, 1, 3),
					new Cell(WALL, 1, 4) },
			{ new Cell(WALL, 2, 0), new Cell(FLOOR, 2, 1), new Cell(FLOOR, 2, 2), new Cell(FLOOR, 2, 3),
					new Cell(WALL, 2, 4) },
			{ new Cell(WALL, 3, 0), new Cell(FLOOR, 3, 1), new Cell(FLOOR, 3, 2), new Cell(FLOOR, 3, 3),
					new Cell(WALL, 3, 4) },
			{ new Cell(WALL, 4, 0), new Cell(FLOOR, 4, 1), new Cell(FLOOR, 4, 2), new Cell(FLOOR, 4, 3),
					new Cell(EXIT, 4, 4) },
			{ new Cell(WALL, 5, 0), new Cell(WALL, 5, 1), new Cell(WALL, 5, 2), new Cell(WALL, 5, 3),
					new Cell(WALL, 5, 4) } };

	private static ArrayList<Block> makeTest1Blocks() {
		ArrayList<Block> blocks = new ArrayList<Block>();
		blocks.add(new Block(4, 2, 2, HORIZONTAL));
		blocks.add(new Block(1, 2, 3, VERTICAL));
		return blocks;
	}

	@Before
	public void setup() // runs before every test case
	{
		board = new Board(testGrid1, makeTest1Blocks());
	}

	@Test
	public void testBlocklength() {
		String msg = "A block constucted with length 2 should have length =2";
		Block block = new Block(2, 1, 2, HORIZONTAL);
		assertEquals(msg, 2, block.getLength());
	}

	@Test
	public void testBlockRow() {
		String msg = "A block constucted on Row 2 should have getRow =2";
		Block block = new Block(2, 1, 2, HORIZONTAL);
		assertEquals(msg, 2, block.getFirstRow());
	}

	@Test
	public void testBlockCol() {
		String msg = "A block constucted on Col 1 should have getCol =1";
		Block block = new Block(2, 1, 2, HORIZONTAL);
		assertEquals(msg, 1, block.getFirstCol());
	}

	@Test
	public void testBlockORientation() {
		String msg = "A block constucted on Horizontally should have getOrienation = HORIZONTAL";
		Block block = new Block(2, 1, 2, HORIZONTAL);
		assertEquals(msg, Orientation.HORIZONTAL, block.getOrientation());
	}

	@Test
	public void testBlockMoveRight() {
		String msg = "A block constucted on Horizontally on col 1, after moving right, should be on col 2";
		Block block = new Block(2, 1, 2, HORIZONTAL);
		block.move(RIGHT);
		assertEquals(msg, 2, block.getFirstCol());
	}

	@Test
	public void testBlockMoveLeft() {
		String msg = "A block constucted on Horizontally on col 1, after moving left, should be on col 0";
		Block block = new Block(2, 1, 2, HORIZONTAL);
		block.move(LEFT);
		assertEquals(msg, 0, block.getFirstCol());
	}

	@Test
	public void testBlockMoveUP() {
		String msg = "A block constucted on Horizontally on row 2, after moving up, should be on row 2";
		Block block = new Block(2, 1, 2, HORIZONTAL);
		block.move(UP);
		assertEquals(msg, 2, block.getFirstRow());
	}

	@Test
	public void testBlockMoveDown() {
		String msg = "A block constucted on Horizontally on row 2, after moving down, should be on row 2";
		Block block = new Block(2, 1, 2, HORIZONTAL);
		block.move(DOWN);
		assertEquals(msg, 2, block.getFirstRow());
	}

	@Test
	public void testBlockMoveRight2() {
		String msg = "A block constucted on VERTICAL on col 1, after moving right, should be on VERTICAL 1";
		Block block = new Block(2, 1, 2, VERTICAL);
		block.move(RIGHT);
		assertEquals(msg, 1, block.getFirstCol());
	}

	@Test
	public void testBlockMoveLeft2() {
		String msg = "A block constucted on VERTICAL on col 1, after moving left, should be on col 1";
		Block block = new Block(2, 1, 2, VERTICAL);
		block.move(LEFT);
		assertEquals(msg, 1, block.getFirstCol());
	}

	@Test
	public void testBlockMoveUP2() {
		String msg = "A block constucted on VERTICAL on row 2, after moving up, should be on row 1";
		Block block = new Block(2, 1, 2, VERTICAL);
		block.move(UP);
		assertEquals(msg, 1, block.getFirstRow());
	}

	@Test
	public void testBlockMoveDown2() {
		String msg = "A block constucted on VERTICAL on row 2, after moving down, should be on row 3";
		Block block = new Block(2, 1, 2, VERTICAL);
		block.move(DOWN);
		assertEquals(msg, 3, block.getFirstRow());
	}

	@Test
	public void testBlockReset() {
		String msg = "A block constucted on Horizontally on col 1, after moving right twice and being recalled, should be on col 1";
		Block block = new Block(2, 1, 2, HORIZONTAL);
		block.move(RIGHT);
		block.move(RIGHT);
		block.reset();
		assertEquals(msg, 1, block.getFirstCol());
	}

	@Test
	public void testBlockSetFirstCol() {
		String msg = "A block constucted on Horizontally on col 1, after moving right twice and first col being set to 0, should be on col 0";
		Block block = new Block(2, 1, 2, HORIZONTAL);
		block.move(RIGHT);
		block.move(RIGHT);
		block.setFirstCol(0);
		assertEquals(msg, 0, block.getFirstCol());
	}

	@Test
	public void testBlockSetFirstROW() {
		String msg = "A block constucted on VERTICAL on row 2, after moving up twice and first row being set to 0, should be on row 0";
		Block block = new Block(2, 1, 2, VERTICAL);
		block.move(UP);
		block.move(DOWN);
		block.setFirstRow(0);
		assertEquals(msg, 0, block.getFirstRow());
	}

	@Test
	public void testGridUtilisExitFalse() {
		String msg = "Creating a grid with 0,0 being a wall, isExit should be false";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", ".", ".", "*" },
				{ "*", "[", "]", ".", "e" }, { "*", ".", ".", ".", "*" }, { "*", "*", "*", "*", "*" } };
		Cell[][] cells = GridUtil.createGrid(testDescription1);
		assertEquals(msg, false, cells[0][0].isExit());
	}

	@Test
	public void testGridUtilisExitTrue() {
		String msg = "Creating a grid with 1,4 being an exit, isExit should be true";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", ".", ".", "e" },
				{ "*", "[", "]", ".", "*" }, { "*", ".", ".", ".", "*" }, { "*", "*", "*", "*", "*" } };
		Cell[][] cells = GridUtil.createGrid(testDescription1);
		assertEquals(msg, true, cells[1][4].isExit());

	}

	@Test
	public void testGridUtilisFloorFalse() {
		String msg = "Creating a grid with 0,0 being a wall, isFloor should be false";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", ".", ".", "e" },
				{ "*", "[", "]", ".", "*" }, { "*", ".", ".", ".", "*" }, { "*", "*", "*", "*", "*" } };
		Cell[][] cells = GridUtil.createGrid(testDescription1);
		assertEquals(msg, false, cells[0][0].isFloor());

	}

	@Test
	public void testGridUtilisFloorTrue() {
		String msg = "Creating a grid with 1,2 being a floor, isFloor should be true";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", ".", ".", "e" },
				{ "*", "[", "]", ".", "*" }, { "*", ".", ".", ".", "*" }, { "*", "*", "*", "*", "*" } };
		Cell[][] cells = GridUtil.createGrid(testDescription1);
		assertEquals(msg, true, cells[1][2].isFloor());

	}

	@Test
	public void testGridUtilisWallFalse() {
		String msg = "Creating a grid with 1,2 being a floor, isWall should be false";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", ".", ".", "e" },
				{ "*", "[", "]", ".", "*" }, { "*", ".", ".", ".", "*" }, { "*", "*", "*", "*", "*" } };
		Cell[][] cells = GridUtil.createGrid(testDescription1);
		assertEquals(msg, false, cells[1][2].isWall());

	}

	@Test
	public void testGridUtilisWallTrue() {
		String msg = "Creating a grid with 0,0 being a Wall, isWall should be true";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", ".", ".", "e" },
				{ "*", "[", "]", ".", "*" }, { "*", ".", ".", ".", "*" }, { "*", "*", "*", "*", "*" } };
		Cell[][] cells = GridUtil.createGrid(testDescription1);
		assertEquals(msg, true, cells[0][0].isWall());

	}

	@Test
	public void testGridUtilBlockisWall1() {
		String msg = "Creating a grid with 1,2 being a ^, celltype should be floor";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", "^", ".", "e" },
				{ "*", ".", "#", ".", "*" }, { "*", ".", "v", ".", "*" }, { "*", ".", "[", "]", "*" },
				{ "*", "*", "*", "*", "*" } };
		Cell[][] cells = GridUtil.createGrid(testDescription1);
		assertEquals(msg, true, cells[1][2].isFloor());

	}

	@Test
	public void testGridUtilBlockisWall2() {
		String msg = "Creating a grid with 2,2 being a #, celltype should be floor";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", "^", ".", "e" },
				{ "*", ".", "#", ".", "*" }, { "*", ".", "v", ".", "*" }, { "*", ".", "[", "]", "*" },
				{ "*", "*", "*", "*", "*" } };
		Cell[][] cells = GridUtil.createGrid(testDescription1);
		assertEquals(msg, true, cells[2][2].isFloor());

	}

	@Test
	public void testGridUtilBlockisWall3() {
		String msg = "Creating a grid with 3,2 being a v, celltype should be floor";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", "^", ".", "e" },
				{ "*", ".", "#", ".", "*" }, { "*", ".", "v", ".", "*" }, { "*", ".", "[", "]", "*" },
				{ "*", "*", "*", "*", "*" } };
		Cell[][] cells = GridUtil.createGrid(testDescription1);
		assertEquals(msg, true, cells[3][2].isFloor());

	}

	@Test
	public void testGridUtilBlockisWall4() {
		String msg = "Creating a grid with 4,2 being a [, celltype should be floor";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", "^", ".", "e" },
				{ "*", ".", "#", ".", "*" }, { "*", ".", "v", ".", "*" }, { "*", ".", "[", "]", "*" },
				{ "*", "*", "*", "*", "*" } };
		Cell[][] cells = GridUtil.createGrid(testDescription1);
		assertEquals(msg, true, cells[4][2].isFloor());

	}

	@Test
	public void testGridUtilBlockisWall5() {
		String msg = "Creating a grid with 4,3 being a ], celltype should be floor";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", "^", ".", "e" },
				{ "*", ".", "#", ".", "*" }, { "*", ".", "v", ".", "*" }, { "*", ".", "[", "]", "*" },
				{ "*", "*", "*", "*", "*" } };
		Cell[][] cells = GridUtil.createGrid(testDescription1);
		assertEquals(msg, true, cells[4][3].isFloor());

	}

	@Test
	public void testGridUtilFindBlocks1() {
		String msg = "Creating a grid with 1 blocks, findblocks should have size 1";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", ".", ".", "e" },
				{ "*", "[", "]", ".", "*" }, { "*", ".", ".", ".", "*" }, { "*", "*", "*", "*", "*" } };
		ArrayList<Block> blocks = GridUtil.findBlocks(testDescription1);
		assertEquals(msg, 1, blocks.size());

	}

	@Test
	public void testGridUtilFindBlocks0() {
		String msg = "Creating a grid with 0 blocks, findblocks should have size 0";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", ".", ".", "e" },
				{ "*", ".", ".", ".", "*" }, { "*", ".", ".", ".", "*" }, { "*", "*", "*", "*", "*" } };
		ArrayList<Block> blocks = GridUtil.findBlocks(testDescription1);
		assertEquals(msg, 0, blocks.size());

	}

	@Test
	public void testGridUtilFindBlocks2() {
		String msg = "Creating a grid with 2 blocks, findblocks should have size 2";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", "^", ".", "e" },
				{ "*", ".", "#", ".", "*" }, { "*", ".", "v", ".", "*" }, { "*", ".", "[", "]", "*" },
				{ "*", "*", "*", "*", "*" } };
		ArrayList<Block> blocks = GridUtil.findBlocks(testDescription1);
		assertEquals(msg, 2, blocks.size());

	}

	public void testGridUtilFindBlocks3() {
		String msg = "Creating a grid with 1 blocks, findblocks should have size 1";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", ".", ".", "e" },
				{ "*", "[", "#", "]", "*" }, { "*", ".", ".", ".", "*" }, { "*", "*", "*", "*", "*" } };
		ArrayList<Block> blocks = GridUtil.findBlocks(testDescription1);
		assertEquals(msg, 1, blocks.size());

	}

	@Test
	public void testGridUtilFindBlockslength2() {
		String msg = "Creating a grid with 1 blocks with length 2, blocks.get(0).getLength() should have length 2";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", ".", ".", "e" },
				{ "*", "[", "]", ".", "*" }, { "*", ".", ".", ".", "*" }, { "*", "*", "*", "*", "*" } };
		ArrayList<Block> blocks = GridUtil.findBlocks(testDescription1);
		assertEquals(msg, 2, blocks.get(0).getLength());
	}

	@Test
	public void testGridUtilFindBlockslength3() {
		String msg = "Creating a grid with 1 blocks with length 3, blocks.get(0).getLength() should have length 2";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", ".", ".", "e" },
				{ "*", "[", "#", "]", "*" }, { "*", ".", ".", ".", "*" }, { "*", "*", "*", "*", "*" } };
		ArrayList<Block> blocks = GridUtil.findBlocks(testDescription1);
		assertEquals(msg, 3, blocks.get(0).getLength());

	}

	@Test
	public void testGridUtilFindBlockslength() {
		String msg = "Creating a grid with 2 blocks with different lengths, blocks.get(0).getLength() should have length 3";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", "^", ".", "e" },
				{ "*", ".", "#", ".", "*" }, { "*", ".", "v", ".", "*" }, { "*", ".", "[", "]", "*" },
				{ "*", "*", "*", "*", "*" } };
		ArrayList<Block> blocks = GridUtil.findBlocks(testDescription1);
		assertEquals(msg, 3, blocks.get(0).getLength());
	}

	@Test
	public void testGridUtilFindBlockslength1() {
		String msg = "Creating a grid with 2 blocks with different lengths, blocks.get(1).getLength() should have length 2";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", "^", ".", "e" },
				{ "*", ".", "#", ".", "*" }, { "*", ".", "v", ".", "*" }, { "*", ".", "[", "]", "*" },
				{ "*", "*", "*", "*", "*" } };
		ArrayList<Block> blocks = GridUtil.findBlocks(testDescription1);
		assertEquals(msg, 2, blocks.get(1).getLength());
	}

	@Test
	public void testGridUtilFindBlocksOrientation() {
		String msg = "Creating a grid with 2 blocks with different lengths, blocks.get(0).getOrientation() should be vertical";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", "^", ".", "e" },
				{ "*", ".", "#", ".", "*" }, { "*", ".", "v", ".", "*" }, { "*", ".", "[", "]", "*" },
				{ "*", "*", "*", "*", "*" } };
		ArrayList<Block> blocks = GridUtil.findBlocks(testDescription1);
		assertEquals(msg, VERTICAL, blocks.get(0).getOrientation());
	}

	@Test
	public void testGridUtilFindBlocksOrientation2() {
		String msg = "Creating a grid with 2 blocks with different lengths, blocks.get(1).getOrientation() should be HORIZONTAL";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", "^", ".", "e" },
				{ "*", ".", "#", ".", "*" }, { "*", ".", "v", ".", "*" }, { "*", ".", "[", "]", "*" },
				{ "*", "*", "*", "*", "*" } };
		ArrayList<Block> blocks = GridUtil.findBlocks(testDescription1);
		assertEquals(msg, HORIZONTAL, blocks.get(1).getOrientation());
	}

	@Test
	public void testGridUtilFindBlocksgetFirstCol() {
		String msg = "Creating a grid with 2 blocks with different lengths, blocks.get(0).getfirstcol() should have column of 2";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", "^", ".", "e" },
				{ "*", ".", "#", ".", "*" }, { "*", ".", "v", ".", "*" }, { "*", ".", "[", "]", "*" },
				{ "*", "*", "*", "*", "*" } };
		ArrayList<Block> blocks = GridUtil.findBlocks(testDescription1);
		assertEquals(msg, 2, blocks.get(0).getFirstCol());
	}

	@Test
	public void testGridUtilFindBlocksgetFirstRow() {
		String msg = "Creating a grid with 2 blocks with different lengths, blocks.get(0).getfirstrow() should have row of 1";
		String[][] testDescription1 = { { "*", "*", "*", "*", "*" }, { "*", ".", "^", ".", "e" },
				{ "*", ".", "#", ".", "*" }, { "*", ".", "v", ".", "*" }, { "*", ".", "[", "]", "*" },
				{ "*", "*", "*", "*", "*" } };
		ArrayList<Block> blocks = GridUtil.findBlocks(testDescription1);
		assertEquals(msg, 1, blocks.get(0).getFirstRow());
	}

	private Board board;

	@Test
	public void testBoard() {
		// System.out.println(board.toString());
	}

	@Test
	public void testBoardGrabBlockOnBlock() {
		String msg = "Grabbing Block at 2,2, should grabbed 1,2,3Vertical Block";
		board.grabBlockAtCell(2, 2);
		String str = "(row=1, col=2, len=3, ori=VERTICAL)";
		assertEquals(msg, true, str.equals(board.getGrabbedBlock().toString()));
	}

	@Test
	public void testBoardGrabBlockOnNoBlock() {
		String msg = "Grabbing Block at 1,1, should grabbed no block";
		board.grabBlockAtCell(1, 1);
		assertEquals(msg, null, board.getGrabbedBlock());
	}

	@Test
	public void testBoardGrabCellCol() {
		String msg = "Grabbing Block at 3,2, should grabbed CEll 3,2";
		board.grabBlockAtCell(3, 2);
		assertEquals(msg, 2, board.getGrabbedCell().getCol());
	}

	@Test
	public void testBoardGrabCellRow() {
		String msg = "Grabbing Block at 3,2, should grabbed CEll 3,2";
		board.grabBlockAtCell(3, 2);
		assertEquals(msg, 3, board.getGrabbedCell().getRow());
	}

	@Test
	public void testBoardGrabVertBlockMoveRight() {
		board.reset();
		String msg = "Grabbing Block at 2,2, and moving it right should do nothing";
		board.grabBlockAtCell(2, 2);
		board.moveGrabbedBlock(RIGHT);
		assertEquals(msg, 2, board.getGrabbedBlock().getFirstCol());

	}

	@Test
	public void testBoardGrabHorizontalBlockMoveLeft() {
		board.reset();
		String msg = "Grabbing Block at 4,2, and moving it left should do move it left";
		board.grabBlockAtCell(4, 2);
		board.moveGrabbedBlock(LEFT);
		assertEquals(msg, 1, board.getGrabbedBlock().getFirstCol());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveLeftNoMove() {
		board.reset();
		String msg = "Grabbing Block at 4,2, and moving it left twice should do move it left";
		board.grabBlockAtCell(4, 2);
		board.moveGrabbedBlock(LEFT);
		board.moveGrabbedBlock(LEFT);
		assertEquals(msg, 1, board.getGrabbedBlock().getFirstCol());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveLeftEnd() {
		board.reset();
		String msg = "Grabbing Block at 4,2, and moving it left should do move it left, and end piece should be in correct spot";
		board.grabBlockAtCell(4, 2);
		board.moveGrabbedBlock(LEFT);
		assertEquals(msg, 2, board.getGrabbedBlock().getFirstCol() + board.getGrabbedBlock().getLength() - 1);
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveLeftEndOld() {
		board.reset();
		String msg = "Grabbing Block at 4,2, and moving it left should do move it left, and end piece should be in correct spot";
		board.grabBlockAtCell(4, 2);
		board.moveGrabbedBlock(LEFT);
		assertEquals(
				msg, null, board
						.getCell(board.getGrabbedBlock().getFirstRow(),
								(board.getGrabbedBlock().getFirstCol() + board.getGrabbedBlock().getLength()))
						.getBlock());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveRight() {
		board.reset();
		String msg = "Grabbing Block at 3,2, and moving it right should do move it right";
		board.grabBlockAtCell(4, 2);
		board.moveGrabbedBlock(LEFT);
		board.moveGrabbedBlock(RIGHT);
		assertEquals(msg, 2, board.getGrabbedBlock().getFirstCol());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveRightEnd() {
		board.reset();
		String msg = "Grabbing Block at 4,2, and moving it left should do move it left, and end piece should be in correct spot";
		board.grabBlockAtCell(4, 2);
		board.moveGrabbedBlock(LEFT);
		board.moveGrabbedBlock(RIGHT);
		assertEquals(msg, 3, board.getGrabbedBlock().getFirstCol() + board.getGrabbedBlock().getLength() - 1);
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveRightEndOld() {
		board.reset();
		String msg = "Grabbing Block at 4,2, and moving it left should do move it left, and end piece should be in correct spot";
		board.grabBlockAtCell(4, 2);
		board.moveGrabbedBlock(LEFT);
		board.moveGrabbedBlock(RIGHT);
		assertEquals(msg, null,
				board.getCell(board.getGrabbedBlock().getFirstRow(), (board.getGrabbedBlock().getFirstCol() - 1))
						.getBlock());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveDownNoMove() {
		board.reset();
		String msg = "Grabbing Block at 2,2, and moving it up should do move it down";
		board.grabBlockAtCell(2, 2);
		board.moveGrabbedBlock(DOWN);
		assertEquals(msg, 1, board.getGrabbedBlock().getFirstRow());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveDown() {
		Cell[][] testGrid2 = {
				{ new Cell(WALL, 0, 0), new Cell(WALL, 0, 1), new Cell(WALL, 0, 2), new Cell(WALL, 0, 3),
						new Cell(WALL, 0, 4) },
				{ new Cell(WALL, 1, 0), new Cell(FLOOR, 1, 1), new Cell(FLOOR, 1, 2), new Cell(FLOOR, 1, 3),
						new Cell(WALL, 1, 4) },
				{ new Cell(WALL, 2, 0), new Cell(FLOOR, 2, 1), new Cell(FLOOR, 2, 2), new Cell(FLOOR, 2, 3),
						new Cell(EXIT, 2, 4) },
				{ new Cell(WALL, 3, 0), new Cell(FLOOR, 3, 1), new Cell(FLOOR, 3, 2), new Cell(FLOOR, 3, 3),
						new Cell(WALL, 3, 4) },
				{ new Cell(WALL, 4, 0), new Cell(FLOOR, 4, 1), new Cell(FLOOR, 4, 2), new Cell(FLOOR, 4, 3),
						new Cell(WALL, 4, 4) },
				{ new Cell(WALL, 5, 0), new Cell(WALL, 5, 1), new Cell(WALL, 5, 2), new Cell(WALL, 5, 3),
						new Cell(WALL, 5, 4) } };
		ArrayList<Block> blocks2 = new ArrayList<Block>();
		blocks2.add(new Block(1, 2, 3, VERTICAL));
		Board board2 = new Board(testGrid2, blocks2);
		String msg = "Grabbing Block at 2,2, and moving it up should do move it dwn";
		board2.grabBlockAtCell(2, 2);
		board2.moveGrabbedBlock(DOWN);
		assertEquals(msg, 2, board2.getGrabbedBlock().getFirstRow());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveDownEnd() {
		Cell[][] testGrid2 = {
				{ new Cell(WALL, 0, 0), new Cell(WALL, 0, 1), new Cell(WALL, 0, 2), new Cell(WALL, 0, 3),
						new Cell(WALL, 0, 4) },
				{ new Cell(WALL, 1, 0), new Cell(FLOOR, 1, 1), new Cell(FLOOR, 1, 2), new Cell(FLOOR, 1, 3),
						new Cell(WALL, 1, 4) },
				{ new Cell(WALL, 2, 0), new Cell(FLOOR, 2, 1), new Cell(FLOOR, 2, 2), new Cell(FLOOR, 2, 3),
						new Cell(EXIT, 2, 4) },
				{ new Cell(WALL, 3, 0), new Cell(FLOOR, 3, 1), new Cell(FLOOR, 3, 2), new Cell(FLOOR, 3, 3),
						new Cell(WALL, 3, 4) },
				{ new Cell(WALL, 4, 0), new Cell(FLOOR, 4, 1), new Cell(FLOOR, 4, 2), new Cell(FLOOR, 4, 3),
						new Cell(WALL, 4, 4) },
				{ new Cell(WALL, 5, 0), new Cell(WALL, 5, 1), new Cell(WALL, 5, 2), new Cell(WALL, 5, 3),
						new Cell(WALL, 5, 4) } };
		ArrayList<Block> blocks2 = new ArrayList<Block>();
		blocks2.add(new Block(1, 2, 3, VERTICAL));
		Board board2 = new Board(testGrid2, blocks2);
		String msg = "Grabbing Block at 2,2, and moving it up should do move it down";
		board2.grabBlockAtCell(2, 2);
		board2.moveGrabbedBlock(DOWN);
		assertEquals(msg, 4, board2.getGrabbedBlock().getFirstRow() + board2.getGrabbedBlock().getLength() - 1);
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveDownEndOld() {
		Cell[][] testGrid2 = {
				{ new Cell(WALL, 0, 0), new Cell(WALL, 0, 1), new Cell(WALL, 0, 2), new Cell(WALL, 0, 3),
						new Cell(WALL, 0, 4) },
				{ new Cell(WALL, 1, 0), new Cell(FLOOR, 1, 1), new Cell(FLOOR, 1, 2), new Cell(FLOOR, 1, 3),
						new Cell(WALL, 1, 4) },
				{ new Cell(WALL, 2, 0), new Cell(FLOOR, 2, 1), new Cell(FLOOR, 2, 2), new Cell(FLOOR, 2, 3),
						new Cell(EXIT, 2, 4) },
				{ new Cell(WALL, 3, 0), new Cell(FLOOR, 3, 1), new Cell(FLOOR, 3, 2), new Cell(FLOOR, 3, 3),
						new Cell(WALL, 3, 4) },
				{ new Cell(WALL, 4, 0), new Cell(FLOOR, 4, 1), new Cell(FLOOR, 4, 2), new Cell(FLOOR, 4, 3),
						new Cell(WALL, 4, 4) },
				{ new Cell(WALL, 5, 0), new Cell(WALL, 5, 1), new Cell(WALL, 5, 2), new Cell(WALL, 5, 3),
						new Cell(WALL, 5, 4) } };
		ArrayList<Block> blocks2 = new ArrayList<Block>();
		blocks2.add(new Block(1, 2, 3, VERTICAL));
		Board board2 = new Board(testGrid2, blocks2);
		String msg = "Grabbing Block at 2,2, and moving it up should do move it down";
		board2.grabBlockAtCell(2, 2);
		board2.moveGrabbedBlock(DOWN);
		assertEquals(msg, null,
				board2.getCell(board2.getGrabbedBlock().getFirstRow() - 1, (board2.getGrabbedBlock().getFirstCol()))
						.getBlock());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveUpNoMove() {
		board.reset();
		String msg = "Grabbing Block at 2,2, and moving it up should do move it down";
		board.grabBlockAtCell(2, 2);
		board.moveGrabbedBlock(UP);
		assertEquals(msg, 1, board.getGrabbedBlock().getFirstRow());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveUP() {
		Cell[][] testGrid2 = {
				{ new Cell(WALL, 0, 0), new Cell(WALL, 0, 1), new Cell(WALL, 0, 2), new Cell(WALL, 0, 3),
						new Cell(WALL, 0, 4) },
				{ new Cell(WALL, 1, 0), new Cell(FLOOR, 1, 1), new Cell(FLOOR, 1, 2), new Cell(FLOOR, 1, 3),
						new Cell(WALL, 1, 4) },
				{ new Cell(WALL, 2, 0), new Cell(FLOOR, 2, 1), new Cell(FLOOR, 2, 2), new Cell(FLOOR, 2, 3),
						new Cell(EXIT, 2, 4) },
				{ new Cell(WALL, 3, 0), new Cell(FLOOR, 3, 1), new Cell(FLOOR, 3, 2), new Cell(FLOOR, 3, 3),
						new Cell(WALL, 3, 4) },
				{ new Cell(WALL, 4, 0), new Cell(FLOOR, 4, 1), new Cell(FLOOR, 4, 2), new Cell(FLOOR, 4, 3),
						new Cell(WALL, 4, 4) },
				{ new Cell(WALL, 5, 0), new Cell(WALL, 5, 1), new Cell(WALL, 5, 2), new Cell(WALL, 5, 3),
						new Cell(WALL, 5, 4) } };
		ArrayList<Block> blocks2 = new ArrayList<Block>();
		blocks2.add(new Block(1, 2, 3, VERTICAL));
		Board board2 = new Board(testGrid2, blocks2);
		String msg = "Grabbing Block at 2,2, and moving it up should do move it dwn";
		board2.grabBlockAtCell(2, 2);
		board2.moveGrabbedBlock(DOWN);
		board2.moveGrabbedBlock(UP);
		assertEquals(msg, 1, board2.getGrabbedBlock().getFirstRow());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveUPEnd() {
		Cell[][] testGrid2 = {
				{ new Cell(WALL, 0, 0), new Cell(WALL, 0, 1), new Cell(WALL, 0, 2), new Cell(WALL, 0, 3),
						new Cell(WALL, 0, 4) },
				{ new Cell(WALL, 1, 0), new Cell(FLOOR, 1, 1), new Cell(FLOOR, 1, 2), new Cell(FLOOR, 1, 3),
						new Cell(WALL, 1, 4) },
				{ new Cell(WALL, 2, 0), new Cell(FLOOR, 2, 1), new Cell(FLOOR, 2, 2), new Cell(FLOOR, 2, 3),
						new Cell(EXIT, 2, 4) },
				{ new Cell(WALL, 3, 0), new Cell(FLOOR, 3, 1), new Cell(FLOOR, 3, 2), new Cell(FLOOR, 3, 3),
						new Cell(WALL, 3, 4) },
				{ new Cell(WALL, 4, 0), new Cell(FLOOR, 4, 1), new Cell(FLOOR, 4, 2), new Cell(FLOOR, 4, 3),
						new Cell(WALL, 4, 4) },
				{ new Cell(WALL, 5, 0), new Cell(WALL, 5, 1), new Cell(WALL, 5, 2), new Cell(WALL, 5, 3),
						new Cell(WALL, 5, 4) } };
		ArrayList<Block> blocks2 = new ArrayList<Block>();
		blocks2.add(new Block(1, 2, 3, VERTICAL));
		Board board2 = new Board(testGrid2, blocks2);
		String msg = "Grabbing Block at 2,2, and moving it up should do move it down";
		board2.grabBlockAtCell(2, 2);
		board2.moveGrabbedBlock(DOWN);
		board2.moveGrabbedBlock(UP);
		assertEquals(msg, 3, board2.getGrabbedBlock().getFirstRow() + board2.getGrabbedBlock().getLength() - 1);
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveUPEndOld() {
		Cell[][] testGrid2 = {
				{ new Cell(WALL, 0, 0), new Cell(WALL, 0, 1), new Cell(WALL, 0, 2), new Cell(WALL, 0, 3),
						new Cell(WALL, 0, 4) },
				{ new Cell(WALL, 1, 0), new Cell(FLOOR, 1, 1), new Cell(FLOOR, 1, 2), new Cell(FLOOR, 1, 3),
						new Cell(WALL, 1, 4) },
				{ new Cell(WALL, 2, 0), new Cell(FLOOR, 2, 1), new Cell(FLOOR, 2, 2), new Cell(FLOOR, 2, 3),
						new Cell(EXIT, 2, 4) },
				{ new Cell(WALL, 3, 0), new Cell(FLOOR, 3, 1), new Cell(FLOOR, 3, 2), new Cell(FLOOR, 3, 3),
						new Cell(WALL, 3, 4) },
				{ new Cell(WALL, 4, 0), new Cell(FLOOR, 4, 1), new Cell(FLOOR, 4, 2), new Cell(FLOOR, 4, 3),
						new Cell(WALL, 4, 4) },
				{ new Cell(WALL, 5, 0), new Cell(WALL, 5, 1), new Cell(WALL, 5, 2), new Cell(WALL, 5, 3),
						new Cell(WALL, 5, 4) } };
		ArrayList<Block> blocks2 = new ArrayList<Block>();
		blocks2.add(new Block(1, 2, 3, VERTICAL));
		Board board2 = new Board(testGrid2, blocks2);
		String msg = "Grabbing Block at 2,2, and moving it up should do move it down";
		board2.grabBlockAtCell(2, 2);
		board2.moveGrabbedBlock(DOWN);
		board2.moveGrabbedBlock(UP);
		assertEquals(msg, null,
				board2.getCell(board2.getGrabbedBlock().getFirstRow() - 1, (board2.getGrabbedBlock().getFirstCol()))
						.getBlock());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveRightEND() {
		board.reset();
		String msg = "Grabbing Block at 3,2, and moving it right should not end the game";
		board.grabBlockAtCell(4, 2);
		board.moveGrabbedBlock(LEFT);
		board.moveGrabbedBlock(RIGHT);
		assertEquals(msg, false, board.isGameOver());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveRightTwiceEND() {
		board.reset();
		String msg = "Grabbing Block at 3,2, and moving it right twice should end the game";
		board.grabBlockAtCell(4, 2);
		board.moveGrabbedBlock(LEFT);
		board.moveGrabbedBlock(RIGHT);
		board.moveGrabbedBlock(RIGHT);
		assertEquals(msg, true, board.isGameOver());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveRightTwiceENDThenMoveLeft() {
		board.reset();
		String msg = "Grabbing Block at 3,2, and moving it right twice should end the game, moving left should do nothing";
		board.grabBlockAtCell(4, 2);
		board.moveGrabbedBlock(LEFT);
		board.moveGrabbedBlock(RIGHT);
		board.moveGrabbedBlock(RIGHT);
		board.moveGrabbedBlock(LEFT);
		assertEquals(msg, 3, board.getBlocks().get(0).getFirstCol());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveRightReset() {
		board.reset();
		String msg = "Grabbing Block at 3,2, and moving it right then reset, should be at start position";
		board.moveGrabbedBlock(LEFT);
		board.moveGrabbedBlock(RIGHT);
		board.moveGrabbedBlock(RIGHT);
		board.reset();
		assertEquals(msg, 2, board.getBlocks().get(0).getFirstCol());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveRightThanUP() {
		board.reset();
		String msg = "Grabbing Block at 3,2, and moving it right then up, up should do nothing";
		board.moveGrabbedBlock(LEFT);
		board.moveGrabbedBlock(UP);
		assertEquals(msg, 4, board.getBlocks().get(0).getFirstRow());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveLeftRelease() {
		board.reset();
		String msg = "Grabbing Block at 3,2, and moving it right and realeasing it, no block should be grabbed";
		board.grabBlockAtCell(4, 2);
		board.moveGrabbedBlock(LEFT);
		board.releaseBlock();
		assertEquals(msg, null, board.getGrabbedBlock());

	}

	@Test
	public void testBoardGrabHorizontalBlockMoveLeftThanRight() {
		board.reset();
		String msg = "Move left than right, move count should be 2";
		board.grabBlockAtCell(4, 2);
		board.moveGrabbedBlock(LEFT);
		board.moveGrabbedBlock(RIGHT);
		assertEquals(msg, 2, board.getMoveCount());
	}

	@Test
	public void testBoardGrabHorizontalBlockNoMoveCount() {
		board.reset();
		String msg = "No move, move count should be 0";
		assertEquals(msg, 0, board.getMoveCount());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveLeftCount() {
		board.reset();
		String msg = "Move left, move count should be 1";
		board.grabBlockAtCell(4, 2);
		board.moveGrabbedBlock(LEFT);
		assertEquals(msg, 1, board.getMoveCount());
	}

	@Test
	public void testBoardGetRowSize() {
		board.reset();
		String msg = "Creating a board with 6 row, get row size should be 6";
		assertEquals(msg, 6, board.getRowSize());
	}

	@Test
	public void testBoardGetColSize() {
		board.reset();
		String msg = "Creating a board with 5 Col, get col size should be 5";
		assertEquals(msg, 5, board.getColSize());
	}

	@Test
	public void testBoardGetCell() {
		board.reset();
		String msg = "Creating a board with ,getting Cell(0,0), should be wall";
		String str1 = "*";
		assertEquals(msg, true, str1.equals(board.getCell(0, 0).toString()));
	}

	@Test
	public void testBoardGetBlocksA() {
		board.reset();
		String msg = "Creating a board with ,getting blocks should be matching";
		String str1 = "(row=1, col=2, len=3, ori=VERTICAL)";
		assertEquals(msg, true, str1.equals(board.getBlocks().get(1).toString()));
	}

	@Test
	public void testBoardGetBlocksB() {
		board.reset();
		String msg = "Creating a board with ,getting  blocks should be matching";
		String str1 = "(row=4, col=2, len=2, ori=HORIZONTAL)";
		assertEquals(msg, true, str1.equals(board.getBlocks().get(0).toString()));
	}

	@Test
	public void testBoardGetBlocksNoBlocks() {
		Cell[][] testGrid2 = {
				{ new Cell(WALL, 0, 0), new Cell(WALL, 0, 1), new Cell(WALL, 0, 2), new Cell(WALL, 0, 3),
						new Cell(WALL, 0, 4) },
				{ new Cell(WALL, 1, 0), new Cell(FLOOR, 1, 1), new Cell(FLOOR, 1, 2), new Cell(FLOOR, 1, 3),
						new Cell(WALL, 1, 4) },
				{ new Cell(WALL, 2, 0), new Cell(FLOOR, 2, 1), new Cell(FLOOR, 2, 2), new Cell(FLOOR, 2, 3),
						new Cell(EXIT, 2, 4) },
				{ new Cell(WALL, 3, 0), new Cell(FLOOR, 3, 1), new Cell(FLOOR, 3, 2), new Cell(FLOOR, 3, 3),
						new Cell(WALL, 3, 4) },
				{ new Cell(WALL, 4, 0), new Cell(FLOOR, 4, 1), new Cell(FLOOR, 4, 2), new Cell(FLOOR, 4, 3),
						new Cell(WALL, 4, 4) },
				{ new Cell(WALL, 5, 0), new Cell(WALL, 5, 1), new Cell(WALL, 5, 2), new Cell(WALL, 5, 3),
						new Cell(WALL, 5, 4) } };
		ArrayList<Block> blocks2 = new ArrayList<Block>();
		Board board2 = new Board(testGrid2, blocks2);
		String msg = "Creating a board with no blocks, get blocks should be nothing";
		assertEquals(msg, 0, board2.getBlocks().size());
	}

	@Test
	public void testBoardMoveWithoutGrab() {
		board.reset();
		String msg = "Move left without grabbing, move count should be 0";
		board.moveGrabbedBlock(LEFT);
		assertEquals(msg, 0, board.getMoveCount());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveLeftThanRightHistory() {
		board.reset();
		String msg = "Testing moveHistory";
		board.grabBlockAtCell(4, 2);
		board.moveGrabbedBlock(LEFT);
		board.moveGrabbedBlock(RIGHT);
		String str1 = "(4, 2) one cell RIGHT";
		assertEquals(msg, true, str1.equals(board.getMoveHistory().get(1).toString()));
	}

	@Test
	public void testBoardGrabHorizontalBlockNoMoveCountHistory() {
		board.reset();
		String msg = "Testing moveHistory";
		assertEquals(msg, 0, board.getMoveHistory().size());
	}

	@Test
	public void testBoardGrabHorizontalBlockMoveLeftCountHistory() {
		board.reset();
		String msg = "Testing moveHistory";
		board.grabBlockAtCell(4, 2);
		board.moveGrabbedBlock(LEFT);
		String str1 = "(4, 1) one cell LEFT";
		assertEquals(msg, true, str1.equals(board.getMoveHistory().get(0).toString()));
	}

	@Test
	public void testBoardCanPlaceBlockOnWall() {
		board.reset();
		String msg = "Testing canPlaceBlock on Grid(0,0), a wall";
		assertEquals(msg, false, board.canPlaceBlock(0, 0));
	}

	@Test
	public void testBoardCanPlaceBlockOnFloor() {
		board.reset();
		String msg = "Testing canPlaceBlock on Grid(1,1), a floor without a block";
		assertEquals(msg, true, board.canPlaceBlock(1, 1));
	}

	@Test
	public void testBoardCanPlaceBlockOnExit() {
		board.reset();
		String msg = "Testing canPlaceBlock on Grid(4,4), the exit";
		assertEquals(msg, true, board.canPlaceBlock(4, 4));
	}

	@Test
	public void testBoardCanPlaceBlockOnBlock() {
		board.reset();
		String msg = "Testing canPlaceBlock on Grid(2,2), the exit";
		assertEquals(msg, false, board.canPlaceBlock(2, 2));
	}

	@Test
	public void testBoardNoBlocksAllPossibleMoves() {
		Cell[][] testGrid2 = {
				{ new Cell(WALL, 0, 0), new Cell(WALL, 0, 1), new Cell(WALL, 0, 2), new Cell(WALL, 0, 3),
						new Cell(WALL, 0, 4) },
				{ new Cell(WALL, 1, 0), new Cell(FLOOR, 1, 1), new Cell(FLOOR, 1, 2), new Cell(FLOOR, 1, 3),
						new Cell(WALL, 1, 4) },
				{ new Cell(WALL, 2, 0), new Cell(FLOOR, 2, 1), new Cell(FLOOR, 2, 2), new Cell(FLOOR, 2, 3),
						new Cell(EXIT, 2, 4) },
				{ new Cell(WALL, 3, 0), new Cell(FLOOR, 3, 1), new Cell(FLOOR, 3, 2), new Cell(FLOOR, 3, 3),
						new Cell(WALL, 3, 4) },
				{ new Cell(WALL, 4, 0), new Cell(FLOOR, 4, 1), new Cell(FLOOR, 4, 2), new Cell(FLOOR, 4, 3),
						new Cell(WALL, 4, 4) },
				{ new Cell(WALL, 5, 0), new Cell(WALL, 5, 1), new Cell(WALL, 5, 2), new Cell(WALL, 5, 3),
						new Cell(WALL, 5, 4) } };
		ArrayList<Block> blocks2 = new ArrayList<Block>();
		Board board2 = new Board(testGrid2, blocks2);
		String msg = "Creating a board with no blocks, should have no moves";
		assertEquals(msg, 0, board2.getAllPossibleMoves().size());
	}

	@Test
	public void testBoardOneBlocksAllPossibleMoves() {
		board.reset();
		String msg = "Creating a board with 2 blocks, should have 2 moves";
		assertEquals(msg, 2, board.getAllPossibleMoves().size());
	}

	@Test
	public void testBoardOneBlocksAllPossibleMoves2() {
		board.reset();
		String msg = "Creating a board with 2 blocks, should have 2 moves";
		board.grabBlockAtCell(4, 2);
		board.moveGrabbedBlock(LEFT);
		assertEquals(msg, 1, board.getAllPossibleMoves().size());
	}

	@Test
	public void testBoard2BlocksAllPossibleMoves() {
		Cell[][] testGrid2 = {
				{ new Cell(WALL, 0, 0), new Cell(WALL, 0, 1), new Cell(WALL, 0, 2), new Cell(WALL, 0, 3),
						new Cell(WALL, 0, 4) },
				{ new Cell(WALL, 1, 0), new Cell(FLOOR, 1, 1), new Cell(FLOOR, 1, 2), new Cell(FLOOR, 1, 3),
						new Cell(WALL, 1, 4) },
				{ new Cell(WALL, 2, 0), new Cell(FLOOR, 2, 1), new Cell(FLOOR, 2, 2), new Cell(FLOOR, 2, 3),
						new Cell(EXIT, 2, 4) },
				{ new Cell(WALL, 3, 0), new Cell(FLOOR, 3, 1), new Cell(FLOOR, 3, 2), new Cell(FLOOR, 3, 3),
						new Cell(WALL, 3, 4) },
				{ new Cell(WALL, 4, 0), new Cell(FLOOR, 4, 1), new Cell(FLOOR, 4, 2), new Cell(FLOOR, 4, 3),
						new Cell(WALL, 4, 4) },
				{ new Cell(WALL, 5, 0), new Cell(WALL, 5, 1), new Cell(WALL, 5, 2), new Cell(WALL, 5, 3),
						new Cell(WALL, 5, 4) } };
		ArrayList<Block> blocks2 = new ArrayList<Block>();
		blocks2.add(new Block(2, 2, 2, VERTICAL));
		blocks2.add(new Block(2, 3, 2, VERTICAL));
		Board board2 = new Board(testGrid2, blocks2);
		String msg = "Creating a board with no blocks, should have 4 moves";
		assertEquals(msg, 4, board2.getAllPossibleMoves().size());
	}

	@Test
	public void testBoard4longBlockMove() {
		Cell[][] testGrid2 = {
				{ new Cell(WALL, 0, 0), new Cell(WALL, 0, 1), new Cell(WALL, 0, 2), new Cell(WALL, 0, 3),
						new Cell(WALL, 0, 4) },
				{ new Cell(WALL, 1, 0), new Cell(FLOOR, 1, 1), new Cell(FLOOR, 1, 2), new Cell(FLOOR, 1, 3),
						new Cell(WALL, 1, 4) },
				{ new Cell(WALL, 2, 0), new Cell(FLOOR, 2, 1), new Cell(FLOOR, 2, 2), new Cell(FLOOR, 2, 3),
						new Cell(EXIT, 2, 4) },
				{ new Cell(WALL, 3, 0), new Cell(FLOOR, 3, 1), new Cell(FLOOR, 3, 2), new Cell(FLOOR, 3, 3),
						new Cell(WALL, 3, 4) },
				{ new Cell(WALL, 4, 0), new Cell(FLOOR, 4, 1), new Cell(FLOOR, 4, 2), new Cell(FLOOR, 4, 3),
						new Cell(WALL, 4, 4) },
				{ new Cell(WALL, 5, 0), new Cell(FLOOR, 5, 1), new Cell(FLOOR, 5, 2), new Cell(FLOOR, 5, 3),
						new Cell(WALL, 5, 4) },
				{ new Cell(WALL, 6, 0), new Cell(WALL, 6, 1), new Cell(WALL, 6, 2), new Cell(WALL, 6, 3),
						new Cell(WALL, 6, 4) } };
		ArrayList<Block> blocks2 = new ArrayList<Block>();
		blocks2.add(new Block(2, 2, 4, VERTICAL));
		Board board2 = new Board(testGrid2, blocks2);
		board2.grabBlockAtCell(2, 2);
		board2.moveGrabbedBlock(UP);
		String msg = "Testing a 4 long block";
		assertEquals(msg, 1, board2.getGrabbedBlock().getFirstRow());
	}
	
	@Test
	public void testBoardGrabBlock2BlocksNextToEachOther() throws FileNotFoundException {
		ArrayList<String[][]> gameDescriptions = DescriptionUtil 
	            .readBoardDescriptionsFromFile("games.txt"); 
	    int boardIndex = 1; // change to select different board setup 
	    Board board2 = new Board(gameDescriptions.get(boardIndex)); 
		System.out.println(board2.toString());
		board2.grabBlockAtCell(2, 3);
		board2.moveGrabbedBlock(UP);
		System.out.println(board2.toString());
		board2.grabBlockAtCell(2, 3);
		board2.moveGrabbedBlock(UP);
		board2.grabBlockAtCell(3, 1);
		board2.moveGrabbedBlock(RIGHT);
		System.out.println(board2.toString());
		board2.grabBlockAtCell(3, 3);
		board2.moveGrabbedBlock(RIGHT);
		System.out.println(board2.toString());
		String msg = "Testing that moveGrabbedBlock when 2 blocks are touching only moves the correct one";
		assertEquals(msg, 4, board2.getGrabbedBlock().getFirstCol()+board2.getGrabbedBlock().getLength()-1);
	}
	
}
