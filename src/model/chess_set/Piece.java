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
 * Basis for a Piece within a PieceSet for a Player within a Game. (Chess)
 * 
 * @version Mar 3, 2019
 * @author gemuelealudino
 */
public abstract class Piece {
	
	private PieceType.Color color;
	protected PieceType pieceType;
	protected String identifier;
	protected Position pos;

	/**
	 * Parameterized constructor
	 * 
	 * @param color the Color of a Player's PieceSet
	 */
	protected Piece(PieceType.Color color) {
		this.color = color;
		pos = null;
		identifier = color.equals(PieceType.Color.WHITE) ? "White " : "Black ";
	}
		
	/**
	 * Compare colors between Pieces for a match
	 * 
	 * @param other the Piece to evaluate
	 * 
	 * @return true if color matches, false otherwise
	 */
	protected boolean matchesColor(Piece other) {
		return color.equals(other.color);
	}

	/**
	 * Retrieve a Piece's String identifier ("White King", "Black Queen", etc)
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
	 * Determine if a Piece is white
	 * 
	 * @return true if color == white, false otherwise
	 */
	protected boolean isWhite() {
		return color.equals(PieceType.Color.WHITE);
	}
	
	/**
	 * Determine if a Piece is black
	 * 
	 * @return true if color == black, false otherwise
	 */
	protected boolean isBlack() {
		return color.equals(PieceType.Color.BLACK);
	}
	
	/**
	 * Determine a Piece's PieceType
	 * 
	 * @return a Piece's PieceType
	 */
	protected PieceType getPieceType() {
		return pieceType;
	}
	
	/**
	 * Determine if a move is legal given a Position pos
	 * 
	 * @param cell current state of the Board
	 * @param pos Represents the new Position for a piece after a move
	 * 
	 * @return true if successful, false otherwise
	 */
	protected abstract boolean isMoveLegal(Cell[][] cell, Position pos);
	
	/**
	 * Subclasses will define functionality for move()
	 * 
	 * @param pos Represents the new Position for a piece after a move
	 * 
	 * @return true if successful, false otherwise
	 */
	protected abstract boolean move(Position pos);
	
	/**
	 * Subclasses will define functionality for move()
	 * 
	 * @param cell current state of the Board
	 * @param pos Represents the new Position for a piece after a move
	 * 
	 * @return true if successful, false otherwise
	 */
	protected abstract boolean move(Cell[][] cell, Position pos);
}
