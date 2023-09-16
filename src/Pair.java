//The goal of this class is to be able to return both the node and its direction in the handleAdjacentNodes method
public class Pair {
    public Node node;
    public int direction;

    public Pair(Node node, int direction) {
        this.node = node;
        this.direction = direction;
    }
}
