/**
 * PieceSet.java
 *
 * Copyright (c) 2019 Gemuele Aludino, Patrick Nogaj. 
 * All rights reserved.
 *
 * Rutgers University: School of Arts and Sciences
 * 01:198:213 Software Methodology, Spring 2019
 * Professor Seshadri Venugopal
 */
package model.chess_set;

import model.PieceType;
import model.chess_set.piecetypes.*;
import model.game.Position;

/**
 * @version Mar 3, 2019
 * @author gemuelealudino
 *
 */
public class PieceSet {
	
	private int PIECE_COUNT = 16;
	
	private PieceType.Color color;
	private Piece[] piece;
		
	/**
	 * 
	 * @param color the Color of a Player's PieceSet
	 */
	public PieceSet(PieceType.Color color) {
		this.color = color;
		
		piece = new Piece[PIECE_COUNT];
		
		Piece king = new King(color);
		Piece queen = new Queen(color);
		Piece bishop_l = new Bishop(PieceType.BISHOP_L, color);
		Piece bishop_r = new Bishop(PieceType.BISHOP_R, color);
		Piece knight_l = new Knight(PieceType.KNIGHT_L, color);
		Piece knight_r = new Knight(PieceType.KNIGHT_R, color);
		Piece rook_l = new Rook(PieceType.ROOK_L, color);
		Piece rook_r = new Rook(PieceType.ROOK_R, color);
		Piece pawn_0 = new Pawn(PieceType.PAWN_0, color);
		Piece pawn_1 = new Pawn(PieceType.PAWN_1, color);
		Piece pawn_2 = new Pawn(PieceType.PAWN_2, color);
		Piece pawn_3 = new Pawn(PieceType.PAWN_3, color);
		Piece pawn_4 = new Pawn(PieceType.PAWN_4, color);
		Piece pawn_5 = new Pawn(PieceType.PAWN_5, color);
		Piece pawn_6 = new Pawn(PieceType.PAWN_6, color);
		Piece pawn_7 = new Pawn(PieceType.PAWN_7, color);
		
		piece[PieceType.KING.ordinal()] = king;
		piece[PieceType.QUEEN.ordinal()] = queen;
		piece[PieceType.BISHOP_L.ordinal()] = bishop_l;
		piece[PieceType.BISHOP_R.ordinal()] = bishop_r;
		piece[PieceType.KNIGHT_L.ordinal()] = knight_l;
		piece[PieceType.KNIGHT_R.ordinal()] = knight_r;
		piece[PieceType.ROOK_L.ordinal()] = rook_l;
		piece[PieceType.ROOK_R.ordinal()] = rook_r;
		piece[PieceType.PAWN_0.ordinal()] = pawn_0;
		piece[PieceType.PAWN_1.ordinal()] = pawn_1;
		piece[PieceType.PAWN_2.ordinal()] = pawn_2;
		piece[PieceType.PAWN_3.ordinal()] = pawn_3;
		piece[PieceType.PAWN_4.ordinal()] = pawn_4;
		piece[PieceType.PAWN_5.ordinal()] = pawn_5;
		piece[PieceType.PAWN_6.ordinal()] = pawn_6;
		piece[PieceType.PAWN_7.ordinal()] = pawn_7;
	}
	
	/**
	 * 
	 * @param pieceType the PieceType of a desired Piece
	 * @return A Piece within the PieceSet
	 */
	Piece getPiece(PieceType pieceType) {
		return piece[pieceType.ordinal()];
	}
	
	/**
	 * 
	 * @return the Color of a Player's PieceSet
	 */
	public PieceType.Color getPieceSetColor() {
		return color;
	}
	
	/**
	 * 
	 * @param pos The Position of the desired Piece
	 * @return if found, the desired Piece, otherwise null
	 */
	public Piece getPieceByPosition(Position pos) {
		// Perhaps we should consider storing the Pieces
		// in a Dictionary (map) -- instead of this linear traversal
		// using an array. This would be easy to fix and it would
		// not affect outside classes.
		for (int i = 0; i < piece.length; i++) {
			if (piece[i].pos.equals(pos)) {
				return piece[i];
			}
		}
		
		return null;
	}
	
	public String toString() {
		String str = " ";
		
		for (int i = 0; i < piece.length; i++) {
			str += piece[i].toString() + "\t\t";
			str += piece[i].getIdentifier() + "\t\t";
			str += piece[i].getPosition() + "\n";
		}
		str += "\n";
		
		
		return str;
	}
}
