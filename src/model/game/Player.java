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
package model.game;

import model.PieceType;
import model.chess_set.Board;
import model.chess_set.Piece;
import model.chess_set.PieceSet;

/**
 * @version Mar 5, 2019
 * @author gemuelealudino
 *
 */
class Player {
	
	private PieceType.Color color;
	private PieceSet pieceSet;
	
	/**
	 * 
	 * @param color the Color associated with a Player's PieceSet
	 */
	Player(PieceType.Color color) {
		this.color = color;
		pieceSet = null;
	}
	
	/**
	 * 
	 * @param board the current Board instance used during a match
	 * @return
	 */
	boolean assignPieceSet(Board board) {
		boolean result = false;
		
		if (pieceSet == null) {
			PieceSet whiteSet = board.getWhiteSet();
			PieceSet blackSet = board.getBlackSet();
			
			boolean playerIsWhite = color.equals(whiteSet.getPieceSetColor());
					
			pieceSet = playerIsWhite ? whiteSet : blackSet;
			
			result = true;
		}
		
		return result;
	}

	/**
	 * 
	 * @param board the current Board instance used during a match
	 * @param piecePosition the Position of a chosen Piece
	 * @param newPosition the Position desired by the Player for a chosen Piece
	 * @return true if successful, false otherwise
	 */
	boolean playMove(Board board, Position piecePosition, 
			Position newPosition) {
		boolean result = false;
		
		boolean requestDiffersFromNewPosition = 
				piecePosition.equals(newPosition) == false;

		if (requestDiffersFromNewPosition) {
			Piece pieceRequested = pieceSet.getPieceByPosition(piecePosition);
			
			if (pieceRequested == null) {
				String error = String.format(
						"ERROR: No %s piece at position %s exists.", 
						color, piecePosition);
				System.err.println(error);
			} else {
				result = board.movePiece(pieceRequested, newPosition);

				/*
				 *  Not necessary, because pieceRequested
				 *  is being passed to board.movePiece(Piece piece, Pos..),
				 *  and movePiece() calls piece.move()
				 *  
				if (result) {
					pieceRequested.setPosition(newPosition);					
				}
				*/
			}
		}
		
		return result;
	}
}