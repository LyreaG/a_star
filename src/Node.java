import java.util.HashMap;
import java.util.Map;

public class Node implements Comparable<Node> {
    String name;
    int x, y; // Coordinates
    Map<Node, Integer> neighbors; // Map of neighbors and their edge costs
    int gCost; // Cost from start node
    int hCost; // Heuristic cost to goal
    Node parent;

    public Node(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.neighbors = new HashMap<>();
        this.gCost = Integer.MAX_VALUE; // Initially set to infinity
        this.hCost = 0;
        this.parent = null;
    }

    public void reset() {
        this.gCost = Integer.MAX_VALUE; // Reset cost to infinity
        this.hCost = 0; // Reset heuristic
        this.parent = null; // Reset parent
    }

    public int fCost() {
        return gCost + hCost;
    }

    public void addNeighbor(Node neighbor, int cost) {
        neighbors.put(neighbor, cost);
        //neighbor.neighbors.put(this, cost);
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.fCost(), other.fCost());
    }

    @Override
    public String toString() {
        return name + " (" + x + ", " + y + ")";
    }
}