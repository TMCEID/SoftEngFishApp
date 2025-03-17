# models/weather.py
#No Api...Ignore for now
class Weather:
    def __init__(self, condition="Unknown"):
        self.condition = condition
        print(f"[Weather] Set weather to: {self.condition}")

    def fetch_weather(self, location):
        print(f"[Weather] Fetching weather for {location}...")

    def delete_weather(self):
        print("[Weather] Deleting weather data...")
