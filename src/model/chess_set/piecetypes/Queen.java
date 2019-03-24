/**
 * Queen.java
 *
 * Copyright (c) 2019 Gemuele Aludino, Patrick Nogaj. 
 * All rights reserved.
 *
 * Rutgers University: School of Arts and Sciences
 * 01:198:213 Software Methodology, Spring 2019
 * Professor Seshadri Venugopal
 */
package model.chess_set.piecetypes;

import model.PieceType;
import model.chess_set.Piece;
import model.chess_set.Board.Cell;
import model.game.Position;

/**
 * @version Mar 3, 2019
 * @author gemuelealudino
 */
public class Queen extends Piece {

	/**
	 * Parameterized constructor
	 *
	 * @param color the Color of a Player's PieceSet
	 */
	public Queen(PieceType.Color color) {
		super(color);
		pieceType = PieceType.QUEEN;

		identifier += "Queen     ";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.chess_set.Piece#isMoveLegal(model.chess_set.Board.Cell[][],
	 * model.game.Position)
	 */
	@Override
	protected boolean isMoveLegal(Cell[][] cell, Position pos) {
		boolean result = true;

		if (Math.abs(this.pos.getRank() - pos.getRank()) == 
				Math.abs(this.pos.getFile() - pos.getFile())) {
			int rowOffset, colOffset;

			if (this.pos.getFile() < pos.getFile()) {
				colOffset = 1;
			} else {
				colOffset = -1;
			}

			if (this.pos.getRank() < pos.getRank()) {
				rowOffset = 1;
			} else {
				rowOffset = -1;
			}

			for (int x = this.pos.getRank() + rowOffset, 
					y = this.pos.getFile() + colOffset; 
					x != pos.getRank(); x += rowOffset) {
				if (cell[x][y].getPiece() != null) {
					result = false;
				}

				y += colOffset;
			}
		} else {
			// This is to check if it is moving on one path aka not diagonal
			if (pos.getRank() != this.pos.getRank()
					&& pos.getFile() != this.pos.getFile()) {
				result = false;
			}

			int offset;

			if (pos.getFile() != this.pos.getFile()) {
				if (this.pos.getFile() < pos.getFile()) {
					offset = 1;
				} else {
					offset = -1;
				}

				for (int x = this.pos.getFile() + offset; 
						x != pos.getFile(); x += offset) {
					if (cell[x][this.pos.getRank()].getPiece() != null) {
						return false;
					}
				}
			}

			if (pos.getRank() != this.pos.getRank()) {
				if (this.pos.getRank() < pos.getRank()) {
					offset = 1;
				} else {
					offset = -1;
				}

				for (int x = this.pos.getRank() + offset; 
						x != pos.getRank(); x += offset) {
					if (cell[this.pos.getFile()][x].getPiece() != null) {
						return false;
					}
				}
			}
		}

		return result;

		//@formatter:off
		/*
		boolean result = true;

		final int deltaRank = Math.abs(this.pos.getRank() - pos.getRank());
		final int deltaFile = Math.abs(this.pos.getFile() - pos.getFile());

		if (deltaRank == deltaFile) {
			int rowOffset = 0, colOffset = 0;

			colOffset = (this.pos.getFile() < pos.getFile()) ? 1 : -1;
			rowOffset = (this.pos.getRank() < pos.getRank()) ? 1 : -1;

			for (int x = this.pos.getRank() + rowOffset, y = this.pos.getFile()
					+ colOffset; x != pos.getRank(); x += rowOffset) {
				Piece curr = cell[x][y].getPiece();
				result = (curr != null) ? false : true;

				y += colOffset;
			}
		} else {
			final boolean differentRanks = pos.getRank() != this.pos.getRank();
			final boolean differentFiles = pos.getFile() != this.pos.getFile();

			result = (differentRanks && differentFiles) ? false : true;

			int offset = 0;

			if (differentFiles) {
				offset = (this.pos.getFile() < pos.getFile()) ? 1 : -1;

				for (int x = this.pos.getFile() + offset; 
						x != pos.getFile(); x += offset) {
					Piece curr = cell[x][this.pos.getRank()].getPiece();

					if (curr != null) {
						return false;
					}
				}
			}

			if (differentRanks) {
				offset = (this.pos.getRank() < pos.getRank()) ? 1 : -1;

				for (int x = this.pos.getRank() + offset; 
						x != pos.getFile(); x += offset) {
					Piece curr = cell[this.pos.getFile()][x].getPiece();
					
					if (curr != null) {
						return false;
					}
				}
			}
		}

		return result;
		*/
		//@formatter:on
	}

	@Override
	public String toString() {
		return super.toString() + "Q";
	}
}
