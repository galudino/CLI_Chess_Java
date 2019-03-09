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
	
	/**
	 * 
	 */
	public Game() {
		board = new Board();
		
		white = new Player(PieceType.Color.WHITE);
		black = new Player(PieceType.Color.BLACK);
		
		white.assignPieceSet(board);
		black.assignPieceSet(board);
	}
	
	/**
	 * 
	 * @param file
	 * @param rank
	 * @param newFile
	 * @param newRank
	 * 
	 * @return
	 */
	public boolean whitePlayMove(int file, int rank, int newFile, int newRank) {
		whitePlay = new Position(file, rank);
		whiteNewPosition = new Position(newFile, newRank);

		return white.playMove(board, whitePlay, whiteNewPosition);
	}
	
	/**
	 * 
	 * @param file
	 * @param rank
	 * @param newFile
	 * @param newRank
	 * 
	 * @return
	 */
	public boolean blackPlayMove(int file, int rank, int newFile, int newRank) {
		blackPlay = new Position(file, rank);
		blackNewPosition = new Position(file, rank);
		
		return black.playMove(board, blackPlay, blackNewPosition);
	}
		
	/**
	 * 
	 * @return
	 */
	Position getWhitePlayPosition() {
		return whitePlay;
	}
	
	/**
	 * 
	 * @return
	 */
	Position getWhiteNewPosition() {
		return whiteNewPosition;
	}
	
	/**
	 * 
	 * @return
	 */
	Position getBlackPlayPosition() {
		return blackPlay;
	}
	
	/**
	 * 
	 * @return
	 */
	Position getBlackNewPosition() {
		return blackNewPosition;
	}

	/**
	 * 
	 * @return
	 */
	public String boardToString() {
		return board.toString();
	}
}
