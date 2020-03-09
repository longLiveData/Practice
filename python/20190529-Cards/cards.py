import random

def comp10001go_play(discard_history, player_no, hand):
    return random.choice(hand)

def comp10001go_group(discard_history, player_no):
    # get each card of player_no in discard_history
    # make them a group and return it
    result = []
    for discard in discard_history:
        result.append([discard[player_no]])
    return result

def diff_colour(c1, c2):
    # judge if c1 and c2 have same colour
    # if same return True else return False
    def diff(c):
        return 1 if c in 'CS' else 0
    return diff(c1) == diff(c2)

# judge if a run
def if_run(group):
    # proccess group: make it a dict: value[colour]
    # 'A' in ace_group and others in group_dict
    group_dict = dict()
    ace_group = []
    for g in group:
        value, colour = g
        if value != 'A':
            # if value already in group_dic
            # that means have a same value then this group can't a run
            if value in group_dict.keys():
                return False
            # if value not in group_dic, we put it
            else:
                group_dict[value] = colour
        else:
            ace_group.append(g)

    # make a order dict
    order_dict = {'2':'3', '3':'4', '4':'5', '5':'6', '6':'7', '7':'8',
                    '8':'9', '9':'0','0':'J','J':'Q','Q':'K'}
    # get min value of group by order_dict
    min_value = '2'
    while(min_value not in group_dict.keys()):
        min_value = order_dict[min_value]
    start = min_value
    # get the order
    # if group_dict is null then we judge each element in it
    while(len(group_dict) != 1):
        # get before colour
        before_colour = group_dict[min_value]
        # delete min_value from group_dict
        group_dict.pop(min_value)
        # update min_value
        min_value = order_dict[min_value]
        end = min_value
        # if there is no gap
        if min_value in group_dict.keys():
            # if color is different from before one
            if not diff_colour(before_colour, group_dict[min_value]):
                pass
            # if color is same before one
            else:
                return False
        # if there is a gap and we need 'A'
        else:
            # no 'A' exists 
            if len(ace_group) is 0: # now group only contains 'A'
                return False
            # 'A' exists
            else:
                # find a match colour
                find = False
                for Ace in ace_group:
                    # if Ace's colour is different from before one
                    if not diff_colour(before_colour, Ace[1]):
                        group_dict[min_value] = Ace[1]
                        ace_group.remove(Ace)
                        find = True
                        break
                # if don't find a 'A' to the gap return False
                if not find:
                    return False
    # if there is no 'A' left, then the group is a run
    if len(ace_group) is 0:
        return True, start, end

# judge if a N_kind
def if_N_kind(group):
    # make all value in group a list
    values = [g[0] for g in group]
    # transform values a set to remove repeating elements
    return len(set(values)) == 1

def comp10001go_valid_groups(groups):
    # judge each group in groups

    def valid(group):
        # length == 1: True
        if len(group) == 1:
            return True
        # length == 2: can't be run, only judge N_kind
        elif len(group) is 2:
            if not if_N_kind(group):
                return False
        # 3 <= length <= 4: judge run and N_kind
        elif 3 <= len(group) and len(group) <= 4:
            if if_run(group) == False:
                if not if_N_kind(group):
                    return False
        # length > 4: can't be N_kind, only judge run
        else:
            if if_run(group) == False:
                return False
        return True
        
    # if groups is null return true
    if len(groups) == 0:
        return True
    # if groups is not null
    else:
        for group in groups:
            # if group not valid return False
            if not valid(group):
                return False
        return True

def comp10001go_score_group(cards):
    grade_dic = {'A':20, '2':2, '3':3, '4':4, '5':5, '6':6, '7':7, '8':8, '9':9,
            '0':10, 'J':11, 'Q':12, 'K':13}

    # length == 1
    if len(cards) == 1:
        return int(grade_dic[cards[0][0]]) * -1
    # length == 2: can't be run, only judge N_kind
    elif len(cards) is 2:
        # if N_kind
        if if_N_kind(cards):
            return 2 * int(grade_dic[cards[0][0]])
        # if not special
        else:
            grade = 0
            for c in cards:
                grade -= grade_dic[c[0][0]]
            return grade

    # 3 <= length <= 4: judge run and N_kind
    elif 3 <= len(cards) and len(cards) <= 4:
        
        # if a N_kind
        if if_N_kind(cards):
            g = grade_dic[cards[0][0]]
            # if have three same numbers
            if len(cards) == 3:
                return 3*2*g
            # if have four same numbers
            elif len(cards) == 4:
                return 4*3*2*g
        # if not a N_kind
        else:
            args = if_run(cards)
            # if a run
            if args != False:
                start, end = int(args[1]), int(args[2])
                grade = 0
                for g in range(start, end+1):
                    grade += g
                return grade
            # not a run
            else:
                grade = 0
                for c in cards:
                    grade -= grade_dic[c[0][0]]
                return grade

    # length > 4: can't be N_kind, only judge run
    else:
        args = if_run(cards)
        # if a run
        if args != False:
            start, end = int(args[1]), int(args[2])
            grade = 0
            for g in range(start, end+1):
                grade += g
            return grade
        # not a run
        else:
            grade = 0
            for c in cards:
                grade -= grade_dic[c[0][0]]
            return grade