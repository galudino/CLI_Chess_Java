/**
 * Rook.java
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
public class Rook extends Piece {
	
	private boolean canCastle;

	/**
	 * Parameterized constructor
	 * 
	 * @param pieceType the PieceType to assign
	 * @param color the Color of a Player's PieceSet
	 */
	public Rook(PieceType pieceType, PieceType.Color color) {
		super(color);
		canCastle = true;
		this.pieceType = pieceType.equals(PieceType.ROOK_R)
				|| pieceType.equals(PieceType.ROOK_L) ? pieceType : null;

		if (this.pieceType == null) {
			System.err.println("ERROR: Set this piece to either "
					+ "PieceType.ROOK_R or PieceType.ROOK_L!");
			identifier += " (invalid)";
		} else {
			identifier += "Rook";
			
			identifier += pieceType.equals(PieceType.ROOK_R) 
					? "   (right)" : "   (left)";
		}
	}
	
	/* (non-Javadoc)
	 * @see model.chess_set.Piece#isMoveLegal(model.chess_set.Board.Cell[][], model.game.Position)
	 */
	@Override
	protected boolean isMoveLegal(Cell[][] cell, Position pos) {
		boolean result = true;
		
		//This is to check if it is moving on one path aka not diagonal
		if(pos.getRank() != this.pos.getRank() && pos.getFile() != this.pos.getFile()) {
			result = false;
		}
		
		//Utilized to check if next piece will be null
		int offset;
		
		if(pos.getFile() != this.pos.getFile()) {
			if(this.pos.getFile() < pos.getFile())
				offset = 1;
			else
				offset = -1;
			
			for(int x = this.pos.getFile() + offset; x != pos.getFile(); x += offset) {
				if(cell[x][this.pos.getRank()].getPiece() != null) {
					return false;
				}
			}
		}
		
		if(pos.getRank() != this.pos.getRank()) {
			if(this.pos.getRank() < pos.getRank())
				offset = 1;
			else
				offset = -1;
			
			for(int x = this.pos.getRank() + offset; x != pos.getRank(); x += offset) {
				if(cell[this.pos.getFile()][x].getPiece() != null) {
					return false;
				}
			}
		}
		
		return result;
	}

	@Override
	public String toString() {
		return super.toString() + "R";
	}

}
