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

import java.util.Scanner;

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
	 * @param file    x axis coordinate of a requested Piece (0-7 only)
	 * @param rank    y axis coordinate of a requested Piece (0-7 only)
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
	 * @param file    x axis coordinate of a requested Piece (0-7 only)
	 * @param rank    y axis coordinate of a requested Piece (0-7 only)
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
	 * Parses a line of input into an integer array of a Piece's current file
	 * and rank, and the desired file and rank for a new Position
	 * 
	 * @param input a line of input
	 * @return an integer array representing a Piece's current position and the
	 *         desired position to move to
	 */
	public int[] getFileRankArray(String input) {
		final String fileRankRegex = "[a-h][1-8]";

		String fileRankStr = "";
		String newFileNewRankStr = "";

		int file = -1;
		int rank = -1;
		int newFile = -1;
		int newRank = -1;

		int[] result = new int[4];

		Scanner parse = new Scanner(input);
		fileRankStr = parse.next(fileRankRegex).toLowerCase();
		newFileNewRankStr = parse.next(fileRankRegex).toLowerCase();

		char chFile = fileRankStr.charAt(0);
		char chNewFile = fileRankStr.charAt(0);

		switch (chFile) {
		case 'a':
			file = 0;
			break;
		case 'b':
			file = 1;
			break;
		case 'c':
			file = 2;
			break;
		case 'd':
			file = 3;
			break;
		case 'e':
			file = 4;
			break;
		case 'f':
			file = 5;
			break;
		case 'g':
			file = 6;
			break;
		case 'h':
			file = 7;
			break;
		}

		switch (chNewFile) {
		case 'a':
			newFile = 0;
			break;
		case 'b':
			newFile = 1;
			break;
		case 'c':
			newFile = 2;
			break;
		case 'd':
			newFile = 3;
			break;
		case 'e':
			newFile = 4;
			break;
		case 'f':
			newFile = 5;
			break;
		case 'g':
			newFile = 6;
			break;
		case 'h':
			newFile = 7;
			break;
		}

		rank = Integer.parseInt(fileRankStr.substring(1)) - 1;

		newRank = Integer.parseInt(newFileNewRankStr.substring(1)) - 1;

		result[0] = file;
		result[1] = rank;
		result[2] = newFile;
		result[3] = newRank;

		parse.close();
		return result;
	}

	public void start() {
		int[] fileRankArray = null;

		final String whiteSpaceRegex = "[ \\\\t\\\\n\\\\x0b\\\\r\\\\f]";
		final String fileRankRegex = "[a-h][1-8]";
		final String validFileRankRegex = String.format("%s%s%s", fileRankRegex,
				whiteSpaceRegex, fileRankRegex);

		final String drawRegex = "draw?";
		final String validFileRankWithDrawRegex = String.format("%s%s%s",
				validFileRankRegex, whiteSpaceRegex, drawRegex);

		boolean active = true;
		boolean whitesMove = true;

		boolean willDraw = false;
		boolean didDraw = false;
		boolean didResign = false;

		Scanner scan = new Scanner(System.in);
		String input = "";

		System.out.println(board);

		while (active) {
			String prompt = "";
			String output = "";

			boolean validMove = false;

			do {
				validMove = false;

				prompt = whitesMove ? "White's " : "Black's ";

				System.out.print(prompt + "move: ");
				input = scan.nextLine();
				System.out.println();

				if (input.matches(validFileRankRegex)) {
					validMove = true;
					willDraw = false;

					fileRankArray = getFileRankArray(input);
				} else if (willDraw && input.equals("draw")) {
					output = "draw";
				} else if (input.matches(validFileRankWithDrawRegex)) {
					validMove = true;
					willDraw = true;

					fileRankArray = getFileRankArray(input);
				} else if (input.equals("resign")) {
					didResign = true;
					output = whitesMove ? "Black wins" : "White wins";
				} else {
					validMove = false;
					output = "Invalid input, try again\n";
				}

				if (validMove) {
					int file = 0;
					int rank = 0;
					int newFile = 0;
					int newRank = 0;

					file = fileRankArray[0];
					rank = fileRankArray[1];
					newFile = fileRankArray[2];
					newRank = fileRankArray[3];

					if (whitesMove) {
						validMove = whitePlayMove(file, rank, newFile, newRank);
					} else {
						validMove = blackPlayMove(file, rank, newFile, newRank);
					}

					output = validMove ? "" : "Illegal move, try again";
				}

				System.out.println(output);

				if (didDraw || didResign) {
					System.exit(0);
				}

			} while (validMove == false);

			whitesMove = whitesMove ? false : true;
			willDraw = willDraw ? false : willDraw;

			System.out.println(board);
		}

		scan.close();
	}

	/**
	 * Accessor to retrieve the Position of the white Player's most recent Piece
	 * request
	 * 
	 * @return a Position object
	 */
	Position getWhitePlayPosition() {
		return whitePlay;
	}

	/**
	 * Accessor to retrieve the Position of the white Player's most recent move
	 * destination
	 * 
	 * @return a Position object
	 */
	Position getWhiteNewPosition() {
		return whiteNewPosition;
	}

	/**
	 * Accessor to retrieve the Position of the black Player's most recent Piece
	 * request
	 * 
	 * @return a Position object
	 */
	Position getBlackPlayPosition() {
		return blackPlay;
	}

	/**
	 * Accessor to retrieve the Position of the black Player's most recent move
	 * destination
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
