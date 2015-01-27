__author__ = 'Girish Ramnani'
"""

This program takes in a sudoku and solves it ,here 0 means not set so add numbers write them down
"""
'''trying the empty board '''


def moves(board, row, column):
    """
    used set to i
    :param board:
    :param row:
    :param column:
    :return:list -> list of legal moves that the computer can choose from
    """

    all_taken = set([x for x in board[row]])
    for x in range(len(board)):
        all_taken.add(board[x][column])
    first_row = (row // 3) * 3
    first_column = (column // 3) * 3
    for x in range(first_row, first_row + 3):
        for y in range(first_column, first_column + 3):
            all_taken.add(board[x][y])
    red_moves = set([x for x in range(1, 10)])
    available_moves = red_moves.difference(all_taken)
    return available_moves


def display(board):
    for x in board:
        print(x)


def flatten_count(board, i):
    li = [y for x in board for y in x]
    return li.count(i)


def make_move(board, move, x, y, no):
    board[x][y] = move
    no -= 1
    return no


def undo_move(board, x, y, no_avail):
    board[x][y] = 0
    no_avail += 1
    return no_avail


def _solve(board, no_avail, a):
    if no_avail <= 0:
        return True
    for x in range(a, len(board)):
        for y in range(len(board)):
            if board[x][y] == 0:
                t = moves(board, x, y)
                if len(t) == 0:
                    return False
                for move in t:
                    no_avail = make_move(board, move, x, y, no_avail)
                    if _solve(board, no_avail, x):
                        return True
                    no_avail = undo_move(board, x, y, no_avail)
                return False



def solve_game(board):
    """
    the main method from where the running starts

    :param board:
    :return:
    """
    import time

    t = time.time()

    no_available = flatten_count(board, 0)
    _solve(board, no_available, 0)
    for z in board:
        for a in z:
            print(a,end="")
        print()
    print()
import time
t = time.time()
No_of_boards = int(input())
for x in range(No_of_boards):
    input()
    board = [list(map(int,input())) for x in range(9)]
    solve_game(board)
print(time.time()-t)