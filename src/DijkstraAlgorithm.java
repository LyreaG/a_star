import java.util.*;


public class DijkstraAlgorithm {

    public static List<Node> findShortestPath(Node start, Node goal) {
        //Node can implement Comparable<T>
        //Just used a Comparator here to be dynamic for both algorithms
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::fCost));

        // Initialize start node
        start.gCost = 0;
        priorityQueue.add(start);

        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();

            // If we reached the goal node, stop the search
            if (current.equals(goal)) {
                break;
            }

            // Process each neighbor of the current node
            for (Map.Entry<Node, Integer> entry : current.neighbors.entrySet()) {
                Node neighbor = entry.getKey();
                int edgeCost = entry.getValue();

                int tentativeGCost = current.gCost + edgeCost;
                if (tentativeGCost < neighbor.gCost) {
                    neighbor.gCost = tentativeGCost;
                    neighbor.parent = current;

                    // Update the priority queue
                    priorityQueue.add(neighbor);
                }
            }
        }

        return reconstructPath(goal);
    }

    private static List<Node> reconstructPath(Node end) {
        List<Node> path = new ArrayList<>();
        for (Node node = end; node != null; node = node.parent) {
            path.add(node);
        }

        //If the Size is 1 means there was no path or start node is the same as goal
        //who probably should be checked before run the algorithm
        if (path.size() == 1) return Collections.emptyList();
        Collections.reverse(path);
        return path;
    }
}
