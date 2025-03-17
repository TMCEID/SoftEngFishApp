# models/location.py

class Location:
    def __init__(self, name):
        self.name = name
        print(f"[Location]: {self.name}")

class Beach(Location):
    def __init__(self, name):
        super().__init__(name)
        print(f"[Beach]: {self.name}")

class Port(Location):
    def __init__(self, name):
        super().__init__(name)
        print(f"[Port]: {self.name}")

class Sea(Location):
    def __init__(self, name):
        super().__init__(name)
        print(f"[Sea]: {self.name}")
