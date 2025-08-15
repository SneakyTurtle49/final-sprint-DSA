
package com.example.binarytree;

import com.example.binarytree.service.TreeService;
import com.example.binarytree.tree.TreeNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TreeServiceTests {

    @Autowired
    private TreeService treeService;

    @Test
    void testParseNumbers() {
        List<Integer> result = treeService.parseNumbers("7,3,9,1");
        assertEquals(Arrays.asList(1, 3, 7, 9), result);
    }

    @Test
    void testBuildBalancedBST() {
        TreeNode root = treeService.buildBalancedBST(Arrays.asList(1, 2, 3));
        assertNotNull(root);
        assertEquals(2, root.value);
    }

    @Test
    void testSerializeTree() throws Exception {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        String json = treeService.serializeTree(root);
        assertTrue(json.contains("10"));
        assertTrue(json.contains("5"));
    }
}
