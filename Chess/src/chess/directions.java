package chess;

/**
 * Simple representation of directional system with horizontal and vertical coordinates.
 * 
 * @author Pavan Mahankali
 * @author Joshua Su
 */
public class directions{
	/**
	 * Fields for horizontal and vertical coordinate values
	 */
	int hor;
	int vert;
	/**
	 * Field to specify direction is Castling move
	 */
	boolean isCastling = false;
	
	/**
	 * Constructs directions from specified horizontal and vertical values
	 * 
	 * @param h horizontal value of direction
	 * @param v vertical value of direction
	 */
	public directions(int h, int v)
	{
		hor= h;
		vert = v;
	}
	
	
	/**
	 * @return horizontal value of direction
	 */
	public int getHor()
	{
		return hor;
	}
	
	
	/**
	 * @return vertical value of direction
	 */
	public int getVert()
	{
		return vert;
	}
	
	/**
	 * @return true if direction has same horizontal and vertical values
	 */
	@Override
	public boolean equals(Object o)
	{
		if (o == this) return true;
		if (!(o instanceof directions)) return false;
		
		directions d = (directions) o;
		
		return d.getHor()==hor && d.getVert()==vert;
	}
	
	/**
	 * Sets vertical coordinate to specified value
	 * 
	 * @param v vertical value to be set
	 */
	public void setVert(int v)
	{
		vert = v;
	}
	
	
	/**
	 * Sets horizontal coordinate to specified value
	 * 
	 * @param h horizontal value to be set
	 */
	public void setHor(int h)
	{
		hor= h;
	}
}
