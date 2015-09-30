def main():
    stdin.readline()  # Skip irrelevant line (what function to use)
    stdin.readline()  # Skip irrelevant line (how many nodes)
    root = stdin.readline().strip()
    goal = stdin.readline().strip()
    if goal == root:
        print 0
        return
    nodes = {}
    depth = 1
    for line in stdin:
        numbers = line.split()
        node = numbers[0]
        for number in numbers:
            if node != number:
                nodes[number] = node
        while goal in nodes:
            goal = nodes[goal]
            if goal == root:
                print depth
                return
            depth += 1

from sys import stdin, setcheckinterval
setcheckinterval(2147483647)
main()