__author__ = 'Girish'
from tkinter import filedialog
import tkinter
root = tkinter.Tk()
root.withdraw()
print(filedialog.askopenfilename())