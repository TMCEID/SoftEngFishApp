# models/order.py
class Order:
    def __init__(self, customer_name):
        self.customer_name = customer_name
        self.items = []  # list of Fish 
        self.is_complete = False

    def add_item(self, item):
        self.items.append(item)

    def review_order(self):
        # Return current list of items(orders)
        return self.items

    def process_order(self):
        # Placeholder for payment
        pass

    def complete_order(self):
        self.is_complete = True
        print("Order completed!!!")
