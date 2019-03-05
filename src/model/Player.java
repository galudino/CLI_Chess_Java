/**
 * Player.java
 *
 * Copyright (c) 2019 Gemuele Aludino, Patrick Nogaj. 
 * All rights reserved.
 *
 * Rutgers University: School of Arts and Sciences
 * 01:198:213 Software Methodology, Spring 2019
 * Professor Seshadri Venugopal
 */
package model;

import model.Piece.Color;

/**
 * @version Mar 5, 2019
 * @author gemuelealudino
 *
 */
public class Player {
	
	private Color color;
	private PieceSet pieceSet;
	
	/**
	 * 
	 * @param color the Color associated with a Player's PieceSet
	 */
	public Player(Color color) {
		this.color = color;
		pieceSet = null;
	}
	
	/**
	 * 
	 * @param board the current Board instance used during a match
	 * @return
	 */
	public boolean assignPieceSet(Board board) {
		if (pieceSet != null) {
			return false;
		}
		
		if (color.equals(board.getWhiteSet().getPieceSetColor())) {
			pieceSet = board.getWhiteSet();
			return true;
		} else if (color.equals(board.getBlackSet().getPieceSetColor())) {
			pieceSet = board.getBlackSet();
			return true;
		}
		
		return false;
	}

	/**
	 * 
	 * @param board the current Board instance used during a match
	 * @param piecePosition the Position of a chosen Piece
	 * @param newPosition the Position desired by the Player for a chosen Piece
	 * @return true if successful, false otherwise
	 */
	public boolean playMove(Board board, Position piecePosition, 
			Position newPosition) {
		if (piecePosition.equals(newPosition)) {
			return false;
		}
		
		Piece toMove = board.getCell(piecePosition).getPiece();
		
		if (toMove == null) {
			return false;
		}
		
		board.movePiece(toMove, newPosition);
		
		return true;
	}
}
