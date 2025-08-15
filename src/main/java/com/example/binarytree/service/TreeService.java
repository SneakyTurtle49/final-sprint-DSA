
package com.example.binarytree.service;

import com.example.binarytree.tree.TreeNode;
import com.example.binarytree.tree.BinarySearchTree;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
