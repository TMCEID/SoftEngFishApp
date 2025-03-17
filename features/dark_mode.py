# features/dark_mode.py

class DarkMode:
    def __init__(self, is_dark=False):
        self.is_dark = is_dark
        print("[DarkMode] Initialized dark mode.")

    def toggle(self):
        self.is_dark = not self.is_dark
        print("[DarkMode] Toggled dark mode.")
