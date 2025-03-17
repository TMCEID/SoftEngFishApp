# models/equipment.py
class Equipment:
    def __init__(self, equipment_type, price=0.0):
        self.equipment_type = equipment_type  #  Fishing rod,net
        self.price = price
        print(f"[Equipment]: {self.equipment_type} at ${self.price}")

    def __str__(self):
        return f"{self.equipment_type} (${self.price})"
