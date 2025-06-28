import java.util.*;

class Pair {
    int node, dist;
    Pair(int n, int d) {
        node = n;
        dist = d;
    }
}

class DeliveryRoute {
    static void dijkstra(int V, List<List<Pair>> graph, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.dist));
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            for (Pair p : graph.get(curr.node)) {
                if (dist[p.node] > dist[curr.node] + p.dist) {
                    dist[p.node] = dist[curr.node] + p.dist;
                    pq.add(new Pair(p.node, dist[p.node]));
                }
            }
        }

        System.out.println("Shortest distances from source:");
        for (int i = 0; i < V; i++) {
            System.out.println("To node " + i + " = " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int V = 5;
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());

        graph.get(0).add(new Pair(1, 2));
        graph.get(0).add(new Pair(3, 1));
        graph.get(1).add(new Pair(2, 3));
        graph.get(3).add(new Pair(2, 1));
        graph.get(2).add(new Pair(4, 5));

        dijkstra(V, graph, 0);
    }
}