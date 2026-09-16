import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class ZigzagTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            LinkedList<Integer> levelList = new LinkedList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                if (leftToRight) {
                    levelList.addLast(currentNode.val);
                } else {
                    levelList.addFirst(currentNode.val);
                }

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            result.add(levelList);
            leftToRight = !leftToRight; // Toggle direction for the next level
        }

        return result;
    }

    // Helper method to build a binary tree from level-order array representation (LeetCode style)
    public static TreeNode buildTree(Integer[] nodes) {
        if (nodes == null || nodes.length == 0 || nodes[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode current = queue.poll();

            if (i < nodes.length && nodes[i] != null) {
                current.left = new TreeNode(nodes[i]);
                queue.offer(current.left);
            }
            i++;

            if (i < nodes.length && nodes[i] != null) {
                current.right = new TreeNode(nodes[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter binary tree level-order values separated by space or comma (use 'null' or 'N' for empty nodes):");
        System.out.println("Example input: 3 9 20 null null 15 7");
        System.out.print("Input: ");

        if (!scanner.hasNextLine()) {
            scanner.close();
            return;
        }

        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("Result: []");
            scanner.close();
            return;
        }

        // Split by space or comma
        String[] parts = input.split("[,\\s]+");
        Integer[] nodes = new Integer[parts.length];

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equalsIgnoreCase("null") || parts[i].equalsIgnoreCase("N")) {
                nodes[i] = null;
            } else {
                try {
                    nodes[i] = Integer.parseInt(parts[i]);
                } catch (NumberFormatException e) {
                    nodes[i] = null;
                }
            }
        }

        TreeNode root = buildTree(nodes);
        ZigzagTraversal solver = new ZigzagTraversal();
        List<List<Integer>> result = solver.zigzagLevelOrder(root);

        System.out.println("Zigzag Level Order Traversal: " + result);
        scanner.close();
    }
}

