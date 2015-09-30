import numpy as np
import matplotlib.pyplot as plot

def lagrange(xValues, functionValues, interpolationPoints):
    results = []
    for x in interpolationPoints:
        total = 0
        for i, xi in enumerate(xValues):
            total += functionValues[i] * l(xValues, xi, x) / l(xValues, xi, xi)
        results += [total]
    return results


def l(xValues, xi, x):
    total = 1.0
    for v in xValues:
        if v != xi:
            total *= (x - v)
    return total

xValues = [-1.5, -0.75, 0, 0.75, 1.5]
functionValues = [-14.1014, -0.931597, 0, 0.931597, 14.1014]
interpolationPoints = np.linspace(-1.5, 1.5, 100)
interpolation = lagrange(xValues, functionValues, interpolationPoints)
fig = plot.figure()
ax = fig.add_subplot(111)
ax.plot(xValues, functionValues, 'o', color='black', label='Known points')
ax.plot(interpolationPoints, np.tan(interpolationPoints), color='black', linestyle='dashed', label='Tan')
ax.plot(interpolationPoints, interpolation, color='black', label='Interpolation')
ax.set_xlim(-1.55, 1.55)
ax.legend(loc='upper left')
plot.show()




