# models/food.py

from models.rating import Rating

#a dish that can be sold and rated.
class Food:
    def __init__(self, name, price=0.0):
        self.name = name
        self.price = price
        self.ratings = []  # List of Rating objects
        print(f"[Food]: {self.name} at ${self.price}")

    #Add a Rating object to the food
    def add_rating(self, rating):
        self.ratings.append(rating)
        print(f"[Food] {self.name} received rating: {rating.stars} stars from {rating.user_name}")

    @property
    def average_rating(self):
        if not self.ratings:
            return 0
        total = sum(r.stars for r in self.ratings)
        return total / len(self.ratings)
