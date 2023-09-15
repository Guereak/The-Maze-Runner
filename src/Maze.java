import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

    public void displayMaze(){
        for(int row = 0; row < rows; row++) {
            System.out.print("+---");
        }
        System.out.println("+");

        for(int col = 0; col < columns; col++) {
            System.out.print("|");
            for(int row = 0; row < rows; row++) {
                if(startNode.x == col && startNode.y == row){
                    System.out.print(" S ");
                }
                else if(endNode != null && endNode.x == col && endNode.y == row){
                    System.out.print(" F ");
                }
                else{
                    System.out.print("   ");
                }
                if(nodes[col][row].cellConnectivity == 1 || nodes[col][row].cellConnectivity == 3){
                    System.out.print(" ");
                }
                else{
                    System.out.print("|");
                }
            }
            System.out.println();
            System.out.print("+");
            for(int row = 0; row < rows; row++) {
                if(nodes[col][row].cellConnectivity == 2 || nodes[col][row].cellConnectivity == 3){
                    System.out.print("   +");
                }
                else{
                    System.out.print("---+");
                }
            }
            System.out.println();
        }
    }


    public void export(String fileName){
        String content = rows + ":" + columns + ":" + (startNode.x * rows + startNode.y + 1) + ":" + (endNode.x * rows + endNode.y + 1) + ":";
        for(int col = 0; col < columns; col++) {
            for(int row = 0; row < rows; row++) {
                content += nodes[col][row].cellConnectivity;
            }
        } 

        try {
            // Create a FileWriter with the given file name
            FileWriter fileWriter = new FileWriter(fileName);

            // Create a BufferedWriter to write efficiently
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the string to the file
            bufferedWriter.write(content);

            // Close the BufferedWriter
            bufferedWriter.close();

            System.out.println("String has been written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Apparently not working as intended
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

        for(int i = 0; i < row_count; i++){
            for(int j = 0; j < col_count; j++){
        
                if(starting_node == i * col_count + j + 1){
                    m.startNode = m.nodes[i][j];
                }
                if(finishing_node == i * col_count + j + 1){
                    m.endNode = m.nodes[i][j];
                }
        
                m.nodes[i][j].cellConnectivity = Character.getNumericValue(cell_connectivity_list.charAt(i * col_count + j));
        
                if(m.nodes[i][j].cellConnectivity == 1 || m.nodes[i][j].cellConnectivity == 3){
                    if(j + 1 < col_count) {
                        m.nodes[i][j].nodeRight = m.nodes[i][j + 1];
                        m.nodes[i][j + 1].nodeLeft = m.nodes[i][j];
                    }
                }
                if(m.nodes[i][j].cellConnectivity == 2 || m.nodes[i][j].cellConnectivity == 3){
                    if(i + 1 < row_count) {
                        m.nodes[i][j].nodeBelow = m.nodes[i + 1][j];
                        m.nodes[i + 1][j].nodeAbove = m.nodes[i][j];
                    }
                }
            }
        }

        return m;
    }
}