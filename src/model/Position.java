/**
 * Position.java
 *
 * Copyright (c) 2019 Gemuele Aludino, Patrick Nogaj. 
 * All rights reserved.
 *
 * Rutgers University: School of Arts and Sciences
 * 01:198:213 Software Methodology, Spring 2019
 * Professor Seshadri Venugopal
 */
package model;

/**
 * @version Mar 3, 2019
 * @author gemuelealudino
 *
 */
public class Position {
	
	private int file = -1;
	private int rank = -1;
	
	/**
	 * Parameterized constructor
	 * 
	 * @param file	Integer representation for a file (0 is a, 1 is b, ...)
	 * @param rank	Integer representation for a rank (0 is 1, 1 is 2, ...)
	 */
	public Position(int file, int rank) {
		if (file < 0 || rank < 0) {
			return;
		}
		
		setFileRank(file, rank);
	}
	
	/**
	 * Mutator to update file and rank
	 * 
	 * @param file	Integer representation for a file (0 is a, 1 is b, ...)
	 * @param rank	Integer representation for a rank (0 is 1, 1 is 2, ...)
	 */
	public void setFileRank(int file, int rank) {
		if (file > 7 || rank > 7) {
			return;
		}
		
		this.file = file;
		this.rank = rank;
	}
	
	/**
	 * 
	 * @return Retrieves the file of the current Position instance
	 */
	public int getFile() {
		return file;
	}
	
	/**
	 * 
	 * @return Retrieves the rank of the current Position instance
	 */
	public int getRank() {
		return rank;
	}
	
	/**
	 * Defines the natural order of position.
	 * (If files are equal, compare the ranks)
	 * (If ranks are equal, compare the files)
	 * (If files and ranks are equal, Positions are equal)
	 * 
	 * @param o Object that may or may not represent a Position
	 * @return 0 if equal, negative if o is "greater", 
	 * positive if this is "greater"
	 */
	public int compareTo(Object o) {
		int result = 0;
		
		if (o != null && o instanceof Position) {
			Position other = (Position)(o);
			
			final int deltaFile = file - other.file;
			final int deltaRank = rank - other.rank;
			
			if (deltaFile == 0) {
				result = deltaRank;
			} else {
				if (deltaRank == 0) {
					result = deltaFile;
				} else {
					result = deltaFile + deltaRank; 
				}
			}
		}
		
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean result = false;
		
		if (o != null && o instanceof Position) {
			Position other = (Position)(o);
			result = compareTo(other) == 0;
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		char file = ' ';
		
		switch (this.file) {
		case 0:
			file = 'a';
			break;
		case 1:
			file = 'b';
			break;
		case 2:
			file = 'c';
			break;
		case 3:
			file = 'd';
			break;
		case 4:
			file = 'e';
			break;
		case 5:
			file = 'f';
			break;
		case 6:
			file = 'g';
			break;
		case 7:
			file = 'h';
			break;
		default:
			file = '-';
			break;
		}
		
		final String rankPrint = rank > -1 ? Integer.toString(rank + 1) : "-";
		
		return String.format("%c%s", file, rankPrint);
	}
}
