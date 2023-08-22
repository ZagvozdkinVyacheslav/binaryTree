package Entity;

import org.example.Abstract.TreeNode;
import org.example.Entity.BinaryTree;
import org.example.Inheritance.DataNode1;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {
    BinaryTree<TreeNode> bt = new BinaryTree<>();

    @Test
    void add() {
        bt.add(new DataNode1(1l,1l,"DataNode1",12d));
        bt.add(new DataNode1(2l,1l,"DataNode1",12d));
        bt.add(new DataNode1(0l,1l,"DataNode1",12d));
        bt.add(new DataNode1(2l,1l,"DataNode1",12d));
        assertEquals("0 1  1 1  2 1", bt.print());
    }

    @Test
    void lcr() {//еще тестов
        bt.add(new DataNode1(1l,1l,"DataNode1",12d));
        bt.add(new DataNode1(2l,1l,"DataNode1",12d));
        bt.add(new DataNode1(0l,1l,"DataNode1",12d));
        bt.add(new DataNode1(2l,1l,"DataNode1",12d));

        assertEquals("0 1  1 1  2 1", bt.print());
    }

    @Test
    void getSize() {
        bt.add(new DataNode1(1l,1l,"DataNode1",12d));
        bt.add(new DataNode1(2l,1l,"DataNode1",12d));
        bt.add(new DataNode1(0l,1l,"DataNode1",12d));
        bt.add(new DataNode1(2l,1l,"DataNode1",12d));
        assertEquals(3, bt.getSize());
    }

    @Test
    void testFind() {
        bt.add(new DataNode1(1l,1l,"DataNode1",12d));
        bt.add(new DataNode1(2l,1l,"DataNode1",12d));
        bt.add(new DataNode1(0l,1l,"DataNode1",12d));
        bt.add(new DataNode1(2l,1l,"DataNode1",12d));
        assertTrue(new DataNode1(0l,1l,"DataNode1",12d).equals(bt.find(0l,1l)));

    }

    @Test
    void testFindExc() {
        bt.add(new DataNode1(1l,1l,"DataNode1",12d));
        bt.add(new DataNode1(2l,1l,"DataNode1",12d));
        bt.add(new DataNode1(0l,1l,"DataNode1",12d));
        bt.add(new DataNode1(2l,1l,"DataNode1",12d));
        assertThrows(NoSuchElementException.class,
                ()->{bt.find(213l, 1l);});
    }

    @Test
    void delete1() {
        bt.add(new DataNode1(100l,0l,"DataNode1",12d));
        bt.add(new DataNode1(50l,0l,"DataNode1",12d));
        bt.add(new DataNode1(150l,0l,"DataNode1",12d));
        bt.add(new DataNode1(120l,0l,"DataNode1",12d));
        bt.add(new DataNode1(170l,0l,"DataNode1",12d));
        bt.add(new DataNode1(160l,0l,"DataNode1",12d));
        bt.add(new DataNode1(180l,0l,"DataNode1",12d));
        bt.add(new DataNode1(190l,0l,"DataNode1",12d));
        bt.add(new DataNode1(175l,0l,"DataNode1",12d));
        bt.add(new DataNode1(177l,0l,"DataNode1",12d));
        bt.add(new DataNode1(193l,0l,"DataNode1",12d));
        bt.add(new DataNode1(181l,0l,"DataNode1",12d));
        bt.add(new DataNode1(185l,0l,"DataNode1",12d));

        bt.delete(170l,0l);
        assertEquals("50 0  100 0  120 0  150 0  160 0  175 0  177 0  180 0  181 0  185 0  190 0  193 0",
                bt.print());
    }

    @Test
    void delete2() {
        bt.add(new DataNode1(100l,0l,"DataNode1",12d));
        bt.add(new DataNode1(50l,0l,"DataNode1",12d));
        bt.add(new DataNode1(150l,0l,"DataNode1",12d));
        bt.add(new DataNode1(120l,0l,"DataNode1",12d));
        bt.add(new DataNode1(170l,0l,"DataNode1",12d));
        bt.add(new DataNode1(160l,0l,"DataNode1",12d));
        bt.add(new DataNode1(180l,0l,"DataNode1",12d));
        bt.add(new DataNode1(190l,0l,"DataNode1",12d));
        bt.add(new DataNode1(175l,0l,"DataNode1",12d));
        bt.add(new DataNode1(177l,0l,"DataNode1",12d));
        bt.add(new DataNode1(193l,0l,"DataNode1",12d));
        bt.add(new DataNode1(181l,0l,"DataNode1",12d));
        bt.add(new DataNode1(185l,0l,"DataNode1",12d));

        bt.delete(180l,0l);
        assertEquals("50 0  100 0  120 0  150 0  160 0  170 0  175 0  177 0  181 0  185 0  190 0  193 0",
                bt.print());
    }

    @Test
    void delete3() {
        bt.add(new DataNode1(100l,0l,"DataNode1",12d));
        bt.add(new DataNode1(50l,0l,"DataNode1",12d));
        bt.add(new DataNode1(150l,0l,"DataNode1",12d));
        bt.add(new DataNode1(120l,0l,"DataNode1",12d));
        bt.add(new DataNode1(170l,0l,"DataNode1",12d));
        bt.add(new DataNode1(160l,0l,"DataNode1",12d));
        bt.add(new DataNode1(180l,0l,"DataNode1",12d));
        bt.add(new DataNode1(190l,0l,"DataNode1",12d));
        bt.add(new DataNode1(175l,0l,"DataNode1",12d));
        bt.add(new DataNode1(177l,0l,"DataNode1",12d));
        bt.add(new DataNode1(193l,0l,"DataNode1",12d));
        bt.add(new DataNode1(181l,0l,"DataNode1",12d));
        bt.add(new DataNode1(185l,0l,"DataNode1",12d));

        bt.delete(193l,0l);
        assertEquals("50 0  100 0  120 0  150 0  160 0  170 0  175 0  177 0  180 0  181 0  185 0  190 0",
                bt.print());
    }

    @Test
    void delete4() {
        bt.add(new DataNode1(100l,0l,"DataNode1",12d));
        bt.add(new DataNode1(50l,0l,"DataNode1",12d));
        bt.add(new DataNode1(150l,0l,"DataNode1",12d));
        bt.add(new DataNode1(120l,0l,"DataNode1",12d));
        bt.add(new DataNode1(170l,0l,"DataNode1",12d));
        bt.add(new DataNode1(160l,0l,"DataNode1",12d));
        bt.add(new DataNode1(180l,0l,"DataNode1",12d));
        bt.add(new DataNode1(190l,0l,"DataNode1",12d));
        bt.add(new DataNode1(175l,0l,"DataNode1",12d));
        bt.add(new DataNode1(177l,0l,"DataNode1",12d));
        bt.add(new DataNode1(193l,0l,"DataNode1",12d));
        bt.add(new DataNode1(181l,0l,"DataNode1",12d));
        bt.add(new DataNode1(185l,0l,"DataNode1",12d));

        bt.delete(100l,0l);
        assertEquals("50 0  120 0  150 0  160 0  170 0  175 0  177 0  180 0  181 0  185 0  190 0  193 0",
                bt.print());
    }

    @Test
    void deleteExc() {
        bt.add(new DataNode1(100l,0l,"DataNode1",12d));
        bt.add(new DataNode1(50l,0l,"DataNode1",12d));
        bt.add(new DataNode1(150l,0l,"DataNode1",12d));
        bt.add(new DataNode1(120l,0l,"DataNode1",12d));
        bt.add(new DataNode1(170l,0l,"DataNode1",12d));
        bt.add(new DataNode1(160l,0l,"DataNode1",12d));
        bt.add(new DataNode1(180l,0l,"DataNode1",12d));
        bt.add(new DataNode1(190l,0l,"DataNode1",12d));
        bt.add(new DataNode1(175l,0l,"DataNode1",12d));
        bt.add(new DataNode1(177l,0l,"DataNode1",12d));
        bt.add(new DataNode1(193l,0l,"DataNode1",12d));
        bt.add(new DataNode1(181l,0l,"DataNode1",12d));
        bt.add(new DataNode1(185l,0l,"DataNode1",12d));

        assertThrows(NoSuchElementException.class,()->{bt.delete(1000l,0l);});
    }

    @Test
    void clear1() {
        bt.add(new DataNode1(100l,0l,"DataNode1",12d));
        bt.add(new DataNode1(50l,0l,"DataNode1",12d));
        bt.add(new DataNode1(150l,0l,"DataNode1",12d));
        bt.add(new DataNode1(120l,0l,"DataNode1",12d));
        bt.add(new DataNode1(170l,0l,"DataNode1",12d));
        bt.clear();
        assertEquals(0,
                bt.getSize());
    }
    @Test
    void clear2() {
        bt.add(new DataNode1(100l,0l,"DataNode1",12d));
        bt.add(new DataNode1(50l,0l,"DataNode1",12d));
        bt.add(new DataNode1(150l,0l,"DataNode1",12d));

        bt.clear();
        assertEquals("",
                bt.print());
    }

    @Test
    void addFromFile1() {//size
        bt.addFromFile("src/main/json_data_test.json");
        assertEquals(2, bt.getSize());
    }
    @Test
    void addFromFile2() {//проверка на создание из абстрактного класса
        bt.addFromFile("src/main/json_data_test.json");
        assertEquals("DataNode1", bt.getRoot().getValue().getClass().getSimpleName());
    }
//остальное падает в log

}