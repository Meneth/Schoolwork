#include "Minesweeper.h"

#include <cstdlib>
#include <stdexcept>

int posToIndex(Pos pos, int rowLength) {
	return pos.y * rowLength + pos.x;
}

void addTile(std::vector<int> &tiles, Pos tile, Pos parent, Pos bounds) {
	if (tile.x == parent.x && tile.y == parent.y) // Not adjacent to itself
		return;
	else if (tile.x < 0 || tile.x >= bounds.x) // x out of bounds
		return;
	else if (tile.y < 0 || tile.y >= bounds.y) // y out of bounds
		return;
	tiles.push_back(posToIndex(tile, bounds.x));
}

std::vector<int> Minesweeper::getAdjacentTiles(Pos tile, Pos bounds) const {
	std::vector<int> tiles;
	// Add all adjacent tiles
	for (int i = -1; i <= 1; i++) {
		for (int j = -1; j <= 1; j++) {
			addTile(tiles, Pos{ tile.x + i, tile.y + j }, tile, bounds);
		}
	}
	return tiles;
}

Minesweeper::Minesweeper(int width, int height, int mines) {
	if (mines > width * height)
		throw std::invalid_argument("Can't have more mines than tiles!");
	if (width < 2 || height < 2)
		throw std::invalid_argument("Map must be at least 2 by 2 tiles!");

	bounds.x = width;
	bounds.y = height;
	tiles = std::vector<Tile>(width * height);
	for (int i = 0; i < height * width; i++) {
		tiles[i] = { false, false, false }; // Set all tiles as closed and having no mine
	}

	// Generate mines
	for (int i = 0; i < mines; i++) {
		int pos = rand() % (height * width);
		if (tiles[pos].mine)
			i--; // Try again
		else
			tiles[pos].mine = true;
	}

	gameOver = false;
	safeTiles = width * height - mines;
}

bool Minesweeper::isGameOver() const {
    return gameOver || safeTiles == 0;
}

bool Minesweeper::isTileOpen(int row, int col) const {
    return tiles[row * bounds.x + col].open;
}

bool Minesweeper::isTileMine(int row, int col) const {
    return tiles[row * bounds.x + col].mine;
}

bool Minesweeper::isTileMarked(int row, int col) const {
	return tiles[row * bounds.x + col].marked;
}

void Minesweeper::openTile(int row, int col) {
	int index = row * bounds.x + col;
	if (tiles[index].mine)
		gameOver = true;

	bool wasOpen = tiles[row * bounds.x + col].open;
	tiles[index].open = true;
	tiles[index].marked = false;
	if (!wasOpen) {
		Pos pos{ col, row };
		std::vector<int> adjacent = getAdjacentTiles(pos, bounds);
		if (numAdjacentMines(row, col) == 0) {
			for (int i = 0; i < adjacent.size(); i++) {
				int tile = adjacent[i];
				if (!tiles[tile].open)
					openTile(tile / bounds.x, tile % bounds.x);
			}
		}
		safeTiles--;
	}
}

void Minesweeper::markTile(int row, int col) {
	tiles[row * bounds.x + col].marked = !tiles[row * bounds.x + col].marked & !tiles[row * bounds.x + col].open;
}

int Minesweeper::numAdjacentMines(int row, int col) const {
	Pos pos{ col, row };
	std::vector<int> adjacent = getAdjacentTiles(pos, bounds);
	int adjCount = 0;
	for (int i = 0; i < adjacent.size(); i++) {
		int tile = adjacent[i];
		if (tiles[tile].mine)
			adjCount++;
	}
    return adjCount;
}
