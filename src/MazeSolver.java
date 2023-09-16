import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    public static void main(String[] args) throws Exception {
        if(args.length != 2){
            System.out.println("Usage: java MazeSolver <maze file> <output file>");
            System.exit(1);
            return;
        }

        String mazePath = args[0];
        String outputPath = args[1];

        Maze m = Maze.readMaze(mazePath);

        m.displayMaze();
        Results r1 = solveMazeDFS(m);
        Results r = solveMazeBFS(m);

        r.show();
        r1.show();

        r.export(outputPath);
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
                positions.push(currentNode.x * m.rows + currentNode.y + 1);
                while(n.parent != null){
                    n = n.parent;
                    positions.push(n.x * m.rows + n.y + 1);
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

    public static Results solveMazeDFS(Maze m) {
        Results r = new Results("DFS");
        Stack<Integer> positions = new Stack<>();

        long startTime = System.currentTimeMillis();

        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(m.startNode);
        m.startNode.visited = true;

        while (!nodeStack.isEmpty()) {
            Node currentNode = nodeStack.pop();
            r.totalSteps++;

            if (currentNode == m.endNode) {
                Node n = currentNode;
                positions.push(currentNode.x * m.rows + currentNode.y + 1);
                while(n.parent != null) {
                    n = n.parent;
                    positions.push(n.x * m.rows + n.y + 1);
                }
                break;
            }

            // Explore neighboring nodes
            for (Node neighbor : currentNode.getNeighbours()) {
                if (!neighbor.visited) {
                    neighbor.visited = true;
                    neighbor.setParent(currentNode);
                    nodeStack.push(neighbor);
                    r.totalSteps++;
                }
            }
        }

        
        long endTime = System.currentTimeMillis();
        r.timeTaken = endTime - startTime;
        
        while(!positions.isEmpty()){
            r.addPosition(positions.pop());
        }

        //We remove the final dequeue from the total steps count
        r.totalSteps -= r.getSteps();
        
        m.refreshMaze();

        return r;   
    }

    
}
