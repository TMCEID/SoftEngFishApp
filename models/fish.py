# models/fish.py

class Fish:
    def __init__(self, species, price=0.0):
        self.species = species
        self.price = price
        print(f"[Fish]: {self.species} at ${self.price}")

class FreshwaterFish(Fish):
    def __init__(self, species, price=0.0):
        super().__init__(species, price)
        print(f"[FreshwaterFish]: {self.species}")

class SaltwaterFish(Fish):
    def __init__(self, species, price=0.0):
        super().__init__(species, price)
        print(f"[SaltwaterFish]: {self.species}")
