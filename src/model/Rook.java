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
package model;

import java.util.*;

import model.Board.Cell;
import model.Piece.Color;

/**
 * @version Mar 3, 2019
 * @author gemuelealudino
 *
 */
public class Rook extends Piece {

	/**
	 * Parameterized constructor
	 * 
	 * @param color Designated color for a piece (WHITE or BLACK)
	 * @param pos	Designated position for a piece (integers)
	 */
	protected Rook(Color color, Position pos) {
		super(color, pos);
		
		identifier += "Rook";
	}
	
	/**
	 * Parameterized constructor
	 * 
	 * @param color	Designated color for a piece (WHITE or BLACK)
	 */
	protected Rook(Color color) {
		super(color);
		
		identifier += "Rook";
	}

	/* (non-Javadoc)
	 * @see model.Piece#move(Position)
	 */
	@Override
	protected boolean move(Position pos) {
		boolean result = false;
		
		// evaluate file and rank based on pos field
		// set to true if file and rank agree with pos.
		result = true;
		
		this.pos = pos;
		
		return result;
	}
	
	@Override
	public String toString() {
		return super.toString() + "R";
	}

}
