/**
 * Game.java
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

/**
 * @version Mar 5, 2019
 * @author gemuelealudino
 *
 */
public class Game {

	private Board board;
	
	private Player white;
	private Player black;
	
	private Position whitePlay;
	private Position whiteNewPosition;
	private Position blackPlay;
	private Position blackNewPosition;
	
	public Game() {
		board = new Board();
		
		white = new Player(PieceType.Color.WHITE);
		black = new Player(PieceType.Color.BLACK);
		
		white.assignPieceSet(board);
		black.assignPieceSet(board);
		
		whitePlay = new Position();
		whiteNewPosition = new Position();
		
		blackPlay = new Position();
		blackNewPosition = new Position();
	}
	
	public boolean whitePlayMove(int file, int rank, int newFile, int newRank) {
		whitePlay.setFileRank(file, rank);
		whiteNewPosition.setFileRank(newFile, newRank);

		boolean result = white.playMove(board, whitePlay, whiteNewPosition);
		
		return result;
	}
	
	public boolean blackPlayMove(int file, int rank, int newFile, int newRank) {
		blackPlay.setFileRank(file, rank);
		blackNewPosition.setFileRank(newFile, newRank);

		boolean result = black.playMove(board, blackPlay, blackNewPosition);
		
		return result;
	}
		
	Position getWhitePlayPosition() {
		return whitePlay;
	}
	
	Position getWhiteNewPosition() {
		return whiteNewPosition;
	}
	
	Position getBlackPlayPosition() {
		return blackPlay;
	}
	
	Position getBlackNewPosition() {
		return blackNewPosition;
	}

	public String boardToString() {
		return board.toString();
	}
}
