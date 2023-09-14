public class Node {
    public Node nodeRight = null;
    public Node nodeLeft = null;
    public Node nodeAbove = null;
    public Node nodeBelow = null;

    public int x;
    public int y;
    
    public int cellConnectivity = 0;
    public boolean visited = false;
    public Node parent = null;
}
