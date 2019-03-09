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
 *
 */
public class Bishop extends Piece {

	public Bishop(PieceType pieceType, PieceType.Color color) {
		super(color);
		
		if (pieceType.equals(PieceType.BISHOP_R) 
				|| pieceType.equals(PieceType.BISHOP_L)) {
			this.pieceType = pieceType;
		} else {
			System.err.println("ERROR: Set this piece to either "
					+ "PieceType.BISHOP_R or PieceType.BISHOP_L!");
		}
		
		identifier += "Bishop";
	}
	
	/* (non-Javadoc)
	 * @see model.chess_set.Piece#isMoveLegal(model.chess_set.Board.Cell[][], model.game.Position)
	 */
	@Override
	protected boolean isMoveLegal(Cell[][] cell, Position pos) {
		// TODO Auto-generated method stub
		return true;
	}


	/* (non-Javadoc)
	 * @see model.Piece#move(Position)
	 */
	@Override
	public boolean move(Position pos) {
		boolean result = false;
		
		// evaluate file and rank based on pos field
		// set to true if file and rank agree with pos.
		result = true;
		
		this.pos = pos;
		
		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see model.chess_set.Piece#move(model.chess_set.Board.Cell[][], model.game.Position)
	 */
	protected boolean move(Cell[][] cell, Position pos) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString() {
		return super.toString() + "B";
	}

}
