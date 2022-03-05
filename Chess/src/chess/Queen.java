package chess;

/**
 * Piece implementation for Queen
 * 
 * @author Pavan Mahankali
 * @author Joshua Su
 */
public class Queen extends Piece{
	
	/**
	 * Constructs a Queen with specified color and horizontal and vertical positions
	 * 
	 * @param color the color of the piece, either true for white or false for black
	 * @param xx	the horizontal position of the piece
	 * @param yy	the vertical position of the piece
	 */
	public Queen(boolean color, int xx, int yy) {
		super(color, xx, yy);
		white = color;
		x = xx;
		y = yy;
		bounded = false;
		str = "Q";
	}
	
	/**
	 *	Returns <code>Queen</code> specific moves for directional evaluation
	 *
	 *	@return	array of <code>directions</code> specific to <code>Queen</code>
	 */
	@Override
	public directions[] getMoves() {
		int[] moves = new int[8];
		directions left = new directions(-1, 0);
		directions right = new directions(1, 0);
		directions up = new directions(0,1);
		directions down = new directions(0, -1);
		directions d1 = new directions(1, 1);
		directions d2 = new directions(1, -1);
		directions d3 = new directions(-1, 1);
		directions d4 = new directions(-1, -1);
		directions[] d = {left, right, up, down, d1, d2, d3, d4};
		return d;
	}
}
