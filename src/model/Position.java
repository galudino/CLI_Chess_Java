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
	
	public Position(int file, int rank) {		
		setFileRank(file, rank);
	}
	
	public void setFileRank(int file, int rank) {
		if ((file < -1 || file > 7) || ((rank < -1) || rank > 7)) {
			return;
		}
		
		this.file = file;
		this.rank = rank;
	}
	
	public int getFile() {
		return file;
	}
	
	public int getRank() {
		return rank;
	}
	
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
		case 1:
			file = 'b';
		case 2:
			file = 'c';
		case 3:
			file = 'd';
		case 4:
			file = 'e';
		case 5:
			file = 'f';
		case 6:
			file = 'g';
		case 7:
			file = 'h';
		}
		
		return String.format("%c%d", file, rank + 1);
	}
}
