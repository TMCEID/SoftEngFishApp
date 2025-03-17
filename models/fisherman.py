# models/fisherman.py

class Fisherman:
    def __init__(self, name):
        self.name = name
        print(f"[Fisherman]: {self.name}")

    def log_catch(self, fish):
        print(f"[Fisherman] {self.name} logged catch: {fish.species}")
        # add catch to fishing_log
        with open("fishing_log.txt", "a") as file:
            file.write(f"{self.name} caught {fish.species}\n")
