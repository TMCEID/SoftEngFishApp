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
    FOREIGN KEY (owner_id) REFERENCES user(user_id),
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
    recipe_id  INTEGER PRIMARY KEY AUTOINCREMENT,
    dish_id    INTEGER  NOT NULL,
    text       TEXT     NOT NULL,
    FOREIGN KEY (dish_id) REFERENCES dish(dish_id)
);

-- ─── BAIT ───────────────────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS bait (
    bait_id       INTEGER PRIMARY KEY AUTOINCREMENT,
    name          TEXT    NOT NULL UNIQUE,
    price         REAL    NOT NULL,
    effectiveness INTEGER NOT NULL    -- 0–10 scale
);

-- ─── SEED DATA ──────────────────────────────────────────────────────────────

-- users
INSERT OR IGNORE INTO user (user_id, username, phone, gender, password) VALUES
  (1, 'kostas', '6981111111', 'M',  '1'),
  (2, 'elena',  '6972222222', 'F',  '12'),
  (3, 'xristos','6945010225', 'M',  '123'),
  (4, 'maria',  '6988885564','M',  '1234');

-- friends
INSERT OR IGNORE INTO friend (owner_id, friend_id) VALUES
  (3, 1),
  (3, 2),
  (4, 3),
  (4, 2);

-- dishes
INSERT OR IGNORE INTO dish (dish_id, name, category, price, rating) VALUES
  (1, 'Salmon tacos',   'fish',    12.5, 4.2),
  (2, 'Shrimp risotto', 'seafood', 14.0, 4.8);

-- recipes
INSERT OR IGNORE INTO recipe (recipe_id, dish_id, text) VALUES
  (1, 1, 'Bake salmon at 180 °C…'),
  (2, 2, 'Stir rice, add shrimps…');

-- bait
INSERT OR IGNORE INTO bait (bait_id, name, price, effectiveness) VALUES
  (1, 'Earthworm',       2.5,  8),
  (2, 'Minnow',          5.0,  9),
  (3, 'Cricket',         3.0,  7),
  (4, 'Artificial Lure', 12.99,6),
  (5, 'Bloodworm',       4.5,  9),
  (6, 'Corn',            1.0,  5),
  (7, 'Shrimp',          8.0,  8);
