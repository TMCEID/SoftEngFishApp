PRAGMA foreign_keys = ON;

-- ─── USERS & FRIENDS ────────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS user (
    user_id   INTEGER PRIMARY KEY,
    username  TEXT    UNIQUE,
    phone     TEXT    UNIQUE,
    gender    CHAR(1),
    password  TEXT
);

CREATE TABLE IF NOT EXISTS friend (
    owner_id  INTEGER,
    friend_id INTEGER,
    PRIMARY KEY (owner_id, friend_id),
    FOREIGN KEY (owner_id)  REFERENCES user(user_id),
    FOREIGN KEY (friend_id) REFERENCES user(user_id)
);

-- ─── DISHES & RECIPES ───────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS dish (
    dish_id  INTEGER PRIMARY KEY,
    name     TEXT,
    category TEXT,
    price    REAL,
    rating   REAL
);

CREATE TABLE IF NOT EXISTS recipe (
    recipe_id INTEGER PRIMARY KEY AUTOINCREMENT,
    dish_id   INTEGER NOT NULL,
    text      TEXT    NOT NULL,
    FOREIGN KEY (dish_id) REFERENCES dish(dish_id)
);

-- ─── BAIT ───────────────────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS bait (
    bait_id       INTEGER PRIMARY KEY AUTOINCREMENT,
    name          TEXT    NOT NULL UNIQUE,
    price         REAL    NOT NULL,
    effectiveness INTEGER NOT NULL
);

-- ─── SEED DATA ──────────────────────────────────────────────────────────────

-- users
INSERT OR IGNORE INTO user (user_id, username, phone, gender, password) VALUES
  (1, 'kostas',  '6981111111', 'M', '1'),
  (2, 'elena',   '6972222222', 'F', '12'),
  (3, 'xristos', '6945010225', 'M', '123'),
  (4, 'maria',   '6988885564', 'M', '1234');

-- friends
INSERT OR IGNORE INTO friend (owner_id, friend_id) VALUES
  (3, 1),
  (3, 2),
  (4, 3),
  (4, 2);

-- dishes
INSERT OR IGNORE INTO dish (dish_id, name, category, price, rating) VALUES
  (1,  'Salmon tacos',       'fish',    12.50, 4.2),
  (2,  'Shrimp risotto',     'seafood', 14.00, 4.8),
  (3,  'Grilled Tzipoura',   'fish',    16.00, 4.5),
  (4,  'Pan-seared Lavraki', 'fish',    18.50, 4.6),
  (5,  'Sargos ceviche',     'fish',    15.00, 4.4),
  (6,  'Baked Fagri',        'fish',    20.00, 4.7),
  (7,  'Octopus salad',      'seafood', 13.50, 4.3),
  (8,  'Mussels marinara',   'seafood', 11.00, 4.1),
  (9,  'Shrimp scampi',      'seafood', 17.00, 4.5),
  (10, 'Squid ink pasta',    'seafood', 16.50, 4.4),
  (11, 'Grilled Sea Bass',   'fish',    19.00, 4.6),
  (12, 'Red Mullet bake',    'fish',    18.00, 4.5);

-- recipes
INSERT OR IGNORE INTO recipe (recipe_id, dish_id, text) VALUES
  (1,  1,  'Bake salmon at 180 °C for 12–15 min. Serve with lime and cilantro.'),
  (2,  2,  'Cook rice in broth; stir in shrimp and parmesan until creamy.'),
  (3,  3,  'Grill whole Tzipoura (sea bream) brushed with olive oil, garlic & lemon zest.'),
  (4,  4,  'Pan-sear Lavraki (European bass) fillets skin-side down in butter until crisp.'),
  (5,  5,  'Make Sargos ceviche: thin-slice fish, marinate in lime juice, toss with onion & chili.'),
  (6,  6,  'Season Fagri (red porgy), roast whole at 200 °C until the flesh flakes easily.'),
  (7,  7,  'Simmer octopus in wine & herbs for 45 min, then slice and dress with olive oil & parsley.'),
  (8,  8,  'Steam mussels in white wine, garlic & shallots until shells open; finish with parsley.'),
  (9,  9,  'Sauté shrimp in garlic-butter, deglaze with white wine, finish with parsley & lemon.'),
  (10, 10, 'Cook pasta in squid-ink sauce; toss with tender squid rings and chili flakes.'),
  (11, 11, 'Grill Sea Bass whole over coals; baste with herbed butter until skin is crisp.'),
  (12, 12, 'Bake Red Mullet with tomatoes, olives & capers in a foil parcel at 180 °C for 20 min.');

-- bait
INSERT OR IGNORE INTO bait (bait_id, name, price, effectiveness) VALUES
  (1, 'Earthworm',       2.5,   8),
  (2, 'Minnow',          5.0,   9),
  (3, 'Cricket',         3.0,   7),
  (4, 'Artificial Lure', 12.99,  6),
  (5, 'Bloodworm',       4.5,   9),
  (6, 'Corn',            1.0,   5),
  (7, 'Shrimp',          8.0,   8);
