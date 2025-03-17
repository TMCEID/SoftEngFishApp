# features/gui.py

import tkinter as tk

def start_gui():
    root = tk.Tk()
    root.title("Fishing App")
    label = tk.Label(root, text="Fishing App GUI")
    label.pack(padx=200, pady=200)
    print("[GUI] GUI started.")
    root.mainloop()
