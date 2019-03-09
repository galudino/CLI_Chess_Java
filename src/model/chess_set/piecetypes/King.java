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

	/**
	 * 
	 * @param color
	 */
	public King(PieceType.Color color) {
		super(color);
		pieceType = PieceType.KING;

		identifier += "King";
	}
	
	/* (non-Javadoc)
	 * @see model.chess_set.Piece#isMoveLegal(model.chess_set.Board.Cell[][], model.game.Position)
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
	 * 
	 * King's move will check if the current position - position it's going to
	 * is absolute 1, if it is allowed the move since the king is allowed to
	 * move in any direction by 1 space.
	 */
	@Override
	protected boolean move(Position pos) {
		boolean result = false;
		
		if (Math.abs(this.pos.getFile() - pos.getFile()) <= 1
				&& (Math.abs(this.pos.getRank() - pos.getRank()) <= 1)) {
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
		return super.toString() + "K";
	}
}
