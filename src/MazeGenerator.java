import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeGenerator {
    public static Maze generateMaze(int columns, int rows) {
        Maze maze = new Maze(columns, rows);

        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < columns; col++) {
                maze.nodes[col][row] = new Node();
                maze.nodes[col][row].x = col;
                maze.nodes[col][row].y = row;
            }
        }

        Random random = new Random();

        //Set a random starting position
        maze.startNode = maze.nodes[random.nextInt(columns)][random.nextInt(rows)];

        // Note: in the Random walk technique, we walk around randomly, therefore the starting node is not necessarily
        // surrounded by 3 walls. If we wanted to do so, we would have to set the starting node again after the first stack.pop()
        // is called.


        //Grid generated, we now take care of the DFS generation
        Stack<Node> nodeStack = new Stack<>();
        int maxStackLength = 0;
        nodeStack.push(maze.startNode);
        

        while(!nodeStack.empty()){
            Node currentNode = nodeStack.peek();
            currentNode.visited = true;
            ArrayList<Pair> neighbours = handleAdjacentNodes(maze, currentNode);
            
            if(nodeStack.size() > maxStackLength){
                maxStackLength = nodeStack.size();
                maze.endNode = maze.nodes[currentNode.x][currentNode.y];
            }

            if(!neighbours.isEmpty()){
                Pair chosenPair = neighbours.get(random.nextInt(neighbours.size()));
                Node chosenNode = chosenPair.node;
                nodeStack.push(chosenNode);
                
                switch(chosenPair.direction){
                    case 0:
                        currentNode.nodeLeft = chosenNode;
                        chosenNode.nodeRight = currentNode;
                        break;
                    case 1:
                        currentNode.nodeRight = chosenNode;
                        chosenNode.nodeLeft = currentNode;

                        break;
                    case 2:
                        currentNode.nodeAbove = chosenNode;
                        chosenNode.nodeBelow = currentNode;
                        break;
                    case 3:
                        currentNode.nodeBelow = chosenNode;
                        chosenNode.nodeAbove = currentNode;
                        break;
                }

            }
            else{
                nodeStack.pop();
            }
        }

        //We then loop through all the nodes and set the cellConnectivity property
        for(int col = 0; col < columns; col++){
            for(int row = 0; row < rows; row++){
                if(maze.nodes[col][row].nodeRight != null){
                    maze.nodes[col][row].cellConnectivity += 1;
                }
            }
            for(int row = 0; row < rows; row++){
                if(maze.nodes[col][row].nodeBelow != null){
                    maze.nodes[col][row].cellConnectivity += 2;
                }
            }
        }

        maze.refreshMaze();
        
        return maze;
    }


    public static ArrayList<Pair> handleAdjacentNodes(Maze maze, Node currentNode){
        ArrayList<Pair> arrayList = new ArrayList<>();
        
        if(currentNode.y > 0 && maze.nodes[currentNode.x][currentNode.y - 1].visited == false){
            Pair p = new Pair(maze.nodes[currentNode.x][currentNode.y - 1], 0);

            arrayList.add(p);
        }   
        if(currentNode.y < maze.rows - 1 && maze.nodes[currentNode.x][currentNode.y + 1].visited == false){
            Pair p = new Pair(maze.nodes[currentNode.x][currentNode.y + 1], 1);
            
            arrayList.add(p);
        }
        if(currentNode.x > 0 && maze.nodes[currentNode.x - 1][currentNode.y].visited == false){
            Pair p = new Pair(maze.nodes[currentNode.x - 1][currentNode.y], 2);
            
            arrayList.add(p);
        }
        if(currentNode.x < maze.columns - 1 && maze.nodes[currentNode.x + 1][currentNode.y].visited == false){
            Pair p = new Pair(maze.nodes[currentNode.x + 1][currentNode.y], 3);
            
            arrayList.add(p);
        }

        return arrayList;
    }

    public static void main(String[] args){
        if(args.length != 3){
            Maze m = generateMaze(5, 8);
            m.displayMaze();

            Maze.readMaze("../maze.txt");
            //m.export("../maze.dat");
        }
        else{
            int columns = Integer.parseInt(args[0]);
            int rows = Integer.parseInt(args[1]);
            String filename = args[2];

            Maze m = generateMaze(columns, rows);
            m.displayMaze();
            m.export(filename);
        }
    }
}
