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
	public Rook(Color color, Position pos) {
		super(color, pos);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see model.Piece#move(int, int)
	 */
	@Override
	public boolean move(int file, int rank) {
		boolean result = false;
		
		// evaluate file and rank based on pos field
		// set to true if file and rank agree with pos.
		result = true;
		
		pos.setFileRank(file, rank);
		
		return result;
	}
	
	@Override
	public String toString() {
		return super.toString() + "R";
	}

}
