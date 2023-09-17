## Usage

### Maze generator

To run the MazeGenerator program, type the following command in the terminal:

```
java MazeGenerator 5 6 maze.dat
```

where `5` is the number of rows, `6` is the number of columns and `maze.dat` is the filename where the generated maze will be saved.

### Maze solver

To run the MazeSolver program, type the following command in the terminal:

```
java MazeSolver maze.dat solution.dat
```

where `maze.dat` is the filename containing the created maze and `solution.dat` is the filename where the solution will be saved.

### Maze verifier

To run the MazeVerifier program, type the following command in the terminal:

```
java MazeVerifier maze.dat solution.dat
```

where `maze.dat` is the filename containing the created maze and `solution.dat` is the filename containing the maze solution.

Each of the three programs should handle any filename or extension. If invalid inputs are given or the program is unable to allocate memory for the maze size specified, an error message will be displayed. 

Before running the programs, make sure that the Java Development Kit (JDK) is installed on your machine and the correct paths are set up in the system variables.

## Bonus: maze statistics

To run the MazeVerifier program, type the following command in the terminal:
  
```
java MazeVerifier maze.dat 1000
```

where `maze.dat` is the filename containing the created maze and `1000` is the number of iterations.

The goal of this supplementary class is to calculate the runtime more accurately by running the DFS and BFS multiple times and computing the average.
