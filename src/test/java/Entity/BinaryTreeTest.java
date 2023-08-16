package Entity;

import Nodes.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {
    BinaryTree<TreeNode> bt = new BinaryTree<>();

    @Test
    void add() {
        bt.add(new TreeNode(1l,1l,"str"));
        bt.add(new TreeNode(2l,1l,"str"));
        bt.add(new TreeNode(0l,1l,"str"));
        bt.add(new TreeNode(2l,1l,"str"));
        assertEquals("0 1  1 1  2 1", bt.print());
    }

    @Test
    void lcr() {//еще тестов
        bt.add(new TreeNode(1l,1l,"str"));
        bt.add(new TreeNode(2l,1l,"str"));
        bt.add(new TreeNode(0l,1l,"str"));
        bt.add(new TreeNode(2l,1l,"str"));

        assertEquals("0 1  1 1  2 1", bt.print());
    }

    @Test
    void getSize() {
        bt.add(new TreeNode(1l,1l,"str"));
        bt.add(new TreeNode(2l,1l,"str"));
        bt.add(new TreeNode(0l,1l,"str"));
        bt.add(new TreeNode(2l,1l,"str"));
        assertEquals(3, bt.getSize());
    }

    @Test
    void testFind() {
        bt.add(new TreeNode(1l,1l,"str"));
        bt.add(new TreeNode(2l,1l,"str"));
        bt.add(new TreeNode(0l,1l,"str"));
        bt.add(new TreeNode(2l,1l,"str"));
        assertTrue(new TreeNode(0l,1l,"str").equals(bt.find(0l,1l)));

    }
    @Test
    void testFindExc() {
        bt.add(new TreeNode(1l,1l,"str"));
        bt.add(new TreeNode(2l,1l,"str"));
        bt.add(new TreeNode(0l,1l,"str"));
        bt.add(new TreeNode(2l,1l,"str"));
        assertThrows(NoSuchElementException.class,
                ()->{bt.find(213l, 1l);});
    }

    @Test
    void delete1() {
        bt.add(new TreeNode(100l,0l,"str"));
        bt.add(new TreeNode(50l,0l,"str"));
        bt.add(new TreeNode(150l,0l,"str"));
        bt.add(new TreeNode(120l,0l,"str"));
        bt.add(new TreeNode(170l,0l,"str"));
        bt.add(new TreeNode(160l,0l,"str"));
        bt.add(new TreeNode(180l,0l,"str"));
        bt.add(new TreeNode(190l,0l,"str"));
        bt.add(new TreeNode(175l,0l,"str"));
        bt.add(new TreeNode(177l,0l,"str"));
        bt.add(new TreeNode(193l,0l,"str"));
        bt.add(new TreeNode(181l,0l,"str"));
        bt.add(new TreeNode(185l,0l,"str"));

        bt.delete(170l,0l);
        assertEquals("50 0  100 0  120 0  150 0  160 0  175 0  177 0  180 0  181 0  185 0  190 0  193 0",
                bt.print());
    }
    @Test
    void delete2() {
        bt.add(new TreeNode(100l,0l,"str"));
        bt.add(new TreeNode(50l,0l,"str"));
        bt.add(new TreeNode(150l,0l,"str"));
        bt.add(new TreeNode(120l,0l,"str"));
        bt.add(new TreeNode(170l,0l,"str"));
        bt.add(new TreeNode(160l,0l,"str"));
        bt.add(new TreeNode(180l,0l,"str"));
        bt.add(new TreeNode(190l,0l,"str"));
        bt.add(new TreeNode(175l,0l,"str"));
        bt.add(new TreeNode(177l,0l,"str"));
        bt.add(new TreeNode(193l,0l,"str"));
        bt.add(new TreeNode(181l,0l,"str"));
        bt.add(new TreeNode(185l,0l,"str"));

        bt.delete(180l,0l);
        assertEquals("50 0  100 0  120 0  150 0  160 0  170 0  175 0  177 0  181 0  185 0  190 0  193 0",
                bt.print());
    }
    @Test
    void delete3() {
        bt.add(new TreeNode(100l,0l,"str"));
        bt.add(new TreeNode(50l,0l,"str"));
        bt.add(new TreeNode(150l,0l,"str"));
        bt.add(new TreeNode(120l,0l,"str"));
        bt.add(new TreeNode(170l,0l,"str"));
        bt.add(new TreeNode(160l,0l,"str"));
        bt.add(new TreeNode(180l,0l,"str"));
        bt.add(new TreeNode(190l,0l,"str"));
        bt.add(new TreeNode(175l,0l,"str"));
        bt.add(new TreeNode(177l,0l,"str"));
        bt.add(new TreeNode(193l,0l,"str"));
        bt.add(new TreeNode(181l,0l,"str"));
        bt.add(new TreeNode(185l,0l,"str"));

        bt.delete(193l,0l);
        assertEquals("50 0  100 0  120 0  150 0  160 0  170 0  175 0  177 0  180 0  181 0  185 0  190 0",
                bt.print());
    }
    @Test
    void delete4() {
        bt.add(new TreeNode(100l,0l,"str"));
        bt.add(new TreeNode(50l,0l,"str"));
        bt.add(new TreeNode(150l,0l,"str"));
        bt.add(new TreeNode(120l,0l,"str"));
        bt.add(new TreeNode(170l,0l,"str"));
        bt.add(new TreeNode(160l,0l,"str"));
        bt.add(new TreeNode(180l,0l,"str"));
        bt.add(new TreeNode(190l,0l,"str"));
        bt.add(new TreeNode(175l,0l,"str"));
        bt.add(new TreeNode(177l,0l,"str"));
        bt.add(new TreeNode(193l,0l,"str"));
        bt.add(new TreeNode(181l,0l,"str"));
        bt.add(new TreeNode(185l,0l,"str"));

        bt.delete(100l,0l);
        assertEquals("50 0  120 0  150 0  160 0  170 0  175 0  177 0  180 0  181 0  185 0  190 0  193 0",
                bt.print());
    }
    @Test
    void deleteExc() {
        bt.add(new TreeNode(100l,0l,"str"));
        bt.add(new TreeNode(50l,0l,"str"));
        bt.add(new TreeNode(150l,0l,"str"));
        bt.add(new TreeNode(120l,0l,"str"));
        bt.add(new TreeNode(170l,0l,"str"));
        bt.add(new TreeNode(160l,0l,"str"));
        bt.add(new TreeNode(180l,0l,"str"));
        bt.add(new TreeNode(190l,0l,"str"));
        bt.add(new TreeNode(175l,0l,"str"));
        bt.add(new TreeNode(177l,0l,"str"));
        bt.add(new TreeNode(193l,0l,"str"));
        bt.add(new TreeNode(181l,0l,"str"));
        bt.add(new TreeNode(185l,0l,"str"));
        System.out.println(bt.print());

        assertThrows(NoSuchElementException.class,()->{bt.delete(1000l,0l);});
    }

    @Test
    void clear1() {
        bt.add(new TreeNode(100l,0l,"str"));
        bt.add(new TreeNode(50l,0l,"str"));
        bt.add(new TreeNode(150l,0l,"str"));
        bt.add(new TreeNode(120l,0l,"str"));
        bt.add(new TreeNode(170l,0l,"str"));
        bt.clear();
        assertEquals(0,
                bt.getSize());
    }
    @Test
    void clear2() {
        bt.add(new TreeNode(100l,0l,"str"));
        bt.add(new TreeNode(50l,0l,"str"));
        bt.add(new TreeNode(150l,0l,"str"));

        bt.clear();
        assertEquals("",
                bt.print());
    }
}