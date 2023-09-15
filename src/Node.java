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
        if(nodeLeft != null && nodeLeft.visited == false){
            neighbours.add(nodeLeft);
        }
        if(nodeRight != null && nodeRight.visited == false){
            neighbours.add(nodeRight);
        }
        if(nodeAbove != null && nodeAbove.visited == false){
            neighbours.add(nodeAbove);
        }
        if(nodeBelow != null && nodeBelow.visited == false){
            neighbours.add(nodeBelow);
        }
        return neighbours;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}