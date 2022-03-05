package chess;

/**
 * Piece implementation for Bishop
 * 
 * @author Pavan Mahankali
 * @author Joshua Su
 *
 */
public class Bishop extends Piece{
	/**
	 * 
	 * Constructs a bishop with specified color and horizontal and vertical positions
	 * 
	 * @param color the color of the piece, either true for white or false for black
	 * @param xx	the horizontal position of the piece
	 * @param yy	the vertical position of the piece
	 */
	public Bishop(boolean color, int xx, int yy) {
		super(color, xx, yy);
		white = color;
		x = xx;
		y = yy;
		bounded = false;
		str= "B";
	}

	/**
	 *	Returns <code>Bishop</code> specific moves for directional evaluation
	 *
	 *	@return	array of <code>directions</code> specific to <code>Bishop</code>
	 */
	@Override
	public directions[] getMoves() {
		int[] moves = new int[4];
		
		directions d1 = new directions(1, 1);
		directions d2 = new directions(1, -1);
		directions d3 = new directions(-1, 1);
		directions d4 = new directions(-1, -1);
		directions[] d = {d1, d2, d3, d4};
		return d;
	}
}
