# utils/user_loader.py

import csv

def load_users(csv_filename):
    users = {}
    with open(csv_filename, newline='', encoding='utf-8') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            username = row.get("username")
            password = row.get("password")
            role = row.get("role")
            users[username] = {"password": password, "role": role}
    return users

def authenticate_user(username, password, users):
    if username in users and users[username]["password"] == password:
        return users[username]
    return None
