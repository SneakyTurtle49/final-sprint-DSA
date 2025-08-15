public class BinarySearchTree {
    public TreeNode buildBalanced(List<Integer> sorted) {
        if (sorted.isEmpty()) return null;
        int mid = sorted.size() / 2;
        TreeNode root = new TreeNode(sorted.get(mid));
        root.left = buildBalanced(sorted.subList(0, mid));
        root.right = buildBalanced(sorted.subList(mid + 1, sorted.size()));
        return root;
    }
}
