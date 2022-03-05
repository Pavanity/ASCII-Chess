package chess;

/**
 * Piece implementation for Pawn
 * 
 * @author Pavan Mahankali
 * @author Joshua Su
 * 
 */
public class Pawn extends Piece{
	
	/**
	 * Constructs a Pawn with specified color and horizontal and vertical positions
	 * 
	 * @param color the color of the piece, either true for white or false for black
	 * @param xx	the horizontal position of the piece
	 * @param yy	the vertical position of the piece
	 */
	public Pawn(boolean color, int xx, int yy) {
		super(color, xx, yy);
		white = color;
		x = xx;
		y = yy;
		bounded = true;
		str= "p";
	}

	/**
	 *	Returns <code>Pawn</code> specific moves for directional evaluation.
	 *	<p>
	 *
	 *	This method is not actually used to determine pawn movement, which is handled separately in <code>Chess.pawnmove()</code>.
	 *
	 *	@return	array of <code>directions</code> specific to <code>Pawn</code>
	 */
	@Override//don't use this method
	public directions[] getMoves() {
		int[] moves = new int[3];
		
		//directions up = new directions(0,1);
		directions d1 = new directions(1, -1);
		directions d3 = new directions(-1, -1);
		if(getColor())
		{
		d1.setVert(1);
		d3.setVert(1);
		}
		
		directions[] d = {d1, d3};
		return d;
	}
	
	/**
	 * @return color of Pawn (white if true, black if false)
	 */
	public boolean getColor()
	{
		return white;
	}
}
