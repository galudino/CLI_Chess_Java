/**
 * Board.java
 *
 * Copyright (c) 2019 Gemuele Aludino, Patrick Nogaj. 
 * All rights reserved.
 *
 * Rutgers University: School of Arts and Sciences
 * 01:198:213 Software Methodology, Spring 2019
 * Professor Seshadri Venugopal
 */
package model.chess_set;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import model.PieceType;
import model.chess_set.piecetypes.*;
import model.game.Position;

/**
 * Represents a Chess board for a Chess game
 * 
 * @version Mar 9, 2019
 * @author gemuelealudino
 */
public class Board {

	/**
	 * Represents a 'square' within a Chess board
	 * 
	 * @version Mar 3, 2019
	 * @author gemuelealudino
	 */
	public class Cell {
		private Position loc;
		private Piece piece;

		/**
		 * Parameterized constructor
		 * 
		 * @param file x axis coordinate (0-7 only)
		 * @param rank y axis coordinate (0-7 only)
		 */
		public Cell(int file, int rank) {
			loc = new Position(file, rank);
			piece = null;
		}

		/**
		 * Accessor method to retrieve a Piece within a Cell
		 * 
		 * @return the Piece occupying a Cell within the Board
		 */
		public Piece getPiece() {
			return piece;
		}
		
		public void setPiece(Piece piece) {
			this.piece = piece;
		}

		/**
		 * Accessor method to retrieve the Position object within a Cell
		 * 
		 * @return the Position object associated with a Cell
		 */
		public Position getPosition() {
			return loc;
		}

		/**
		 * Returns a string representation of the Piece occupying the cell,
		 * if there is a Piece present
		 */
		@Override
		public String toString() {
			return piece == null ? "--" : piece.toString();
		}
	}
	
	/**
	 * Represents a move within a Chess game, for logging purposes
	 * 
	 * @version Mar 24, 2019
	 * @author gemuelealudino
	 */
	public class Move {
		Piece piece;
		
		Position startPos;
		Position endPos;
		
		LocalTime localTime;

		int moveNumber;
		
		Move(Piece piece, Position startPos, Position endPos, int moveNumber) {
			this.piece = piece;
			this.startPos = startPos;
			this.endPos = endPos;
			
			localTime = LocalTime.now();
			
			this.moveNumber = moveNumber;
		}
		
		public String toString() {
			return localTime + "\t" + moveNumber + "\t" 
		+ piece + "\t" + startPos + "\t" + endPos;
		}
	}
	
	private static final int MAX_LENGTH_WIDTH = 8;

	private Cell[][] cell;
	
	private ArrayList<Move> moveList;
	private int moves;
	
	private PieceSet whiteSet;
	private PieceSet blackSet;

	/**
	 * Default constructor
	 */
	public Board() {
		cell = new Cell[MAX_LENGTH_WIDTH][MAX_LENGTH_WIDTH];

		for (int file = 0; file < MAX_LENGTH_WIDTH; file++) {
			for (int rank = 0; rank < MAX_LENGTH_WIDTH; rank++) {
				cell[file][rank] = new Cell(file, rank);
			}
		}

		whiteSet = new PieceSet(PieceType.Color.WHITE);
		blackSet = new PieceSet(PieceType.Color.BLACK);

		assignWhitePieces();
		assignBlackPieces();
		
		moveList = new ArrayList<Move>();
	}

	/**
	 * Accessor to retrieve the PieceSet for a white-piece Player
	 * 
	 * @return the white PieceSet
	 */
	public PieceSet getWhiteSet() {
		return whiteSet;
	}

	/**
	 * Accessor to retrieve the PieceSet for a black-piece Player
	 * 
	 * @return the black PieceSet
	 */
	public PieceSet getBlackSet() {
		return blackSet;
	}

	/**
	 * Accessor to retrieve a particular Cell within the Board
	 * 
	 * @param pos the Position of the desired Cell
	 * 
	 * @return the Cell with the given file and rank of a Position
	 */
	public Cell getCell(Position pos) {
		return cell[pos.getFile()][pos.getRank()];
	}

	/**
	 * Requested Piece will be moved to a Cell corresponding to the file
	 * and rank of newPosition, provided newPosition is a legal move
	 * for a given Piece
	 * 
	 * @param piece       accessed by a Player for a move
	 * @param newPosition the new Position desired by the Player for a Piece
	 * 
	 * @return true if successful, false otherwise
	 */
	public boolean movePiece(Piece piece, PieceSet pieceSet, Position newPosition) {
		boolean result = false;

		Cell oldPositionCell = getCell(piece.pos);
		Cell newPositionCell = getCell(newPosition);

		boolean isLegalMove = piece.isMoveLegal(cell, newPosition);
		
		System.out.println(isLegalMove);

		if (isLegalMove) {
			Piece other = newPositionCell.piece;
			boolean pieceFoundAtNewPosition = other != null;

			if (pieceFoundAtNewPosition) {
				boolean allyPieceFound = piece.matchesColor(other);

				if (allyPieceFound) {
					// This prevents a Piece of the same color "taking"
					// another piece.
					result = false;
				} else {
					// At this point, a "piece" is taken.
					newPositionCell.piece = null;

					// result = true means we will allow our requested
					// piece to move to the cell with newPosition.
					result = true;
				}
			} else {
				// result = true means we will allow our requested
				// piece to move to the cell with newPosition.
				result = true;
			}

			// Since the Piece moved from the old location to the new location,
			// the Cell will no longer have a reference to that Piece.

			if (result) {
				switch (piece.pieceType) {
				case PAWN_0:
				case PAWN_1:
				case PAWN_2:
				case PAWN_3:
				case PAWN_4:
				case PAWN_5:
				case PAWN_6:
				case PAWN_7:
					if (result == true) {
						Scanner input = new Scanner(System.in);
						String inputAns;

						if (piece.isWhite()) {	
							
							if(newPosition.getRank() == 7) {
								System.out.println("Pawn is now promotable, what would you like to promote it to?\nQ for Queen, B for bishop, N for knight, or R for Rook");
								inputAns = input.next();

								if (inputAns.equalsIgnoreCase("Q")) {				
									Piece promo = new Queen(PieceType.Color.WHITE);
									promo.pos = piece.pos;
									Piece p = pieceSet.getPieceByPosition(piece.pos);
									pieceSet.piece[p.pieceType.ordinal()] = promo;
									piece = promo;
								} else if (inputAns.equalsIgnoreCase("B")) {
									Piece promo = new Bishop(PieceType.BISHOP_R, PieceType.Color.WHITE);
									promo.pos = piece.pos;
									Piece p = pieceSet.getPieceByPosition(piece.pos);
									pieceSet.piece[p.pieceType.ordinal()] = promo;
									piece = promo;
								} else if (inputAns.equalsIgnoreCase("N")) {
									Piece promo = new Knight(PieceType.KNIGHT_R, PieceType.Color.WHITE);
									promo.pos = piece.pos;
									Piece p = pieceSet.getPieceByPosition(piece.pos);
									pieceSet.piece[p.pieceType.ordinal()] = promo;
									piece = promo;
								} else if (inputAns.equalsIgnoreCase("R")) {
									Piece promo = new Rook(PieceType.ROOK_R, PieceType.Color.WHITE);
									promo.pos = piece.pos;
									Piece p = pieceSet.getPieceByPosition(piece.pos);
									pieceSet.piece[p.pieceType.ordinal()] = promo;
									piece = promo;
								}
							}
						} else if (piece.isBlack()) {
							if(newPosition.getRank() == 0) {
								System.out.println("Pawn is now promotable, what would you like to promote it to?\nQ for Queen, B for bishop, N for knight, or R for Rook");
								inputAns = input.next();

								if (inputAns.equalsIgnoreCase("Q")) {							
									Piece promo = new Queen(PieceType.Color.BLACK);
									promo.pos = piece.pos;
									Piece p = pieceSet.getPieceByPosition(piece.pos);
									pieceSet.piece[p.pieceType.ordinal()] = promo;
									piece = promo;
								} else if (inputAns.equalsIgnoreCase("B")) {
									Piece promo = new Bishop(PieceType.BISHOP_R, PieceType.Color.BLACK);
									promo.pos = piece.pos;
									Piece p = pieceSet.getPieceByPosition(piece.pos);
									pieceSet.piece[p.pieceType.ordinal()] = promo;
									piece = promo;
								} else if (inputAns.equalsIgnoreCase("N")) {
									Piece promo = new Knight(PieceType.KNIGHT_R, PieceType.Color.BLACK);
									promo.pos = piece.pos;
									Piece p = pieceSet.getPieceByPosition(piece.pos);
									pieceSet.piece[p.pieceType.ordinal()] = promo;
									piece = promo;
								} else if (inputAns.equalsIgnoreCase("R")) {
									Piece promo = new Rook(PieceType.ROOK_R, PieceType.Color.BLACK);
									promo.pos = piece.pos;
									Piece p = pieceSet.getPieceByPosition(piece.pos);
									pieceSet.piece[p.pieceType.ordinal()] = promo;
									piece = promo;
								}
							}
						}
						
						//input.close();
					}
					
					//input.close();
					
					break;
				default:
					break;
				}
				
				// This statement nullifies any reference to a Piece
				// for this Cell object. (Next line: piece will be reassigned
				// to the newPositionCell.piece field).
				oldPositionCell.piece = null;
				
				// This statement affects what Pieces print
				// at which cells when board.toString() is called.
				newPositionCell.piece = piece;

				// This statement affects the internal position
				// data within a Piece object.
				piece.pos = newPosition;
				// piece.move(newPosition) // why use this? pos is protected.
					
				System.out.println(this);
			}	
			
			++moves;
			Move newestMove 
			= new Move(piece, oldPositionCell.loc, piece.pos, moves);
			moveList.add(newestMove);
			
			final int moveListLastIndex = moveList.size() - 1;
		
			Move lastMove = moveList.get(moveListLastIndex - 2);
			Move beforeLastMove = moveList.get(moveListLastIndex - 3);
			Move beforeBeforeLastMove = moveList.get(moveListLastIndex - 4);
			
			// Conditions for enpassant (wikipedia)
			// capturing pawn must be on rank 5
			// captured pawn must be on an adjacent file
				// and must have just moved two squares in a single move
				// i.e. a double-step move
			// capture can only be made on the move immediately after
			// the opposing pawn makes the double-step move;
			// otherwise the right to capture it via enpassant is lost.
			
			// NOTE: 
			// file A-H 
			//   is 0-7
			//
			// rank 1-8 
			//   is 0-7
			
			
			final boolean capturingPawnOnRank5
			= newestMove.startPos.getRank() == 5;

			

		}	
		
<<<<<<< HEAD
		/*
		MovePair move = new MovePair(piece, newPosition);
		moveList.add(move);
		//moveStack.push(move);

		
		Piece lastMovePiece = moveList.get(moveList.size() - 2).piece;
		Position lastMovePosition = moveList.get(moveList.size() - 2).pos;
		
		PieceType pieceType = lastMovePiece.pieceType;
		
		switch (pieceType) {
		case PAWN_0:
		case PAWN_1:
		case PAWN_2:
		case PAWN_3:
		case PAWN_4:
		case PAWN_5:
		case PAWN_6:
		case PAWN_7:
			
			
			break;
		default:

			break;
		}
		*/
		//printMoveLog();
=======
		printMoveLog();
>>>>>>> branch 'master' of https://Patricknogaj@bitbucket.org/galudino/chess22.git
		return result;
	}
		
	/**
	 * Prints the log of moves as per the moveList field (ArrayList)
	 */
	public void printMoveLog() {
		System.out.println("MOVE LOG (ALL PIECES) ---------------------");
		
		String str = "";
		
		str += "Time\t\tMove #\tPiece\tStart\tEnd\n";
		str += "-------------------------------------------\n";
		
		System.out.print(str);
		
		for (Move mp : moveList) {
			System.out.println(mp);
		}
		
		System.out.println();
	}

	/**
	 * Returns a string representation of the Board's state
	 */
	@Override
	public String toString() {
		String str = "";

		for (int rank = cell.length - 1; rank >= 0; rank--) {
			for (int file = 0; file < cell[rank].length; file++) {
				// FOR RELEASE: Print piece at cell
				if (cell[file][rank].piece == null) {
					if (rank % 2 != 0) {
						if (file % 2 != 0) {
							str += "##";
						} else {
							str += "  ";
						}
					} else {
						if (file % 2 == 0) {
							str += "##";
						} else {
							str += "  ";
						}
					}
				} else {
					// FOR RELEASE: Print Pieces
					str += cell[file][rank];

					// FOR DEBUGGING: See positions instead of Pieces
					// str += cell[file][rank].getPosition();
				}

				str += " "; // Two spaces in between cells

				if (file == cell[rank].length - 1) {
					// FOR RELEASE: Print real rank enumerations
					// str += String.format("%d", rank + 1); // FOR RELEASE

					// FOR DEBUGGING: Print rank indices as per an array
					str += String.format("%d", rank);
				}
			}

			str += "\n";
		}

		// FOR RELEASE: Print real file characters
		// str += String.format(" a b c d e f g h\n");

		// FOR DEBUGGING: Print file indices as per an array
		str += String.format(" 0  1  2  3  4  5  6  7\n");

		return str;
	}

	/**
	 * Used during Board instantiation: initialize all Piece and Cell instances
	 * to their default starting positions prior to beginning a Chess match
	 */
	private void assignWhitePieces() {
		Piece king = whiteSet.getPiece(PieceType.KING);
		king.pos = cell[4][0].loc;
		cell[4][0].piece = king;

		Piece queen = whiteSet.getPiece(PieceType.QUEEN);
		queen.pos = cell[3][0].loc;
		cell[3][0].piece = queen;

		Piece bishop_r = whiteSet.getPiece(PieceType.BISHOP_R);
		bishop_r.pos = cell[5][0].loc;
		cell[5][0].piece = bishop_r;

		Piece bishop_l = whiteSet.getPiece(PieceType.BISHOP_L);
		bishop_l.pos = cell[2][0].loc;
		cell[2][0].piece = bishop_l;

		Piece knight_r = whiteSet.getPiece(PieceType.KNIGHT_R);
		knight_r.pos = cell[6][0].loc;
		cell[6][0].piece = knight_r;

		Piece knight_l = whiteSet.getPiece(PieceType.KNIGHT_L);
		knight_l.pos = cell[1][0].loc;
		cell[1][0].piece = knight_r;

		Piece rook_r = whiteSet.getPiece(PieceType.ROOK_R);
		rook_r.pos = cell[7][0].loc;
		cell[7][0].piece = rook_r;

		Piece rook_l = whiteSet.getPiece(PieceType.ROOK_L);
		rook_l.pos = cell[0][0].loc;
		cell[0][0].piece = rook_l;

		Piece pawn_0 = whiteSet.getPiece(PieceType.PAWN_0);
		pawn_0.pos = cell[0][1].loc;
		cell[0][1].piece = pawn_0;

		Piece pawn_1 = whiteSet.getPiece(PieceType.PAWN_1);
		pawn_1.pos = cell[1][1].loc;
		cell[1][1].piece = pawn_1;

		Piece pawn_2 = whiteSet.getPiece(PieceType.PAWN_2);
		pawn_2.pos = cell[2][1].loc;
		cell[2][1].piece = pawn_2;

		Piece pawn_3 = whiteSet.getPiece(PieceType.PAWN_3);
		pawn_3.pos = cell[3][1].loc;
		cell[3][1].piece = pawn_3;

		Piece pawn_4 = whiteSet.getPiece(PieceType.PAWN_4);
		pawn_4.pos = cell[4][1].loc;
		cell[4][1].piece = pawn_4;

		Piece pawn_5 = whiteSet.getPiece(PieceType.PAWN_5);
		pawn_5.pos = cell[5][1].loc;
		cell[5][1].piece = pawn_5;

		Piece pawn_6 = whiteSet.getPiece(PieceType.PAWN_6);
		pawn_6.pos = cell[6][1].loc;
		cell[6][1].piece = pawn_6;

		Piece pawn_7 = whiteSet.getPiece(PieceType.PAWN_7);
		pawn_7.pos = cell[7][1].loc;
		cell[7][1].piece = pawn_7;
	}

	/**
	 * Used during Board instantiation: initialize all Piece and Cell instances
	 * to their default starting positions prior to beginning a Chess match
	 */
	private void assignBlackPieces() {
		Piece king = blackSet.getPiece(PieceType.KING);
		king.pos = cell[4][7].loc;
		cell[4][7].piece = king;

		Piece queen = blackSet.getPiece(PieceType.QUEEN);
		queen.pos = cell[3][7].loc;
		cell[3][7].piece = queen;

		Piece bishop_r = blackSet.getPiece(PieceType.BISHOP_R);
		bishop_r.pos = cell[5][7].loc;
		cell[5][7].piece = bishop_r;

		Piece bishop_l = blackSet.getPiece(PieceType.BISHOP_L);
		bishop_l.pos = cell[2][7].loc;
		cell[2][7].piece = bishop_l;

		Piece knight_r = blackSet.getPiece(PieceType.KNIGHT_R);
		knight_r.pos = cell[6][7].loc;
		cell[6][7].piece = knight_r;

		Piece knight_l = blackSet.getPiece(PieceType.KNIGHT_L);
		knight_l.pos = cell[1][7].loc;
		cell[1][7].piece = knight_r;

		Piece rook_r = blackSet.getPiece(PieceType.ROOK_R);
		rook_r.pos = cell[7][7].loc;
		cell[7][7].piece = rook_r;

		Piece rook_l = blackSet.getPiece(PieceType.ROOK_L);
		rook_l.pos = cell[0][7].loc;
		cell[0][7].piece = rook_l;

		Piece pawn_0 = blackSet.getPiece(PieceType.PAWN_0);
		pawn_0.pos = cell[0][6].loc;
		cell[0][6].piece = pawn_0;

		Piece pawn_1 = blackSet.getPiece(PieceType.PAWN_1);
		pawn_1.pos = cell[1][6].loc;
		cell[1][6].piece = pawn_1;

		Piece pawn_2 = blackSet.getPiece(PieceType.PAWN_2);
		pawn_2.pos = cell[2][6].loc;
		cell[2][6].piece = pawn_2;

		Piece pawn_3 = blackSet.getPiece(PieceType.PAWN_3);
		pawn_3.pos = cell[3][6].loc;
		cell[3][6].piece = pawn_3;

		Piece pawn_4 = blackSet.getPiece(PieceType.PAWN_4);
		pawn_4.pos = cell[4][6].loc;
		cell[4][6].piece = pawn_4;

		Piece pawn_5 = blackSet.getPiece(PieceType.PAWN_5);
		pawn_5.pos = cell[5][6].loc;
		cell[5][6].piece = pawn_5;

		Piece pawn_6 = blackSet.getPiece(PieceType.PAWN_6);
		pawn_6.pos = cell[6][6].loc;
		cell[6][6].piece = pawn_6;

		Piece pawn_7 = blackSet.getPiece(PieceType.PAWN_7);
		pawn_7.pos = cell[7][6].loc;
		cell[7][6].piece = pawn_7;
	}
}
