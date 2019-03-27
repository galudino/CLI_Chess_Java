/**
 * Board.java
 *
 * Copyright (c) 2019 Gemuele Aludino, Patrick Nogaj. 
 * All rights reserved.
 *
 * Rutgers University: School of Arts and Sciences
 * 01:198:213 Software Methodology, Spring 2019
 * Professor Seshadri Venugopal
 */
package model.chess_set;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import model.PieceType;
import model.chess_set.piecetypes.*;
import model.game.Position;

/**
 * Represents a Chess board for a Chess game
 * 
 * @version Mar 9, 2019
 * @author gemuelealudino
 */
public class Board {

	/**
	 * Represents a 'square' within a Chess board
	 * 
	 * @version Mar 3, 2019
	 * @author gemuelealudino
	 */
	public class Cell {
		private Position loc;
		private Piece piece;

		/**
		 * Parameterized constructor
		 * 
		 * @param file x axis coordinate (0-7 only)
		 * @param rank y axis coordinate (0-7 only)
		 */
		public Cell(int file, int rank) {
			loc = new Position(file, rank);
			piece = null;
		}

		/**
		 * Accessor method to retrieve a Piece within a Cell
		 * 
		 * @return the Piece occupying a Cell within the Board
		 */
		public Piece getPiece() {
			return piece;
		}

		public void setPiece(Piece piece) {
			this.piece = piece;
		}

		/**
		 * Accessor method to retrieve the Position object within a Cell
		 * 
		 * @return the Position object associated with a Cell
		 */
		public Position getPosition() {
			return loc;
		}

		public Move getLastMove() {
			return moveList.get(moveList.size() - 1);
		}

		public void setPieceNull(int file, int rank) {
			loc = new Position(file, rank);
			piece = null;
		}

		/**
		 * Returns a string representation of the Piece occupying the cell, if
		 * there is a Piece present
		 */
		@Override
		public String toString() {
			return piece == null ? "--" : piece.toString();
		}
	}

	/**
	 * Represents a move within a Chess game, for logging purposes
	 * 
	 * @version Mar 24, 2019
	 * @author gemuelealudino
	 */
	public class Move {
		Piece piece;

		Position startPos;
		Position endPos;

		LocalTime localTime;

		int moveNumber;

		Move(Piece piece, Position startPos, Position endPos, int moveNumber) {
			this.piece = piece;
			this.startPos = startPos;
			this.endPos = endPos;

			this.moveNumber = moveNumber;
			localTime = LocalTime.now();
		}

		public Piece getLastPiece() {
			return piece;
		}

		public Position getStartPosition() {
			return startPos;
		}

		public Position getEndPosition() {
			return endPos;
		}

		public String toString() {
			return localTime.toString() + "\t" + moveNumber + "\t" + piece
					+ "\t" + startPos + "\t" + endPos;
		}
	}

	private static final int MAX_LENGTH_WIDTH = 8;

	private Cell[][] cell;

	private ArrayList<Move> moveList;
	private int moves;

	private PieceSet whiteSet;
	private PieceSet blackSet;

	/**
	 * Default constructor
	 */
	public Board() {
		cell = new Cell[MAX_LENGTH_WIDTH][MAX_LENGTH_WIDTH];

		for (int file = 0; file < MAX_LENGTH_WIDTH; file++) {
			for (int rank = 0; rank < MAX_LENGTH_WIDTH; rank++) {
				cell[file][rank] = new Cell(file, rank);
			}
		}

		whiteSet = new PieceSet(PieceType.Color.WHITE);
		blackSet = new PieceSet(PieceType.Color.BLACK);

		assignWhitePieces();
		assignBlackPieces();

		moveList = new ArrayList<Move>();
	}

	/**
	 * Accessor to retrieve the PieceSet for a white-piece Player
	 * 
	 * @return the white PieceSet
	 */
	public PieceSet getWhiteSet() {
		return whiteSet;
	}

	/**
	 * Accessor to retrieve the PieceSet for a black-piece Player
	 * 
	 * @return the black PieceSet
	 */
	public PieceSet getBlackSet() {
		return blackSet;
	}

	/**
	 * Accessor to retrieve a particular Cell within the Board
	 * 
	 * @param pos the Position of the desired Cell
	 * 
	 * @return the Cell with the given file and rank of a Position
	 */
	public Cell getCell(Position pos) {
		return cell[pos.getFile()][pos.getRank()];
	}

	/**
	 * Requested Piece will be moved to a Cell corresponding to the file and
	 * rank of newPosition, provided newPosition is a legal move for a given
	 * Piece
	 * 
	 * @param piece       accessed by a Player for a move
	 * @param newPosition the new Position desired by the Player for a Piece
	 * @param promoType   the PieceType to promote param piece to (if eligible)
	 * 
	 * @return true if successful, false otherwise
	 */
	public boolean movePiece(Piece piece, PieceSet pieceSet,
			Position newPosition, PieceType promoType) {
		boolean result = false;

		Cell oldPositionCell = getCell(piece.pos);
		Cell newPositionCell = getCell(newPosition);

		boolean isLegalMove = piece.isMoveLegal(cell, newPosition);
		
		King k = null;
		
		if(piece.isWhite()) {
			k = (King) whiteSet.getPiece(PieceType.KING);
		} else {
			k = (King) blackSet.getPiece(PieceType.KING);
		}
		
		if (isLegalMove) {
			Piece other = newPositionCell.piece;
			boolean pieceFoundAtNewPosition = other != null;

			if (pieceFoundAtNewPosition) {
				boolean allyPieceFound = piece.matchesColor(other);

				if (allyPieceFound) {
					// This prevents a Piece of the same color "taking"
					// another piece.
					result = false;
				} else {
					// At this point, a "piece" is taken.
					newPositionCell.piece = null;

					// result = true means we will allow our requested
					// piece to move to the cell with newPosition.
					result = true;
				}
			} else {
				// result = true means we will allow our requested
				// piece to move to the cell with newPosition.
				result = true;
			}

			// Since the Piece moved from the old location to the new location,
			// the Cell will no longer have a reference to that Piece.
			
			/**
			 * If a successful move is made,
			 * piece will be evaluated by pieceSet
			 * to determine if piece is a promotable Pawn.
			 */
			if (result) {
				
					boolean promoteWhite = promoType != null &&
							piece.isWhite() && newPosition.getRank() == 7;
						
					boolean promoteBlack = promoType != null &&
							piece.isBlack() && newPosition.getRank() == 0;
						
					if (promoteWhite || promoteBlack) {
						piece = pieceSet.promotePawn(piece, promoType);
					}
					

					
					// This statement nullifies any reference to a Piece
					// for this Cell object. (Next line: piece will be reassigned
					// to the newPositionCell.piece field).
					oldPositionCell.piece = null;

					// This statement affects what Pieces print
					// at which cells when board.toString() is called.
					newPositionCell.piece = piece;

					// This statement affects the internal position
					// data within a Piece object.
					piece.pos = newPosition;
					// piece.move(newPosition) // why use this? pos is protected.

					// System.out.println(this);
					//need to figure out how to prompt user to enter a valid legal move to make sure King is safe...
			}
			

			
			//System.out.println(k + "\nFILE: " + k.pos.getFile() + "\nRANK: " + k.pos.getRank());
			//System.out.println(k.hasValidMoves(cell, k.pos));
		
			++moves;

			Move newestMove = new Move(piece, oldPositionCell.loc, piece.pos,
					moves);
			moveList.add(newestMove);

		}

		canCheck(piece);
		return result;
	}

	/**
	 * Prints the log of moves as per the moveList field (ArrayList)
	 */
	public void printMoveLog() {
		System.out.println("MOVE LOG (ALL PIECES) ---------------------");

		String str = "";

		str += "Time\t\tMove #\tPiece\tStart\tEnd\n";
		str += "-------------------------------------------\n";

		System.out.print(str);

		for (Move mp : moveList) {
			System.out.println(mp);
		}

		System.out.println();
	}

	public boolean canCheck(Piece piece) {
		boolean result = false;

		Position oppositeKing = null;
		Piece oppositeK = null;

		if (piece.isWhite()) {
			oppositeK = getBlackSet().getPiece(PieceType.KING);
			oppositeKing = oppositeK.pos;
		} else if (piece.isBlack()) {
			oppositeK = getWhiteSet().getPiece(PieceType.KING);
			oppositeKing = oppositeK.pos;
		}

		boolean isMoveLegal = piece.isMoveLegal(cell, oppositeKing);

		if (isMoveLegal) {
			System.out.println("Check");
			result = true;
		}

		return result;
	}
	
	public boolean isKingSafe(King k) {
		boolean result = true;
		PieceSet opponent = null;
		
		if(k.isWhite())
			opponent = getBlackSet();
		else
			opponent = getWhiteSet();
		
		Piece BISH_L = opponent.getPiece(PieceType.BISHOP_L);
		Piece BISH_R = opponent.getPiece(PieceType.BISHOP_L);
		Piece KNIGHT_L = opponent.getPiece(PieceType.KNIGHT_L);
		Piece KNIGHT_R = opponent.getPiece(PieceType.KNIGHT_R);
		Piece ROOK_R = opponent.getPiece(PieceType.ROOK_R);
		Piece ROOK_L = opponent.getPiece(PieceType.ROOK_L);
		Piece PAWN_0 = opponent.getPiece(PieceType.PAWN_0);
		Piece PAWN_1 = opponent.getPiece(PieceType.PAWN_1);
		Piece PAWN_2 = opponent.getPiece(PieceType.PAWN_2);
		Piece PAWN_3 = opponent.getPiece(PieceType.PAWN_3);
		Piece PAWN_4 = opponent.getPiece(PieceType.PAWN_4);
		Piece PAWN_5 = opponent.getPiece(PieceType.PAWN_5);
		Piece PAWN_6 = opponent.getPiece(PieceType.PAWN_6);
		Piece PAWN_7 = opponent.getPiece(PieceType.PAWN_7);
		Piece QUEEN = opponent.getPiece(PieceType.QUEEN);
		
		if(ROOK_R.isMoveLegal(cell, k.pos) || ROOK_L.isMoveLegal(cell, k.pos) || QUEEN.isMoveLegal(cell, k.pos)
				|| PAWN_0.isMoveLegal(cell, k.pos) || PAWN_1.isMoveLegal(cell, k.pos) || PAWN_2.isMoveLegal(cell, k.pos)
				|| PAWN_3.isMoveLegal(cell, k.pos) || PAWN_4.isMoveLegal(cell, k.pos) || PAWN_5.isMoveLegal(cell, k.pos)
				|| PAWN_6.isMoveLegal(cell, k.pos) || PAWN_7.isMoveLegal(cell, k.pos) || BISH_L.isMoveLegal(cell, k.pos)
				|| BISH_R.isMoveLegal(cell, k.pos) || KNIGHT_L.isMoveLegal(cell, k.pos) || KNIGHT_R.isMoveLegal(cell, k.pos)) {	
			result = false;
		}
		
		return result;
	}

	/**
	 * Returns a string representation of the Board's state
	 */
	@Override
	public String toString() {
		String str = "";

		for (int rank = cell.length - 1; rank >= 0; rank--) {
			for (int file = 0; file < cell[rank].length; file++) {
				// FOR RELEASE: Print piece at cell
				if (cell[file][rank].piece == null) {
					if (rank % 2 != 0) {
						if (file % 2 != 0) {
							str += "##";
						} else {
							str += "  ";
						}
					} else {
						if (file % 2 == 0) {
							str += "##";
						} else {
							str += "  ";
						}
					}
				} else {
					// FOR RELEASE: Print Pieces
					str += cell[file][rank];

					// FOR DEBUGGING: See positions instead of Pieces
					// str += cell[file][rank].getPosition();
				}

				str += " "; // Two spaces in between cells

				if (file == cell[rank].length - 1) {
					// FOR RELEASE: Print real rank enumerations
					str += String.format("%d", rank + 1); // FOR RELEASE

					// FOR DEBUGGING: Print rank indices as per an array
					// str += String.format("%d", rank);
				}
			}

			str += "\n";
		}

		// FOR RELEASE: Print real file characters
		str += String.format(" a  b  c  d  e  f  g  h\n");

		// FOR DEBUGGING: Print file indices as per an array
		// str += String.format(" 0 1 2 3 4 5 6 7\n");

		return str;
	}

	/**
	 * Used during Board instantiation: initialize all Piece and Cell instances
	 * to their default starting positions prior to beginning a Chess match
	 */
	private void assignWhitePieces() {
		Piece king = whiteSet.getPiece(PieceType.KING);
		king.pos = cell[4][0].loc;
		cell[4][0].piece = king;

		Piece queen = whiteSet.getPiece(PieceType.QUEEN);
		queen.pos = cell[3][0].loc;
		cell[3][0].piece = queen;

		Piece bishop_r = whiteSet.getPiece(PieceType.BISHOP_R);
		bishop_r.pos = cell[5][0].loc;
		cell[5][0].piece = bishop_r;

		Piece bishop_l = whiteSet.getPiece(PieceType.BISHOP_L);
		bishop_l.pos = cell[2][0].loc;
		cell[2][0].piece = bishop_l;

		Piece knight_r = whiteSet.getPiece(PieceType.KNIGHT_R);
		knight_r.pos = cell[6][0].loc;
		cell[6][0].piece = knight_r;

		Piece knight_l = whiteSet.getPiece(PieceType.KNIGHT_L);
		knight_l.pos = cell[1][0].loc;
		cell[1][0].piece = knight_r;

		Piece rook_r = whiteSet.getPiece(PieceType.ROOK_R);
		rook_r.pos = cell[7][0].loc;
		cell[7][0].piece = rook_r;

		Piece rook_l = whiteSet.getPiece(PieceType.ROOK_L);
		rook_l.pos = cell[0][0].loc;
		cell[0][0].piece = rook_l;

		Piece pawn_0 = whiteSet.getPiece(PieceType.PAWN_0);
		pawn_0.pos = cell[0][1].loc;
		cell[0][1].piece = pawn_0;

		Piece pawn_1 = whiteSet.getPiece(PieceType.PAWN_1);
		pawn_1.pos = cell[1][1].loc;
		cell[1][1].piece = pawn_1;

		Piece pawn_2 = whiteSet.getPiece(PieceType.PAWN_2);
		pawn_2.pos = cell[2][1].loc;
		cell[2][1].piece = pawn_2;

		Piece pawn_3 = whiteSet.getPiece(PieceType.PAWN_3);
		pawn_3.pos = cell[3][1].loc;
		cell[3][1].piece = pawn_3;

		Piece pawn_4 = whiteSet.getPiece(PieceType.PAWN_4);
		pawn_4.pos = cell[4][1].loc;
		cell[4][1].piece = pawn_4;

		Piece pawn_5 = whiteSet.getPiece(PieceType.PAWN_5);
		pawn_5.pos = cell[5][1].loc;
		cell[5][1].piece = pawn_5;

		Piece pawn_6 = whiteSet.getPiece(PieceType.PAWN_6);
		pawn_6.pos = cell[6][1].loc;
		cell[6][1].piece = pawn_6;

		Piece pawn_7 = whiteSet.getPiece(PieceType.PAWN_7);
		pawn_7.pos = cell[7][1].loc;
		cell[7][1].piece = pawn_7;
	}

	/**
	 * Used during Board instantiation: initialize all Piece and Cell instances
	 * to their default starting positions prior to beginning a Chess match
	 */
	private void assignBlackPieces() {
		Piece king = blackSet.getPiece(PieceType.KING);
		king.pos = cell[4][7].loc;
		cell[4][7].piece = king;

		Piece queen = blackSet.getPiece(PieceType.QUEEN);
		queen.pos = cell[3][7].loc;
		cell[3][7].piece = queen;

		Piece bishop_r = blackSet.getPiece(PieceType.BISHOP_R);
		bishop_r.pos = cell[5][7].loc;
		cell[5][7].piece = bishop_r;

		Piece bishop_l = blackSet.getPiece(PieceType.BISHOP_L);
		bishop_l.pos = cell[2][7].loc;
		cell[2][7].piece = bishop_l;

		Piece knight_r = blackSet.getPiece(PieceType.KNIGHT_R);
		knight_r.pos = cell[6][7].loc;
		cell[6][7].piece = knight_r;

		Piece knight_l = blackSet.getPiece(PieceType.KNIGHT_L);
		knight_l.pos = cell[1][7].loc;
		cell[1][7].piece = knight_r;

		Piece rook_r = blackSet.getPiece(PieceType.ROOK_R);
		rook_r.pos = cell[7][7].loc;
		cell[7][7].piece = rook_r;

		Piece rook_l = blackSet.getPiece(PieceType.ROOK_L);
		rook_l.pos = cell[0][7].loc;
		cell[0][7].piece = rook_l;

		Piece pawn_0 = blackSet.getPiece(PieceType.PAWN_0);
		pawn_0.pos = cell[0][6].loc;
		cell[0][6].piece = pawn_0;

		Piece pawn_1 = blackSet.getPiece(PieceType.PAWN_1);
		pawn_1.pos = cell[1][6].loc;
		cell[1][6].piece = pawn_1;

		Piece pawn_2 = blackSet.getPiece(PieceType.PAWN_2);
		pawn_2.pos = cell[2][6].loc;
		cell[2][6].piece = pawn_2;

		Piece pawn_3 = blackSet.getPiece(PieceType.PAWN_3);
		pawn_3.pos = cell[3][6].loc;
		cell[3][6].piece = pawn_3;

		Piece pawn_4 = blackSet.getPiece(PieceType.PAWN_4);
		pawn_4.pos = cell[4][6].loc;
		cell[4][6].piece = pawn_4;

		Piece pawn_5 = blackSet.getPiece(PieceType.PAWN_5);
		pawn_5.pos = cell[5][6].loc;
		cell[5][6].piece = pawn_5;

		Piece pawn_6 = blackSet.getPiece(PieceType.PAWN_6);
		pawn_6.pos = cell[6][6].loc;
		cell[6][6].piece = pawn_6;

		Piece pawn_7 = blackSet.getPiece(PieceType.PAWN_7);
		pawn_7.pos = cell[7][6].loc;
		cell[7][6].piece = pawn_7;
	}
}
