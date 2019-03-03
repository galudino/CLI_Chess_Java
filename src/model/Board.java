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
	
	public static final Position whiteKing = new Position(4, 0);
	
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
			String str = "";
			
			String placeholder = "--";
			
			// determine if a placeholder is "##" or " "
			// by field pos.
			
			str = occupied ? piece.toString() : placeholder;
			
			return str;
		}
	}
	
	private Cell[][] cell;
	
	public Board() {
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

		cell[4][0].assignPiece(new King(Color.WHITE, new Position(4, 0)));
		
		
		// Now we would instantiate all cells with pieces,
		// and where they would go.
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
