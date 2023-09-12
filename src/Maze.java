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
        firstNode = nodeInit(0, 0);
    }

    public Node nodeInit(int col, int row) {
        Node n = new Node();

        if(col != columns) {
            n.nodeRight = nodeInit(col + 1, row);
        }

        if(row != rows) {
            n.nodeBelow = nodeInit(col, row + 1);
        }

        return n;
    }

    public Maze mazeGen() {
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

        //Now we take care of the DFS Random walk

        return null;
    }
}
