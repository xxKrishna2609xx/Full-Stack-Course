import java.util.*;

public static ArrayList<Integer> BFS(int V, ArrayList<ArrayList<Integer>> adj) {

    Queue<Integer> q1 = new LinkedList<>();

    int visited[] = new int[V];

    q1.add(0);
    visited[0] = 1;

    ArrayList<Integer> ans = new ArrayList<>();

    while (!q1.isEmpty()) {

        int node = q1.poll();  // Front element delete
        ans.add(node);

        for (int i = 0; i < adj.get(node).size(); i++) {

            if (visited[adj.get(node).get(i)] == 0) {

                visited[adj.get(node).get(i)] = 1;
                q1.add(adj.get(node).get(i));

            }
        }
    }

    return ans;
}