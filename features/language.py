# features/language.py

class Language:
    def __init__(self, language_code="en"):
        self.language_code = language_code
        print(f"[Language] Set language to: {self.language_code}")

    def switch_language(self):
        self.language_code = "gr" if self.language_code == "en" else "en"
        print(f"[Language] Switched language to: {self.language_code}")
