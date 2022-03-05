package chess;

/**
 * Representation of Chess Board Square.
 * <p>
 * Defines a <code>Square</code> with values and methods to determine if <code>Square</code> is occupied and by which <code>Piece</code>
 * 
 * @author Pavan Mahankali
 * @author Joshua Su
 */
public class Square implements Cloneable{
	/**
	 * Field to represent <code>Piece</code> that currently occupies this <code>Square</code>
	 */
	private Piece p;
	
	/**
	 * Checks if this Square is currently occupied by a <code>Piece</code>
	 */
	private boolean occupied = false;
	
	/**
	 * Field to check if Square contents have been moved (not currently being used)
	 */
	boolean hasMoved=false; //used for castling, double pawn push
	
	/**
	 * Constructs empty <code>Square</code> which is unoccupied by default. 
	 */
	public Square()
	{
		
	}
	
	
	/**
	 * @return Piece currently occupying this square, null if unoccupied
	 */
	public Piece getPiece()
	{
		return p;
	}
	
	/**
	 * Sets <code>Piece</code> that is occupying this Square to specified <code>Piece</code>
	 * @param x <code>Piece</code> to be set
	 */
	public void setPiece(Piece x)
	{
		p = x;
		occupied = true;
	}
	
	
	/**
	 * @return true if Square is occupied i.e. Square contains a Piece
	 */
	public boolean isOccupied()
	{
		return occupied;
	}
	
	
	/**
	 * Sets occupied to true, i.e. when Square contains a Piece
	 */
	public void occupy()
	{
		occupied = true;
	}
	
	
	/**
	 * Sets occupied to false, i.e. Square contains no Piece.
	 */
	public void deoccupy()
	{
		occupied = false;
		p = null;  
	}
	
	
	/**
	 * Creates a clone of this <code>Square</code>. The cloned Square is a shallow copy of this Square, and any Piece reference it contains is copied, i.e.
	 * no new <code>Piece</code> instance is created.
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
		
	}
	
	
}
