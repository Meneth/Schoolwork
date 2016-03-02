#pragma once

#include <vector>

struct Tile {
	bool open, marked;
	bool mine;
};

struct Pos {
	int x, y;
};

class Minesweeper {
	std::vector<Tile> tiles;
	Pos bounds;
	std::vector<int> getAdjacentTiles(Pos tile, Pos bounds) const;
	bool gameOver;
	int safeTiles;

public:
    Minesweeper(int width, int height, int mines);

    bool isGameOver() const;

    bool isTileOpen(int row, int col) const;
	bool isTileMine(int row, int col) const;
	bool isTileMarked(int row, int col) const;

	void openTile(int row, int col);
	void markTile(int row, int col);

    int numAdjacentMines(int row, int col) const;

    Minesweeper(const Minesweeper &) = delete;
    Minesweeper &operator=(const Minesweeper &) = delete;
};
