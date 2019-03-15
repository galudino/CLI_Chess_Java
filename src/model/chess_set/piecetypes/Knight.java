/**
 * Knight.java
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
public class Knight extends Piece {

	/**
	 * Parameterized constructor
	 * 
	 * @param pieceType the PieceType to assign
	 * @param color the Color of a Player's PieceSet
	 */
	public Knight(PieceType pieceType, PieceType.Color color) {
		super(color);

		this.pieceType = pieceType.equals(PieceType.KNIGHT_R)
				|| pieceType.equals(PieceType.KNIGHT_L) ? pieceType : null;

		if (this.pieceType == null) {
			System.err.println("ERROR: Set this piece to either "
					+ "PieceType.KNIGHT_R or PieceType.KNIGHT_L!");
			identifier += " (invalid)";
		} else {
			identifier += "Knight";
			
			identifier += pieceType.equals(PieceType.KNIGHT_R) 
					? " (right)" : " (left)";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.chess_set.Piece#isMoveLegal(model.chess_set.Board.Cell[][],
	 * model.game.Position)
	 */
	@Override
	protected boolean isMoveLegal(Cell[][] cell, Position pos) {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Piece#move(Position)
	 */
	@Override
	public boolean move(Position pos) {
		boolean result = false;

		// evaluate file and rank based on pos field
		// set to true if file and rank agree with pos.
		if ((Math.abs(this.pos.getFile() - pos.getFile()) == 2
				&& (Math.abs(this.pos.getRank() - pos.getRank()) == 1))
				|| (Math.abs(this.pos.getFile() - pos.getFile()) == 1 && (Math
						.abs(this.pos.getRank() - pos.getRank()) == 2))) {
			result = true;
			this.pos = pos;
		} else {
			result = false;
			System.out.println("Illegal move");
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.chess_set.Piece#move(model.chess_set.Board.Cell[][],
	 * model.game.Position)
	 */
	protected boolean move(Cell[][] cell, Position pos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return super.toString() + "N";
	}
}
