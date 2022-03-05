package chess;

/**
 * Piece implementation for Rook
 * 
 * @author Pavan Mahankali
 * @author Joshua Su
 */
public class Rook extends Piece {
	
	
	/**
	 * Constructs a Rook with specified color and horizontal and vertical positions
	 * 
	 * @param color the color of the piece, either true for white or false for black
	 * @param xx	the horizontal position of the piece
	 * @param yy	the vertical position of the piece
	 */
	public Rook(boolean color, int xx, int yy)
	{
		super(color, xx, yy);
		white = color;
		x = xx;
		y = yy;
		bounded = false;
		str= "R";
	}
	
	/**
	 *	Returns <code>Rook</code> specific moves for directional evaluation
	 *
	 *	@return	array of <code>directions</code> specific to <code>Rook</code>
	 */
	@Override
	public directions[] getMoves() {
		
		directions left = new directions(-1, 0);
		directions right = new directions(1, 0);
		directions up = new directions(0,1);
		directions down = new directions(0, -1);
		directions[] d = {left, right, up, down};
		return d;
		
	}
	

}
