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
 */
public class Pawn extends Piece {

	private boolean firstMove = true;

	/**
	 * Parameterized constructor
	 * 
	 * @param pieceType the PieceType to assign
	 * @param color the Color of a Player's PieceSet
	 */
	public Pawn(PieceType pieceType, PieceType.Color color) {
		super(color);

		switch (pieceType) {
		case PAWN_0:
		case PAWN_1:
		case PAWN_2:
		case PAWN_3:
		case PAWN_4:
		case PAWN_5:
		case PAWN_6:
		case PAWN_7:
			this.pieceType = pieceType;
			identifier += "Pawn";
			break;
		default:
			this.pieceType = null;
			identifier += " (invalid)";
			
			System.err.println("ERROR: Set this piece to either "
					+ "PieceType.PAWN_n, n being [0 - 7]!");
			
			break;
		}
		
		switch (this.pieceType) {
		case PAWN_0:
			identifier += "   (1)";
			break;
		case PAWN_1:
			identifier += "   (2)";
			break;
		case PAWN_2:
			identifier += "   (3)";
			break;
		case PAWN_3:
			identifier += "   (4)";
			break;
		case PAWN_4:
			identifier += "   (5)";
			break;
		case PAWN_5:
			identifier += "   (6)";
			break;
		case PAWN_6:
			identifier += "   (7)";
			break;
		case PAWN_7:
			identifier += "   (8)";
			break;
		default:	
			break;
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
		// System.out.println("Value of first move: " + firstMove);
		// if(validDirection(pos))

		// System.out.println("PieceType: " + this.pieceType + " \nCurrent POS
		// RANK: " + this.getPosition().getRank() + "\nMOVING TO POS: " +
		// pos.getRank());

		if (firstMove) {
			if (Math.abs(pos.getRank() - this.pos.getRank()) == 2
					|| Math.abs(pos.getRank()
							- this.pos.getRank()) == 1) {
				result = true;
				
				this.pos = pos;
			}

			firstMove = false;
		} else {
			if (Math.abs(pos.getRank() - this.pos.getRank()) == 1) {
				result = true;
				
				this.pos = pos;
			}
		}

		// result = true;
		// this.pos = pos;
		// System.out.println("Result: " + result);
		return result;
	}

	/**
	 * @author Patrick Nogaj
	 * Helper method to determine if pos is a valid direction
	 * 
	 * @param pos the Position to evaluate if valid
	 * 
	 * @return true if pos is determined to be valid, false otherwise
	 */
	private boolean validDirection(Position pos) {
		return (isWhite() ? (this.pos.getRank() <= pos.getRank())
				: (pos.getRank() <= this.pos.getRank()));
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
		return super.toString() + "P";
	}

}
