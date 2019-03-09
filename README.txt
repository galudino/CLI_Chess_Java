Authors:
--------
Gemuele Aludino
Patrick Nogaj

Repository URL:
---------------
https://galudino@bitbucket.org/galudino/chess22.git

Java Version: 
-------------
Java SE 1.8

SYNOPSIS
--------
This program implements the game of Chess for two players.
The program, when launched, will draw the board in ASCII form
to stdout, and prompt one of the two players to play their move.

The current player will input their move in the following format:
[fileRANK] [fileRANK]

file: horizontal axis 	[a-h]
RANK: vertical axis		[1-8]

For example, when the game begins, 
the 'white' piece player will make their move -- as an example:

White's move: a2 a4

When the game begins, square a2 is the location of the leftmost white pawn,
and the player is moving said piece to square a4.

Once the move is executed, the board post-move is drawn,
and the opposing player is then queried.
