import copy

# create a new board
def new_board():
    board = []
    for i in range(8):
        board.append(8*[0])
    board[3][3] = 2
    board[4][4] = 2
    board[3][4] = 1
    board[4][3] = 1
    return board

# show board to people
def print_board(board):
    s=[" ", "B", "W"]
    for i in range(8):
        row=str(i+1)
        for j in range(8):
            row += " " + s[board[i][j]]
        print(row)
    print("  a b c d e f g h")

# print score of two players
def score(board):
    s1=0
    s2=0
    for row in board:
        for col in row:
            if col == 1:
                s1 = s1 + 1
            if col == 2:
                s2 = s2 + 1
    return s1, s2

# if a right position
def enclosing(board, player, pos, direct):
    next = (pos[0]+direct[0], pos[1] +direct[1])
    r, c = next
    n = 0
    while 0 <= r and r < 8 and 0 <= c and c<8:
        if board[r][c]==player:
            if n > 0:
                return True
            else:
                return False
        if board[r][c] == 0:
            return False
        next = (r+direct[0], c+direct[1])
        r,c = next
        n = n + 1
    return False

# get valid moves
def valid_moves(board, player):
    res = []
    for i in range(len(board)):
        for j in range(len(board[0])):
            if(board[i][j] == player):
                res += valid_moves_position(board, i, j, -1, -1)
                res += valid_moves_position(board, i, j, -1, 0)
                res += valid_moves_position(board, i, j, -1, 1)
                res += valid_moves_position(board, i, j, 0, -1)
                res += valid_moves_position(board, i, j, 0, 1)
                res += valid_moves_position(board, i, j, 1, -1)
                res += valid_moves_position(board, i, j, 1, 0)
                res += valid_moves_position(board, i, j, 1, 1)
    return list(set(res))

def valid_moves_position(board, i, j, ii, jj):
    res = []
    ni = i + ii
    nj = j + jj
    p = board[i][j]
    np = 1 if p == 2 else 2
    if (ifOverIndex(board, ni, nj) and board[ni][nj] == np):
        conti = True
        while(ifOverIndex(board, ni, nj) and conti):
            if(board[ni][nj] == 0):
                res.append((ni, nj))
                conti = False
            elif(board[ni][nj] == p):
                conti = False
            else:
                ni += ii
                nj += jj
    return res

# judge if over index
def ifOverIndex(board, i, j):
    if (0 <= i and i < len(board) and 0 <= j and j < len(board[0])):
        return True
    else:
        return False

def next_state(board, player, pos):
    # update board
    i = pos[0]
    j = pos[1]
    board[i][j] = player
    board = update_state(board, i, j, -1, -1)
    board = update_state(board, i, j, -1, 0)
    board = update_state(board, i, j, -1, 1)
    board = update_state(board, i, j, 0, -1)
    board = update_state(board, i, j, 0, 1)
    board = update_state(board, i, j, 1, -1)
    board = update_state(board, i, j, 1, 0)
    board = update_state(board, i, j, 1, 1)

    # return value
    p = player
    np = 1 if p == 2 else 2

    nparr = valid_moves(board, np)
    parr = valid_moves(board, p)

    if(len(nparr) > 0):
        return board, np
    elif(len(parr) > 0):
        return board, p
    else:
        return board, 0

def update_state(board, i, j, ii, jj):
    p = board[i][j]
    np = 1 if p == 2 else 2
    ni = i + ii
    nj = j + jj
    conti = True
    if (ifOverIndex(board, ni, nj) and board[ni][nj] == np):
        arr = [(ni, nj)]
        ni += ii
        nj += jj
        while(ifOverIndex(board, ni, nj) and conti):
            if(board[ni][nj] == p):
                for pos in arr:
                    board[pos[0]][pos[1]] = p
                conti = False
            elif(board[ni][nj] == np):
                arr.append((ni, nj))
                ni += ii
                nj += jj
            else:
                conti = False
    return board

def position(string):
    if (len(string) != 2):
        return None
    a = string[0]
    b = string[1]
    if ('a' <= a and a <= 'h' and '1' <= b and b <= '8'):
        x = ord(a) - 97
        y = int(b) - 1
        return (y, x)
    else:
        return None

def run_two_players():
    board = new_board()
    curPlayer = 1
    
    while(True):
        print_board(board)
        valid1 = valid_moves(board, 1)
        valid2 = valid_moves(board, 2)
        
        posStr = input("please player " + str(curPlayer) + " input a position:")
        if (posStr == "q"):
            break

        # if a correct input
        pos = position(posStr)
        if (pos == None):
            print("invalid move")
            break

        # if a valid input
        valid = valid1 if curPlayer == 1 else valid2
        if (pos not in valid):
            print("invalid move")
            break
        
        board, curPlayer = next_state(board, curPlayer, pos)
        # if no valid moves, stop
        if(curPlayer == 0):
            print(score(board))
            break


def run_single_player():
    board = new_board()
    curPlayer = 1
    
    while(True):
        print_board(board)
        valid1 = valid_moves(board, 1)
        valid2 = valid_moves(board, 2)

        # computer operations
        if (curPlayer == 2):
            pos = getBestPos(copy.deepcopy(board), curPlayer, valid2)
        
        # human operations
        else:
            posStr = input("please player " + str(curPlayer) + " input a position:")
            if (posStr == "q"):
                break

            # if a correct input
            pos = position(posStr)
            if (pos == None):
                print("invalid move")
                break

            # if a valid input
            valid = valid1 if curPlayer == 1 else valid2
            if (pos not in valid):
                print("invalid move")
                break
        
        board, curPlayer = next_state(board, curPlayer, pos)
        if(curPlayer == 0):
            print(score(board))
            break

# computer choose a best position
def getBestPos(board, curPlayer, poslist):
    maxscore = 0
    maxpos = poslist[0]
    for pos in poslist:
        board2 = next_state(copy.deepcopy(board), curPlayer, pos)[0]
        curscore = score(board2)[1]
        if (curscore > maxscore):
            maxscore = curscore
            maxpos = pos
    return maxpos

# run_two_players()
run_single_player()