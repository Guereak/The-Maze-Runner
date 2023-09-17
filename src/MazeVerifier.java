import java.util.Stack;
import java.util.ArrayList;

public class MazeVerifier {
    
    public static void main(String[] args) throws Exception {
        if(args.length != 1){
            System.out.println("Usage: java MazeVerifier <maze file>");
            System.exit(1);
            return;
        }
        Maze m = Maze.readMaze(args[0]);

        Results r = MazeSolver.solveMazeDFS(m);
        displayMaze(m, r.positions);


        System.out.println("Number of cells having four walls: " + cellsFourWalls(m));
        System.out.println("Number of cells having zero walls: " + cellsZeroWalls(m));
        System.out.println("Any circular path: " + circularPaths(m));
        System.out.println("All nodes visitable: " + allNodesVisitable(m));
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

    public static boolean circularPaths(Maze m){
        boolean circularPaths = false;

        

        return circularPaths;
    }

    public static boolean allNodesVisitable(Maze m){
        boolean visitable = true;

        // We perform a DFS search to see if all the nodes can be visited from the starting point
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(m.startNode);
        m.startNode.visited = true;

        while (!nodeStack.isEmpty()) {
            Node currentNode = nodeStack.pop();

            // Explore neighboring nodes
            for (Node neighbor : currentNode.getNeighbours()) {
                if (!neighbor.visited) {
                    neighbor.visited = true;
                    nodeStack.push(neighbor);
                }
            }
        }
        
        // Once the DFS search is done, we check if each node has been visited individually
        for(int row = 0; row < m.rows; row++){
            for(int col = 0; col < m.columns; col++){
                if(m.nodes[col][row].visited == false){
                    visitable = true;
                    break;
                }
            }
        }

        m.refreshMaze();

        return visitable;
    }

    //Display the maze with the path to the solution marked with asterisks
    public static void displayMaze(Maze m, ArrayList<Integer> positions){
        for(int row = 0; row < m.rows; row++) {
            System.out.print("+---");
        }
        System.out.println("+");

        for(int col = 0; col < m.columns; col++) {
            System.out.print("|");
            for(int row = 0; row < m.rows; row++) {
                if(m.startNode.x == col && m.startNode.y == row){
                    System.out.print(" S ");
                }
                else if(m.endNode != null && m.endNode.x == col && m.endNode.y == row){
                    System.out.print(" F ");
                }
                else if(positions.contains(col * m.rows + row + 1)){
                    System.out.print(" * ");
                }
                else{
                    System.out.print("   ");
                }
                if(m.nodes[col][row].cellConnectivity == 1 || m.nodes[col][row].cellConnectivity == 3){
                    System.out.print(" ");
                }
                else{
                    System.out.print("|");
                }
            }
            System.out.println();
            System.out.print("+");
            for(int row = 0; row < m.rows; row++) {
                if(m.nodes[col][row].cellConnectivity == 2 || m.nodes[col][row].cellConnectivity == 3){
                    System.out.print("   +");
                }
                else{
                    System.out.print("---+");
                }
            }
            System.out.println();
        }
    }
    
}
