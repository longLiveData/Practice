SIGN_GROUPS = '[ARI,LEO,SAG],[TAU,VIR,CAP],[GEM,LIB,AQU],[PIS,SCO,CAN]'

# NOTE FROM BOB: DO NOT EDIT THIS STRING
# SIGNS is a string representing the start and end date of each sign
# Each star sign is represented using three characters (e.g. Aries = ARI)
# This is followed by a : and then the start month and start date
# and then a -- and then the end month and end date
# (e.g. Aries starts on 03,21 (March 21), and ends on 04,19 (April 19))
# Each star sign's information ends with a semicolon
SIGNS = 'ARI:03,21--04,19;TAU:04,20--05,20;GEM:05,21--06,21;CAN:06,22--07,22;' + \
        'LEO:07,23--08,22;VIR:08,23--09,22;LIB:09,23--10,23;SCO:10,24--11,20;' + \
        'SAG:11,21--12,21;CAP:12,22--01,20;AQU:01,21--02,21;PIS:02,22--03,20;'

def extract_month(bday: str) -> int:
    '''
    Given a string representing a date in the format DD-MM-YYYY, return the
    month within the string as an int.

    >>> extract_month('02-08-1991')
    8

    >>> extract_month('22-12-1995')
    12
    '''

    # The "pass" here is a keyword that basically means 'do nothing'
    # Get rid of all these "pass" statements, as well as these comments
    # so your final code is cleaned up
    return int(bday.split('-')[1])

def extract_date(bday: str) -> int:
    '''
    Given a string representing a date in the format DD-MM-YYYY, return the
    date within the string as an int.

    >>> extract_date('02-08-1991')
    2

    >>> extract_date('22-12-1995')
    22
    '''

    # Again: The "pass" here is a keyword that basically means 'do nothing'
    # Get rid of all these "pass" statements, as well as these comments
    # so your final code is cleaned up
    return int(bday.split('-')[0])

def count_common_occurrences(word1: str, word2: str) -> int:
    '''
    Given two strings, word1 and word2, return how many characters in word1 are
    characters that also occur in word2. You can assume any letters in the
    given string are lowercase. Also, spaces do NOT count as a common character.

    Algorithm:
    - Go through each character in word1, one by one. If this character
      occurs in word2, then add 1 to the count. Return total count after
      you're done going through the characters.

    >>> count_common_occurrences('bob y', 'bobbette z')
    3

    >>> count_common_occurrences('bobbette z', 'bob y')
    4
    '''
    num = 0
    for ch in word1:
        if ch != ' ' and ch in word2:
            num += 1
    return num

def occurs_within(word1: str, word2: str) -> bool:
    ''' 
    Given two strings, word1 and word2, return True iff ALL characters in word2
    occur in word1, in their original order (e.g. if word2 was "hey", then we
    would return True iff within word1, the letter "h" occurred at an index
    earlier than "e", and "e" occurred in word1 at an index earlier than "y".)
    
    Assume all letters in the given words are lowercase.
    You may use string methods if any are useful.

    >>> occurs_within('aaacaabxyt', 'cat')
    True
    
    >>> occurs_within('tac', 'cat')
    False

    >>> occurs_within('oboe', 'bob')
    False
    
    >>> occurs_within('ecxbtalt', 'exalt')
    True
    
    >>> occurs_within('ecxbtal', 'exalt')
    False
    '''

    temp_word = word1
    for ch in word2:
        index = temp_word.find(ch)
        if index == -1:
            return False
        else:
            temp_word = temp_word[index::]
    return True

def get_sign_group(sign: str) -> int:
    '''
    Given a three character string representing a star sign, return
    which group (out of 0, 1, 2, or 3) this star sign belongs to.

    Use the SIGN_GROUPS string (already defined for you above) to figure
    out the group.
    i.e. As given by this string '[ARI,LEO,SAG],[TAU,VIR,CAP],[GEM,LIB,AQU],[PIS,SCO,CAN]'
         the signs ARI, LEO and SAG are in group 0, the signs TAU, VIR, CAP are in group 1,
         and so on.

    >>> get_sign_group('ARI')
    0

    >>> get_sign_group('CAN')
    3
    '''
    sign_array = SIGN_GROUPS.split(']')
    for index in range(len(sign_array)):
        if sign_array[index].find(sign) > -1:
            return index
        
def find_astrological_compatibility(sign_1: str, sign_2: str) -> int:
    '''
    Given two 3-character strings representing star signs, return an int
    representing how compatible they are.
    
    According to Bob's rules, the compatibility between signs is calculated
    based on the SIGN_GROUPS they belong in, as follows:
    - If the signs are in the same group, return 100
    - If both the signs are in an odd-numbered group (i.e. 1 and 3)
      OR if both are in an even group (i.e. 0 and 2), then return 70
    - For all other cases, return 50

    >>> find_astrological_compatibility('ARI', 'LEO')
    100

    >>> find_astrological_compatibility('GEM', 'LIB')
    100

    >>> find_astrological_compatibility('SAG', 'AQU')
    70
    
    >>> find_astrological_compatibility('VIR', 'PIS')
    70
    
    >>> find_astrological_compatibility('LEO', 'TAU')
    50

    >>> find_astrological_compatibility('AQU', 'SCO')
    50
    '''

    num1 = get_sign_group(sign_1)
    num2 = get_sign_group(sign_2)
    if num1 == num2:
        return 100
    elif (num1 == 1 and num2 == 3) or (num1 == 3 and num2 == 1):
        return 70
    elif (num1 == 0 and num2 == 2) or (num1 == 2 and num2 == 0):
        return 70
    else:
        return 50

def find_astrological_sign(month: int, date: int) -> str:
    '''    
    Given two int values representing a month and a date, return a
    3-character string that gives us what star sign a person born in that
    month and on that date belongs to. Use the SIGNS string (already
    defined for you at the top of this file) to figure this out.

    NOTE FROM BOB: A lot of string slicing to do here. It looks like the
                   information for each sign is exactly 17 characters long.
                   We can probably use that.

    >>> find_astrological_sign(8, 24)
    'VIR'

    >>> find_astrological_sign(12, 31)
    'CAP'
    '''
    records = SIGNS.split(';')
    for record in records:
        if record != '':
            sign = record[0:3:]
            start_month = int(record[4:6:])
            start_day = int(record[7:9:])
            end_month = int(record[11:13:])
            end_day = int(record[14:16:])
            if month == start_month and date >= start_day:
                return sign
            if month == end_month and date <= end_day:
                return sign


def get_bday_compatibility(bday1: str, bday2: str) -> int:
    '''
    Given two strings representing birthdates in the format DD-MM-YYYY,
    figure out what star sign each birthdate belongs to and return
    the astrological compatibility of the two signs.

    NOTE FROM BOB: This code should look similar in structure to the
                   get_name_compatibility function that I already finished.
                   That is, it should call on other functions for help,
                   and not be more than a couple of lines long in total.

    >>> get_bday_compatibility('30-08-1998', '01-09-1998')
    100
    
    >>> get_bday_compatibility('30-08-1998', '08-12-1978')
    50
    '''

    return find_astrological_compatibility(find_astrological_sign(extract_month(bday1), extract_date(bday1)), \
        find_astrological_sign(extract_month(bday2), extract_date(bday2)))

#
# NOTE FROM BOB: THE FUNCTION BELOW IS DONE ALREADY. DO NOT CHANGE ANYTHING HERE.
#
def get_name_compatibility(name1: str, name2: str) -> int:
    '''    
    Given two strings, return their name compatibility.

    NOTE FROM BOB:
    According to Bob's rules, this should be calculated by checking
    how many letters in the first name occurs in the second, and
    adding this to how many letters in the second name occurs in
    the first, and then multiplying this sum by 11 if either all the letters
    in the first name occur in their original order within the second name or
    vice versa. If neither names occur within the other, multiply
    by 10 instead.
    
    For example:
    If the names are BOB Y and BOBBETTE Z, then
    - for BOB Y, we get the number 3, because 3 letters in this name
      occur in the other
    - for BOBBETTE Z, we get the number 4, because 4 letters in this
      name occur in the other.
    
    The number returned for these two names should be 3 + 4 = 7,
    and since neither of the names occur entirely within the other,
    (the function occurs_within would return False, since
    there is no Y in "BOBBETTE Z" and no E,T nor Z in "BOB Y"),
    we multiply by 10 = 70.

    >>> get_name_compatibility('bob y', 'bobbette z')
    70

    >>> get_name_compatibility('sadia sharmin', 'ryan gosling')
    150
    '''

    if occurs_within(name1, name2) or occurs_within(name2, name1):
        multiplier = 11
    else:
        multiplier = 10

    return (count_common_occurrences(name1, name2) + \
            count_common_occurrences(name2, name1)) * multiplier


#
# NOTE FROM BOB: THE FUNCTION BELOW IS DONE ALREADY. DO NOT CHANGE ANYTHING HERE.
#
def calculate_love_score(name1: str, name2: str, bday1: str, bday2: str) -> float:
    '''
    Given four strings representing two names and two birthdates in the
    format DD-MM-YYYY, return the average of the name compatibility of the
    names given and the birthdate compatibility of the birthdates given.
    
    NOTE FROM BOB: I'm not going to worry about checking if this number
                   is greater than 100. It's fine.

    >>> calculate_love_score('ian somerhalder', 'bobbette z', '08-12-1978', '01-09-1998')
    55.0
    '''

    return (get_name_compatibility(name1, name2) + \
            get_bday_compatibility(bday1, bday2)) / 2

#
# NOTE FROM BOB: THE FUNCTION BELOW IS DONE ALREADY. DO NOT CHANGE ANYTHING HERE.
#
def is_valid_date(bday: str) -> bool:
    '''
    Given a string, return True iff it is in the format DD-MM-YYYY, where
    each character in DD, MM, and YYYY is a number, and each are separated
    by a - character.

    >>> is_valid_date('30-02-1903')
    True

    >>> is_valid_date('01-20-1999')
    False
    
    >> is_valid_date('310-31-3123')
    False
    '''
    
    if count_common_occurrences(bday, '0123456789') == 8 and bday[2] == '-' and bday[5] == '-':
        month = extract_month(bday)
        date = extract_date(bday)
        if ((month >= 1) and (month <= 12)) and \
           ((date >= 1) and (date <= 31)):
            return True
        
    return False

# ===================================================================== 
# NOTE FROM BOB:
# Do NOT change any of the lines below (feel free to comment them out
# for testing purposes though)
# p.s. remember to delete all your test stuff, and keep the code below
#      EXACTLY the same as it is now as when you're handing it in
# =====================================================================
if __name__ == "__main__":
    name1 = input("Give me your first and last name: ")
    bday1 = input("Give me your birthdate in the format DD-MM-YYYY: ")
    while not is_valid_date(bday1):
        bday1 = input("Invalid input. Give me your birthdate in the format DD-MM-YYYY: ")

    print("=====")
    name2 = input("Give me your crush's first and last name: ")
    bday2 = input("Give me your crush's birthdate in the format DD-MM-YYYY: ")
    while not is_valid_date(bday2):
        bday2 = input("Invalid input. Give me your birthdate in the format DD-MM-YYYY: ")

    # The .lower() after the names turns all characters into lowercase so you don't
    # have to worry about comparisons taking into consideration both upper and lowercase of each letter
    print("You are " + str(calculate_love_score(name1.lower(), name2.lower(), bday1, bday2)) +"% compatible in love!")

