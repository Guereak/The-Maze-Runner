import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        Maze m = readMaze("../maze.txt");
        //solveMazeBFS(m);
        m.displayMaze();
        m.displayMazeTest();
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
                    System.out.println(n.x + ", " + n.y);
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

    public static Maze readMaze(String filepath){
        int row_count = 0;
        int col_count = 0;
        int starting_node = 0;
        int finishing_node = 0;
        String cell_connectivity_list = "";


        try {
            // Create a FileReader to read the file
            FileReader fileReader = new FileReader(filepath);
            
            // Create a BufferedReader to read lines from the FileReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            // Initialize a StringBuilder to store the file content
            StringBuilder fileContent = new StringBuilder();
            
            String line;
            
            // Read lines from the file and append them to the StringBuilder
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line);
            }
            
            // Close the BufferedReader and FileReader
            bufferedReader.close();
            fileReader.close();
            
            // Put the file content to be a single string
            String fileContentString = fileContent.toString();
            
            // Split the string with columns
            String[] numbers = fileContentString.split(":");
            
            if(numbers.length != 5){
                System.out.println("Invalid file");
                return null;
            }


            row_count = Integer.parseInt(numbers[0]);
            col_count = Integer.parseInt(numbers[1]);
            starting_node = Integer.parseInt(numbers[2]);
            finishing_node = Integer.parseInt(numbers[3]);
            cell_connectivity_list = numbers[4];

        } catch (IOException e) {
            e.printStackTrace();
        }

        //We generate the maze with the corresponding dimensions
        Maze m = new Maze(col_count, row_count);

        for(int i = 0; i < col_count; i++){
            for(int j = 0; j < row_count; j++){

                if(starting_node == i * row_count + j + 1){
                    m.startNode = m.nodes[j][i];
                }
                if(finishing_node == i * row_count + j + 1){
                    m.endNode = m.nodes[j][i];
                }

                m.nodes[i][j].cellConnectivity = Character.getNumericValue(cell_connectivity_list.charAt(j * row_count + i));

                if(m.nodes[i][j].cellConnectivity == 1 || m.nodes[i][j].cellConnectivity == 3){
                    m.nodes[i][j].nodeLeft = m.nodes[i + 1][j];
                    m.nodes[i + 1][j].nodeRight = m.nodes[i][j];
                }
                if(m.nodes[i][j].cellConnectivity == 2 || m.nodes[i][j].cellConnectivity == 3){
                    m.nodes[i][j].nodeBelow = m.nodes[i][j + 1];
                    m.nodes[i][j + 1].nodeAbove = m.nodes[i][j];
                }
            }
        }

        //Just for debug
        for(int col = 0; col < col_count; col++){
            for(int row = 0; row < row_count; row++){
                if(m.nodes[col][row].nodeAbove != null){
                    System.out.print(" | ");
                }
                else{
                    System.out.print("   ");
                }
            }
            System.out.println();
            for(int row = 0; row < row_count; row++){
                if(m.nodes[col][row].nodeLeft != null){
                    System.out.print("-+");
                }
                else{
                    System.out.print(" +");
                }
                if(m.nodes[col][row].nodeRight != null){
                    System.out.print("-");

                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.println();
            for(int row = 0; row < row_count; row++){
                if(m.nodes[col][row].nodeBelow != null){
                    System.out.print(" | ");
                }
                else{
                    System.out.print("   ");
                }
            }
            System.out.println();
        }

        return m;
    }
}
