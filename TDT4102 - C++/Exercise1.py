def isFibonacciNumber(n):
    a = 0
    b = 1
    while b < n:
        temp = b
        b = a + b
        a = temp
    if b == n:
        return True
    else:
        return False

for n in range(20):
    print(str(n) + ": " + str(isFibonacciNumber(n)))
