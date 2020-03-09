from room import Room
from item import Item
from adventurer import Adventurer
from quest import Quest
import sys
import os

def read_paths(source):
	"""Returns a list of lists according to the specifications in a config file, (source).

	source contains path specifications of the form:
	origin > direction > destination.

	read_paths() interprets each line as a list with three elements, containing exactly those attributes. Each list is then added to a larger list, `paths`, which is returned."""

	# TODO
	paths = []
	path_file = open(source, 'r')
	for line in path_file.readlines():
		paths.append(line.split(' > '))
	path_file.close()
	return paths

def create_rooms(paths):
	"""Receives a list of paths and returns a list of rooms based on those paths. Each room will be generated in the order that they are found."""

	# TODO
	rooms = []
	for path in paths:
		cur_room = Room(path[0])
		dest_room = Room(path[2])
		if cur_room not in rooms:
			rooms.append(cur_room)
		if dest_room not in rooms:
			rooms.append(dest_room)
		cur_room.set_path(path[1], dest_room)

	return rooms


def generate_items(source):
	"""Returns a list of items according to the specifications in a config file, (source).

	source contains item specifications of the form:
	item name | shortname | skill bonus | will bonus
	"""

	# TODO
	items = []
	item_file = open(source, 'r')
	for line in item_file.readlines():
		items.append(line.split(' | '))
	item_file.close()

	return items


def generate_quests(source, items, rooms):
	"""Returns a list of quests according to the specifications in a config file, (source).

	source contains quest specifications of the form:
	reward | action | quest description | before_text | after_text | quest requirements | failure message | success message | quest location
	"""
	# TODO
	quests = []
	quest_file = open(source, 'r')
	for line in quest_file.readlines():
		quests.append(line.split(' | '))
	quest_file.close()

	return quests


# TODO: Retrieve info from CONFIG files. Use this information to make Adventurer, Item, Quest, and Room objects.
def read_config(args):
	path_file = 'path_config.txt'
	item_file = 'item_config.txt'
	quest_file = 'quest_config.txt'

	if len(args) < 3:
		print('Usage: python3 simulation.py <paths> <items> <quests>')
		sys.exit()
	if not (path_file in args and os.path.exists(path_file)):
		print('"Please specify a valid configuration file.')
		sys.exit()
	if not (item_file in args and os.path.exists(item_file)):
		print('"Please specify a valid configuration file.')
		sys.exit()
	if not (quest_file in args and os.path.exists(quest_file)):
		print('"Please specify a valid configuration file.')
		sys.exit()

	paths = read_paths(path_file)
	if len(paths) is 0:
		print('"No rooms exist! Exiting program...')
		sys.exit()
	
	rooms_list = create_rooms(paths)
	items_list = generate_items(item_file)

	quest_file = open(quest_file, 'r')
	quest = quest_file.readlines()
	quest_file.close()
	if len(paths) is 0:
		print('"No rooms exist! Exiting program...')
		sys.exit()
	quests_list = generate_quests(quest, items_list, rooms_list)


# TODO: Receive commands from standard input and act appropriately.
def commands(adventurer, cur_room):
	while(True):
		order = input('>>> ')
		print(order)
		if order == 'QUIT':
			print('Bye!')
			return
		if order == 'HELP':
			print('HELP       - Shows some available commands. ')
			print('LOOK or L  - Lets you see the map/room again. ')
			print('QUESTS     - Lists all your active and completed quests. ')
			print('INV        - Lists all the items in your inventory. ')
			print('CHECK      - Lets you see an item (or yourself) in more detail. ')
			print('NORTH or N - Moves you to the north. ')
			print('SOUTH or S - Moves you to the south. ')
			print('EAST or E  - Moves you to the east. ')
			print('WEST or W  - Moves you to the west. ')
			print('QUIT       - Ends the adventure.')
		if order == 'LOOK' or order == 'L':
			cur_room.draw()
			# cur_room.draw()
		if order == 'INV':
			print('You are carrying:')
			inv = adventurer.get_inv()
			if len(inv) == 0:
				print('Nothing.')
		if order == 'CHECK':
			item = input("Check what? ")
			if item in adventurer.inventory:
				print(item)
			else:
				print("You don't have that!")
			
if __name__ == '__main__':
	args = sys.argv[1:]
	adventurer = Adventurer()
	rooms_list, items_list, quests_list = [], [], []
	read_config(args)
	cur_room = rooms_list[0]
	commands(adventurer, cur_room)