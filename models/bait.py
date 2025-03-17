# models/bait.py

class Bait:
    def __init__(self, bait_type):
        self.bait_type = bait_type
        self.price = 0.0
        print(f"[Bait]: {self.bait_type}")

    def increment_success(self):
        print(f"[Bait success]: {self.bait_type}")
