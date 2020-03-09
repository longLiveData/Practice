class Room:
	
	def __init__(self, name):
		"""TODO: Initialises a room. Do not change the function signature (line 2)."""
		...
		self._name = name

		self._north = None
		self._south = None
		self._east = None
		self._west = None

		self._quest = None

	def get_name(self):
		"""TODO: Returns the room's name."""
		...
		return self._name

	def get_short_desc(self):
		"""TODO: Returns a string containing a short description of the room. This description changes based on whether or not a relevant quest has been completed in this room.

		If there are no quests that are relevant to this room, this should return: 'There is nothing in this room.' """
		...
		if self._quest is None:
			return "There is nothing in this room."
		else:
			return self._quest.get_room_desc()

	def get_quest_action(self):
		"""TODO: If a quest can be completed in this room, returns a command that the user can input to attempt the quest."""
		...
		return self._quest.get_action()

	def set_quest(self, q):
		"""TODO: Sets a new quest for this room."""
		...
		self._quest = q

	def get_quest(self):
		"""TODO: Returns a Quest object that can be completed in this room."""
		...
		return self._quest
		
	def set_path(self, dir, dest):
		"""TODO: Creates an path leading from this room to another."""
		...
		if(dir == 'NORTH'):
			self._north = dest
		elif(dir == 'SOUTH'):
			self._south = dest
		elif(dir == 'EAST'):
			self._east = dest
		elif(dir == 'WEST'):
			self._west = dest
				
	def draw(self):
		"""TODO: Creates a drawing depicting the exits in each room."""
		...
		np = 'NN' if self._north else '--'
		sp = 'SS' if self._south else '--'
		wp = 'WW' if self._west else '|'
		ep = 'EE' if self._east else '|'

		print('+---------{}---------+'.format(np))
		for i in range(5):
			print('|                    |')
		print('{}                    {}'.format(wp, ep))
		for i in range(5):
			print('|                    |')
		print('+---------{}---------+'.format(sp))
		print("You are standing at the {}.".format(self._name))
		print(self.get_short_desc())

	def move(self, dir):
		"""TODO: Returns an adjoining Room object based on a direction given. (i.e. if dir == "NORTH", returns a Room object in the north)."""
		...
		dir = dir[0]
		if(dir == 'N'):
			return self._north
		elif(dir == 'S'):
			return self._south
		elif(dir == 'E'):
			return self._east
		elif(dir == 'W'):
			return self._west

