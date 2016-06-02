#pragma once

#include <algorithm>
#include <iterator>

template <typename T> class SimpleSet {
public:
	/** Construct empty set **/
	SimpleSet() {
		currentSize = 0;
		maxSize = 0;
		data = nullptr;
	}
	/** Destructor */
	~SimpleSet() {
		if (data != nullptr) {
			delete[] data;
			data = nullptr;
		}
	}

	/** Copy assignment **/
	SimpleSet & operator=(SimpleSet & rhs) {
		std::swap(data, rhs.data);
		std::swap(currentSize, rhs.currentSize);
		std::swap(maxSize, rhs.maxSize);
	}

	/** Copy constructor **/
	SimpleSet(const SimpleSet &other) : SimpleSet() {
		for (int i = 0; i < other.currentSize; i++) {
			insert(other.data[i]);
		}
	}

	/** Insert i into set, return true if the element was inserted, else false **/
	bool insert(T i) {
		if (currentSize >= maxSize) {
			resize(maxSize == 0 ? 1 : maxSize * 2);
		}
		for (int j = 0; j < currentSize; j++) {
			if (data[j] == i) {
				return false;
			}
		}
		data[currentSize++] = i;
		std::sort(data, data + currentSize);
		return true;
	}
	/** Returns true if i is in the set **/
	bool exists(T i) {
		for (int j = 0; j < currentSize; j++) {
			if (data[j] == i) {
				return true;
			}
		}
		return false;
	}
	/** Removes i from the set, return true if an element was removed **/
	bool erase(T i) {
		for (int j = 0; j < currentSize; j++) {
			if (data[j] == i) {
				currentSize--;
				data[j] = data[currentSize];
				std::sort(data, data + currentSize);
				if (currentSize <= maxSize / 2) {
					resize(maxSize / 2);
				}
				return true;
			}
		}
		return false;
	}

	void print(std::ostream &os) {
		for (int i = 0; i < currentSize; i++) {
			os << data[i] << " ";
		}
	}

private:
	/** Dynamic array containing set elements **/
	int *data;
	/** Current number of elements **/
	int currentSize;
	/** Max capasity of data **/
	int maxSize;

	/** Returns the index where i may be found, else an invalid index. **/
	int find(int i) {
		for (int j = 0; j < currentSize; j++) {
			if (data[j] == i) {
				return j;
			}
		}
		return -1;
	}
	/** Resizes data, superflous elements are dropped. **/
	void resize(int n) {
		int * newData = new int[n] {};
		for (int i = 0; i < n && i < currentSize; i++) {
			newData[i] = data[i];
		}
		if (data != nullptr)
			delete[] data;
		data = newData;
		maxSize = n;
		currentSize = currentSize > maxSize ? maxSize : currentSize;
	}
};

template<typename T> std::ostream &operator<<(std::ostream &os, SimpleSet<T> set) {
	set.print(os);
	return os;
}