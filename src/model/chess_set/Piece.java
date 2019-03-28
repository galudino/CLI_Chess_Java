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
import model.chess_set.piecetypes.King;
import model.chess_set.piecetypes.Pawn;
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
	protected Piece piece;
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
	public boolean isWhite() {
		return color.equals(PieceType.Color.WHITE);
	}
	
	/**
	 * Determine if a Piece is black
	 * 
	 * @return true if color == black, false otherwise
	 */
	public boolean isBlack() {
		return color.equals(PieceType.Color.BLACK);
	}
	
	/**
	 * Determines if a Piece p is a Pawn, or not
	 * 
	 * @param p Piece to assess for type (pawn or not)
	 * @return true if p is a type PieceType.PAWN_n, false otherwise
	 */
	public boolean isPawn() {
		PieceType pt = pieceType;
		
		boolean isPieceTypePawn = pt.equals(PieceType.PAWN_0) || pt.equals(PieceType.PAWN_1)
				|| pt.equals(PieceType.PAWN_2) || pt.equals(PieceType.PAWN_3)
				|| pt.equals(PieceType.PAWN_4) || pt.equals(PieceType.PAWN_5)
				|| pt.equals(PieceType.PAWN_6) || pt.equals(PieceType.PAWN_7);
		
		return isPieceTypePawn && this instanceof Pawn;
	}
	
	/**
	 * Determines if a Piece p is a KING, or not
	 * 
	 * @param p Piece to assess for type (pawn or not)
	 * @return true if p is a type PieceType.KING, false otherwise
	 */
	public boolean isKing() {
		PieceType pt = pieceType;
		
		boolean isPieceTypeKing = pt.equals(PieceType.KING);
		
		return isPieceTypeKing && this instanceof King;
	}
	
	/**
	 * Determine a Piece's PieceType
	 * 
	 * @return a Piece's PieceType
	 */
	protected PieceType getPieceType() {
		return pieceType;
	}
	
	protected Piece getPiece() {
		return piece;
	}
	
	/**
	 * Determine if a move is legal given a Position pos
	 * 
	 * @param cell current state of the Board
	 * @param pos Represents the new Position for a piece after a move
	 * 
	 * @return true if successful, false otherwise
	 */
	public abstract boolean isMoveLegal(Cell[][] cell, Position pos);
	
}
