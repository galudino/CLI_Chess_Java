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
package model.chess_set.piecetypes;

import model.PieceType;
import model.chess_set.Piece;
import model.chess_set.Board.Cell;
import model.game.Position;
import java.util.Scanner;

/**
 * @version Mar 3, 2019
 * @author gemuelealudino
 */
public class Pawn extends Piece {

	private boolean firstMove = true;
	private boolean isPromoted = false;

	/**
	 * Parameterized constructor
	 * 
	 * @param pieceType the PieceType to assign
	 * @param color     the Color of a Player's PieceSet
	 */
	public Pawn(PieceType pieceType, PieceType.Color color) {
		super(color);

		switch (pieceType) {
		case PAWN_0:
		case PAWN_1:
		case PAWN_2:
		case PAWN_3:
		case PAWN_4:
		case PAWN_5:
		case PAWN_6:
		case PAWN_7:
			this.pieceType = pieceType;
			identifier += "Pawn";
			break;
		default:
			this.pieceType = null;
			identifier += " (invalid)";

			System.err.println("ERROR: Set this piece to either "
					+ "PieceType.PAWN_n, n being [0 - 7]!");

			break;
		}

		switch (this.pieceType) {
		case PAWN_0:
			identifier += "   (1)";
			break;
		case PAWN_1:
			identifier += "   (2)";
			break;
		case PAWN_2:
			identifier += "   (3)";
			break;
		case PAWN_3:
			identifier += "   (4)";
			break;
		case PAWN_4:
			identifier += "   (5)";
			break;
		case PAWN_5:
			identifier += "   (6)";
			break;
		case PAWN_6:
			identifier += "   (7)";
			break;
		case PAWN_7:
			identifier += "   (8)";
			break;
		default:
			break;
		}
	}
	
	public boolean isPromoted() {
		return isPromoted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.chess_set.Piece#isMoveLegal(model.chess_set.Board.Cell[][],
	 * model.game.Position)
	 */
	@Override
	protected boolean isMoveLegal(Cell[][] cell, Position pos) {
		boolean result = false;
		
		if (this.isWhite()) {
			if (this.pos.getRank() < pos.getRank()) {
				if (firstMove) {
					if ((Math.abs(pos.getRank() - this.pos.getRank()) == 2
							|| Math.abs(pos.getRank() - this.pos.getRank()) == 1)
									&& (this.pos.getFile() == pos.getFile() && (cell[pos.getFile()][pos.getRank()].getPiece() == null))) {
						result = true;
					}
					firstMove = false;
				} else {
					if (Math.abs(pos.getRank() - this.pos.getRank()) == 1
							&& this.pos.getFile() == pos.getFile() && (cell[pos.getFile()][pos.getRank()].getPiece() == null)) {
						result = true;
					} else if (Math.abs(pos.getRank() - this.pos.getRank()) == 1
							&& Math.abs(pos.getFile() - this.pos.getFile()) == 1
							&& cell[this.pos.getFile() + 1][this.pos.getRank() + 1].getPiece() != null) {
						if (cell[pos.getFile()][pos.getRank()].getPiece()
								.isBlack())
							result = true;
					}
				}
			}
		} else if (this.isBlack()) {
			if (pos.getRank() < this.pos.getRank()) {
				if (firstMove) {
					if (Math.abs(pos.getRank() - this.pos.getRank()) == 2 || Math.abs(pos.getRank() - this.pos.getRank()) == 1) {
						result = true;
					}
					firstMove = false;
				} else {
					if (Math.abs(pos.getRank() - this.pos.getRank()) == 1 && (cell[pos.getFile()][pos.getRank()].getPiece() == null)) {
						result = true;
					} else if (Math.abs(pos.getRank() - this.pos.getRank()) == 1
							&& Math.abs(pos.getFile() - this.pos.getFile()) == 1 && cell[pos.getFile()][pos.getRank()].getPiece() != null) {
				
						if (cell[pos.getFile()][pos.getRank()].getPiece().isWhite())
							result = true;
					}
				}
			}
		}
		
		/*
		if (result == true) {
			Scanner input = new Scanner(System.in);
			String inputAns;

			if (this.isWhite() && pos.getRank() == 7) {
				System.out.println(
						"Pawn is now promotable, what would you like to promote it to?\nQ for Queen, B for bishop, N for knight, or R for Rook");
				inputAns = input.next();

				if (inputAns.equalsIgnoreCase("Q")) {
					Piece promo = new Queen(PieceType.Color.WHITE);
					cell[this.pos.getFile()][this.pos.getRank()].setPiece(null);
					cell[pos.getFile()][pos.getRank()].setPiece(promo);
				} else if (inputAns.equalsIgnoreCase("B")) {
					Piece promo = new Bishop(PieceType.Color.WHITE);
					cell[this.pos.getFile()][this.pos.getRank()].setPiece(null);
					cell[pos.getFile()][pos.getRank()].setPiece(promo);
				} else if (inputAns.equalsIgnoreCase("N")) {
					Piece promo = new Knight(pieceType, PieceType.Color.WHITE);
				} else if (inputAns.equalsIgnoreCase("R")) {
					Piece promo = new Rook(pieceType, PieceType.Color.WHITE);
				}
			}
			
			//input.close();
		}
		*/
		
		if (result == true) {
			isPromoted = true;
		}
		
		return result;
		
		//@formatter:off
		/*
		boolean result = false;

		final int deltaRank = Math.abs(pos.getRank() - this.pos.getRank());
		final int deltaFile = Math.abs(pos.getFile() - this.pos.getFile());
		final boolean sameFileAsOpponent = this.pos.getFile() == pos.getFile();
		final boolean deltaRankTwoOrOne = deltaRank == 2 || deltaRank == 1;

		Cell thisCell = cell[this.pos.getFile()][this.pos.getRank()];
		Cell thisCellRightDiagonal = cell[this.pos.getFile() + 1][this.pos
				.getRank() + 1];
		Cell otherCell = cell[pos.getFile()][pos.getRank()];

		Piece thisCellPieceRightDiagonal = thisCellRightDiagonal.getPiece();
		Piece otherCellPiece = otherCell.getPiece();

		if (this.isWhite()) {
			if (this.pos.getRank() < pos.getRank()) {
				if (firstMove) {
					if (deltaRankTwoOrOne && sameFileAsOpponent) {
						result = true;
					}

					firstMove = false;
				} else {
					if (deltaRank == 1 && sameFileAsOpponent) {
						result = true;
					} else if (deltaRank == 1 && deltaFile == 1
							&& thisCellPieceRightDiagonal != null) {
						if (otherCellPiece.isBlack()) {
							result = true;
						}
					}
				}
			}
		} else if (this.isBlack()) {
			if (pos.getRank() < this.pos.getRank()) {
				if (firstMove) {
					if (deltaRank == 2 || deltaRank == 1) {
						result = true;
					}

					firstMove = false;
				} else {
					if (deltaRank == 1) {
						result = true;
					} else if (deltaRank == 1 && deltaFile == 1
							&& otherCellPiece != null) {
						if (otherCellPiece.isWhite()) {
							result = true;
						}
					}
				}
			}
		}
	
		if (result) {
			Scanner input = new Scanner(System.in);
			String inputAns = "";

			if (isWhite() && pos.getRank() == 7) {
				final String prompt = 
						String.format("%s\n\t%s\n\t%s\n\t%s\n\t%s\n\n%s",
						"Pawn is now promotable, "
								+ "what would you like to promote it to?",
						"Q for Queen", "B for Bishop", "N for Knight",
						"R for Rook", "==> ");

				final String invalid = "Invalid response. Try again.";
				
				boolean wrongAnswer = false;
				do {
					System.out.print(prompt);
					inputAns = input.next();

					Piece promo = null;
					
					switch (inputAns) {
					case "Q":
					case "q":
						wrongAnswer = false;
						promo = new Queen(PieceType.Color.WHITE);

						thisCell.setPiece(null);
						otherCell.setPiece(promo);
						break;
					case "B":
					case "b":
						wrongAnswer = false;
						promo = new Bishop(PieceType.Color.WHITE);
						
						thisCell.setPiece(null);
						otherCell.setPiece(promo);
						break;
					case "N":
					case "n":
						wrongAnswer = false;
						promo = new Knight(pieceType, PieceType.Color.WHITE);
						break;
					case "R":
					case "r":
						wrongAnswer = false;
						promo = new Rook(pieceType, PieceType.Color.WHITE);
						break;
					default:
						wrongAnswer = true;
						System.out.println(String.format("%s\n", invalid));
						break;
					}
				} while (wrongAnswer);
			}

			input.close();
		}

		return result;
		*/
		//@formatter:on
	}

	@Override
	public String toString() {
		return super.toString() + "P";
	}

}
