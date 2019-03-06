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

import model.PieceType;
import model.game.Position;

public class Board {
	
	/**
	 * @version Mar 3, 2019
	 * @author gemuelealudino
	 * 
	 */
	public class Cell {
		private Position loc;
		private Piece piece;
		
		public Cell(int file, int rank) {
			loc = new Position(file, rank);
			piece = null;
		}
		
		public Piece getPiece() {
			return piece;
		}
		
		public Position getPosition() {
			return loc;
		}
		
		public String toString() {
			return piece == null ? "--" : piece.toString();
		}
	}
	
	private static final int MAX_LENGTH_WIDTH = 8;
	
	private Cell[][] cell;
	
	private PieceSet whiteSet;
	private PieceSet blackSet;
	
	/**
	 * 
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
	}
	
	/**
	 * 
	 * @return the PieceSet for a white-piece player
	 */
	public PieceSet getWhiteSet() {
		return whiteSet;
	}
	
	/**
	 * 
	 * @return the PieceSet for a black-piece player
	 */
	public PieceSet getBlackSet() {
		return blackSet;
	}
	
	/**
	 * 
	 * @param pos the Position of the desired Cell
	 * @return the Cell with the given file and rank of a Position
	 */
	public Cell getCell(Position pos) {
		return cell[pos.getFile()][pos.getRank()];
	}
	
	/**
	 * 
	 * @param piece accessed by a Player for a move
	 * @param newPosition the new Position desired by the Player for a Piece
	 * @return true if successful, false otherwise
	 */
	public boolean movePiece(Piece piece, Position newPosition) {
		Cell oldPositionCell = getCell(piece.getPosition());
		Cell newPositionCell = getCell(newPosition);
		
		boolean isLegalMove = piece.move(newPosition);
		// At this point, a Piece will have been moved to its new position,
		// provided it was legal.
		
		if (isLegalMove) {
			Piece other = newPositionCell.piece;
			
			if (other != null) {
				if (piece.getColor().equals(other.getColor())) {
					// This prevents a Piece of the same color "taking"
					// another piece.
					return false;
				} else {
					// At this point, a "piece" is taken.
					// Perhaps a boolean variable would be better,
					// instead of setting something null or not.
					// An inactive piece would require an update to toString
					// to ensure that it does not appear on the board.
					newPositionCell.piece = null;
				}
			}
			
			// Since the Piece moved from the old location to the new location,
			// the Cell will no longer have a reference to that Piece.
			oldPositionCell.piece = null;
		}
		
		return false;
	}
	
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
					//str += cell[file][rank].getPosition();
				}
				
				str += " ";	// Two spaces in between cells
				
				if (file == cell[rank].length - 1) {
					// FOR RELEASE: Print real rank enumerations
					//str += String.format("%d", rank + 1);	// FOR RELEASE
					
					// FOR DEBUGGING: Print rank indices as per an array
					str += String.format("%d", rank);
				}
			}
			
			str += "\n";
		}

		// FOR RELEASE: Print real file characters
		//str += String.format(" a  b  c  d  e  f  g  h\n");
		
		// FOR DEBUGGING: Print file indices as per an array
		str += String.format(" 0  1  2  3  4  5  6  7\n");
		
		return str;
	}
	
	/**
	 * 
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
		cell[1][1].piece = pawn_0;
		
		Piece pawn_2 = whiteSet.getPiece(PieceType.PAWN_2);
		pawn_2.pos = cell[2][1].loc;
		cell[2][1].piece = pawn_0;
		
		Piece pawn_3 = whiteSet.getPiece(PieceType.PAWN_3);
		pawn_3.pos = cell[3][1].loc;
		cell[3][1].piece = pawn_0;
		
		Piece pawn_4 = whiteSet.getPiece(PieceType.PAWN_4);
		pawn_4.pos = cell[4][1].loc;
		cell[4][1].piece = pawn_0;
		
		Piece pawn_5 = whiteSet.getPiece(PieceType.PAWN_5);
		pawn_5.pos = cell[5][1].loc;
		cell[5][1].piece = pawn_0;
		
		Piece pawn_6 = whiteSet.getPiece(PieceType.PAWN_6);
		pawn_6.pos = cell[6][1].loc;
		cell[6][1].piece = pawn_0;
		
		Piece pawn_7 = whiteSet.getPiece(PieceType.PAWN_7);
		pawn_7.pos = cell[7][1].loc;
		cell[7][1].piece = pawn_0;
	}
	
	/**
	 * 
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
		cell[1][6].piece = pawn_0;
		
		Piece pawn_2 = blackSet.getPiece(PieceType.PAWN_2);
		pawn_2.pos = cell[2][6].loc;
		cell[2][6].piece = pawn_0;
		
		Piece pawn_3 = blackSet.getPiece(PieceType.PAWN_3);
		pawn_3.pos = cell[3][6].loc;
		cell[3][6].piece = pawn_0;
		
		Piece pawn_4 = blackSet.getPiece(PieceType.PAWN_4);
		pawn_4.pos = cell[4][6].loc;
		cell[4][6].piece = pawn_0;
		
		Piece pawn_5 = blackSet.getPiece(PieceType.PAWN_5);
		pawn_5.pos = cell[5][6].loc;
		cell[5][6].piece = pawn_0;
		
		Piece pawn_6 = blackSet.getPiece(PieceType.PAWN_6);
		pawn_6.pos = cell[6][6].loc;
		cell[6][6].piece = pawn_0;
		
		Piece pawn_7 = blackSet.getPiece(PieceType.PAWN_7);
		pawn_7.pos = cell[7][6].loc;
		cell[7][6].piece = pawn_0;
	}
}
