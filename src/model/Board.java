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
		private Position pos;
		private Piece piece;
		private boolean occupied;

		public Cell(Position pos) {
			this.pos = pos;
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
		cell = new Cell[8][8];
		
		for (int file = 0; file < cell.length; file++) {
			for (int rank = 0; rank < cell[file].length; rank++) {
				Cell newCell = new Cell(new Position(file, rank));	
				cell[file][rank] = newCell;
			}
		}
		
		// not sure if a cell should be aware of position,
		// or piece should be aware of position, or both.
		
		// Test instantiation of a black pawn at 0,0 (not the right place)
		// but needed it to test toString of Board
		cell[3][1].assignPiece(new Pawn(Color.BLACK, new Position(0, 0)));
	}
	
	@Override
	public String toString() {
		String str = "";

		for (int rank = cell.length - 1; rank >= 0; rank--) {
			for (int file = 0; file < cell[rank].length; file++) {
				str += cell[file][rank];
				str += " ";
				
				if (file == cell[rank].length - 1) {
					//str += String.format("%d", rank + 1);	// FOR RELEASE
					str += String.format("%d", rank);
				}
			}
			str += "\n";
		}

		
		//str += String.format(" a  b  c  d  e  f  g  h\n");	// FOR RELEASE
		str += String.format(" 0  1  2  3  4  5  6  7\n");
		
		return str;
	}
}
