import java.util.ArrayList;

public class Node {
    public Node nodeRight;
    public Node nodeLeft;
    public Node nodeAbove;
    public Node nodeBelow;

    public int x;
    public int y;
    
    public int cellConnectivity = 0;
    public boolean visited = false;
    public Node parent = null;

    public ArrayList<Node> getNeighbours(){
        ArrayList<Node> neighbours = new ArrayList<Node>();
        if(nodeLeft != null){
            neighbours.add(nodeLeft);
        }
        if(nodeRight != null){
            neighbours.add(nodeRight);
        }
        if(nodeAbove != null){
            neighbours.add(nodeAbove);
        }
        if(nodeBelow != null){
            neighbours.add(nodeBelow);
        }
        return neighbours;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
