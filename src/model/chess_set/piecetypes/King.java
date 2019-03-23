/**
 * King.java
 *
 * Copyright (c) 2019 Gemuele Aludino, Patrick Nogaj. 
 * All rights reserved.
 *
 * Rutgers University: School of Arts and Sciences
 * 01:198:213 Software Methodology, Spring 2019
 * Professor Seshadri Venugopal
 */
package model.chess_set.piecetypes;

import model.PieceType;
import model.chess_set.Board.Cell;
import model.chess_set.Piece;
import model.game.Position;

/**
 * @version Mar 3, 2019
 * @author gemuelealudino
 */
public class King extends Piece {
	
	private boolean castled;

	/**
	 * Parameterized constructor 
	 *
	 * @param color the Color of a Player's PieceSet
	 */
	public King(PieceType.Color color) {
		super(color);
		pieceType = PieceType.KING;
		this.castled = false;

		identifier += "King      ";
	}
	
	/* (non-Javadoc)
	 * @see model.chess_set.Piece#isMoveLegal(model.chess_set.Board.Cell[][], model.game.Position)
	 */
	@Override
	protected boolean isMoveLegal(Cell[][] cell, Position pos) {
		boolean result = true;
		
		if(Math.abs(this.pos.getFile() - pos.getFile()) <= 1 && (Math.abs(this.pos.getRank() - pos.getRank()) <= 1)) {
			
			//Castling logic.
			if(pos.getFile() - this.pos.getFile() == 2 && pos.getRank() == this.pos.getRank()) {
				if(cell[pos.getRank()][this.pos.getFile() + 1] != null || cell[pos.getRank()][this.pos.getFile() + 2] != null) {
					castled = false;
					result = false;
				}
			} else if(this.pos.getFile() - pos.getFile() == 3 && this.pos.getRank() == pos.getRank()) {
				if(cell[pos.getRank()][this.pos.getFile() - 1] != null || cell[pos.getRank()][this.pos.getFile() - 2] != null || cell[pos.getRank()][this.pos.getFile() - 3] != null) {
					castled = false;
					result = false;
				}
			} else {
				castled = false;
				result = false;
			}
			castled = true;
		}
		return result;
	}

	@Override
	public String toString() {
		return super.toString() + "K";
	}
}
