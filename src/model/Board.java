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

/**
 * @version Mar 3, 2019
 * @author gemuelealudino
 *
 */
public class Board {
	
	public class Cell {
		private Piece piece;
		private boolean occupied;

		public Cell() {
			piece = null;
			occupied = false;
		}
		
		public void assignPiece(Piece piece) {
			this.piece = piece;
			occupied = true;
		}
		
		@Override
		public String toString() {
			return occupied ? piece.toString() : "--";
		}
	}
	
	private Cell[][] cell;

	public Board() {
		final Position POSITION_WK = new Position(4, 0);
		final Position POSITION_WQ = new Position(3, 0);
		final Position POSITION_WB_R = new Position(5, 0);
		final Position POSITION_WB_L = new Position(2, 0);
		final Position POSITION_WN_R = new Position(6, 0);
		final Position POSITION_WN_L = new Position(1, 0);
		final Position POSITION_WR_R = new Position(7, 0);
		final Position POSITION_WR_L = new Position(0, 0);
		
		final Position POSITION_WP_0 = new Position(0, 1);
		final Position POSITION_WP_1 = new Position(1, 1);
		final Position POSITION_WP_2 = new Position(2, 1);
		final Position POSITION_WP_3 = new Position(3, 1);
		final Position POSITION_WP_4 = new Position(4, 1);
		final Position POSITION_WP_5 = new Position(5, 1);
		final Position POSITION_WP_6 = new Position(6, 1);
		final Position POSITION_WP_7 = new Position(7, 1);
		
		final Position POSITION_BK = new Position(4, 7);
		final Position POSITION_BQ = new Position(3, 7);
		final Position POSITION_BB_R = new Position(5, 7);
		final Position POSITION_BB_L = new Position(2, 7);
		final Position POSITION_BN_R = new Position(6, 7);
		final Position POSITION_BN_L = new Position(1, 7);
		final Position POSITION_BR_R = new Position(7, 7);
		final Position POSITION_BR_L = new Position(0, 7);
		
		final Position POSITION_BP_0 = new Position(0, 6);
		final Position POSITION_BP_1 = new Position(1, 6);
		final Position POSITION_BP_2 = new Position(2, 6);
		final Position POSITION_BP_3 = new Position(3, 6);
		final Position POSITION_BP_4 = new Position(4, 6);
		final Position POSITION_BP_5 = new Position(5, 6);
		final Position POSITION_BP_6 = new Position(6, 6);
		final Position POSITION_BP_7 = new Position(7, 6);
		
		// Instantiate 2D array of cells, that represent the board.
		cell = new Cell[8][8];
		
		// Instantiate all cells with Position object.
		for (int file = 0; file < cell.length; file++) {
			for (int rank = 0; rank < cell[file].length; rank++) {	
				cell[file][rank] = new Cell();
			}
		}
		
		// not sure if a cell should be aware of position,
		// or piece should be aware of position, or both.

		cell[4][0].assignPiece(new King(Color.WHITE, POSITION_WK));
		cell[3][0].assignPiece(new Queen(Color.WHITE, POSITION_WQ));
		cell[5][0].assignPiece(new Bishop(Color.WHITE, POSITION_WB_R));
		cell[2][0].assignPiece(new Bishop(Color.WHITE, POSITION_WB_L));
		cell[6][0].assignPiece(new Knight(Color.WHITE, POSITION_WN_R));
		cell[1][0].assignPiece(new Knight(Color.WHITE, POSITION_WN_L));
		cell[7][0].assignPiece(new Rook(Color.WHITE, POSITION_WR_R));
		cell[0][0].assignPiece(new Rook(Color.WHITE, POSITION_WR_L));
		
		cell[0][1].assignPiece(new Pawn(Color.WHITE, POSITION_WP_0));
		cell[1][1].assignPiece(new Pawn(Color.WHITE, POSITION_WP_1));
		cell[2][1].assignPiece(new Pawn(Color.WHITE, POSITION_WP_2));
		cell[3][1].assignPiece(new Pawn(Color.WHITE, POSITION_WP_3));
		cell[4][1].assignPiece(new Pawn(Color.WHITE, POSITION_WP_4));
		cell[5][1].assignPiece(new Pawn(Color.WHITE, POSITION_WP_5));
		cell[6][1].assignPiece(new Pawn(Color.WHITE, POSITION_WP_6));
		cell[7][1].assignPiece(new Pawn(Color.WHITE, POSITION_WP_7));
		
		cell[4][7].assignPiece(new King(Color.BLACK, POSITION_BK));
		cell[3][7].assignPiece(new Queen(Color.BLACK, POSITION_BQ));
		cell[5][7].assignPiece(new Bishop(Color.BLACK, POSITION_BB_R));
		cell[2][7].assignPiece(new Bishop(Color.BLACK, POSITION_BB_L));
		cell[6][7].assignPiece(new Knight(Color.BLACK, POSITION_BN_R));
		cell[1][7].assignPiece(new Knight(Color.BLACK, POSITION_BN_L));
		cell[7][7].assignPiece(new Rook(Color.BLACK, POSITION_BR_R));
		cell[0][7].assignPiece(new Rook(Color.BLACK, POSITION_BR_L));
		
		cell[0][6].assignPiece(new Pawn(Color.BLACK, POSITION_BP_0));
		cell[1][6].assignPiece(new Pawn(Color.BLACK, POSITION_BP_1));
		cell[2][6].assignPiece(new Pawn(Color.BLACK, POSITION_BP_2));
		cell[3][6].assignPiece(new Pawn(Color.BLACK, POSITION_BP_3));
		cell[4][6].assignPiece(new Pawn(Color.BLACK, POSITION_BP_4));
		cell[5][6].assignPiece(new Pawn(Color.BLACK, POSITION_BP_5));
		cell[6][6].assignPiece(new Pawn(Color.BLACK, POSITION_BP_6));
		cell[7][6].assignPiece(new Pawn(Color.BLACK, POSITION_BP_7));


	}
	
	@Override
	public String toString() {
		String str = "";

		for (int rank = cell.length - 1; rank >= 0; rank--) {
			for (int file = 0; file < cell[rank].length; file++) {
				
				// FOR DEBUGGING: Print file/rank indices 
				//str += String.format("%d%d", cell[file][rank].pos.getFile(), cell[file][rank].pos.getRank());

				// FOR DEBUGGING: Print file/rank markings per cell
				//str += cell[file][rank].pos;	// FILE/RANK MARKS
				
				// FOR RELEASE: Print piece at cell
				if (cell[file][rank].toString().equals("--")) {
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
}
