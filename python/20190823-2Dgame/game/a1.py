from a1_support import *
import copy

# Write the expected functions here

def main():

    # record data here
    file_name = input("Please enter the name of the level file (e.g. level1.txt): ")
    level = load_level(file_name)
    score = 0
    player_position = [0, 1]

    check_level = ""
    check_score = 0
    check_position = [0,0]

    INGAME = True
    while(INGAME):

        tile, level = tile_status(level, player_position)
        if tile == COIN:
            score += 1
        if tile == CHECKPOINT:
            check_level = copy.copy(level)
            check_position = copy.copy(player_position)
            check_score = copy.copy(score)
        print("Score: ", str(score))
        print_level(level, player_position)
        command = input("Please enter an action (enter '?' for help): ")
        if command == "?":
            print(HELP_TEXT)
        elif command == RIGHT:
            player_position = move(level, player_position, RIGHT)
        elif command == LEFT:
            player_position = move(level, player_position, LEFT)
        elif command == "a":
            level = attack(level, player_position)
        elif command == "n":
            level = check_level
            player_position = check_position
            score = check_score
        elif command == "q":
            INGAME = False


def get_position_in_direction(position, direction):
    """get a new position by direction

    Parameters:
        position (tuple<int, int>): The current position.
        direction (char): The direction.

    Returns:
        (tuple<int, int>): The new position.
    """
    if direction == RIGHT:
        position[0] += 1
    elif direction == LEFT:
        position[0] -= 1
    elif direction == UP:
        position[1] += 1
    elif direction == DOWN:
        position[1] -= 1
    return position

def get_tile_at_position(level, position):
    """Return the character representing the tile at the given position in a level string

    Parameters:
        level (str): The level string.
        position (tuple<int, int>): The current position.
        
    Returns:
        title (char): the title in that position of the level 
    """
    size = level_size(level)
    index = position_to_index(position, size)
    title = level[index]
    return title

def get_tile_in_direction(level, position, direction):
    """Determine the new position which results from moving the given position in the given 
        direction, and return the character representing the tile found at this new position.

    Parameters:
        level (str): The level string.
        position (tuple<int, int>): The current position.
        direction (char): The direction.
        
    Returns:
        tile (char): the character representing the tile found at this new position
    """
    return get_tile_at_position(level, get_position_in_direction(position, direction))
    

def remove_from_level(level, position):
    """Return a level string exactly the same as the one given, 
        but with the given position replaced by an air tile.

    Parameters:
        level (str): The level string.
        position (tuple<int, int>): The current position.
        
    Returns:
        level (string): the new level
    """
    size = level_size(level)
    index = position_to_index(position, size)
    level = level[0:index] + AIR + level[index+1:-1] + level[-1]
    return level

def move(level, position, direction):
    """Return the updated position that results from moving the character 
        from the given position in the given direction. 
        If the tile at the updated position is a wall tile, adjust the position up 
        until an air tile is found and return that as the position instead. 
        If the tile immediately below the next position is an air tile, adjust the position 
        down until the tile below is a not an air tile and return that as the position instead.

    Parameters:
        level (str): The level string.
        position (tuple<int, int>): The current position.
        direction (char): The direction.
        
    Returns:
        position (tuple<int, int>): The new position.
    """
    new_position = get_position_in_direction(position, direction)
    new_tile = get_tile_at_position(level, new_position)
    if new_tile == WALL:
        while(new_tile == WALL):
            new_position = get_position_in_direction(position, UP)
            new_tile = get_tile_at_position(level, new_position)
        return new_position
    elif new_tile == AIR:
        new_position = get_position_in_direction(position, DOWN)
        new_tile = get_tile_at_position(level, new_position)
        while new_tile != WALL:
            new_position = get_position_in_direction(position, DOWN)
            new_tile = get_tile_at_position(level, new_position)
        new_position = get_position_in_direction(position, UP)
        return new_position
    else:
        return new_position

def print_level(level, position):
    """Print the level (i.e. string) with the tile of the given position 
        replaced by the player tile.

    Parameters:
        level (str): The level string.
        position (tuple<int, int>): The current position.
    """
    size = level_size(level)
    index = position_to_index(position, size)
    level = level[0:index] + PLAYER + level[index+1:-1] + level[-1]
    print(level)

def attack(level, position):
    """Check if the position to the left of the player is a monster, if it is then print  
    and return the level with the monster tile removed. 
    Check if the position to the right of the player is a monster, if it is then print  
    and return the level with the monster tile removed. 
    If neither the left side nor the right side of the player contains a monster, 
    then print  and return the level unchanged.

    Parameters:
        level (str): The level string.
        position (tuple<int, int>): The current position.
        
    Returns:
        level (str): The new level string.

    """
    new_position = get_position_in_direction(position, LEFT)
    left_tile = get_tile_at_position(level, new_position)
    if left_tile == MONSTER:
        print("Attacking the monster on your left!")
        level = remove_from_level(level, new_position)
        position = get_position_in_direction(new_position, RIGHT)
        return level
    
    new_position = get_position_in_direction(new_position, RIGHT)
    new_position = get_position_in_direction(new_position, RIGHT)
    right_tile = get_tile_at_position(level, new_position)
    if right_tile == MONSTER:
        print("Attacking the monster on your right!")
        level = remove_from_level(level, new_position)
        position = get_position_in_direction(new_position, LEFT)
        return level

    position = get_position_in_direction(new_position, LEFT)
    print("No monsters to attack!")
    return level


def tile_status(level, position):
    """get status of game

    Parameters:
        level (str): The level string.
        position (tuple<int, int>): The current position.
        
    Returns:
        (tuple<char, str>): a tuple containing the tile character and the level.
    """
    tile = get_tile_at_position(level, position)
    if tile == GOAL:
        print("Congratulations! You finished the level")
    elif tile == MONSTER:
        print("Hit a monster!")
    elif tile == COIN:
        level = remove_from_level(level, position)
    elif tile == CHECKPOINT:
        level = remove_from_level(level, position)
    return [tile, level]

if __name__ == "__main__":
    main()
