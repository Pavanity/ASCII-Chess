package chess;

import java.util.Scanner;

/**
 * Main class of Chess application. Initializes and defines functions for playing and printing the Chess game and fields for the Board representation.
 * 
 * @author Pavan Mahankali
 * @author Joshua Su
 */
public class Chess {
	/**
	 * Indicates whether it is white's turn
	 */
	public static boolean whiteTurn = true;
	/**
	 * Primary Board representation for game
	 */
	static Board b = new Board();
	/**
	 * Board field for storing previous Board state before movement.
	 */
	static Board prevb;
	/**
	 * Board field for storing Board state from previous turn.
	 */
	static Board prevprevb;
	//public static boolean checkmate= false;
	/**
	 * Indicates whether current turn player is under check.
	 */
	public static boolean check= false;
	/**
	 * Unused flag to check gameover condition.
	 */
	public static boolean gameover= false;
	
	
	public static void main(String[] args) {
		//Board b = new Board();
		
		
		
		initialize();
		
		Scanner sc = new Scanner(System.in);
		while(!gameover)
		{
			printBoard();
			
			prevb = new Board(b);
			
			String comm = sc.nextLine();
			int xi = comm.charAt(0) - 96; //convert letter to number
			
			int yi= comm.charAt(1)-48;
			int xf = comm.charAt(3)- 96;
			int yf = comm.charAt(4)-48;
			//System.out.println(xi+" "+yi);
			if(comm.substring(comm.length()-5).contentEquals("draw?"))
			{
				if(!whiteTurn)
				{
					System.out.println("White's move:");
				}
				else
				{
					System.out.println("Black's move:");
				}
				while(true)
				{	
					comm = sc.nextLine();
					if(comm.contentEquals("draw"))
					{
						return;
					}
					else
					{
						System.out.println("invalid input try again");
					}
				}	
				
			}
			if(comm.contentEquals("resign"))
			{
				if(whiteTurn)
				{
					System.out.println("Black Wins!");
				}
				else
				{
					System.out.println("White Wins!");
				}
				return;
			}
			if(move(xi,yi,xf,yf))
			{
				prevprevb = prevb;
				b.getSquare(xf, yf).getPiece().moved();
				if (b.getSquare(xf, yf).getPiece() instanceof Pawn) {
					Piece promoted;
					if (b.getSquare(xf, yf).getPiece().isWhite() && yf == 8) {
						if (comm.length() >= 7) {
							switch(comm.charAt(6)) {
								case 'N':
									promoted = new Knight(true, xf, yf);
									break;
								case 'B':
									promoted = new Bishop(true, xf, yf);
									break;
								case 'R':
									promoted = new Rook(true, xf, yf);
									break;
								default:
									promoted = new Queen(true, xf, yf);
							}
						}
						else {
							promoted = new Queen(true, xf, yf);
						}
						b.getSquare(xf, yf).setPiece(promoted);
					}
					else if(!b.getSquare(xf, yf).getPiece().isWhite() && yf == 1) {
						if (comm.length() == 7) {
							switch(comm.charAt(6)) {
								case 'N':
									promoted = new Knight(false, xf, yf);
									break;
								case 'B':
									promoted = new Bishop(false, xf, yf);
									break;
								case 'R':
									promoted = new Rook(false, xf, yf);
									break;
								default:
									promoted = new Queen(false, xf, yf);
							}
						}
						else {
							promoted = new Queen(false, xf, yf);
						}
						b.getSquare(xf, yf).setPiece(promoted);
					}
				}
				whiteTurn = !whiteTurn; //if move was successful change player turns
				System.out.println();
			}
			else
			{
				System.out.println("Illegal move, try again" + System.lineSeparator()) ;
			}
			if(checkcheck(whiteTurn))
			{
				check = true;
				if(checkcheckmate(whiteTurn))
				{
					System.out.println("Checkmate");
					if(whiteTurn)
					{
						System.out.println("Black wins");
					}
					else
					{
						System.out.println("White wins");
					}
					gameover= true;
				}
				else {
				System.out.println("Check" + System.lineSeparator());
				}
			}
			else {
				check = false;
			}
		}
		

	}
	/**
	 * 
	 * Checks if specified player is in checkmate by calling <code>move</code> for all possible moves to escape check, and returns true if none exist.
	 * 
	 * @param white player to check (true if white, false if black)
	 * @return true if said player is in checkmate, false otherwise 
	 */
	public static boolean checkcheckmate(boolean white)
	{
		
		for(int x = 1; x<9; x++)
			for(int y= 1; y<9; y++)
			{
				if(b.getSquare(x, y).isOccupied() &&b.getSquare(x, y).getPiece().isWhite()==white)
				{
					//System.out.println(x+" "+y);
					directions[] legalMoves= getLegalMoves(x, y);
					
					for(directions d: legalMoves)
					{
						Board temp = new Board(b);
						if(move(x, y, d.getHor(), d.getVert()))
						{
							b = temp; //reverts the changes
							return false;
						}
						b = temp;
							
					}
				}
			}
		
		gameover = true;
		return true;
	}
	
	/**
	 * Creates all the pieces of the Chess game and sets them to their starting positions.
	 */
	public static void initialize()
	{
		Rook wr1 = new Rook(true, 1, 1);
		b.getSquare(1, 1).setPiece(wr1);
		Rook wr2 = new Rook(true, 8, 1);
		b.getSquare(8, 1).setPiece(wr2);
		Rook br1 = new Rook(false, 1, 8);
		b.getSquare(1, 8).setPiece(br1);
		Rook br2 = new Rook(false, 8, 8);
		b.getSquare(8, 8).setPiece(br2);
		
		Knight wn1 = new Knight(true, 2, 1);
		b.getSquare(2, 1).setPiece(wn1);
		Knight wn2 = new Knight(true, 7, 1);
		b.getSquare(7, 1).setPiece(wn2);
		Knight bn1 = new Knight(false, 2, 8);
		b.getSquare(2, 8).setPiece(bn1);
		Knight bn2 = new Knight(false, 7, 8);
		b.getSquare(7, 8).setPiece(bn2);
		
		Bishop wb1= new Bishop(true, 3, 1);
		b.getSquare(3, 1).setPiece(wb1);
		Bishop wb2 = new Bishop(true, 6, 1);
		b.getSquare(6, 1).setPiece(wb2);
		Bishop bb1= new Bishop(false, 3, 8);
		b.getSquare(3, 8).setPiece(bb1);
		Bishop bb2= new Bishop(false, 6, 8);
		b.getSquare(6, 8).setPiece(bb2);
		
		Queen wq = new Queen(true, 4, 1);
		b.getSquare(4, 1).setPiece(wq);
		Queen bq = new Queen(false, 4, 8);
		b.getSquare(4, 8).setPiece(bq);
		King wk = new King(true, 5, 1);
		b.getSquare(5, 1).setPiece(wk);
		King bk = new King(false, 5, 8);
		b.getSquare(5, 8).setPiece(bk);
		
		for(int i= 1; i<9; i++)
		{
			Pawn wp = new Pawn(true, i, 2);
			Pawn bp = new Pawn(false, i, 7);
			b.getSquare(i, 2).setPiece(wp);
			b.getSquare(i, 7).setPiece(bp);
		}
		
	}
	
	/**
	 * Moves a piece from specified starting position to ending position by first evaluating legality of move, and then executing move if legal
	 * <p>
	 * 
	 * Legality is determined by whether position coordinates return true from <code>inbound(int x, int y)</code> and are contained in array returned by
	 * <code>getLegalMoves(int startX, int startY)</code>.
	 * <p>
	 * 
	 * If legal, piece is moved to specified ending position and the board is updated. If movement results in the player's King placed in check, the board is reverted
	 * and the function returns false.
	 * 
	 * @param startX the horizontal position of the starting position
	 * @param startY the vertical position of the starting position
	 * @param endX the horizontal position of the ending position
	 * @param endY the vertical position of the ending position
	 * @return true if move is valid and the movement does not place player's King in check, false otherwise.
	 */
	public static boolean move(int startX, int startY, int endX, int endY)
	{
		if(!(inbound(startX, startY)&&inbound(endX, endY))||b.getSquare(startX, startY).getPiece()==null||b.getSquare(startX, startY).getPiece().isWhite()!=whiteTurn)
		{
			//System.out.println("illegal move");
			return false;
		}
		if(b.getSquare(startX, startY).getPiece() instanceof Pawn)
		{
			return pawnmove(startX, startY, endX, endY);
			
		}
		
		//take the getMoves array and extend it until it's out of bounds or blocked
		//if rook bishop or queen
		directions[] legalMoves = getLegalMoves(startX, startY);
		boolean legal = false;
		directions target = new directions(endX, endY);
		for(directions m: legalMoves)
		{
			if(m!= null && m.equals(target))
			{
				
				legal = true;
				//System.out.println("legal");
				if (m.isCastling) {
					return castlemove(startX, startY, endX, endY);
				}
			}
		}	
		if(legal)
		{
			Piece p = b.getSquare(startX, startY).getPiece();
			
			p.setPosition(endX, endY);
			b.getSquare(startX, startY).deoccupy();
			
			if(b.getSquare(endX, endY).isOccupied())
			{
				Piece captured = b.getSquare(endX, endY).getPiece();
				captured.setPosition(0, 0); //removes from game.
				b.getSquare(endX, endY).setPiece(p);
				//System.out.println("capture");
				if(checkcheck(p.isWhite()))
				{
					captured.setPosition(endX, endY);
					p.setPosition(startX, startY);
					b.getSquare(startX, startY).setPiece(p);
					b.getSquare(startX, startY).occupy();
					b.getSquare(endX, endY).setPiece(captured);
					
					return false;
				}
			}
			b.getSquare(endX, endY).occupy();
			b.getSquare(endX, endY).setPiece(p);
			if(checkcheck(p.isWhite()))
			{
				b.getSquare(startX, startY).occupy();
				p.setPosition(startX, startY);
				b.getSquare(startX, startY).setPiece(p);
				b.getSquare(endX, endY).deoccupy();
				return false;
			}
//			p.moved();
	
			return true;
		}
		return false;

	}
	
	
	/**
	 *  
	 *  Function to define a castling move, called from <code>move</code> with specified starting and ending positions. The castling move evaluates whether King moves through check in successive <code>move</code>
	 *  calls, reverting if <code>move</code> returns false, or completing the castling by updating Board with the King and Rook in their ending positions. 
	 *  <p>
	 *  
	 *  The legality of the castling move otherwise is handled before this function is called.
	 *
	 * @param startX 	the horizontal position of the starting position of the King
	 * @param startY 	the vertical position of the starting position of the King
	 * @param endX 		the horizontal position of the ending position of the King
	 * @param endY 		the vertical position of the ending position of the King
	 * @return true if castle move completes successfully, false otherwise
	 */
	public static boolean castlemove(int startX, int startY, int endX, int endY) {
		King king = (King) b.getSquare(startX, startY).getPiece();
		int dx = endX - startX;
		if (dx > 0) {
			for (int i = 0; i < 2; i++) {
				if (!move(startX+i, startY, startX+i+1, endY)) {
					b.getSquare(startX+i, startY).deoccupy();
					b.getSquare(startX, startY).setPiece(king);
					return false;
				}
			}
			b.getSquare(startX+1, startY).setPiece(b.getSquare(startX+3, startY).getPiece());
			b.getSquare(startX+1, startY).getPiece().moved();
			b.getSquare(startX+3, startY).deoccupy();
		}
		else {
			for (int i = 0; i < 2; i++) {
				if (!move(startX-i, startY, startX-i-1, endY)) {
					b.getSquare(startX-i, startY).deoccupy();
					b.getSquare(startX, startY).setPiece(king);
					return false;
				}
			}
			b.getSquare(startX-1, startY).setPiece(b.getSquare(startX-4, startY).getPiece());
			b.getSquare(startX-1, startY).getPiece().moved();
			b.getSquare(startX-4, startY).deoccupy();
		}
		return true;
	}
	
	/** 
	 *  Function to handle pawn movement, called from <code>move</code>. Pawn movement is evaluated from specified starting position to ending position
	 *  and board is updated only if movement is valid.
	 *  
	 *  @param startX 	the horizontal position of the starting position of the pawn
	 *  @param startY 	the vertical position of the starting position of the pawn
	 *  @param endX		the horizontal position of the ending position of the pawn
	 *  @param endY 	the vertical position of the ending position of the pawn
	 *  @return true if move is valid pawn move and can be executed, false otherwise.
	 */
	public static boolean pawnmove(int startX, int startY, int endX, int endY)
	{//white goes up, black goes down
		Pawn pawn = (Pawn) b.getSquare(startX, startY).getPiece();
		int dx = endX-startX;
		int dy = endY-startY;
		if(pawn.isWhite())
		{//4 possible movements
			
			if(dx ==0 &&dy==1)
			{
				if(!b.getSquare(endX, endY).isOccupied())
				{
					
					b.getSquare(endX, endY).setPiece(pawn);
					pawn.setPosition(endX, endY);
					
					b.getSquare(startX, startY).deoccupy();
					b.getSquare(endX, endY).occupy();
					if(checkcheck(b.getSquare(endX, endY).getPiece().isWhite()))
							{
								b.getSquare(startX, startY).setPiece(pawn);
								pawn.setPosition(startX, startY);
								b.getSquare(endX, endY).deoccupy();
								b.getSquare(startX, startY).occupy();
								return false;
							}
//					pawn.moved();
					return true;
				}
				else
					return false;
			}
			if(dx==0 && dy==2)
			{
				if(!b.getSquare(endX, endY).isOccupied() && !b.getSquare(endX, endY-1).isOccupied()&&!pawn.hasMoved())
				{
					b.getSquare(endX, endY).setPiece(pawn);
					pawn.setPosition(endX, endY);
					
					b.getSquare(startX, startY).deoccupy();
					b.getSquare(endX, endY).occupy();
					if(checkcheck(b.getSquare(endX, endY).getPiece().isWhite()))
					{
						b.getSquare(startX, startY).setPiece(pawn);
						pawn.setPosition(startX, startY);
						b.getSquare(endX, endY).deoccupy();
						b.getSquare(startX, startY).occupy();
						return false;
					}
//					pawn.moved();
					return true;
				}
				else
					return false;
			}
			if((dx==1||dx==-1)&&dy==1)
			{ //need to implement enpassen
				if(b.getSquare(endX, endY).isOccupied() && !b.getSquare(endX, endY).getPiece().isWhite())
				{
					Piece captured = b.getSquare(endX, endY).getPiece();
					captured.setPosition(0, 0);
					b.getSquare(endX, endY).setPiece(pawn);
					pawn.setPosition(endX, endY);
					
					b.getSquare(startX, startY).deoccupy();
					b.getSquare(endX, endY).occupy();
					if(checkcheck(b.getSquare(endX, endY).getPiece().isWhite()))
					{
						b.getSquare(endX, endY).setPiece(captured);
						captured.setPosition(endX, endY);
						b.getSquare(startX, startY).setPiece(pawn);
						pawn.setPosition(startX, startY);
						
						b.getSquare(startX, startY).occupy();
						return false;
					}
//					pawn.moved();
					return true;
				}
				else if (b.getSquare(endX, startY).isOccupied() && b.getSquare(endX, startY).getPiece().equals(prevprevb.getSquare(endX, startY+2).getPiece())) {
					Piece captured = b.getSquare(endX, startY).getPiece();
					b.getSquare(endX, startY).deoccupy();
					
					b.getSquare(endX, endY).setPiece(pawn);
					b.getSquare(startX, startY).deoccupy();
					if (checkcheck(b.getSquare(endX, endY).getPiece().isWhite()))
					{
						b.getSquare(endX, startY).setPiece(captured);
						b.getSquare(startX, startY).setPiece(pawn);
						b.getSquare(endX, endY).deoccupy();
						
						return false;
					}
					return true;
				}
				else
					return false;
			}
		}
		else{//is black
			if(dx ==0 &&dy==-1)
			{
				if(!b.getSquare(endX, endY).isOccupied())
				{
					
					b.getSquare(endX, endY).setPiece(pawn);
					pawn.setPosition(endX, endY);
					
					b.getSquare(startX, startY).deoccupy();
					b.getSquare(endX, endY).occupy();
					if(checkcheck(b.getSquare(endX, endY).getPiece().isWhite()))
							{
								b.getSquare(startX, startY).setPiece(pawn);
								pawn.setPosition(startX, startY);
								b.getSquare(endX, endY).deoccupy();
								b.getSquare(startX, startY).occupy();
								return false;
							}
//					pawn.moved();
					return true;
				}
				else
					return false;
			}
			if(dx==0 && dy==-2)
			{
				if(!b.getSquare(endX, endY).isOccupied() && !b.getSquare(endX, endY+1).isOccupied()&&!pawn.hasMoved())
				{
					b.getSquare(endX, endY).setPiece(pawn);
					pawn.setPosition(endX, endY);
					
					b.getSquare(startX, startY).deoccupy();
					b.getSquare(endX, endY).occupy();
					if(checkcheck(b.getSquare(endX, endY).getPiece().isWhite()))
					{
						b.getSquare(startX, startY).setPiece(pawn);
						pawn.setPosition(startX, startY);
						b.getSquare(endX, endY).deoccupy();
						b.getSquare(startX, startY).occupy();
						return false;
					}
//					pawn.moved();
					return true;
				}
				else
					return false;
			}
			if((dx==1||dx==-1)&&dy==-1)
			{ //need to implement enpassen
				if(b.getSquare(endX, endY).isOccupied() && b.getSquare(endX, endY).getPiece().isWhite())
				{
					Piece captured = b.getSquare(endX, endY).getPiece();
					captured.setPosition(0, 0);
					b.getSquare(endX, endY).setPiece(pawn);
					pawn.setPosition(endX, endY);
					pawn.moved();
					b.getSquare(startX, startY).deoccupy();
					b.getSquare(endX, endY).occupy();
					if(checkcheck(b.getSquare(endX, endY).getPiece().isWhite()))
					{
						b.getSquare(endX, endY).setPiece(captured);
						captured.setPosition(endX, endY);
						b.getSquare(startX, startY).setPiece(pawn);
						pawn.setPosition(startX, startY);
						
						b.getSquare(startX, startY).occupy();
						return false;
					}
//					pawn.moved();
					return true;
				}
				else if (b.getSquare(endX, startY).isOccupied() && b.getSquare(endX, startY).getPiece().equals(prevprevb.getSquare(endX, startY-2).getPiece())) {
					Piece captured = b.getSquare(endX, startY).getPiece();
					b.getSquare(endX, startY).deoccupy();
					
					b.getSquare(endX, endY).setPiece(pawn);
					b.getSquare(startX, startY).deoccupy();
					if (checkcheck(b.getSquare(endX, endY).getPiece().isWhite()))
					{
						b.getSquare(endX, startY).setPiece(captured);
						b.getSquare(startX, startY).setPiece(pawn);
						b.getSquare(endX, endY).deoccupy();
						
						return false;
					}
					return true;
				}
				else
					return false;
			}
			
		}
		return false;
	}
	
	/** 
	 * Finds and returns all legal moves of <code>Piece</code> at specified coordinates.
	 * <p>
	 * Determines directions from <code>Piece.getMoves()</code> which returns class specific array of directions , and then extends them for unbounded pieces.
	 * If piece is King, determines whether castling move has/can be performed.
	 * 
	 * @param startX the horizontal position of the starting position
	 * @param startY the vertical position of the starting position
	 * @return an array of directions for all legal moves (empty if none exists)
	 */
	public static directions[] getLegalMoves(int startX, int startY)
	{  //need to trim this
		directions[] legalMoves= new directions[30];  //using directions object for coordinates
		directions[] d = b.getSquare(startX, startY).getPiece().getMoves();
		int j=0; //index
		int x0= startX;
		int y0= startY;
		//System.out.println("start"+ x0+ " "+ y0);
		for(directions i: d)
		{
			
				int xinc= i.getHor();
				int yinc = i.getVert();
				int xf= x0+xinc;
				int yf= y0+yinc;
			//	System.out.println("inc: "+xinc +" "+yinc);
				if(b.getSquare(x0, y0).getPiece().isBounded())
				{
					if(inbound(xf, yf))
					{	
					  if (!b.getSquare(xf, yf).isOccupied())//bounded movement
					  {
						directions n = new directions(xf, yf);
						//System.out.println("add: "+xf +" "+yf);
						legalMoves[j]= n;
						j++;
					  }
					  else //space is occupied
					  {
						  if((b.getSquare(xf, yf).getPiece().isWhite() && !b.getSquare(x0, y0).getPiece().isWhite())
									|| !b.getSquare(xf, yf).getPiece().isWhite() && b.getSquare(x0, y0).getPiece().isWhite())
							{
								directions n = new directions(xf, yf);
								legalMoves[j]= n;
								j++;
								//System.out.println("add: "+xf +" "+yf);
							}
					  }
					}
						
					
					
				}
				else {
					while(inbound(xf, yf) && !b.getSquare(xf, yf).isOccupied())//unbounded movement
					{
						directions n = new directions(xf, yf);
						//System.out.println("add: "+xf +" "+yf);
						legalMoves[j]= n;
						j++;
						xf+=xinc;
						yf+=yinc;
					}
					//xf-=xinc;
					//yf-=yinc;
					if(inbound(xf, yf))//!!!
					{//check if pieces are opposite color
						if((b.getSquare(xf, yf).getPiece().isWhite() && !b.getSquare(x0, y0).getPiece().isWhite())
								|| !b.getSquare(xf, yf).getPiece().isWhite() && b.getSquare(x0, y0).getPiece().isWhite())
						{
							directions n = new directions(xf, yf);
							legalMoves[j]= n;
							j++;
							//System.out.println("add: "+xf +" "+yf);
						}
					}
				}	
				
				
				
		}
		if (b.getSquare(startX, startY).getPiece() instanceof King) {
			King k = (King) b.getSquare(startX, startY).getPiece();
			if (!check && !k.hasMoved) {
				if (!b.getSquare(startX+1, startY).isOccupied() && !b.getSquare(startX+2, startY).isOccupied()) {
					if (b.getSquare(startX+3, startY).isOccupied() && !b.getSquare(startX+3, startY).getPiece().hasMoved) {
						directions cd = new directions(startX + 2, startY);
						cd.isCastling = true;
						legalMoves[j] = cd;
						j++;
					}
				}
				if (!b.getSquare(startX-1, startY).isOccupied() && !b.getSquare(startX-2, startY).isOccupied() && !b.getSquare(startX-3, startY).isOccupied()) {
					if (b.getSquare(startX-4, startY).isOccupied() && !b.getSquare(startX-4, startY).getPiece().hasMoved) {
						directions cd = new directions(startX - 2, startY);
						cd.isCastling = true;
						legalMoves[j] = cd;
						j++;
					}
				}
			}
		}
		directions[] trimmedlegalmoves = new directions[j];
		for(int i = 0; i<j; i++)
		{
			trimmedlegalmoves[i] = legalMoves[i];
		}
		return trimmedlegalmoves;
	}
	
	/** 
	 *  Verifies if the player specified is in check by looking at all possible moves for the other player.
	 *  
	 *  @param white the player color to be checked (white if true)
	 *  @return true if the player is in check, false if player is not in check
	 */
	public static boolean checkcheck(boolean white)//if white is true, returns true if white is in check
	{
		for(int x = 1; x<9; x++)
			for(int y= 1; y<9; y++)
			{
				if(b.getSquare(x, y).isOccupied() &&b.getSquare(x, y).getPiece().isWhite()!=white)
				{
					//System.out.println(x+" "+y);
					directions[] legalMoves= getLegalMoves(x, y);
					for(directions d: legalMoves)
					{
						//System.out.println("check" +d.getHor()+" "+d.getVert());
						if(b.getSquare(d.getHor(), d.getVert()).isOccupied())
						{
							Piece k = b.getSquare(d.getHor(), d.getVert()).getPiece();
							if((k instanceof King )&& k.isWhite()==white)
							{
//								King king = (King) k;
//								king.check();
								return true;
							}
						}
					}
					
				}
			}
//		for(int x = 1; x<9; x++)
//			for(int y= 1; y<9; y++)
//			{
//				if (b.getSquare(x, y).isOccupied() && b.getSquare(x, y).getPiece().isWhite() == white && b.getSquare(x, y).getPiece() instanceof King) {
//					King king = (King) b.getSquare(x, y).getPiece();
//					king.uncheck();
//				}
//			}
		return false;
	}
	/** 
	 * Checks if specified coordinates are within the bounds of the board and returns true if they are.
	 * 
	 * @param x the horizontal coordinate to be checked
	 * @param y the vertical coordinate to be checked
	 * @return true if coordinate is on the board, false if they are not
	 */
	public static boolean inbound(int x, int y)
	{
		return(x>0 && x<9 && y>0 && y<9);
	}
	
	/**
	 * Prints the chess board and also prompts the player that it is now their turn
	 */
	public static void printBoard()
	{
		for(int y = 8; y>0; y--)
		{
			for(int x=1; x<9; x++)
			{
				Square s = b.getSquare(x, y);
				if(s.isOccupied())
				{
					Piece p = s.getPiece();
					if(p.isWhite())
					{
						System.out.print("w");
					}
					else
					{
						System.out.print("b");
					}
					System.out.print(p.print()+" ");
				}
				else
				{
					if(y%2==1)
					{
						if(x%2==1)
						{
							System.out.print("## ");
						}
						else
						{
							System.out.print("   ");
						}
					}
					else
					{
						if(x%2==0)
						{
							System.out.print("## ");
						}
						else
						{
							System.out.print("   ");
						}
					}
				}
					
						
			}
			System.out.println(y);
			
		}
		System.out.println(" a  b  c  d  e  f  g  h");
		System.out.println();
		if(whiteTurn)
		{
			System.out.print("White's move: ");
		}
		else
		{
			System.out.print("Black's move: ");
		}
	}
	
	

}
