# models/seller.py

class Seller:
    def __init__(self, name):
        self.name = name
        print(f"[Seller]: {self.name}")

    def add_item_to_inventory(self, item, price):
        item.price = price
        print(f"[Seller] {self.name} added '{getattr(item, 'species', getattr(item, 'name', 'Unknown'))}' at ${price} to inventory.")
