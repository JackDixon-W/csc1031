import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TrieNode {
    // this class represents a single node in the tree
    Character value;
    Map<Character, TrieNode> children;
    boolean endOfWord;

    public TrieNode(Character val) {
        this.children = new HashMap<>(); // Store child nodes
        this.endOfWord = false; // Marks end of a word
        this.value = val;
    }

    public void markAsLeaf() {
        this.endOfWord = true;
    }
}

public class PrefixTree {
    TrieNode rootNode;

    public PrefixTree() {
        this.rootNode = new TrieNode(null);
    }
    
    public void insert(String word) {
        TrieNode currNode = this.rootNode;

        for (Character c : word.toCharArray()) {
            currNode.children.putIfAbsent(c, new TrieNode(c));
            currNode = currNode.children.get(c);
        }

        currNode.endOfWord = true;
    }

    public boolean search(String word) {
        TrieNode currNode = this.rootNode;

        for (Character c : word.toCharArray()) {
            if (!currNode.children.containsKey(c)) {
                return false;
            }

            currNode = currNode.children.get(c);
        }

        return currNode.endOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode currNode = this.rootNode;

        for (Character c : prefix.toCharArray()) {
            if (!currNode.children.containsKey(c)) {
                return false;
            }
            currNode = currNode.children.get(c);
        }

        return true;
    }

    public void traverse() {
        TrieNode currNode = this.rootNode;

        traverse(currNode, 1);
    }

    public void traverse(TrieNode currNode, int depth) {
        if (currNode.children.isEmpty()) return;

        for (TrieNode node : currNode.children.values()) {
            System.out.println("  ".repeat(depth) + "└── " + node.value + (node.endOfWord ? " (end)" : ""));
            traverse(node, depth + 1);
        }
    }
}

