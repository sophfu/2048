# 2048 Game Implementation

This project is a Java implementation of the classic 2048 puzzle game. The game features a graphical interface, player controls, and a score-tracking system. The objective of the game is to combine tiles with the same number to eventually create a tile with the value of 2048.

## Gameplay

### Controls:
- **Arrow Keys**: Move tiles up, down, left, or right.
- **Enter**: Restart the game.
- **Space**: Continue playing after winning (reaching the 2048 tile).

### Objective:
Combine tiles of the same number to create larger numbered tiles. The goal is to create a tile with the value of 2048.

### Scoring:
- Points are awarded when tiles are combined. The score increases by the value of the combined tiles.
- The high score is tracked and displayed during the game.

### Game Over:
The game ends when no valid moves are available.

## Code Overview

### `Grid.java`
- Creates the main game window.
- Sets up the game dimensions and appearance.
- Adds the `GridPanel` where the game logic and rendering occur.

### `GridPanel.java`
- Handles the core gameplay mechanics and rendering.
- Implements the logic for moving tiles, combining tiles, and checking game states.
- Manages user input via the `KeyListener` interface.

### `GridTile.java`
- Represents individual tiles on the grid.
- Stores the value of the tile and its position.
- Handles rendering and state changes of tiles.

## Features

- **Graphical Interface**: The game grid and controls are displayed with custom graphics.
- **Dynamic Tile Generation**: New tiles are generated randomly with values of either 2 or 4.
- **Game State Management**:
  - Detects when the game is won or lost.
  - Allows restarting or continuing after a win.
- **High Score Tracking**: The highest score achieved during the session is displayed.

## Known Limitations

- The high score resets when the application is closed. Persistent storage for high scores is not implemented.
- Currently, there is no undo feature.

## Future Improvements

- Add persistent storage for high scores.
- Implement an undo button for the last move.
- Improve UI aesthetics.
- Optimize the code for better performance.

## Credits

This project is developed as a standalone implementation of the 2048 game, inspired by the original game created by Gabriele Cirulli.

## License

This project is released under the MIT License. Feel free to use and modify the code as needed.

