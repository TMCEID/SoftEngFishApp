# models/rating.py

class Rating:
    def __init__(self, user_name, item_name, stars, comment=""):
        self.user_name = user_name
        self.item_name = item_name
        self.stars = stars  # For example, from 1 to 5
        self.comment = comment

    def __str__(self):
        return f"{self.user_name} rated {self.item_name}: {self.stars} stars. {self.comment}"

        # comment?
