import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        //Maze m = Maze.readMaze("../maze.txt");
        Maze m = MazeGenerator.generateMaze(5, 5);

        m.displayMaze();
        //solveMazeDFS(m);
        Results r = solveMazeBFS(m);
        r.show();
    }

    public static Results solveMazeBFS(Maze m) {
        Queue<Node> nodeQueue = new LinkedList<>(); // Use a regular queue
        Stack<Integer> positions = new Stack<>();
        Results r = new Results("BFS");

        long startTime = System.currentTimeMillis();

        nodeQueue.add(m.startNode);
        m.startNode.visited = true;

        while (!nodeQueue.isEmpty()) {
            Node currentNode = nodeQueue.poll(); // Remove the first node from the queue
            r.totalSteps++;

            if (currentNode == m.endNode) {
                Node n = currentNode;
                while(n.parent != null){
                    n = n.parent;
                    positions.push(n.y * m.columns + n.x + 1);
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
        
        long endTime = System.currentTimeMillis();

        r.timeTaken = endTime - startTime;

        while(!positions.isEmpty()){
            r.addPosition(positions.pop());
        }

        m.refreshMaze();

        return r;
    }

    public static void solveMazeDFS(Maze m) {
    Stack<Node> nodeStack = new Stack<>();
    nodeStack.push(m.startNode);
    m.startNode.visited = true;

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
