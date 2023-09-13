import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class Maze {

    public Node firstNode;
    public int columns;
    public int rows;
    public Node startNode;
    public Node endNode;

    public Maze(int cols, int rows) {
        columns = cols;
        this.rows = rows;
        //We generate the nodes
        firstNode = nodeInit();
    }

    public Node nodeInit() {
        Node[][] nodes = new Node[rows][columns];
    
        // Initialize nodes
        for(int row = 0; row < rows; ++row) {
            for(int col = 0; col < columns; ++col) {
                nodes[row][col] = new Node();
            }
        }
    
        // Set neighbors
        for(int row = 0; row < rows; ++row) {
            for(int col = 0; col < columns; ++col) {
                if(row > 0) nodes[row][col].nodeAbove = nodes[row-1][col];
                if(col > 0) nodes[row][col].nodeLeft = nodes[row][col-1];
                if(row < rows - 1) nodes[row][col].nodeBelow = nodes[row+1][col];
                if(col < columns - 1) nodes[row][col].nodeRight = nodes[row][col+1];
            }
        }
        return nodes[0][0];	
    }

    /*public Maze mazeGen() {
        Random random = new Random();

        int startX, startY;

        startX = random.nextInt(columns);
        startY = random.nextInt(rows);
        
        startNode = firstNode;

        //Set the starting node appropriately
        for(int i = 0; i < startX; i++){
            startNode = startNode.nodeRight;
        }
        for(int i = 0; i < startY; i++){
            startNode = startNode.nodeBelow;
        }
        
        Stack<Node> s = new Stack<Node>();
        s.push(startNode);

        System.out.println("Start node: " + startX + " " + startY);

        //Now we take care of the DFS Random walk
        int iterationsNum_Debug = 0;

        while(!s.empty()) {
            boolean nodeVisited = false;
            
            //Count the number of edges to see if the node is in a dead end
            int edges = 0;
            if(s.lastElement().nodeAbove == null || s.lastElement().nodeAbove.visited == true){
                edges++;
            }
            if(s.lastElement().nodeRight == null || s.lastElement().nodeRight.visited == true){
                edges++;
            }
            if(s.lastElement().nodeBelow == null || s.lastElement().nodeBelow.visited == true){
                edges++;
            }
            if(s.lastElement().nodeLeft == null || s.lastElement().nodeLeft.visited == true){
                edges++;
            }

            if(edges >= 3){
                s.pop();
            }
            else{
                iterationsNum_Debug++;
                while(!nodeVisited){
                    int direction = random.nextInt(4);
    
                    switch(direction){
                        case 0:
                            if(s.lastElement().nodeAbove != null && s.lastElement().nodeAbove.visited == false){
                                s.lastElement().nodeAbove.visited = true;
                                s.push(s.lastElement().nodeAbove);
                                nodeVisited = true;

                                System.out.println("Node above");
                            }
                            break;
                        case 1:
                            if(s.lastElement().nodeRight != null && s.lastElement().nodeRight.visited == false){
                                s.lastElement().nodeRight.visited = true;
                                s.push(s.lastElement().nodeRight);
                                nodeVisited = true;

                                System.out.println("Node right");
                            }
                            break;
                        case 2:
                            if(s.lastElement().nodeBelow != null && s.lastElement().nodeBelow.visited == false){
                                s.lastElement().nodeBelow.visited = true;
                                s.push(s.lastElement().nodeBelow);
                                nodeVisited = true;

                                System.out.println("Node below");
                            }
                            break;
                        case 3:
                            if(s.lastElement().nodeLeft != null && s.lastElement().nodeLeft.visited == false){
                                s.lastElement().nodeLeft.visited = true;
                                s.push(s.lastElement().nodeLeft);
                                nodeVisited = true;

                                System.out.println("Node left");
                            }
                            break;             
                    }
                }
            }

            System.out.println(iterationsNum_Debug);
        }
        
        return null;
    }*/

    public void mazeGen() {
    
        // Set a random starting position
        Random random = new Random();
        int startX = random.nextInt(columns);
        int startY = random.nextInt(rows);
    
        startNode = firstNode;
        for (int i = 0; i < startX; i++) {
            startNode = startNode.nodeRight;
        }
        for (int i = 0; i < startY; i++) {
            startNode = startNode.nodeBelow;
        }
    
        // Implement Depth-first Search
        Stack<Node> nodeStack = new Stack<>();
        startNode.visited = true;
        nodeStack.push(startNode);
    
        while (!nodeStack.isEmpty()) {
            Node currentNode = nodeStack.pop();
            ArrayList<Node> neighbours = getNeighbours(currentNode);
    
            if (!neighbours.isEmpty()) {
                nodeStack.push(currentNode);

                //TODO Implement randomness
                Node chosenNode = neighbours.get(random.nextInt(neighbours.size()));
                chosenNode.visited = true;
                nodeStack.push(chosenNode);
            }
        }

        //Doesn't make sense
        if (!nodeStack.isEmpty()) {
            endNode = nodeStack.pop();
        }
    }

    private ArrayList<Node> getNeighbours(Node node) {
    ArrayList<Node> neighbours = new ArrayList<Node>();
    if(node.nodeLeft != null && !node.nodeLeft.visited)
        neighbours.add(node.nodeLeft);
    if(node.nodeRight != null && !node.nodeRight.visited)
        neighbours.add(node.nodeRight);
    if(node.nodeAbove != null && !node.nodeAbove.visited)
        neighbours.add(node.nodeAbove);
    if(node.nodeBelow != null && !node.nodeBelow.visited)
        neighbours.add(node.nodeBelow);
    Collections.shuffle(neighbours);
    return neighbours;
}


public void displayMaze() {
    
    Node currentNode = firstNode;
    Node rowNode = firstNode;
    
    // Iterate through every node in the maze
    for(int row = 0; row < rows; ++row) {
        
        StringBuilder topLine= new StringBuilder("+");
        StringBuilder midLine = new StringBuilder("|");
        
        for(int col = 0; col < columns; ++col) {
            //If the current node has been visited, it form part of the path
            if(currentNode.visited) {
                topLine.append(" +");
                //if there is a node on the right and it has been visited to, no wall(horizontal) in between   
                if(currentNode.nodeRight != null && currentNode.nodeRight.visited)
                    midLine.append(" |");
                else
                    midLine.append("-|");
            }
            //If the current node has not been visited, it forms a wall
            else {
                topLine.append("--+");
                midLine.append("  |");
            }
            //Move to the next column
            currentNode = currentNode.nodeRight;
        }
        
        System.out.println(topLine.toString());
        System.out.println(midLine.toString());
        
        //Move to the next row
        rowNode = rowNode.nodeBelow;
        currentNode = rowNode;
    }
    
    //Print the bottom wall
    StringBuilder bottomLine = new StringBuilder("+");
    
    for(int col = 0; col < columns; ++col)
        bottomLine.append("--+");
    
    System.out.println(bottomLine.toString());
}
}
