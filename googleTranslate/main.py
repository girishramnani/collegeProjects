__author__ = 'Girish'
import os
from tkinter import *
import tkinter.ttk as ttk

class UI:
    def __init__(self,master):

        self.master = master
        self.init_UI()



    def init_UI(self):
        self.label = Label(self.master,text="FileName:")
        self.entry = Entry(self.master)
        self.button = Button(self.master,text="Select file")
        self.label.grid(row=0,column=0,pady=(5,5),padx=(10))
        self.entry.grid(row=0,column=1,pady=(5,5),padx=(10))
        self.button.grid(row=0,column=2,pady=(5,5),padx=(2))


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
    root.title("google Translate")
    root.minsize(320,100)
    application = UI(root)
    root.mainloop()

