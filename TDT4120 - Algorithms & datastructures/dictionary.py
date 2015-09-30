def build(dictionary):
    root = [{}, []]
    for word in dictionary:
        node = root
        for character in word:
            try:
                node = node[0][character]
            except KeyError:
                node[0][character] = [{}, []]
                node = node[0][character]
        node[1] = dictionary[word]
    return root


def positions(word, index, node, length):
    if index == length:
        return node[1]
    c = word[index]
    if c == "?":
        pos = []
        for c in node[0].itervalues():
            pos += positions(word, index + 1, c, length)
        return pos
    try:
        return positions(word, index + 1, node[0][c], length)
    except KeyError:
        return []


def main():
    dictionary = {}
    position = 0
    for word in stdin.readline().split():
        try:
            dictionary[word].append(position)
        except KeyError:
            dictionary[word] = [position]
        position += len(word) + 1
    root = build(dictionary)
    printList = []

    for word in stdin:
        word = word.strip()
        if "?" in word:  # Tree traversal necessary
            pos = positions(word, 0, root, len(word))
            pos.sort()
        else:  # Simply looking it up works if there are no wildcards
            pos = dictionary.get(word, [])
        printList += ["\n%s:" % word] + pos

    printList[0] = printList[0][1:]
    printList = map(str, printList)
    print " ".join(printList)

from sys import stdin, setcheckinterval
setcheckinterval(2147483647)
main()