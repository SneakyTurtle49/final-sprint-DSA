@Service
public class TreeService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split(","))
                     .map(String::trim)
                     .map(Integer::parseInt)
                     .sorted()
                     .collect(Collectors.toList());
    }

    public TreeNode buildBalancedBST(List<Integer> sorted) {
        return new BinarySearchTree().buildBalanced(sorted);
    }

    public String serializeTree(TreeNode root) throws JsonProcessingException {
        return objectMapper.writeValueAsString(root);
    }
}
