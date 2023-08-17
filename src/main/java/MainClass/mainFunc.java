package MainClass;

import Entity.BinaryTree;
import Nodes.DataNode1;
import Nodes.TreeNode;

public class mainFunc {
    public static void main(String[] args) {
        BinaryTree<TreeNode> bt = new BinaryTree<>();
        bt.add(new DataNode1(1l,2l,"DataNode1",13d));

    }
}
