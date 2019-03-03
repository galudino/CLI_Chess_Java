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

/**
 * @version Mar 3, 2019
 * @author gemuelealudino
 *
 */
public abstract class Piece {
	public enum Color {
		WHITE, BLACK
	}

	protected Color color;
	protected Position pos;
	
	protected boolean active;
	
	/**
	 * 
	 * @param color
	 * @param file
	 * @param rank
	 */
	public Piece(Color color, Position pos) {
		this.color = color;
		this.pos = pos;
	}
	
	/**
	 * 
	 * @return
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * 
	 * @return
	 */
	public Position getPosition() {
		return pos;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * 
	 */
	public void deactivate() {
		pos.setFileRank(-1, -1);
		active = false;
	}
	
	@Override
	public String toString() {
		return color == Color.WHITE ? "w" : "b";
	}
		
	/**
	 * 
	 * @param file
	 * @param rank
	 * @return
	 */
	public abstract boolean move(int file, int rank);
}
