# models/customer.py

from models.rating import Rating

class Customer:
    def __init__(self, name):
        self.name = name
        print(f"[Customer] Created customer: {self.name}")

    def purchase_food(self, food):
        print(f"[Customer] {self.name} purchased food: {food.name}")

    def rate_food(self, food, stars, comment=""):
        rating = Rating(self.name, food.name, stars, comment)
        food.add_rating(rating)
        print(f"[Customer] {self.name} rated '{food.name}' with {stars} stars")
