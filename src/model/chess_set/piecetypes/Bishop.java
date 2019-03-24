/**
 * Bishop.java
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
import model.chess_set.Piece;
import model.chess_set.Board.Cell;
import model.game.Position;

/**
 * @version Mar 3, 2019
 * @author gemuelealudino
 */
public class Bishop extends Piece {

	/**
	 * Parameterized constructor
	 * 
	 * @param pieceType the PieceType to assign
	 * @param color     the Color of a Player's PieceSet
	 */
	public Bishop(PieceType pieceType, PieceType.Color color) {
		super(color);

		this.pieceType = pieceType.equals(PieceType.BISHOP_R)
				|| pieceType.equals(PieceType.BISHOP_L) ? pieceType : null;

		if (this.pieceType == null) {
			System.err.println("ERROR: Set this piece to either "
					+ "PieceType.BISHOP_R or PieceType.BISHOP_L!");
			identifier += " (invalid)";
		} else {
			identifier += "Bishop";

			identifier += pieceType.equals(PieceType.BISHOP_R) ? " (right)"
					: " (left)";
		}
	}

	public Bishop(PieceType.Color color) {
		super(color);
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

		if (this.pos.getRank() == pos.getRank()
				|| this.pos.getFile() == pos.getFile()) {
			result = false;
		}

		if (Math.abs(this.pos.getRank() - pos.getRank()) 
				!= Math.abs(this.pos.getFile() - pos.getFile())) {
			result = false;
		}

		int rowOffset, colOffset;

		if (this.pos.getFile() < pos.getFile()) {
			colOffset = 1;
		} else {
			colOffset = -1;
		}

		if (this.pos.getRank() < pos.getRank()) {
			rowOffset = 1;
		} else {
			rowOffset = -1;
		}

		for (int x = this.pos.getRank() + rowOffset, 
				y = this.pos.getFile() + colOffset; 
				x != pos.getRank(); x += rowOffset) {
			if (cell[x][y].getPiece() != null)
				result = false;

			y += colOffset;
		}

		return result;
		
		//@formatter:off
		/*
		boolean result = true;
		
		final boolean sameRankAsOpponent = this.pos.getRank() == pos.getRank();
		final boolean sameFileAsOpponent = this.pos.getFile() == pos.getFile();
		
		final int deltaRank = Math.abs(this.pos.getRank() - pos.getRank());
		final int deltaFile = Math.abs(this.pos.getFile() - pos.getFile());
		
		result = sameRankAsOpponent || sameFileAsOpponent;
		result = deltaRank != deltaFile;
		
		int rowOffset = 0, colOffset = 0;
		
		colOffset = (this.pos.getFile() < pos.getFile()) ? 1 : -1;
		rowOffset = (this.pos.getRank() < pos.getRank()) ? 1 : -1;
		
		for (int x = this.pos.getRank() + rowOffset,
				y = this.pos.getFile() + colOffset;
				x != pos.getRank(); x += rowOffset) {
			Piece curr = cell[x][y].getPiece();
			
			result = curr != null ? false : true;
			y += colOffset;
		}
		
		return result;
		*/
		//@formatter:on
	}

	@Override
	public String toString() {
		return super.toString() + "B";
	}

}
