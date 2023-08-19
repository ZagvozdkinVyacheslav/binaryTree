package MainClass;

import Entity.BinaryTree;
import Inheritance.DataNode1;
import Abstract.TreeNode;

public class mainFunc {
    public static void main(String[] args) {
        BinaryTree<TreeNode> bt = new BinaryTree<>();
        bt.add(new DataNode1(1l,2l,"DataNode1",13d));

    }
}
