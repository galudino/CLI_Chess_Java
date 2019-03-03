/**
 * King.java
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

/**
 * @version Mar 3, 2019
 * @author gemuelealudino
 *
 */
public class King extends Piece {

	/**
	 * Parameterized constructor
	 * 
	 * @param color Designated color for a piece (WHITE or BLACK)
	 * @param file	Integer representation for a file (0 is a, 1 is b, ...)
	 * @param rank	Integer representation for a rank (0 is 1, 1 is 2, ...)
	 */
	public King(Color color, Position pos) {
		super(color, pos);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see model.Piece#move(int, int)
	 */
	@Override
	public boolean move(int file, int rank) {
		// TODO Auto-generated method stub

		return false;
	}
	
	@Override
	public String toString() {
		return super.toString() + "K";
	}

}
