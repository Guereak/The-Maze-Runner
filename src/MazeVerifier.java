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

    
}
