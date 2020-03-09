class Adventurer:
    
    def __init__(self):
        """TODO: Initialises an adventurer object."""
        ...
        self._inventory = []
        self._skill = 5
        self._will = 5


    def get_inv(self):
        """TODO: Returns the adventurer's inventory."""
        ...
        return self._inventory

    def get_skill(self):
        """TODO: Returns the adventurer's skill level. Whether this value is generated before or after item bonuses are applied is your decision to make."""
        ...
        return self._skill if self._skill >= 0 else 0

    def get_will(self):
        """TODO: Returns the adventurer's will power. Whether this value is generated before or after item bonuses are applied is your decision to make."""
        ...
        return self._will if self._will >= 0 else 0

    def take(self, item):
        """TODO: Adds an item to the adventurer's inventory."""
        ...
        self._inventory.append(item)

    def check_self(self):
        """TODO: Shows adventurer stats and all item stats."""
        ...
        print('You are an adventurer, with a SKILL of {} and a WILL of {}.'.format(self._skill, self._will))
        print('You are carrying:')

        total_skill = self._skill
        total_will = self._will
        for inv in self._inventory:
            # print item info
            print(inv.get_info())
            total_skill += inv.get_skill()
            total_will += inv.get_will()
        print()
        print('With your items, you have a SKILL level of {} and a WILL power of {}.'.format(total_skill, total_will))