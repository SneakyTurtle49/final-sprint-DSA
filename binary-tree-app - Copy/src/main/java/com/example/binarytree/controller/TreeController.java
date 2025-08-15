@PostMapping("/process-numbers")
public ResponseEntity<Map<String, Object>> processNumbers(@RequestParam String numbers) {
    List<Integer> numList = treeService.parseNumbers(numbers);
    TreeNode root = treeService.buildBalancedBST(numList);
    String treeJson = treeService.serializeTree(root);

    // Save to DB
    TreeEntry entry = new TreeEntry(numbers, treeJson);
    repository.save(entry);

    Map<String, Object> response = new HashMap<>();
    response.put("input", numbers);
    response.put("tree", new ObjectMapper().readValue(treeJson, Object.class));
    return ResponseEntity.ok(response);
}

@Controller
public class TreeController {
    @Autowired private TreeService treeService;
    @Autowired private TreeEntryRepository repository;

    @GetMapping("/enter-numbers")
    public String enterNumbersPage() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    @ResponseBody
    public ResponseEntity<?> processNumbers(@RequestParam String numbers) throws JsonProcessingException {
        // logic from above
    }

    @GetMapping("/previous-trees")
    public String previousTrees(Model model) {
        List<TreeEntry> entries = repository.findAll();
        model.addAttribute("entries", entries);
        return "previous-trees";
    }
}
