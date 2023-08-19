package Nodes;

import Abstract.TreeNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node<T extends TreeNode>  {

    private T value;
    private Node<T> leftChild;
    private Node<T> rightChild;
    public Node(T value) {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }
}
