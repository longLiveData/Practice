from reversi import *

print(score(new_board()))

print(enclosing(new_board(), 1, (4, 5), (0, -1)))

print(next_state(new_board(), 1, (4, 5)))

print(valid_moves(next_state(new_board(), 1, (4, 5))[0], 2))

print(position("e3"))
