#include "stdafx.h"
#include "LList.h"

ListNode::ListNode(const std::string & value) {
	this->value = value;
	next = NULL;
}

std::string ListNode::getValue() const {
	return value;
}

ListNode * ListNode::getNext() const {
	return next;
}

LinkedList::LinkedList() {
	head = NULL;
	last = NULL;
}

LinkedList::~LinkedList() {
	std::string s;
	while (!isEmpty())
		removeFromFront(s);
}

bool LinkedList::isEmpty() const {
	return head == NULL;
}

void LinkedList::insertAtFront(const std::string & elem) {
	ListNode * next = head;
	head = new ListNode(elem);
	head->next = next;
	if (last == NULL)
		last = head;
}

void LinkedList::insertAtBack(const std::string & elem) {
	if (last == NULL) {
		insertAtFront(elem);
		return;
	}
	last->next = new ListNode(elem);
	last = last->getNext();
}

bool LinkedList::removeFromFront(std::string & output) {
	if (isEmpty())
		return false;
	ListNode * next = head->getNext();
	output = head->getValue();
	delete head;
	head = next;
	if (isEmpty())
		last = NULL;
	return true;
}

bool LinkedList::removeFromBack(std::string & output) {
	if (isEmpty())
		return false;
	if (head == last) {
		return removeFromFront(output);
	}
	ListNode * i = head;
	while (i->getNext() != last) {
		i = i->getNext();
	}
	output = last->getValue();
	delete last;
	last = i;
	i->next = NULL;
	return true;
}

ListNode * LinkedList::search(const std::string & value) {
	ListNode * i = head;
	while (i != NULL) {
		if (i->getValue() == value)
			return i;
		i = i->getNext();
	}
	return NULL;
}

void LinkedList::remove(const std::string & value) {
	ListNode * i = head;
	ListNode * prev = NULL;
	while (i != NULL) {
		if (i->getValue() == value) {
			if (prev == NULL)
				removeFromFront(std::string());
			else if (prev->getNext() == NULL)
				removeFromBack(std::string());
			else {
				prev->next = i->getNext();
				delete i;
				i = prev->getNext();
			}
		} else {
			prev = i;
			i = i->getNext();
		}
	}
}

std::ostream & operator<<(std::ostream & stream, const LinkedList & list) {
	ListNode * i = list.head;
	while (i != NULL) {
		stream << i->getValue() << " ";
		i = i->getNext();
	}
	return stream;
}
