__author__ = 'Girish'
import os
from tkinter import *
import tkinter.ttk as ttk

class UI(Frame):
    def __init__(self,master):
        Frame.__init__(self, master)
        self.master = master
        self.initUI()



    def show_progress_bar(self):
        self.popup = popup = Toplevel(self)
        Label(popup, text="Please while translation is being done").grid(
            row=0, column=0)
        self.progressbar = progressbar = ttk.Progressbar(popup,
            orient=HORIZONTAL, length=200, mode='indeterminate')
        progressbar.grid(row=1, column=0)
        progressbar.start()
        self.popup.attributes("-topmost",True)




if __name__=="__main__":
    root = Tk()
    application = UI(root)
    root.mainloop()

