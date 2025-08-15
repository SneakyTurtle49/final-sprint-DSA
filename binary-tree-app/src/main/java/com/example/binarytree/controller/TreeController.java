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

@PostMapping("/process-numbers")
@ResponseBody
public ResponseEntity<?> processNumbers(@RequestParam String numbers) throws JsonProcessingException {
    // numbers --> "7,3,9,1,5" from the form
    List<Integer> numList = treeService.parseNumbers(numbers);  // converts to [1,3,5,7,9]
    TreeNode root = treeService.buildBalancedBST(numList);      // creates BST
    String treeJson = treeService.serializeTree(root);          // JSON representation

    TreeEntry entry = new TreeEntry(numbers, treeJson);         // create DB entity
    repository.save(entry);                                     // save to DB

    Map<String, Object> response = new HashMap<>();
    response.put("input", numbers);
    response.put("tree", new ObjectMapper().readValue(treeJson, Object.class));
    return ResponseEntity.ok(response);
}
