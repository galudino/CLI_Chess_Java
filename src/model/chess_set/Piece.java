/**
 * Piece.java
 *
 * Copyright (c) 2019 Gemuele Aludino, Patrick Nogaj. 
 * All rights reserved.
 *
 * Rutgers University: School of Arts and Sciences
 * 01:198:213 Software Methodology, Spring 2019
 * Professor Seshadri Venugopal
 */
package model.chess_set;

import model.PieceType;
import model.chess_set.Board.Cell;
import model.game.Position;

/**
 * @version Mar 3, 2019
 * @author gemuelealudino
 *
 */
public abstract class Piece {
	
	private PieceType.Color color;
	protected PieceType pieceType;
	protected String identifier;
	protected Position pos;

	/**
	 * Parameterized constructor
	 * 
	 * @param color Designated color for a piece (WHITE or BLACK)
	 */
	protected Piece(PieceType.Color color) {
		this.color = color;
		pos = null;

		identifier = color.equals(PieceType.Color.WHITE) ? "White " : "Black ";
	}

	/**
	 * 
	 * @return A Piece's Position
	 */
	public Position getPosition() {
		return pos;
	}
		
	/**
	 * 
	 * @param other
	 * @return
	 */
	public boolean matchesColor(Piece other) {
		return color.equals(other.color);
	}

	/**
	 * 
	 * @return A Piece's identifier (its color and type)
	 */
	protected String getIdentifier() {
		return identifier;
	}

	@Override
	public String toString() {
		return color == PieceType.Color.WHITE ? "w" : "b";
	}
	
	/**
	 * 
	 * @return
	 */
	protected boolean isWhite() {
		return color.equals(PieceType.Color.WHITE);
	}
	
	/**
	 * 
	 * @return
	 */
	protected boolean isBlack() {
		return color.equals(PieceType.Color.BLACK);
	}
	
	/**
	 * 
	 * @return
	 */
	protected PieceType getPieceType() {
		return pieceType;
	}
	
	/**
	 * 
	 * @param cell current state of the Board
	 * @param pos Represents the new Position for a piece after a move
	 * @return true if successful, false otherwise
	 */
	protected abstract boolean isMoveLegal(Cell[][] cell, Position pos);
	
	/**
	 * Subclasses will define functionality for move()
	 * 
	 * @param pos Represents the new Position for a piece after a move
	 * @return true if successful, false otherwise
	 */
	protected abstract boolean move(Position pos);
	
	/**
	 * Subclasses will define functionality for move()
	 * 
	 * @param cell current state of the Board
	 * @param pos Represents the new Position for a piece after a move
	 * @return true if successful, false otherwise
	 */
	protected abstract boolean move(Cell[][] cell, Position pos);
}
