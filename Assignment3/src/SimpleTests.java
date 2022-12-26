import static api.CellType.*;
import static api.Orientation.*;
import static api.Direction.*;

import java.util.ArrayList;
import java.util.Arrays;

import api.Cell;
import api.Move;
import hw3.Block;
import hw3.Board;
import hw3.GridUtil;
//change
public class SimpleTests {
public static final String[][] testDescription1 =
{ { "*", "*", "*", "*", "*" },
 { "*", ".", ".", ".", "*" },
 { "*", "[", "]", ".", "e" },
 { "*", ".", ".", ".", "*" },
 { "*", "*", "*", "*", "*" } };
public static final String[][] testDescription2 =
{ { "*", "*", "*", "*", "*", "*", "*", "*" },
 { "*", "[", "]", "^", "^", ".", ".", "*" },
 { "*", ".", ".", "v", "v", "[", "]", "*" },
 { "*", "[", "#", "#", "]", "^", ".", "*" },
 { "*", "^", "[", "]", ".", "v", "^", "e" },
 { "*", "#", "^", ".", "[", "]", "#", "*" },
 { "*", "#", "v", ".", "[", "]", "v", "*" },
 { "*", "v", "[", "#", "#", "#", "]", "*" },
 { "*", "*", "*", "*", "*", "*", "*", "*" }};

// private static final Cell[][] testGrid1 = {
// { new Cell(WALL, 0, 0), new Cell(WALL, 0, 1), new Cell(WALL, 0, 2), new Cell(WALL, 0, 3), new Cell(WALL, 0, 4) },
// { new Cell(WALL, 1, 0), new Cell(FLOOR, 1, 1), new Cell(FLOOR, 1, 2), new Cell(FLOOR, 1, 3), new Cell(WALL, 1, 4) },
// { new Cell(WALL, 2, 0), new Cell(FLOOR, 2, 1), new Cell(FLOOR, 2, 2), new Cell(FLOOR, 2, 3), new Cell(EXIT, 2, 4) },
// { new Cell(WALL, 3, 0), new Cell(FLOOR, 3, 1), new Cell(FLOOR, 3, 2), new Cell(FLOOR, 3, 3), new Cell(WALL, 3, 4) },
// { new Cell(WALL, 4, 0), new Cell(WALL, 4, 1), new Cell(WALL, 4, 2), new Cell(WALL, 4, 3), new Cell(WALL, 4, 4) } };
private static final Cell[][] testGrid1 = {
{ new Cell(WALL, 0, 0), new Cell(WALL, 0, 1), new Cell(WALL, 0, 2), new Cell(WALL, 0, 3), new Cell(WALL, 0, 4), new Cell(WALL, 0, 5), new Cell(WALL, 0, 6), },
{ new Cell(WALL, 1, 0), new Cell(FLOOR, 1, 1), new Cell(FLOOR, 1, 2), new Cell(FLOOR, 1, 3), new Cell(FLOOR, 1, 4), new Cell(FLOOR, 1, 5), new Cell(WALL, 1, 6) },
{ new Cell(WALL, 2, 0), new Cell(FLOOR, 2, 1), new Cell(FLOOR, 2, 2), new Cell(FLOOR, 2, 3), new Cell(FLOOR, 2, 4), new Cell(FLOOR, 2, 5), new Cell(EXIT, 2, 6) },
{ new Cell(WALL, 3, 0), new Cell(FLOOR, 3, 1), new Cell(FLOOR, 3, 2), new Cell(FLOOR, 3, 3), new Cell(FLOOR, 3, 4), new Cell(FLOOR, 3, 5), new Cell(WALL, 3, 6) },
{ new Cell(WALL, 4, 0), new Cell(FLOOR, 4, 1), new Cell(FLOOR, 4, 2), new Cell(FLOOR, 4, 3), new Cell(FLOOR, 4, 4), new Cell(FLOOR, 4, 5), new Cell(WALL, 4, 6) },
{ new Cell(WALL, 5, 0), new Cell(FLOOR, 5, 1), new Cell(FLOOR, 5, 2), new Cell(FLOOR, 5, 3), new Cell(FLOOR, 5, 4), new Cell(FLOOR, 5, 5), new Cell(WALL, 5, 6) },
{ new Cell(WALL, 6, 0), new Cell(FLOOR, 6, 1), new Cell(FLOOR, 6, 2), new Cell(FLOOR, 6, 3), new Cell(FLOOR, 6, 4), new Cell(FLOOR, 6, 5), new Cell(WALL, 6, 6) },
{ new Cell(WALL, 7, 0), new Cell(WALL, 7, 1), new Cell(WALL, 7, 2), new Cell(WALL, 7, 3), new Cell(WALL, 7, 4), new Cell(WALL, 7, 5), new Cell(WALL, 7, 6) } };

// private static final Cell[][] testGrid2 = {
// { new Cell(WALL, 0, 0), new Cell(WALL, 0, 1), new Cell(WALL, 0, 2), new Cell(WALL, 0, 3), new Cell(WALL, 0, 4) , new Cell(WALL, 0, 4), new Cell(WALL, 0, 4), new Cell(WALL, 0, 4)},
// { new Cell(WALL, 1, 0), new Cell(FLOOR, 1, 1), new Cell(FLOOR, 1, 2), new Cell(FLOOR, 1, 3), new Cell(FLOOR, 1, 4), new Cell(FLOOR, 1, 5), new Cell(FLOOR, 1, 6), new Cell(WALL, 1, 7)},
// { new Cell(WALL, 2, 0), new Cell(FLOOR, 2, 1), new Cell(FLOOR, 2, 2), new Cell(FLOOR, 2, 3), new Cell(FLOOR, 2, 4), new Cell(FLOOR, 2, 5), new Cell(FLOOR, 2, 6), new Cell(WALL, 2, 7)},
// { new Cell(WALL, 3, 0), new Cell(FLOOR, 3, 1), new Cell(FLOOR, 3, 2), new Cell(FLOOR, 3, 3), new Cell(FLOOR, 3, 4), new Cell(FLOOR, 3, 5), new Cell(FLOOR, 3, 6), new Cell(WALL, 3, 7)},
// { new Cell(WALL, 4, 0), new Cell(FLOOR, 4, 1), new Cell(FLOOR, 4, 2), new Cell(FLOOR, 4, 3), new Cell(FLOOR, 4, 4), new Cell(FLOOR, 4, 5), new Cell(FLOOR, 4, 6), new Cell(EXIT, 4, 7)},
// { new Cell(WALL, 5, 0), new Cell(FLOOR, 5, 1), new Cell(FLOOR, 5, 2), new Cell(FLOOR, 5, 3), new Cell(FLOOR, 5, 4), new Cell(FLOOR, 5, 5), new Cell(FLOOR, 5, 6), new Cell(WALL, 5, 7)},
// { new Cell(WALL, 6, 0), new Cell(FLOOR, 6, 1), new Cell(FLOOR, 6, 2), new Cell(FLOOR, 6, 3), new Cell(FLOOR, 6, 4), new Cell(FLOOR, 6, 5), new Cell(FLOOR, 6, 6), new Cell(WALL, 6, 7)},
// { new Cell(WALL, 7, 0), new Cell(FLOOR, 7, 1), new Cell(FLOOR, 7, 2), new Cell(FLOOR, 7, 3), new Cell(FLOOR, 7, 4), new Cell(FLOOR, 7, 5), new Cell(FLOOR, 7, 6), new Cell(WALL, 7, 7)},
// { new Cell(WALL, 8, 0), new Cell(WALL, 8, 1), new Cell(WALL, 8, 2), new Cell(WALL, 8, 3), new Cell(WALL, 8, 4), new Cell(WALL, 8, 5), new Cell(WALL, 8, 6), new Cell(WALL, 8, 7)} };
//
//

private static ArrayList<Block> makeTest1Blocks() {
ArrayList<Block> blocks = new ArrayList<Block>();
blocks.add(new Block(2, 1, 3, VERTICAL));
return blocks;
}

// private static ArrayList<Block> makeTest1Blocks() {
// ArrayList<Block> blocks = new ArrayList<Block>();
// blocks.add(new Block(2, 1, 2, HORIZONTAL));
// return blocks;
// }
//
// private static ArrayList<Block> makeTest2Blocks() {
// ArrayList<Block> blocks = new ArrayList<Block>();
// blocks.add(new Block(1, 1, 2, HORIZONTAL));
// blocks.add(new Block(2, 3, 2, VERTICAL));
// blocks.add(new Block(1, 4, 2, VERTICAL));
// blocks.add(new Block(2, 5, 2, HORIZONTAL));
// //blocks.add(new Block(3, 1, 4, HORIZONTAL));
// //blocks.add(new Block(3, 5, 2, HORIZONTAL));
// blocks.add(new Block(4, 1, 4, VERTICAL));
// blocks.add(new Block(4, 2, 2, HORIZONTAL));
// blocks.add(new Block(4, 6, 3, VERTICAL));
// blocks.add(new Block(5, 2, 2, VERTICAL));
// blocks.add(new Block(5, 4, 2, HORIZONTAL));
// blocks.add(new Block(6, 4, 2, HORIZONTAL));
// blocks.add(new Block(7, 2, 5, HORIZONTAL));
// return blocks;
// }
public static void main(String args[]) {
// Block block = new Block(2, 1, 2, HORIZONTAL);
// System.out.println("Block is " + block);
// block.move(DOWN);
// System.out.println("After move DOWN, block is " + block);
// System.out.println("Expected block at (row=2, col=1).");
// block.move(RIGHT);
// System.out.println("After move RIGHT, block is " + block);
// System.out.println("Expected block at (row=2, col=2).");
// System.out.println();
//
// Block block2 = new Block(0, 1, 2, VERTICAL);
// System.out.println("Block is " + block2);
// block2.move(UP);
// System.out.println("After move UP, block is " + block2);
// System.out.println("Expected block at (row=0, col=1).");
// block2.move(LEFT);
// System.out.println("After move LEFT, block is " + block2);
// System.out.println("Expected block at (row=0, col=1).");
// block2.move(DOWN);
// System.out.println("After move DOWN, block is " + block2);
// System.out.println("Expected block at (row=1, col=1).");
// block2.move(RIGHT);
// System.out.println("After move RIGHT, block is " + block2);
// System.out.println("Expected block at (row=1, col=1).");
// System.out.println();
// Cell[][] cells = GridUtil.createGrid(testDescription1);
// System.out.println("Using testDescription1, cell (2, 4) is an exit is "
// + cells[2][4].isExit() + ", expected is true.");
// System.out.println("Using testDescription1, cell (0, 4) is an wall is "
// + cells[0][4].isWall() + ", expected is true.");
// System.out.println("Using testDescription1, cell (2, 3) is an floor is "
// + cells[2][3].isFloor() + ", expected is true.");
// ArrayList<Block> blocks = GridUtil.findBlocks(testDescription1);
// System.out.println("Using testDescription1, number of blocks is "
// + blocks.size() + ", expected is 1.");
// System.out.println("Using testDescription1, first block is length "
// + blocks.get(0).getLength() + ", expected is 2.");
// System.out.println();

// ArrayList<Block> blocks1 = GridUtil.findBlocks(testDescription2);
// System.out.println("Using testDescription2, number of blocks is "
// + blocks1.size() + ", expected is 13.");
// System.out.println("Using testDescription2, second block is length "
// + blocks1.get(1).getLength() + ", expected is 2.");
// System.out.println();

//
System.out.println("Making board with testGrid1.");
Board board = new Board(testGrid1, makeTest1Blocks());
System.out.println(board.toString());
System.out.println();
//
// System.out.println("Making board with testGrid2.");
// Board board = new Board(testGrid2, makeTest2Blocks());
// System.out.println(board.toString());
// System.out.println();
board.grabBlockAtCell(3, 1);
System.out.println("Grabbed block " + board.getGrabbedBlock());
System.out.println("Location of grab is at (" + board.getGrabbedCell().getRow()
+ ", " + board.getGrabbedCell().getCol() + ") " + ", expected (6, 1).");
System.out.println();
board.moveGrabbedBlock(UP);
System.out.println(board.toString());
board.moveGrabbedBlock(DOWN);
System.out.println();
System.out.println(board.toString());
board.moveGrabbedBlock(UP);
System.out.println();
System.out.println(board.toString());
board.reset();
System.out.println();
System.out.println(board.toString());
//
// board.grabBlockAtCell(2, 3);
// System.out.println("Grabbed block " + board.getGrabbedBlock());
// System.out.println("Location of grab is at (" + board.getGrabbedCell().getRow()
// + ", " + board.getGrabbedCell().getCol() + ") " + ", expected (6, 1).");
// System.out.println();
//
// board.moveGrabbedBlock(DOWN);
// System.out.println(board.toString());
// board.moveGrabbedBlock(UP);
// System.out.println();
// System.out.println(board.toString());
// board.moveGrabbedBlock(DOWN);
// System.out.println();
// System.out.println(board.toString());
// board.moveGrabbedBlock(UP);
// System.out.println();
// System.out.println(board.toString());
//
// board.grabBlockAtCell(2, 1);
// System.out.println("Grabbed block " + board.getGrabbedBlock());
// System.out.println("Location of grab is at (" + board.getGrabbedCell().getRow()
// + ", " + board.getGrabbedCell().getCol() + ") " + ", expected (2, 1).");
// System.out.println();
//
// board.moveGrabbedBlock(RIGHT);
// System.out.println("After moving block right one time game over is " + board.isGameOver()
// + ", expected is false.");
// System.out.println(board.toString());
// System.out.println(board.getMoveCount());
// System.out.println();
//
// board.moveGrabbedBlock(LEFT);
// System.out.println("After moving block right one time game over is " + board.isGameOver()
// + ", expected is false.");
// System.out.println(board.toString());
// System.out.println(board.getMoveCount());
// System.out.println();
//
// board.moveGrabbedBlock(UP);
// System.out.println("After moving block right one time game over is " + board.isGameOver()
// + ", expected is false.");
// System.out.println(board.toString());
// System.out.println(board.getMoveCount());
// System.out.println();
//
// board.moveGrabbedBlock(DOWN);
// System.out.println("After moving block right one time game over is " + board.isGameOver()
// + ", expected is false.");
// System.out.println(board.toString());
// System.out.println(board.getMoveCount());
// System.out.println();

// board.moveGrabbedBlock(RIGHT);
// System.out.println("After moving block right two times game over is " + board.isGameOver()
// + ", expected is true.");
// System.out.println(board.toString());
// System.out.println(board.getMoveCount());
// System.out.println();
//
// board.reset();
// System.out.println("After reset:");
// System.out.println(board.toString());
// System.out.println(board.isGameOver());
// System.out.println();

ArrayList<Move> moves = board.getAllPossibleMoves();
System.out.println("All possible moves: " + Arrays.toString(moves.toArray()));
}
}