/**
 * Pawn.java
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
public class Pawn extends Piece {
	
	public Pawn(PieceType pieceType, PieceType.Color color) {
		super(color);
		
		if (pieceType.equals(PieceType.PAWN_0) ||
				pieceType.equals(PieceType.PAWN_1) ||
				pieceType.equals(PieceType.PAWN_2) ||
				pieceType.equals(PieceType.PAWN_3) ||
				pieceType.equals(PieceType.PAWN_4) ||
				pieceType.equals(PieceType.PAWN_5) ||
				pieceType.equals(PieceType.PAWN_6) ||
				pieceType.equals(PieceType.PAWN_7)) {
			this.pieceType = pieceType;
		} else {
			System.err.println("ERROR: Set this piece to either "
					+ "PieceType.PAWN_n, n being [0 - 7]!");
		}
			
		identifier += "Pawn";
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
		
		//this.pos = pos;
		
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
		return super.toString() + "P";
	}
}
