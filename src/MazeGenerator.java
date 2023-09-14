import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeGenerator {
    public static Maze generateMaze(int columns, int rows) {
        Maze maze = new Maze(columns, rows);

        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < columns; col++) {
                maze.nodes[row][col] = new Node();
                maze.nodes[row][col].x = col;
                maze.nodes[row][col].y = row;
            }
        }

        Random random = new Random();

        //Set a random starting position
        maze.startNode = maze.nodes[random.nextInt(columns)][random.nextInt(rows)];

        System.out.println("Starting position: " + maze.startNode.x + ", " + maze.startNode.y);

        //Grid generated, we now take care of the DFS generation
        Stack<Node> nodeStack = new Stack<>();

        nodeStack.push(maze.startNode);
        

        while(!nodeStack.empty()){
            Node currentNode = nodeStack.peek();
            currentNode.visited = true;
            ArrayList<Pair> neighbours = getUnvisitedNeighbours(maze, currentNode);

            if(!neighbours.isEmpty()){
                Pair chosenPair = neighbours.get(random.nextInt(neighbours.size()));
                Node chosenNode = chosenPair.node;
                nodeStack.push(chosenNode);

                switch(chosenPair.direction){
                    case 0:
                        currentNode.nodeLeft = chosenNode;
                        chosenNode.nodeRight = currentNode;

                        //System.out.println("Left");
                        break;
                    case 1:
                        currentNode.nodeRight = chosenNode;
                        chosenNode.nodeLeft = currentNode;

                        //System.out.println("Right");

                        break;
                    case 2:
                        currentNode.nodeAbove = chosenNode;
                        chosenNode.nodeBelow = currentNode;


                        //System.out.println("Above");

                        break;
                    case 3:
                        currentNode.nodeBelow = chosenNode;
                        chosenNode.nodeAbove = currentNode;


                        //System.out.println("Below");

                        break;
                }

                System.out.println(currentNode.x + "," + currentNode.y + " -> " + chosenNode.x + "," + chosenNode.y);
            }
            else{
                nodeStack.pop();
                System.out.println("Popped");
            }
        }


                //We then loop through all the nodes and set the cellConnectivity property
                for(int col = 0; col < columns; col++){
                    for(int row = 0; row < rows; row++){
                        if(maze.nodes[col][row].nodeAbove != null){
                            System.out.print(" | ");
                        }
                        else{
                            System.out.print("   ");
                        }
                    }
                    System.out.println();
                    for(int row = 0; row < rows; row++){
                        if(maze.nodes[col][row].nodeLeft != null){
                            System.out.print("-+");
                        }
                        else{
                            System.out.print(" +");
                        }
                        if(maze.nodes[col][row].nodeRight != null){
                            System.out.print("-");
                            maze.nodes[col][row].cellConnectivity += 2;

                        }
                        else{
                            System.out.print(" ");
                        }
                    }
                    System.out.println();
                    for(int row = 0; row < rows; row++){
                        if(maze.nodes[col][row].nodeBelow != null){
                            System.out.print(" | ");
                            maze.nodes[col][row].cellConnectivity += 1;
                        }
                        else{
                            System.out.print("   ");
                        }
                    }
                    System.out.println();
                }

        return maze;
    }

    public static ArrayList<Pair> getUnvisitedNeighbours(Maze maze, Node currentNode){
        ArrayList<Pair> arrayList = new ArrayList<>();
        
        if(currentNode.x > 0 && maze.nodes[currentNode.y][currentNode.x - 1].visited == false){
            Pair p = new Pair(maze.nodes[currentNode.y][currentNode.x - 1], 0);

            arrayList.add(p);
        }   
        if(currentNode.x < maze.columns - 1 && maze.nodes[currentNode.y][currentNode.x + 1].visited == false){
            Pair p = new Pair(maze.nodes[currentNode.y][currentNode.x + 1], 1);
            
            arrayList.add(p);
        }
        if(currentNode.y > 0 && maze.nodes[currentNode.y - 1][currentNode.x].visited == false){
            Pair p = new Pair(maze.nodes[currentNode.y - 1][currentNode.x], 2);
            
            arrayList.add(p);
        }
        if(currentNode.y < maze.rows - 1 && maze.nodes[currentNode.y + 1][currentNode.x].visited == false){
            Pair p = new Pair(maze.nodes[currentNode.y + 1][currentNode.x], 3);
            
            arrayList.add(p);
        }

        return arrayList;
    }

    public static void main(String[] args){
        Maze m = generateMaze(5, 5);
        m.displayMazeTest();
        m.displayMaze();
    }
}
