import java.util.*;

public class AStarAlgorithm {
    public static List<Node> findPath(Node start, Node goal) {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.hCost));
        Set<Node> closedSet = new HashSet<>();

        // Initialize start node
        start.gCost = 0;
        start.hCost = manhattanDistance(start, goal); // Use Manhattan distance as heuristic
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            // If goal is reached, reconstruct and return the path
            if (current.equals(goal)) {
                return reconstructPath(current);
            }

            closedSet.add(current);

            // Explore neighbors
            for (Map.Entry<Node, Integer> entry : current.neighbors.entrySet()) {
                Node neighbor = entry.getKey();
                int edgeCost = entry.getValue();

                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeGCost = current.gCost + edgeCost;
                if (tentativeGCost < neighbor.gCost) {
                    // Update neighbor's cost and parent
                    neighbor.parent = current;
                    neighbor.gCost = tentativeGCost;
                    neighbor.hCost = manhattanDistance(neighbor, goal); // Use Manhattan distance as heuristic

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    } else {
                        // Reorder the priority queue if the node's fCost has changed
                        openSet.remove(neighbor);
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return Collections.emptyList(); // Path not found
    }

    private static List<Node> reconstructPath(Node end) {
        List<Node> path = new ArrayList<>();
        for (Node node = end; node != null; node = node.parent) {
            path.add(node);
        }
        Collections.reverse(path);
        return path;
    }

    private static int manhattanDistance(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}
