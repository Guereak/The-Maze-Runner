import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class Maze {

    public Node[][] nodes;
    public int columns;
    public int rows;
    public Node startNode;
    public Node endNode;

    public int nodeIndicator;

    public Maze(int cols, int rows) {
        columns = cols;
        this.rows = rows;
        //We generate the nodes
        nodes = nodeInit();
    }

    public Node[][] nodeInit() {
        Node[][] nodes = new Node[columns][rows];
    
        // Initialize nodes
        for(int row = 0; row < rows; ++row) {
            for(int col = 0; col < columns; ++col) {
                nodes[col][row] = new Node();
                nodes[col][row].x = col;
                nodes[col][row].y = row;
            }
        }
        return nodes;
    }

    public void mazeGen() {
        // Set a random starting position
        Random random = new Random();
        int startX = random.nextInt(columns);
        int startY = random.nextInt(rows);
        int currentX = startX;
        int currentY = startY;

        System.out.println("Starting position: " + startX + ", " + startY);
    
        startNode = nodes[startX][startY];
    
        // Implement Depth-first Search
        Stack<Node> nodeStack = new Stack<>();
        startNode.visited = true;
        nodeStack.push(startNode);

        while (!nodeStack.isEmpty()) {
            Node currentNode = nodeStack.peek();
            ArrayList<Node> neighbours = getUnvisitedNeighbours(currentNode, currentX, currentY);
    
            if (!neighbours.isEmpty()) {                
                Node chosenNode = neighbours.get(0);
                chosenNode.visited = true;

                switch(nodeIndicator){
                    case 0:
                        currentNode.nodeLeft = chosenNode;
                        chosenNode.nodeRight = currentNode;
                        currentX += 1;
                        break;
                    case 1:
                        currentNode.nodeRight = chosenNode;
                        chosenNode.nodeLeft = currentNode;
                        currentX -= 1;
                        break;
                    case 2:
                        currentNode.nodeAbove = chosenNode;
                        chosenNode.nodeBelow = currentNode;
                        currentY -= 1;
                        break;
                    case 3:
                        currentNode.nodeBelow = chosenNode;
                        chosenNode.nodeAbove = currentNode;
                        currentY += 1;
                        break;
                }   
                
                nodeStack.push(chosenNode);
            }
            else{
                nodeStack.pop();
            }
        }

        //We then loop through all the nodes and set the cellConnectivity property
        for(int col = 0; col < columns; col++){
            for(int row = 0; row < rows; row++){
                if(nodes[col][row].nodeRight != null){
                    nodes[col][row].cellConnectivity += 1;
                }
                if(nodes[col][row].nodeBelow != null){
                    nodes[col][row].cellConnectivity += 2;
                }
            }
        }

    }

    //returns all unvisited neighbours of a node
    private ArrayList<Node> getUnvisitedNeighbours(Node node, int currentX, int currentY) {
        ArrayList<Node> neighbours = new ArrayList<Node>();
        if(node.nodeLeft == null && currentX > 0){
            neighbours.add(nodes[currentX - 1][currentY]);
        }
        if(node.nodeRight == null && currentX < columns - 1){
            neighbours.add(nodes[currentX + 1][currentY]);
        }
        if(node.nodeAbove == null && currentY > 0){
            System.out.println("CurrentPosition: " + currentX + ", " + currentY);
            System.out.println("Rows: " + rows);
            neighbours.add(nodes[currentX][currentY - 1]);
        }
        if(node.nodeBelow == null && currentY < rows - 1){
            System.out.println("CurrentPosition: " + currentX + ", " + currentY);
            neighbours.add(nodes[currentX][currentY + 1]);
        }
    
        Collections.shuffle(neighbours);
      
        if (!neighbours.isEmpty()) {
            if (node.nodeLeft != null && neighbours.get(0).x == node.nodeLeft.x && neighbours.get(0).y == node.nodeLeft.y) {
                nodeIndicator = 0;
            }
            if (node.nodeRight != null && neighbours.get(0).x == node.nodeRight.x && neighbours.get(0).y == node.nodeRight.y) {
                nodeIndicator = 1;
            }
            if (node.nodeAbove != null && neighbours.get(0).x == node.nodeAbove.x && neighbours.get(0).y == node.nodeAbove.y) {
                nodeIndicator = 2;
            }
            if (node.nodeBelow != null && neighbours.get(0).x == node.nodeBelow.x && neighbours.get(0).y == node.nodeBelow.y) {
                nodeIndicator = 3;
            }
        }
    
        return neighbours;
    }

    public void displayMaze(){
        for(int col = 0; col < columns; col++) {
            for(int row = 0; row < rows; row++) {
                System.out.print(nodes[row][col].cellConnectivity + "  ");
                if(nodes[row][col].cellConnectivity == 1 || nodes[row][col].cellConnectivity == 3){
                    System.out.print("|");
                }
                else{
                    System.out.print(" ");
                }
            }
            for(int row = 0; row < rows; row++) {
                if(nodes[row][col].cellConnectivity == 2 || nodes[row][col].cellConnectivity == 3){
                    System.out.print("--+");
                }
                else{
                    System.out.print("  +");
                }
            }
            System.out.println();
        }
    }
}