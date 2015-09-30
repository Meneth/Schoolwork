package chess;

import java.util.Stack;

public class Game {
	private Piece[][] board; // 8*8 array representing the tiles/pieces
	private boolean whiteTurn; // Keeps track of who's turn it is
	private final Piece whiteKing;
	private final Piece blackKing;
	private Stack<Move> log;

	public Game() { // Fills out board
		board = new Piece[8][8];
		whiteTurn = true;
		board[0][0] = new PieceRook(false, 0, 0, this);
		board[0][1] = new PieceKnight(false, 0, 1);
		board[0][2] = new PieceBishop(false, 0, 2, this);
		board[0][3] = new PieceQueen(false, 0, 3, this);
		board[0][4] = new PieceKing(false, 0, 4);
		board[0][5] = new PieceBishop(false, 0, 5, this);
		board[0][6] = new PieceKnight(false, 0, 6);
		board[0][7] = new PieceRook(false, 0, 7, this);
		for (int i = 0; i < board.length; i++) {
			board[1][i] = new PiecePawn(false, 1, i, this);
			board[6][i] = new PiecePawn(true, 6, i, this);
		}
		board[7][0] = new PieceRook(true, 7, 0, this);
		board[7][1] = new PieceKnight(true, 7, 1);
		board[7][2] = new PieceBishop(true, 7, 2, this);
		board[7][3] = new PieceQueen(true, 7, 3, this);
		board[7][4] = new PieceKing(true, 7, 4);
		board[7][5] = new PieceBishop(true, 7, 5, this);
		board[7][6] = new PieceKnight(true, 7, 6);
		board[7][7] = new PieceRook(true, 7, 7, this);
		whiteKing = board[7][4];
		blackKing = board[0][4];
		log = new Stack<>();
	}
	
	public Piece getPiece(int row, int col) {
		if (isEmpty(row, col)) {
			return null; // Ensures null is returned if outside the board
		}
		return board[row][col];
	}
	
	public boolean validTarget(int row, int col, boolean color) { // Can only target enemy pieces and empty tiles
		return isEmpty(row, col) || board[row][col].getColor() != color;
	}
	
	public boolean isEmpty(int row, int col) {
		if (row > 7 || col > 7 || row < 0 || col < 0) {
			throw new IllegalArgumentException("Location not on board.");
			//return true;
		}
		return board[row][col] == null;
	}
	
	public boolean canMove(int oldX, int oldY, int newX, int newY, boolean color) {
		if (isEmpty(oldX, oldY)) {
			throw new IllegalArgumentException("Can't move the contents of an empty tile.");
		}
		if (board[oldX][oldY].getColor() != color) {
			throw new IllegalArgumentException("Can't move the opponent's pieces.");
		}
		if (!validTarget(newX, newY, color)) {
			throw new IllegalArgumentException("Can't attack your own pieces.");
		}
		int dx = newX - oldX; // Relative movement
		int dy = newY - oldY;
		int x = dx == 0 ? 0 : dx / Math.abs(dx); // Direction of movement
		int y = dy == 0 ? 0 : dy / Math.abs(dy);
		if (board[oldX][oldY].canMove(Math.abs(dx), Math.abs(dy), x, y)) {
			return true;
		}
		return false;
	}
	public boolean move(int oldX, int oldY, int newX, int newY) {
		return move(oldX, oldY, newX, newY, whiteTurn);
	}
	
	public boolean move(int oldX, int oldY, int newX, int newY, boolean color) {
		if (canMove(oldX, oldY, newX, newY, whiteTurn)) {
			log.push(new Move(oldX, oldY, newX, newY, board[newX][newY]));
			int dx = newX - oldX; // Relative movement
			int dy = newY - oldY;
			int x = dx == 0 ? 0 : dx / Math.abs(dx); // Direction of movement
			int y = dy == 0 ? 0 : dy / Math.abs(dy);
			board[oldX][oldY].move(newX, newY);
			if (board[oldX][oldY].getType() == "pawn" && isEmpty(newX, newY)) {
				if (oldY - newY != 0) {
					log.pop();
					log.push(new Move(oldX, oldY, newX, newY, board[oldX][newY]));
					board[oldX][newY] = null; // En passant
				}
			}
			board[newX][newY] = board[oldX][oldY];
			board[oldX][oldY] = null;
			whiteTurn = !whiteTurn; // Switch turn
			if ((log.peek().getDefender() == null || log.peek().getDefender().getType() != "king") && inCheck(color)) {
				undo(); // If capturing the enemy king, leaving yourself in check is not illegal
				return false;
			}
			return true;
		}
		return false;
	}
	
	public void undo() {
		if (log.isEmpty())
			throw new IllegalArgumentException("Can't undo when the log is empty.");
		Move move = log.pop();
		board[move.getOldX()][move.getOldY()] = board[move.getNewX()][move.getNewY()];
		board[move.getNewX()][move.getNewY()] = null;
		if (move.getDefender() != null)
			board[move.getDefender().row][move.getDefender().col] = move.getDefender();
		whiteTurn = !whiteTurn;
		board[move.getOldX()][move.getOldY()].move(move.getOldX(), move.getOldY());
	}

	private boolean inCheck(boolean color) {
		Piece king = color ? whiteKing : blackKing;
		int newX = king.getPosition()[0];
		int newY = king.getPosition()[1];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (isEmpty(i, j) || board[i][j].getColor() == color) {
					continue;
				}
				if (canMove(i, j, newX, newY, !color)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkmate(boolean color) {
		if (!inCheck(color))
			return false;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (isEmpty(i, j) || board[i][j].getColor() != color)
					continue;
				for (int x = 0; x < board.length; x++) {
					for (int y = 0; y < board.length; y++) {
						if (!isEmpty(x, y) && board[x][y].getColor() == color) {
							continue;
						}
						if (move(i, j, x, y, color)) {
							if (!inCheck(color)) {
								undo();
								return false;
							}
							undo();
						}
					}
				}
			}
		}
		return true;
	}
	
	public String getLog() {
		return log.toString();
	}
	
	public Move logPeek() {
		return log.peek();
	}
	
	public String toString() {
		String s = "    a   b   c   d   e   f   g   h\n";
		s += "-----------------------------------\n";
		for (int i = 0; i < board.length; i++) {
			s += 8 - i + " |\t";
			for (Piece piece : board[i]) {
				if (piece == null) {
					s += "\t|\t"; // Roughly as wide as a piece
				} else {
					s += piece + "\t|\t";
				}
				s += "";
			}
			s += "\n";
			s += "-----------------------------------\n";
		}
		return s;
	}
	
	public boolean getWhiteTurn() {
		return whiteTurn;
	}
}
