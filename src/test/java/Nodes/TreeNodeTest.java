package Nodes;

import Inheritance.DataNode1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeNodeTest {

    @Test
    void compareTo1() {
        DataNode1 dn = new DataNode1(1l,2l,"tr", 12d);
        DataNode1 dn1 = new DataNode1(1l,2l,"tr", 12d);
        assertEquals(0,dn1.compareTo(dn));
    }
    @Test
    void compareTo2() {
        DataNode1 dn = new DataNode1(1l,2l,"tr", 123d);
        DataNode1 dn1 = new DataNode1(1l,2l,"tr", 12d);
        assertEquals(0,dn.compareTo(dn1));
    }
    @Test
    void compareToEqual() {
        DataNode1 dn = new DataNode1(1l,2l,"tr", 12d);
        DataNode1 dn1 = new DataNode1(1l,2l,"tr", 12d);

        assertEquals(0,dn1.compareTo(dn));
    }
    @Test
    void compareTo3() {
        DataNode1 dn = new DataNode1(1l,2l,"tr",12d);
        DataNode1 dn1 = new DataNode1(15l,2l,"tr", 12d);

        assertEquals(-1,dn.compareTo(dn1));
    }
    @Test
    void compareTo4() {
        DataNode1 dn1 = new DataNode1(5l,2l,"tr", 12d);
        DataNode1 dn = new DataNode1(15l,2l,"tr", 12d);

        assertEquals(1,dn.compareTo(dn1));
    }
}