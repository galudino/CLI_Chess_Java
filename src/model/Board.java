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

		public Cell(Piece piece) {
			this.piece = piece;
			
			occupied = piece != null ? true : false;
		}
		
		@Override
		public String toString() {
			String str = "";
			
			String placeholder = "";
			
			// determine if a placeholder is "##" or " "
			// by field pos.

			
			str = occupied ? piece.toString() : placeholder;
			
			return str;
		}
	}
	
	private Cell[][] cell;
	
	public Board() {
		cell = new Cell[8][8];
		
		for (int file = 0; file < cell.length; file++) {
			for (int rank = 0; rank < cell[file].length; rank++) {
				Cell newCell = new Cell(new Pawn(Color.BLACK, new Position(0, 0)));
				
				cell[file][rank] = newCell;
			}
		
		}
	
	}
	
	@Override
	public String toString() {
		String str = "";
		
		final int CELL_LENGTH = cell.length;
		
		for (int file = 0; file < CELL_LENGTH; file++) {
			for (int rank = cell[file].length - 1; rank >= 0; rank--) {
				str += cell[file][rank];
				str += " ";
				
				if (rank == 0) {
					str += String.format("%d", CELL_LENGTH - file);
				}
			}
			
			str += "\n";
		}
		
		str += String.format(" a  b  c  d  e  f  g  h\n");
		
		return str;
	}
}
