package Nodes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeNodeTest {

    @Test
    void compareTo1() {
        TreeNode tn = new TreeNode(1l,2l,"tr");
        DataNode1 dn = new DataNode1(1l,2l,"tr", 12d);
        assertEquals(0,tn.compareTo(dn));
    }
    @Test
    void compareTo2() {
        TreeNode tn = new TreeNode(1l,2l,"tr");
        DataNode1 dn = new DataNode1(1l,2l,"tr", 12d);
        assertEquals(0,dn.compareTo(tn));
    }
    @Test
    void compareToEqual() {
        TreeNode tn = new TreeNode(1l,2l,"tr");
        TreeNode tn1 = new TreeNode(1l,2l,"tr");

        assertEquals(0,tn1.compareTo(tn));
    }
    @Test
    void compareTo3() {
        TreeNode tn = new TreeNode(1l,2l,"tr");
        DataNode1 dn = new DataNode1(15l,2l,"tr", 12d);

        assertEquals(-1,tn.compareTo(dn));
    }
    @Test
    void compareTo4() {
        TreeNode tn = new TreeNode(1l,2l,"tr");
        DataNode1 dn = new DataNode1(15l,2l,"tr", 12d);

        assertEquals(1,dn.compareTo(tn));
    }
}