import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        Maze m = Maze.readMaze("../maze.txt");
        m.displayMaze();
        solveMazeBFS(m);
    }


    //Not tested yet
    public static void solveMazeBFS(Maze m) {
        Queue<Node> nodeQueue = new LinkedList<>(); // Use a regular queue
        nodeQueue.add(m.startNode);

        while (!nodeQueue.isEmpty()) {
            Node currentNode = nodeQueue.poll(); // Remove the first node from the queue

            if (currentNode == m.endNode) {
                Node n = currentNode;
                while(n.parent != null){
                    n = n.parent;
                    //System.out.println(n.x + ", " + n.y);
                }
                break;
            }

            // Explore neighboring nodes
            for (Node neighbor : currentNode.getNeighbours()) {
                if (!neighbor.visited) {
                    neighbor.visited = true;
                    neighbor.setParent(currentNode);
                    nodeQueue.add(neighbor);
                }
            }
        }        

    }

    //Not tested yet
    public static void solveMazeDFS(Maze m) {
    Stack<Node> nodeStack = new Stack<>();
    nodeStack.push(m.startNode);

    while (!nodeStack.isEmpty()) {
        Node currentNode = nodeStack.pop();

        if (currentNode == m.endNode) {
            Node n = currentNode;
            while(n.parent != null) {
                n = n.parent;
                System.out.println(n.x + ", " + n.y);
            }
            break;
        }

        // Explore neighboring nodes
        for (Node neighbor : currentNode.getNeighbours()) {
            if (!neighbor.visited) {
                neighbor.visited = true;
                neighbor.setParent(currentNode);
                nodeStack.push(neighbor);
            }
        }
    }   
}

    
}
