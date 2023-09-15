public class MazeVerifier {
    

    public static boolean verifyMaze(Maze maze){
        for(int col = 0; col < maze.columns; col++){
            for(int row = 0; row < maze.rows; row++){
                if(maze.nodes[col][row].cellConnectivity != 3){
                    return false;
                }
            }
        }
        return true;
    }

    public static int cellsFourWalls(Maze maze){
        int count = 0;

        for(int col = 0; col < maze.columns; col++){
            for(int row = 0; row < maze.rows; row++){
                if(maze.nodes[col][row].nodeBelow != null && maze.nodes[col][row].nodeLeft != null && maze.nodes[col][row].nodeAbove != null && maze.nodes[col][row].nodeRight != null){
                    count++;
                }
            }
        }

        return count;
    }

    public static int cellsZeroWalls(Maze maze){
        int count = 0;

        for(int col = 0; col < maze.columns; col++){
            for(int row = 0; row < maze.rows; row++){
                if(maze.nodes[col][row].nodeBelow == null && maze.nodes[col][row].nodeLeft == null && maze.nodes[col][row].nodeAbove == null && maze.nodes[col][row].nodeRight == null){
                    count++;
                }
            }
        }

        return count;
    }

    public static int circularPaths(Maze m){
        int count = 0;

        

        return count;
    }
    
}
