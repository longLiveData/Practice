from pi import *

print(wallis(0.2))

print(basel(0.1))

print(taylor(0.2))

print(spigot(0.1))

print(race(0.01, [taylor, wallis, basel]))

print_results([(2, 78), (3, 96), (1, 100)])