package chess;

/**
 * Abstract class for Piece representation. Provides default functionality for determining color, position, and whether piece has moved, as well as the abstract method
 * <code>getMoves()</code> for Piece specific directional implementation.
 * 
 * @author Pavan Mahankali
 * @author Joshua Su
 */
public abstract class Piece {

	/**
	 * Field for checking piece color, true if white, false if black
	 */
	public  boolean white;
	/**
	 * Fields for horizontal and vertical coordinates for Piece position.
	 */
	public int x;
	public int y;
	/**
	 * Field for checking if piece has been moved in game (true upon being moved).
	 */
	boolean hasMoved= false;
	/**
	 * Field for checking if piece is bounded (has movement restricted to a maximum distance) or unbounded (no distance restrictions apart from board boundaries)
	 */
	public boolean bounded;
	
	/**
	 * String value for Piece notation
	 */
	public String str;

	
	/**
	 * Abstract constructor to maintain all pieces have a color and location
	 * 
	 * @param color piece color (true if white, false if black)
	 * @param xx horizontal position of the piece
	 * @param yy vertical position of the piece
	 */
	public Piece(boolean color, int xx, int yy) {
		white = color;
		x = xx;
		y= yy;
	}



	/**
	 * Abstract method to be implemented by subclasses to define Piece-specific directionality
	 * 
	 * @return Piece-specific array of <code>directions</code>
	 */
	public abstract directions[] getMoves();
	
	/**
	 * 
	 * Sets position of this piece to specified coordinates.
	 * @param xx horizontal position to be set
	 * @param yy vertical position to be set
	 */
	public void setPosition(int xx, int yy)
	{
		x= xx;
		y= yy;
	}
	/**
	 * @return true if Piece is white, false if Piece is black
	 */
	public boolean isWhite()
	{
		return white;
	}
	
	
	/**
	 * @return <code>directions</code> object containing (x,y) coordinates of Piece. 
	 */
	public directions getPosition()
	{
		directions position = new directions(x, y);
		return position;
	}
	
	
	/**
	 * @return true if Piece has been moved, false otherwise
	 */
	public boolean hasMoved()
	{
		return hasMoved;
	}
	
	/**
	 * @return X field of piece, i.e. the horizontal position (file)
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * @return Y field of piece, i.e. the vertical position (rank)
	 */
	public int getY()
	{
		return y;
	}
	
	
	/**
	 * @return true if Piece movement is bounded, false otherwise
	 */
	public boolean isBounded()
	{
		return bounded;
	}
	
	
	 /**
	 * @return sets value for <code>hasMoved()</code> to true
	 */
	public boolean moved()
	{
		return hasMoved = true;
	}
	
	
	/**
	 * @return Piece notation <code>String</code>
	 */
	public String print()
	{
		return str;
	}
		
	
}
