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
package model;

import java.util.*;

/**
 * @version Mar 3, 2019
 * @author gemuelealudino
 *
 */
public class Pawn extends Piece {

	/**
	 * 
	 * @param color
	 * @param file
	 * @param rank
	 */
	public Pawn(Color color, Position pos) {
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
		return super.toString() + "P";
	}
}
