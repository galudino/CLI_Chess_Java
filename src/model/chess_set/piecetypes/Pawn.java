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
		
		this.pos = pos;
		
		return result;
	}
	
	@Override
	public String toString() {
		return super.toString() + "P";
	}
}
