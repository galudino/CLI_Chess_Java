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
	
	public Board() {
		cell = new Cell[MAX_LENGTH_WIDTH][MAX_LENGTH_WIDTH];
		
		for (int file = 0; file < MAX_LENGTH_WIDTH; file++) {
			for (int rank = 0; rank < MAX_LENGTH_WIDTH; rank++) {
				cell[file][rank] = new Cell(file, rank);
			}
		}
	
		cell[4][0].piece = new King(Color.WHITE, PieceStart.POSITION_WK);
		cell[3][0].piece = new Queen(Color.WHITE, PieceStart.POSITION_WQ);
		cell[5][0].piece = new Bishop(Color.WHITE, PieceStart.POSITION_WB_R);
		cell[2][0].piece = new Bishop(Color.WHITE, PieceStart.POSITION_WB_L);
		cell[6][0].piece = new Knight(Color.WHITE, PieceStart.POSITION_WN_R);
		cell[1][0].piece = new Knight(Color.WHITE, PieceStart.POSITION_WN_L);
		cell[7][0].piece = new Rook(Color.WHITE, PieceStart.POSITION_WR_R);
		cell[0][0].piece = new Rook(Color.WHITE, PieceStart.POSITION_WR_L);
	
		cell[0][1].piece = new Pawn(Color.WHITE, PieceStart.POSITION_WP_0);
		cell[1][1].piece = new Pawn(Color.WHITE, PieceStart.POSITION_WP_1);
		cell[2][1].piece = new Pawn(Color.WHITE, PieceStart.POSITION_WP_2);
		cell[3][1].piece = new Pawn(Color.WHITE, PieceStart.POSITION_WP_3);
		cell[4][1].piece = new Pawn(Color.WHITE, PieceStart.POSITION_WP_4);
		cell[5][1].piece = new Pawn(Color.WHITE, PieceStart.POSITION_WP_5);
		cell[6][1].piece = new Pawn(Color.WHITE, PieceStart.POSITION_WP_6);
		cell[7][1].piece = new Pawn(Color.WHITE, PieceStart.POSITION_WP_7);
		
		cell[4][7].piece = new King(Color.BLACK, PieceStart.POSITION_BK);
		cell[3][7].piece = new Queen(Color.BLACK, PieceStart.POSITION_BQ);
		cell[5][7].piece = new Bishop(Color.BLACK, PieceStart.POSITION_BB_R);
		cell[2][7].piece = new Bishop(Color.BLACK, PieceStart.POSITION_BB_L);
		cell[6][7].piece = new Knight(Color.BLACK, PieceStart.POSITION_BN_R);
		cell[1][7].piece = new Knight(Color.BLACK, PieceStart.POSITION_BN_L);
		cell[7][7].piece = new Rook(Color.BLACK, PieceStart.POSITION_BR_R);
		cell[0][7].piece = new Rook(Color.BLACK, PieceStart.POSITION_BR_L);
		
		cell[0][6].piece = new Pawn(Color.BLACK, PieceStart.POSITION_BP_0);
		cell[1][6].piece = new Pawn(Color.BLACK, PieceStart.POSITION_BP_1);
		cell[2][6].piece = new Pawn(Color.BLACK, PieceStart.POSITION_BP_2);
		cell[3][6].piece = new Pawn(Color.BLACK, PieceStart.POSITION_BP_3);
		cell[4][6].piece = new Pawn(Color.BLACK, PieceStart.POSITION_BP_4);
		cell[5][6].piece = new Pawn(Color.BLACK, PieceStart.POSITION_BP_5);
		cell[6][6].piece = new Pawn(Color.BLACK, PieceStart.POSITION_BP_6);
		cell[7][6].piece = new Pawn(Color.BLACK, PieceStart.POSITION_BP_7);
	}

	/**
	 * 
	 * @param file
	 * @param rank
	 * @param newFile
	 * @param newRank
	 * @return
	 */
	public boolean makeMove(int file, int rank, int newFile, int newRank) {
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
				}
				
				if (piece.getPosition().equals(other.getPosition())) {
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
