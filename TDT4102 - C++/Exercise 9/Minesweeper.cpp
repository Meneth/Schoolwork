#include "Minesweeper.h"

#include <cstdlib>
#include <stdexcept>

Minesweeper::Minesweeper(int width, int height, int mines) {
	if (mines > width * height)
		throw std::invalid_argument("Can't have more mines than tiles!");
	this->height = height;
	this->width = width;
	tiles = std::vector<Tile>(width * height);
	for (int i = 0; i < height * width; i++) {
		tiles[i] = { false, false }; // Set all tiles as closed and having no mine
	}
	for (int i = 0; i < mines; i++) {
		int pos = rand() % (height * width);
		if (tiles[pos].mine)
			i--; // Try again
		else
			tiles[pos].mine = true;
	}
}

Minesweeper::~Minesweeper() {
	std::vector<Tile>().swap(tiles);
}

bool Minesweeper::isGameOver() const {
    return false;
}

bool Minesweeper::isTileOpen(int row, int col) const {
    return tiles[row * width + col].open;
}

bool Minesweeper::isTileMine(int row, int col) const {
    return tiles[row * width + col].mine;
}

void Minesweeper::openTile(int row, int col) {
	tiles[row * width + col].open = true;
}

int Minesweeper::numAdjacentMines(int row, int col) const {
    return 0;
}
