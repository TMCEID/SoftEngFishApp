# main.py

from utils.user_loader import load_users, authenticate_user
from utils.data_loader import load_fish, load_locations, load_baits, load_equipment

from models.fisherman import Fisherman
from models.fish import FreshwaterFish, SaltwaterFish
from models.seller import Seller
from models.location import Location, Beach, Port, Sea
#from models.weather import Weather  #No API yet
from models.bait import Bait
from models.cook import Cook, Recipe
from models.food import Food
from models.customer import Customer

from features.language import Language
from features.dark_mode import DarkMode
from features.search_fisherman import search_baits
from features.search_customer import search_food
from features.level_up import level_up_system
from features.gui import start_gui

from getpass import getpass

def main():
    print("----- Starting Fishing App -----")

    # User Login
    users = load_users("data/users.csv")
    username = input("Enter username: ")
    password = getpass("Enter password: ")
    user_info = authenticate_user(username, password, users)
    if not user_info:
        print("Invalid credentials (-_-)")
        return
    role = user_info["role"].lower()
    print(f"Welcome {username}! You are logged in as a {role}.")

    # user object based on role
    user = None
    if role == "fisherman":
        user = Fisherman(username)
    elif role == "cook":
        user = Cook(username)
    elif role == "customer":
        user = Customer(username)
    elif role == "seller":
        user = Seller(username)
    else:
        print("Role not recognized.")
        return

    # Load static data from CSV files
    fish_list = load_fish("data/fish.csv")
    locations = load_locations("data/locations.csv")
    baits = load_baits("data/baits.csv")

    # Print loaded data
    print("Loaded Fish:")
    for fish in fish_list:
        print(f" - {fish.species} (${fish.price})")

    print("Loaded Locations:")
    for loc in locations:
        # object's class name (Beach, Port, Sea, or generic Location)
        print(f" - {loc.name} (Class: {loc.__class__.__name__})")

    print("Loaded Baits:")
    for bait in baits:
        print(f" - {bait.bait_type} (${bait.price})")

    # Load and display equipment for the fisherman
    if role == "fisherman":
        equipment_list = load_equipment("data/equipment.csv")
        print("Loaded Equipment:")
        for eq in equipment_list:
            print(f" - {eq}")

    # Role-based actions--Show data relevant to the role.
    if role == "fisherman":
        if fish_list:
            user.log_catch(fish_list[0])
        search_baits(baits)
    elif role == "cook":
        recipe = Recipe("Grilled Tsipoura", ["Tsipoura", "Lemon"])
        user.add_recipe(recipe)
        food_item = user.cook_food(recipe)
    elif role == "customer":
        # Create food item and let the customer purchase and rate it.
        food_item = Food("Fish Soup", price=8.0)
        user.purchase_food(food_item)
        # food rating
        user.rate_food(food_item, 3, "Delicious!")
        search_food([food_item])
    elif role == "seller":
        if fish_list:
            user.add_item_to_inventory(fish_list[0], price=12.0)

    # features
    language = Language("en")
    language.switch_language()

    dark_mode = DarkMode()
    dark_mode.toggle()

    level_up_system(user)

    start_gui()

    print("----- End of App -----")

if __name__ == "__main__":
    main()
