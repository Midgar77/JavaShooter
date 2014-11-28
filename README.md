JavaShooter
===========

A simple Java shooter game for two players to face each other. Both player images were taken from an old folder of sprites. The bottom sprite was taken from Bucky's Bucky Land game from thenewboston.org.

This is a very simple game where the two players can move UP, DOWN, LEFT, and RIGHT (WASD for top player, arrows for bottom player) and shoot (Space for top player, Enter for bottom player).

Each player cannot go past the yellow line in the middle. 

The goal of the game is to shoot the opponent 20 times. Each player's score is shown in gray on the left side of the screen.

One special feature is that the sprites gain movement speed for every successful hit (someone with a score of 18 can move MUCH quicker than someone with a score of 2).

Additionally, each sprite can only have 5 active bullets. Once a bullet goes off the screen or hits opponent, the sprite gains another bullet to use. So, if a player shoots 6 bullets, the very first bullet will just disappear (this stops the users from being able to fill the entire screen with bullets).


Possible features to add:
        -At the beginning of each game, ask user to set up settings. Settings include Score Limit, speed of sprites, and # of            active bullets allowed.
        -Add "powerups" that pop up in each sprite's half of the screen equally. Allow the user to gain a powerup by either              colliding with it, or making a powerup on opponent's side of the screen disappear by shooting it.
