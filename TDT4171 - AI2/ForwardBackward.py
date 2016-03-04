import numpy as np


def normalize(matrix):
    """
    Normalizes a matrix or vector so that it adds up to 1. Note, also works in-place
    :param matrix: A matrix or vector
    :return: The normalized matrix
    """
    total = 0
    for vector in matrix:
        for number in vector:
            total += number
    matrix /= total
    return matrix


def forward(state, sensor, transition):
    """
    Generates a prediction based on the current state, the sensor probabilities, and a transition matrix
    :param state: Current probabilistic state
    :param sensor: The probability of each state given the evidence
    :param transition: The transition matrix from one state to another
    :return: The prediction
    """
    new_state = sensor.dot(transition).dot(state.transpose()).transpose()
    normalize(new_state)
    print("Prediction:", new_state)
    return new_state


def backward(state, sensor, transition):
    """
    Generates a backward message based on the current state, the sensor probabilities, and a transition matrix
    :param state: The current state
    :param sensor: The probability of each state given the evidence
    :param transition: The transition matrix from one state to another
    :return: The backward message
    """
    back = transition.dot(sensor).dot(state.transpose())
    back = normalize(back).transpose()
    return back


def forward_backward(ev, prior, transition):
    """
    Does smoothing based on evidence
    :param ev: The list of sensor probabilities caused by the evidence
    :param prior: The probability at t = 0
    :param transition: The transition matrix from one state to another
    :return: The smoothed probabilities
    """
    fv = [prior]
    sv = []
    for i, evidence in enumerate(ev):
        fv.append(forward(fv[i], evidence, transition))
    b = prior.copy()
    for i, vector in enumerate(b):
        for j, number in enumerate(vector):
            b[i][j] = 1
    for i in reversed(range(1, len(fv))):
        sv.append(normalize(fv[i] * b))
        b = backward(b, ev[i - 1], transition)
    print("Smoothed predictions:")
    for vector in sv:
        print(vector)
    return sv

hidden_t = np.array([[0.5, 0.5]])
sensor_true = np.array([[0.9, 0],
                        [0, 0.2]])
sensor_false = np.array([[0.1, 0],
                        [0, 0.8]])
transition = np.array([[0.7, 0.3],
                      [0.3, 0.7]])

hidden_t1 = forward(hidden_t, sensor_true, transition)
hidden_t2 = forward(hidden_t1, sensor_true, transition)
hidden_t3 = forward(hidden_t2, sensor_false, transition)
hidden_t4 = forward(hidden_t3, sensor_true, transition)
hidden_t5 = forward(hidden_t4, sensor_true, transition)

print()

forward_backward([sensor_true, sensor_true], hidden_t, transition)
print()
forward_backward([sensor_true, sensor_true, sensor_false, sensor_true, sensor_true], hidden_t, transition)