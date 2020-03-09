class Quest:
	def __init__(self, reward, action, desc, before, after, req, fail_msg, pass_msg, room):
		"""TODO: Initialises a quest."""
		...
		self._reward = reward
		self._action = action
		self._desc = desc
		self._before = before
		self._after = after
		self._req = req
		self._fail_msg = fail_msg
		self._pass_msg = pass_msg
		self._room = room
		key, value = self._req.split(' ')
		self._items = {key: int(value)}
		self._complete = False

	def get_info(self):
		"""TODO: Returns the quest's description."""
		...
		return self._desc

	def is_complete(self):
		"""TODO: Returns whether or not the quest is complete."""
		...
		return self._complete

	def get_action(self):
		"""TODO: Returns a command that the user can input to attempt the quest."""
		...
		return self._action

	def get_room_desc(self):
		"""TODO: Returns a description for the room that the quest is currently in. Note that this is different depending on whether or not the quest has been completed."""
		...
		return self._after if self._complete is True else self._before

	def attempt(self, player):
		"""TODO: Allows the player to attempt this quest.

		Check the cumulative skill or will power of the player and all their items. If this value is larger than the required skill or will threshold for this quest's completion, they succeed and are rewarded with an item (the room's description will also change because of this).

		Otherwise, nothing happens."""
		...
		if self._complete is True:
			return 'You have already completed this quest.'
		if self.judge(player) is True:
			# can do this quest
			player.take(self._reward)
			self._complete = True
		return self._pass_msg if self._complete is True else self._fail_msg
	
	def judge(self, player):
		cur_skill = player.get_skill()
		cur_will = player.get_will()
		
		need_skill = self._items.get('SKILL', 0)
		need_will = self._items.get('WILL', 0)

		return True if cur_skill >= need_skill and cur_will >= need_will else False
