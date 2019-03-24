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
 * Represents the state of a Chess game and all of its components
 * 
 * @version Mar 5, 2019
 * @author gemuelealudino
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
	 * Default constructor
	 */
	public Game() {
		board = new Board();
		
		white = new Player(PieceType.Color.WHITE);
		black = new Player(PieceType.Color.BLACK);
		
		white.assignPieceSet(board);
		black.assignPieceSet(board);
	}
		
	/**
	 * Piece from the white PieceSet will be moved to a new Cell on the Board
	 * 
	 * @param file x axis coordinate of a requested Piece (0-7 only)
	 * @param rank y axis coordinate of a requested Piece (0-7 only)
	 * @param newFile x axis coordinate of the desired move (0-7 only)
	 * @param newRank y axis coordinate of the desired move (0-7 only)
	 * 
	 * @return true if move executed successfully, false otherwise
	 */
	public boolean whitePlayMove(int file, int rank, int newFile, int newRank) {
		whitePlay = new Position(file, rank);
		whiteNewPosition = new Position(newFile, newRank);

		return white.playMove(board, whitePlay, whiteNewPosition);
	}
	
	/**
	 * Piece from the black PieceSet will be moved to a new Cell on the Board
	 * 
	 * @param file x axis coordinate of a requested Piece (0-7 only)
	 * @param rank y axis coordinate of a requested Piece (0-7 only)
	 * @param newFile x axis coordinate of the desired move (0-7 only)
	 * @param newRank y axis coordinate of the desired move (0-7 only)
	 * 
	 * @return true if move executed successfully, false otherwise
	 */
	public boolean blackPlayMove(int file, int rank, int newFile, int newRank) {
		blackPlay = new Position(file, rank);
		blackNewPosition = new Position(newFile, newRank);
		
		return black.playMove(board, blackPlay, blackNewPosition);
	}
		
	/**
	 * Accessor to retrieve the Position of the white Player's 
	 * most recent Piece request
	 * 
	 * @return a Position object
	 */
	Position getWhitePlayPosition() {
		return whitePlay;
	}
	
	/**
	 * Accessor to retrieve the Position of the white Player's most recent
	 * move destination
	 * 
	 * @return a Position object
	 */
	Position getWhiteNewPosition() {
		return whiteNewPosition;
	}
	
	/**
	 * Accessor to retrieve the Position of the black Player's 
	 * most recent Piece request
	 * 
	 * @return a Position object
	 */
	Position getBlackPlayPosition() {
		return blackPlay;
	}
	
	/**
	 * Accessor to retrieve the Position of the black Player's most recent
	 * move destination
	 * 
	 * @return a Position object
	 */
	Position getBlackNewPosition() {
		return blackNewPosition;
	}
	
	/**
	 * Prints the current state of the move list
	 */
	public void printMoveLog() {
		board.printMoveLog();
	}

	/**
	 * Returns the current state of the Game as an ASCII chess board 
	 * 
	 * @return string representation of the Game's Board instance
	 */
	public String boardToString() {
		return board.toString();
	}
}
