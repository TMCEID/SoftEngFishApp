# models/cook.py

from models.food import Food

class Recipe:
    def __init__(self, name, ingredients):
        self.name = name
        self.ingredients = ingredients
        print(f"[Recipe]: {self.name} with ingredients: {self.ingredients}")

class Cook:
    def __init__(self, name):
        self.name = name
        print(f"[Cook]: {self.name}")

    def add_recipe(self, recipe):
        print(f"[Cook] {self.name} added recipe: {recipe.name}")

    def cook_food(self, recipe):
        print(f"[Cook] {self.name} cooked food from recipe: {recipe.name}")
        return Food(recipe.name)
