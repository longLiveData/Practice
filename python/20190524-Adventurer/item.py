class Item:
	def __init__(self, name, short, skill_bonus, will_bonus):
		"""TODO: Initialises an item."""
		...
		self._name = name
		self._short = short
		self._skill_bonus = skill_bonus
		self._will_bonus = will_bonus

	def get_name(self):
		"""TODO: Returns an item's name."""
		...
		return self._name

	def get_short(self):
		"""TODO: Returns an item's short name."""
		...
		return self._short

	def get_info(self):
		"""TODO: Prints information about the item."""
		...
		print(self._name)
		print('Grants a bonus of {} to SKILL.'.format(self._skill_bonus))
		print('Grants a bonus of {} to WILL.'.format(self._will_bonus))

	def get_skill(self):
		"""TODO: Returns the item's skill bonus."""
		...
		return self._skill_bonus

	def get_will(self):
		"""TODO: Returns the item's will bonus."""
		...
		return self._will_bonus
