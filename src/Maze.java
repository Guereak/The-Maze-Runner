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
                    System.out.print(" E ");
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

    public void displayMazeTest(){
        for(int col = 0; col < columns; col++) {
            for(int row = 0; row < rows; row++) {
                System.out.print(nodes[col][row].cellConnectivity + " ");
            }
            System.out.println();
        }
    }

    public void export(){
        System.out.println(columns + " " + rows);
        System.out.println(startNode.x + " " + startNode.y);
        System.out.println(endNode.x + " " + endNode.y);
        for(int col = 0; col < columns; col++) {
            for(int row = 0; row < rows; row++) {
                System.out.print(nodes[col][row].cellConnectivity);
            }
        }
    }
}