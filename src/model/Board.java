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
package model;

import java.util.*;

import model.Piece.Color;
import model.PieceSet.PieceType;

public class Board {
	
	/**
	 * @version Mar 3, 2019
	 * @author gemuelealudino
	 *
	 */
	private static final class PieceStart {
		static final Position POSITION_WK = new Position(4, 0);
		static final Position POSITION_WQ = new Position(3, 0);
		static final Position POSITION_WB_R = new Position(5, 0);
		static final Position POSITION_WB_L = new Position(2, 0);
		static final Position POSITION_WN_R = new Position(6, 0);
		static final Position POSITION_WN_L = new Position(1, 0);
		static final Position POSITION_WR_R = new Position(7, 0);
		static final Position POSITION_WR_L = new Position(0, 0);
		
		static final Position POSITION_WP_0 = new Position(0, 1);
		static final Position POSITION_WP_1 = new Position(1, 1);
		static final Position POSITION_WP_2 = new Position(2, 1);
		static final Position POSITION_WP_3 = new Position(3, 1);
		static final Position POSITION_WP_4 = new Position(4, 1);
		static final Position POSITION_WP_5 = new Position(5, 1);
		static final Position POSITION_WP_6 = new Position(6, 1);
		static final Position POSITION_WP_7 = new Position(7, 1);
		
		static final Position POSITION_BK = new Position(4, 7);
		static final Position POSITION_BQ = new Position(3, 7);
		static final Position POSITION_BB_R = new Position(5, 7);
		static final Position POSITION_BB_L = new Position(2, 7);
		static final Position POSITION_BN_R = new Position(6, 7);
		static final Position POSITION_BN_L = new Position(1, 7);
		static final Position POSITION_BR_R = new Position(7, 7);
		static final Position POSITION_BR_L = new Position(0, 7);
		
		static final Position POSITION_BP_0 = new Position(0, 6);
		static final Position POSITION_BP_1 = new Position(1, 6);
		static final Position POSITION_BP_2 = new Position(2, 6);
		static final Position POSITION_BP_3 = new Position(3, 6);
		static final Position POSITION_BP_4 = new Position(4, 6);
		static final Position POSITION_BP_5 = new Position(5, 6);
		static final Position POSITION_BP_6 = new Position(6, 6);
		static final Position POSITION_BP_7 = new Position(7, 6);
	}
	
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
	
	public Board() {
	
		cell = new Cell[MAX_LENGTH_WIDTH][MAX_LENGTH_WIDTH];
		
		for (int file = 0; file < MAX_LENGTH_WIDTH; file++) {
			for (int rank = 0; rank < MAX_LENGTH_WIDTH; rank++) {
				cell[file][rank] = new Cell(file, rank);
			}
		}
		
		whiteSet = new PieceSet(Color.WHITE);
		blackSet = new PieceSet(Color.BLACK);
		
		assignWhitePieces();
		assignBlackPieces();
	}
	
	/**
	 * 
	 */
	public void assignWhitePieces() {
		cell[4][0].piece = whiteSet.piece[PieceType.KING.ordinal()];
		cell[4][0].piece.pos = cell[4][0].loc;
		
		cell[3][0].piece = whiteSet.piece[PieceType.QUEEN.ordinal()];
		cell[3][0].piece.pos = cell[3][0].loc;
		
		cell[5][0].piece = whiteSet.piece[PieceType.BISHOP_R.ordinal()];
		cell[5][0].piece.pos = cell[5][0].loc;
		
		cell[2][0].piece = whiteSet.piece[PieceType.BISHOP_L.ordinal()];
		cell[2][0].piece.pos = cell[2][0].loc;

		cell[6][0].piece = whiteSet.piece[PieceType.KNIGHT_R.ordinal()];
		cell[6][0].piece.pos = cell[6][0].loc;
		
		cell[1][0].piece = whiteSet.piece[PieceType.KNIGHT_L.ordinal()];
		cell[1][0].piece.pos = cell[1][0].loc;
		
		cell[7][0].piece = whiteSet.piece[PieceType.ROOK_R.ordinal()];
		cell[7][0].piece.pos = cell[7][0].loc;
		
		cell[0][0].piece = whiteSet.piece[PieceType.ROOK_L.ordinal()];
		cell[0][0].piece.pos = cell[0][0].loc;
		
		cell[0][1].piece = whiteSet.piece[PieceType.PAWN_0.ordinal()];
		cell[0][1].piece.pos = cell[0][1].loc;
		
		cell[1][1].piece = whiteSet.piece[PieceType.PAWN_1.ordinal()];
		cell[1][1].piece.pos = cell[1][1].loc;
		
		cell[2][1].piece = whiteSet.piece[PieceType.PAWN_2.ordinal()];
		cell[2][1].piece.pos = cell[2][1].loc;
		
		cell[3][1].piece = whiteSet.piece[PieceType.PAWN_3.ordinal()];
		cell[3][1].piece.pos = cell[3][1].loc;
		
		cell[4][1].piece = whiteSet.piece[PieceType.PAWN_4.ordinal()];
		cell[4][1].piece.pos = cell[4][1].loc;
		
		cell[5][1].piece = whiteSet.piece[PieceType.PAWN_5.ordinal()];
		cell[5][1].piece.pos = cell[5][1].loc;
		
		cell[6][1].piece = whiteSet.piece[PieceType.PAWN_6.ordinal()];
		cell[6][1].piece.pos = cell[6][1].loc;
		
		cell[7][1].piece = whiteSet.piece[PieceType.PAWN_7.ordinal()];
		cell[7][1].piece.pos = cell[7][1].loc;
	}
	
	public void assignBlackPieces() {
		cell[4][7].piece = blackSet.piece[PieceType.KING.ordinal()];
		cell[4][7].piece.pos = cell[4][7].loc;
		
		cell[3][7].piece = blackSet.piece[PieceType.QUEEN.ordinal()];
		cell[3][7].piece.pos = cell[3][7].loc;
		
		cell[5][7].piece = blackSet.piece[PieceType.BISHOP_R.ordinal()];
		cell[5][7].piece.pos = cell[5][7].loc;
		
		cell[2][7].piece = blackSet.piece[PieceType.BISHOP_L.ordinal()];
		cell[2][7].piece.pos = cell[2][7].loc;

		cell[6][7].piece = blackSet.piece[PieceType.KNIGHT_R.ordinal()];
		cell[6][7].piece.pos = cell[6][7].loc;
		
		cell[1][7].piece = blackSet.piece[PieceType.KNIGHT_L.ordinal()];
		cell[1][7].piece.pos = cell[1][7].loc;
		
		cell[7][7].piece = blackSet.piece[PieceType.ROOK_R.ordinal()];
		cell[7][7].piece.pos = cell[7][7].loc;
		
		cell[0][7].piece = blackSet.piece[PieceType.ROOK_L.ordinal()];
		cell[0][7].piece.pos = cell[0][7].loc;
		
		cell[0][6].piece = blackSet.piece[PieceType.PAWN_0.ordinal()];
		cell[0][6].piece.pos = cell[0][6].loc;
		
		cell[1][6].piece = blackSet.piece[PieceType.PAWN_1.ordinal()];
		cell[1][6].piece.pos = cell[1][6].loc;
		
		cell[2][6].piece = blackSet.piece[PieceType.PAWN_2.ordinal()];
		cell[2][6].piece.pos = cell[2][6].loc;
		
		cell[3][6].piece = blackSet.piece[PieceType.PAWN_3.ordinal()];
		cell[3][6].piece.pos = cell[3][6].loc;
		
		cell[4][6].piece = blackSet.piece[PieceType.PAWN_4.ordinal()];
		cell[4][6].piece.pos = cell[4][6].loc;
		
		cell[5][6].piece = blackSet.piece[PieceType.PAWN_5.ordinal()];
		cell[5][6].piece.pos = cell[5][6].loc;
		
		cell[6][6].piece = blackSet.piece[PieceType.PAWN_6.ordinal()];
		cell[6][6].piece.pos = cell[6][6].loc;
		
		cell[7][6].piece = blackSet.piece[PieceType.PAWN_7.ordinal()];
		cell[7][6].piece.pos = cell[7][6].loc;
	}

	/**
	 * 
	 * @param file
	 * @param rank
	 * @param newFile
	 * @param newRank
	 * @return
	 */
	/*
	public boolean makeMove(int file, int rank, int newFile, int newRank) {
		Position playerMove = new Position(newFile, newRank);
		
		if (file == newFile && rank == newRank) {
			return false;
		}
		
		// retrieve the Piece to move
		Piece piece = cell[file][rank].piece;
		
		if (piece == null) {
			return false;
		}
		
		boolean isLegalMove = piece.move(newFile, newRank);
		// if returns true, piece has its position data changed to new data.
		
		if (isLegalMove) {
			Piece other = cell[newFile][newRank].piece;
			
			if (other != null) {
				if (piece.color.ordinal() == other.color.ordinal()) {
					return false;
				} else {
					cell[newFile][newRank].piece = null;
				}
			}
			
			// move piece to desired file/rank
			cell[newFile][newRank].piece = piece;
			
			// remove reference to piece within former file/rank
			cell[file][rank].piece = null;
		} else {
			return false;
		}
		
		return true;
	}
	*/
	
	public boolean makeMove(int file, int rank, int newFile, int newRank) {
		
		
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
					str += cell[file][rank];
				}
				
				// Necessary space in between cells
				str += " ";
				
				if (file == cell[rank].length - 1) {
					// FOR RELEASE: Print real rank enumerations
					str += String.format("%d", rank + 1);	// FOR RELEASE
					
					// FOR DEBUGGING: Print rank indices as per an array
					//str += String.format("%d", rank);
				}
			}
			
			str += "\n";
		}

		// FOR RELEASE: Print real file characters
		str += String.format(" a  b  c  d  e  f  g  h\n");
		
		// FOR DEBUGGING: Print file indices as per an array
		//str += String.format(" 0  1  2  3  4  5  6  7\n");
		
		return str;
	}
}
