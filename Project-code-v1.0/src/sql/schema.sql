-- Users / friends
CREATE TABLE IF NOT EXISTS user (
    user_id  INTEGER PRIMARY KEY,
    username TEXT UNIQUE,
    phone    TEXT UNIQUE
);

CREATE TABLE IF NOT EXISTS friend (
    owner_id  INTEGER,
    friend_id INTEGER,
    PRIMARY KEY (owner_id, friend_id)
);

-- Dishes / recipes
CREATE TABLE IF NOT EXISTS dish (
    dish_id  INTEGER PRIMARY KEY,
    name     TEXT,
    category TEXT,
    price    REAL,
    rating   REAL
);

CREATE TABLE IF NOT EXISTS recipe (
    recipe_id INTEGER PRIMARY KEY AUTOINCREMENT,
    dish_id   INTEGER  NOT NULL,
    text      TEXT     NOT NULL,
    FOREIGN KEY (dish_id) REFERENCES dish (dish_id)
);

-- seed
INSERT OR IGNORE INTO user  VALUES (1,'kostas' ,'6981111111'),
                                   (2,'elena'  ,'6972222222');

INSERT OR IGNORE INTO dish  VALUES (1,'Salmon tacos' , 'fish'   ,12.5,4.2),
                                   (2,'Shrimp risotto','seafood',14.0,4.8);

INSERT OR IGNORE INTO recipe VALUES (1,1,'Bake salmon at 180 °C…'),
                                   (2,2,'Stir rice, add shrimps…');
