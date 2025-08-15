@Entity
public class TreeEntry {
    @Id @GeneratedValue private Long id;
    private String inputNumbers;

    @Lob
    private String treeJson;

    public TreeEntry() {}

    public TreeEntry(String inputNumbers, String treeJson) {
        this.inputNumbers = inputNumbers;
        this.treeJson = treeJson;
    }

    // Getters/setters
}
