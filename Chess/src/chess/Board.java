package chess;

/**
 * Model for Chess board, represented by 8x8 <code>Square</code> array.
 * 
 * @author Pavan Mahankali
 * @author Joshua Su
 */
public class Board {

	/**
	 * 2D <code>Square</code> array for board.
	 */
	private Square[][] board;
	
	
	
	/**
	 * Constructs default <code>Board</code> of empty Squares. Actual array is 9x9 to simplify positional notation using indexes from 1.
	 */
	public Board()
	{
		board = new Square[9][9];//position 0 should not be filled
		for(int i =1; i<9; i++)
		{
			for(int j = 1; j<9; j++)
			{
			board[i][j] = new Square();
			}
		}
	}
	
	/**
	 * Constructs new <code>Board</code> that is a copy of the specified <code>Board</code>. The resulting <code>Board</code> creates new instances of <code>Board
	 * </code> and <code>Squares</code> but retains the same <code>Piece</code> references.
	 * 
	 * @param boardToCopy the board that is copied
	 */
	public Board(Board boardToCopy) {
		board = new Square[9][9];
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				Square sq = new Square();
				if (boardToCopy.getSquare(i, j).isOccupied()) {
					sq.setPiece(boardToCopy.getSquare(i, j).getPiece());
				}
				board[i][j] = sq;
			}
		}
	}
	
	/**
	 * 
	 * Returns <code>Square</code> at the specified position of the <code>Board</code>.
	 * 
	 * @param i the horizontal position of the square to be returned
	 * @param j the vertical position of the square to be returned
	 * @return the <code>Square</code> at the position (i,j)
	 */
	public Square getSquare(int i, int j)
	{
		return board[i][j];
	}
	
	/**
	 * Method to copy this <code>Board</code> and return a new instance of <code>Board</code>.
	 * 
	 * @return new instance of <code>Board</code> that is a copy of this <code>Board</code>
	 * @throws CloneNotSupportedException if <code>Square</code> has not implemented <code>Cloneable</code>
	 */
	public Board copy() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Board b = new Board();
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				b.board[i][j] = (Square) this.board[i][j].clone();
			}
		}
		
		return b;
	}
	
}
