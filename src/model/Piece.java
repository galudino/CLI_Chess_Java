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
package model;

import java.util.*;

import model.Board.Cell;

/**
 * @version Mar 3, 2019
 * @author gemuelealudino
 *
 */
public abstract class Piece {
	/**
	 * @version Mar 3, 2019
	 * @author gemuelealudino
	 * 
	 */
	public enum Color {
		WHITE, BLACK
	}
	
	protected Color color;
	protected Position pos;
	protected String identifier;
		
	/**
	 * Parameterized constructor
	 * 
	 * @param color Designated color for a piece (WHITE or BLACK)
	 * @param pos	Designated position for a piece (integers)
	 */
	public Piece(Color color, Position pos) {
		this(color);
		this.pos = pos;
	}
	
	/**
	 * Parameterized constructor
	 * 
	 * @param color	Designated color for a piece (WHITE or BLACK)
	 */
	public Piece(Color color) {
		this.color = color;
		pos = null;
		
		identifier = color.equals(Color.WHITE) ? "White " : "Black ";
	}
	
	/**
	 * 
	 * @return A Piece's Color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * 
	 * @return A Piece's Position
	 */
	public Position getPosition() {
		return pos;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	@Override
	public String toString() {
		return color == Color.WHITE ? "w" : "b";
	}
		
	/**
	 * Functionality is determined by subclasses.
	 * (Each child of Piece moves in its own way)
	 * 
	 * @param file	Integer representation for a file (0 is a, 1 is b, ...)
	 * @param rank	Integer representation for a rank (0 is 1, 1 is 2, ...)
	 * @return	true if move was successful, false otherwise
	 */
	public abstract boolean move(int file, int rank);
}
