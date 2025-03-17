# utils/data_loader.py

import csv
from models.fish import FreshwaterFish, SaltwaterFish
from models.location import Location, Beach, Port, Sea
from models.bait import Bait
from models.equipment import Equipment

def load_fish(csv_filename):
    fish_list = []
    with open(csv_filename, newline='', encoding='utf-8') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            species = row.get("species", "Unknown")
            water_type = row.get("water_type", "fresh").lower()
            price = float(row.get("price", 0))
            if water_type == "salt":
                fish_list.append(SaltwaterFish(species, price))
            else:
                fish_list.append(FreshwaterFish(species, price))
    return fish_list

def load_locations(csv_filename):
    locations = []
    with open(csv_filename, newline='', encoding='utf-8') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            name = row.get("name", "Unknown")
            subclass = row.get("subclass", "").lower()
            if subclass == "beach":
                locations.append(Beach(name))
            elif subclass == "port":
                locations.append(Port(name))
            elif subclass == "sea":
                locations.append(Sea(name))
            else:
                locations.append(Location(name))
    return locations

def load_baits(csv_filename):
    baits = []
    with open(csv_filename, newline='', encoding='utf-8') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            bait_type = row.get("bait_type", "Unknown")
            price = float(row.get("price", 0))
            bait = Bait(bait_type)
            bait.price = price
            baits.append(bait)
    return baits

def load_equipment(csv_filename):
    equipment_list = []
    with open(csv_filename, newline='', encoding='utf-8') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            equipment_type = row.get("equipment_type", "Unknown")
            price = float(row.get("price", 0))
            equipment_list.append(Equipment(equipment_type, price))
    return equipment_list
