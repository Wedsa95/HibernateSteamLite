# HibernateSteamLite
A gui application that uses Hibernate to create a "Steam like" experience.

# ERD of the database
![Steamlite ERD](https://github.com/Wedsa95/HibernateSteamLite/blob/master/SteamLite/img/SteamSkinERD.jpg)

# Hibernate Entity Relationship Map
![Steamlite ERM](https://github.com/Wedsa95/HibernateSteamLite/blob/master/SteamLite/img/SteamSkinHR.jpg)

# MySQL Code
```ruby
CREATE DATABASE steam_lite;

USE steam_lite;

CREATE TABLE users (
user_id INT(80) PRIMARY KEY AUTO_INCREMENT,
user_name VARCHAR(80) DEFAULT NULL, 
user_email VARCHAR(80) DEFAULT NULL,
user_password VARCHAR(80) NOT NULL
);

CREATE TABLE libraries (
library_id INT(80) PRIMARY KEY AUTO_INCREMENT,
owner INT(80) NOT NULL,
FOREIGN KEY (owner) REFERENCES users(user_id)
);

CREATE TABLE games (
game_id INT(80) PRIMARY KEY AUTO_INCREMENT,
game_name VARCHAR(80) DEFAULT NULL,
game_app_id INT(80) DEFAULT NULL
);

CREATE TABLE categories (
categories_id INT(80) PRIMARY KEY AUTO_INCREMENT,
categories_name VARCHAR(80) DEFAULT NULL
);

CREATE TABLE achievements (
achiev_id INT(80) PRIMARY KEY AUTO_INCREMENT,
game_with_achiev INT(80) NOT NULL,
achiev_name VARCHAR(80) DEFAULT NULL, 
achiev_points INT(80) DEFAULT NULL,
FOREIGN KEY (game_with_achiev) REFERENCES games(game_id)
);

CREATE TABLE ratings (
rating_id INT(80) PRIMARY KEY AUTO_INCREMENT,
critic INT(80) NOT NULL,
subject INT(80) NOT NULL,
rating_value INT(80) NOT NULL,
FOREIGN KEY (critic) REFERENCES users(user_id),
FOREIGN KEY (subject) REFERENCES games(game_id)
);

CREATE TABLE library_have_games (
library_have INT(80) NOT NULL,
have_game INT(80) NOT NULL,
FOREIGN KEY (library_have) REFERENCES libraries(library_id),
FOREIGN KEY (have_game) REFERENCES games(game_id)
);

CREATE TABLE user_have_achiev (
user_have_achiev_id INT(80) PRIMARY KEY AUTO_INCREMENT,
user_have INT(80) NOT NULL,
have_achiev INT(80) NOT NULL,
achiev_unlocked BOOLEAN DEFAULT 0,
FOREIGN KEY (user_have) REFERENCES users(user_id),
FOREIGN KEY (have_achiev) REFERENCES achievements(achiev_id)
);

CREATE TABLE categories_for_games (
categories_for INT(80) NOT NULL,
for_games INT(80) NOT NULL,
FOREIGN KEY (categories_for) REFERENCES categories(categories_id),
FOREIGN KEY (for_games) REFERENCES games(game_id)
);
```
