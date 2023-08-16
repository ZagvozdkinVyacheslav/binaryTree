package Entity;

import Nodes.Node;
import Nodes.TreeNode;
import lombok.Getter;
import lombok.Setter;

import javax.swing.plaf.InsetsUIResource;
import java.util.NoSuchElementException;
import java.util.TreeMap;
@Getter
@Setter
public class BinaryTree<T extends TreeNode> {
    private Node root;
    int size;
    StringBuilder out;
    BinaryTree(){
        size = 0;
        out = new StringBuilder();
    }
    public void add(T value){
        root = addRecursive(root, value);
    }
    private Node addRecursive(Node current, T value){

        if(current == null){
            size++;
            return new Node(value);
        }
        if(value.compareTo(current.getValue()) < 0){
            current.setLeftChild(addRecursive(current.getLeftChild(), value));
        }
        else if(value.compareTo(current.getValue()) > 0){
            current.setRightChild(addRecursive(current.getRightChild(), value));
        }
        else {
            return current;
        }
        return current;

    }
    public TreeNode find(Long id, Long subId){
        var temp = new TreeNode(id,subId);
        var current = root;
        while (current != null){
            switch(temp.compareTo(current.getValue())){
                case 1:
                    current = current.getRightChild();
                    break;
                case 0:
                    return current.getValue();
                case -1:
                    current = current.getLeftChild();
                    break;
            }
        }
        throw new NoSuchElementException("Такого элемента нет");
    }
    public void delete(Long id, Long subId){
        var temp = new TreeNode(id,subId);
        Node current = root;
        Node parent = root;
        while (current.getValue().compareTo(temp) != 0) {
            parent = current;
            if (temp.compareTo(current.getValue()) < 0) { // Определяем, куда двигаемся влево или нет?
                current = current.getLeftChild();
            }
            else {
                current = current.getRightChild();
            }
            if (current == null)
                throw new NoSuchElementException("Такого элемента нет");
        }
        size--;
        if (current.getLeftChild() == null && current.getRightChild() == null) { // 0 потомков
            if (current == root)
                root = null;
            else if(parent.getLeftChild().getValue().compareTo(current.getValue()) == 0){
                parent.setLeftChild(null);
            }
            else
                parent.setRightChild(null);
        }
        else if (current.getRightChild() == null) { // 1 левый потомок(поддерево)
            if (current == root)
                root = current.getLeftChild();
            else if (parent.getLeftChild().getValue().compareTo(current.getValue()) == 0)
                parent.setLeftChild(current.getLeftChild());
            else
                parent.setRightChild(current.getLeftChild());
        }
        else if (current.getLeftChild() == null) { // 1 правый потомок(поддерево)
            if (current == root)
                root = current.getRightChild();
            else if (parent.getLeftChild().getValue().compareTo(current.getValue()) == 0)
                parent.setLeftChild(current.getRightChild());
            else
                parent.setRightChild(current.getRightChild());
        }
        else { // 2 потока, узел заменяем приемником
            Node heir = receiveHeir(current);// ищем приемника удаляемому потоку
            if (current == root)
                root = heir;
            else if (parent.getLeftChild().getValue().compareTo(current.getValue()) == 0)
                parent.setLeftChild(heir);
            else
                parent.setRightChild(heir);
        }

    }
    private Node receiveHeir(Node node) {
        Node parent = node;
        Node heir = node;
        Node current = node.getRightChild(); // Переход к правому потомку
        while (current != null) //идем до самого левого потомка
        {
            parent = heir;
            heir = current;
            current = current.getLeftChild();
        }

        if (heir != node.getRightChild()) //Если преемник не является правым потомком,
        {
            parent.setLeftChild(heir.getRightChild());
            heir.setRightChild(node.getRightChild());
        }
        heir.setLeftChild(node.getLeftChild());
        return heir;
    }

    private void lcr(Node node) {
        if (node != null) {
            lcr(node.getLeftChild());
            out.append(node.getValue().getId() + " " + node.getValue().getSubId() + "  ");
            lcr(node.getRightChild());
        }
    }
    public String print(){
        lcr(root);
        setOut(out.delete(out.length() - 2, out.length()));
        String str = out.toString();
        setOut(new StringBuilder());
        return str;
    }
}
