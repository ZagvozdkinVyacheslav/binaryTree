package Entity;

import Nodes.DataNode1;
import Nodes.Node;
import Nodes.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.source.tree.Tree;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
@Getter
@Setter
@Slf4j
public class BinaryTree<T extends TreeNode> {
    private Node root;
    int size;
    StringBuilder out;
    public BinaryTree(){
        size = 0;
        out = new StringBuilder();
    }
    public void add(T value){
        try {
            switch(value.getClassName()){
                case "TreeNode":
                case "DataNode1":
                    root = addRecursive(root, value);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        catch (IllegalArgumentException e){
            log.error("Invalid class name \"" + value.getClassName() + "\"");
        }



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
        var temp = new DataNode1(id,subId);
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
        var temp = new DataNode1(id,subId);
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
        if(out.length() >= 2){
            setOut(out.delete(out.length() - 2, out.length()));
        }

        String str = out.toString();
        setOut(new StringBuilder());
        return str;
    }
    private void clearTree(Node node) {

        if (node != null) {
            clearTree(node.getLeftChild());
            clearTree(node.getRightChild());
            node.setValue(null);
            size--;
        }

    }
    public void clear(){
        clearTree(root);
        root = null;
    }
    /*public static <T> List<T> parseJsonArray(String path,
                                             Class<T> classOnWhichArrayIsDefined)
            throws IOException, ClassNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(path);
        Class<T[]> arrayClass = null;
        try {
            arrayClass = (Class<T[]>) Class.forName(String.valueOf(classOnWhichArrayIsDefined.getDeclaredField("className")));
        } catch (NoSuchFieldException e) {
            log.error("Invalid json data");
        }
        T[] objects = mapper.readValue(file, arrayClass);
        return Arrays.asList(objects);
    }*/

    private List<T> fileToListOfPojos(String path) throws IOException {//дописать на несколько типов
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(path);
        JavaType type = objectMapper.getTypeFactory().
                constructCollectionType(List.class, DataNode1.class);
        List<T> typeList = objectMapper.readValue(file, type);//new TypeReference<>(){}
        return typeList;
    }
    public void addFromFile(String path){
        try {
            var list = fileToListOfPojos(path);
            for (var elem:list
            ) {
                this.add(elem);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
