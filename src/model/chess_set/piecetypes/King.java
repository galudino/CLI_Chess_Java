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
	
	/**
	 * Determines if an instance of King is castled, or not
	 * 
	 * @return true if King is castled, false otherwise
	 */
	public boolean isCastled() {
		return castled;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.chess_set.Piece#isMoveLegal(model.chess_set.Board.Cell[][],
	 * model.game.Position)
	 */
	@Override
	protected boolean isMoveLegal(Cell[][] cell, Position pos) {
		boolean result = true;

		if (Math.abs(this.pos.getFile() - pos.getFile()) <= 1
				&& (Math.abs(this.pos.getRank() - pos.getRank()) <= 1)) {

			// Castling logic.
			if (pos.getFile() - this.pos.getFile() == 2
					&& pos.getRank() == this.pos.getRank()) {
				if (cell[pos.getRank()][this.pos.getFile() + 1] != null
						|| cell[pos.getRank()][this.pos.getFile()
								+ 2] != null) {
					castled = false;
					result = false;
				}
			} else if (this.pos.getFile() - pos.getFile() == 3
					&& this.pos.getRank() == pos.getRank()) {
				if (cell[pos.getRank()][this.pos.getFile() - 1] != null
						|| cell[pos.getRank()][this.pos.getFile() - 2] != null
						|| cell[pos.getRank()][this.pos.getFile()
								- 3] != null) {
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

		//@formatter:off
		/*
		boolean result = true;
		
		final int deltaFile = Math.abs(this.pos.getFile() - pos.getFile());
		final int deltaRank = Math.abs(this.pos.getRank() - pos.getRank());
		
		final boolean sameRankAsOpponent = pos.getRank() == this.pos.getRank();
		
		if (deltaFile <= 1 && deltaRank <= 1) {
			if (deltaFile == 2 && sameRankAsOpponent) {
				if (cell[pos.getRank()][this.pos.getFile() + 1] != null || 
						cell[pos.getRank()][this.pos.getFile() + 2] != null) {
					castled = false;
					result = false;
				}
			} else if (deltaFile == 3 && sameRankAsOpponent) {
				if (cell[pos.getRank()][this.pos.getFile() - 1] != null ||
						cell[pos.getRank()][this.pos.getFile() - 2] != null ||
						cell[pos.getRank()][this.pos.getFile() - 3] != null) {
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
		*/
		//@formatter:on
	}

	@Override
	public String toString() {
		return super.toString() + "K";
	}
}
