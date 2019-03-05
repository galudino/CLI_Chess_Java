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
package chess;

import model.Board;
import model.Piece;
import model.PieceSet;
import model.Position;
import model.Board.Cell;
import model.Piece.Color;

/**
 * @version Mar 5, 2019
 * @author gemuelealudino
 *
 */
public class Player {
	
	private Color color;
	
	private PieceSet pieceSet;
	
	public Player(Color color) {
		this.color = color;
		pieceSet = null;
	}
	
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
