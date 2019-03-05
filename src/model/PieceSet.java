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
package model;

import java.util.*;

import model.Piece.Color;

/**
 * @version Mar 3, 2019
 * @author gemuelealudino
 *
 */
public class PieceSet {
	
	public enum PieceType {
		KING, QUEEN, BISHOP_L, BISHOP_R, KNIGHT_L, KNIGHT_R, ROOK_L, ROOK_R,
		PAWN_0, PAWN_1, PAWN_2, PAWN_3, PAWN_4, PAWN_5, PAWN_6, PAWN_7
	};
	
	private int PIECE_COUNT = 16;
	
	private Color color;
	protected Piece[] piece;
		
	/**
	 * 
	 * @param color
	 */
	protected PieceSet(Color color) {
		this.color = color;
		
		piece = new Piece[PIECE_COUNT];
		
		piece[PieceType.KING.ordinal()] = new King(color);
		piece[PieceType.QUEEN.ordinal()] = new Queen(color);
		piece[PieceType.BISHOP_L.ordinal()] = new Bishop(color);
		piece[PieceType.BISHOP_R.ordinal()] = new Bishop(color);
		piece[PieceType.KNIGHT_L.ordinal()] = new Knight(color);
		piece[PieceType.KNIGHT_R.ordinal()] = new Knight(color);
		piece[PieceType.ROOK_L.ordinal()] = new Rook(color);
		piece[PieceType.ROOK_R.ordinal()] = new Rook(color);
		piece[PieceType.PAWN_0.ordinal()] = new Pawn(color);
		piece[PieceType.PAWN_1.ordinal()] = new Pawn(color);
		piece[PieceType.PAWN_2.ordinal()] = new Pawn(color);
		piece[PieceType.PAWN_3.ordinal()] = new Pawn(color);
		piece[PieceType.PAWN_4.ordinal()] = new Pawn(color);
		piece[PieceType.PAWN_5.ordinal()] = new Pawn(color);
		piece[PieceType.PAWN_6.ordinal()] = new Pawn(color);
		piece[PieceType.PAWN_7.ordinal()] = new Pawn(color);
	}
	
	/**
	 * 
	 * @return
	 */
	public Color getPieceSetColor() {
		return color;
	}
	
	/**
	 * 
	 * @param pos
	 * @return
	 */
	protected Piece getPiece(Position pos) {
		for (int i = 0; i < piece.length; i++) {
			if (piece[i].pos.equals(pos)) {
				return piece[i];
			}
		}
		
		return null;
	}
}
