import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        // Create nodes with coordinates
        Node start = new Node("Start", 0, 0);
        Node a = new Node("A", 1, 2);
        Node b = new Node("B", 2, 1);
        Node c = new Node("C", 4, 4);
        Node d = new Node("D", 3, 2);
        Node e = new Node("E", 5, 1);
        Node f = new Node("F", 6, 3);
        Node g = new Node("G", 4, 1);
        Node goal = new Node("Goal", 3, 3);

        HashSet<Node> nodes = new HashSet<>(Arrays.asList(start, a, b, c, d, e, f, g, goal));
        // Define neighbors and edge costs (bidirectional)
        start.neighbors.put(a, 2);
        start.neighbors.put(b, 4);
        a.neighbors.put(start, 2);
        a.neighbors.put(b, 4);
        b.neighbors.put(start, 4);
        b.neighbors.put(a, 4);
        b.neighbors.put(c, 2);
        b.neighbors.put(d, 3);
        d.neighbors.put(b, 3);
        d.neighbors.put(goal, 6);
        goal.neighbors.put(d, 6);
        goal.neighbors.put(b, 1);
        b.neighbors.put(goal, 1);

        c.neighbors.put(b, 2);
        c.neighbors.put(goal, 40);
        goal.neighbors.put(c, 40);

        e.neighbors.put(b, 5);
        b.neighbors.put(e, 5);
        e.neighbors.put(f, 4);
        f.neighbors.put(e, 4);
        f.neighbors.put(g, 3);
        g.neighbors.put(f, 3);
        g.neighbors.put(c, 3);
        c.neighbors.put(g, 3);

        // Adding a loop back edge for testing
        //a.neighbors.put(start, 2); // Loop edge (should be avoided)

        // Find path
        List<Node> path = AStarAlgorithm.findPath(start, goal);

        printPath(path);

        nodes.forEach(Node::reset);

        path = DijkstraAlgorithm.findShortestPath(start, goal);

        printPath(path);
    }

    private static void printPath(List<Node> path) {
        if (path.isEmpty()) {
            System.out.println("No path found.");
        } else {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }
}
