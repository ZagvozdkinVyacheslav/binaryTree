package Entity;

import Deserializer.CustomOneElemDeserializer;
import Inheritance.DataNode1;
import Nodes.Node;
import Abstract.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.rmi.NoSuchObjectException;
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
    public void add(TreeNode value){
        try {
            switch(value.getClassName()){
                case "DataNode1":
                case "DataNode2":
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
    private Node addRecursive(Node current, TreeNode value){

        if(current == null){
            size++;
            return new Node(value);
        }
        try{
            if(value.compareTo(current.getValue()) < 0){
                current.setLeftChild(addRecursive(current.getLeftChild(), value));
            }
            else if(value.compareTo(current.getValue()) > 0){
                current.setRightChild(addRecursive(current.getRightChild(), value));
            }
            else {
                return current;
            }

        }catch (NullPointerException e){
            log.error("Wrong data");
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

    /*private List<TreeNode> fileToListOfPojos(String path) throws IOException {


        final ObjectMapper mapper = new ObjectMapper();
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(TreeNode.class, new CustomOneElemDeserializer());
        mapper.registerModule(module);
        jsonToListOfPojos(path);


        //List<TreeNode>  typeList = Arrays.asList(mapper.readValue(file, TreeNode.class)); //new TypeReference<>(){}

        return new ArrayList<>();
    }*/
    private List<TreeNode> listJsonToListOfPojos(String path) throws IOException {//One Elem Deserialize
        final ObjectMapper mapper = new ObjectMapper();
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(TreeNode.class, new CustomOneElemDeserializer());
        mapper.registerModule(module);
        List<String> listJson = jsonToListOfPojos(path);
        List<TreeNode>  typeList = new ArrayList<>();
        for (int i = 0; i < listJson.size(); i++) {
            try {
                typeList.add(mapper.readValue(listJson.get(i), TreeNode.class));
            }catch (IllegalArgumentException e){
                log.error(e.getMessage());
            }
        }
        return typeList;
    }
    private List<String> jsonToListOfPojos(String path) throws IOException {
        File file = new File(path);
        StringBuilder jsonSb = new StringBuilder(FileUtils.readFileToString(file, StandardCharsets.UTF_8));
        jsonSb.deleteCharAt(0);
        List<String> listStrJson = new ArrayList<>();
        for (int i = 0; i < jsonSb.length(); i++) {
            StringBuilder temp = new StringBuilder();
            while(jsonSb.charAt(0) != '}'){
                temp.append(jsonSb.charAt(0));
                jsonSb.deleteCharAt(0);
            }
            temp.append(jsonSb.charAt(0));
            jsonSb.deleteCharAt(0);
            jsonSb.deleteCharAt(0);
            listStrJson.add(temp.toString());
        }

        return listStrJson;
    }

    public void addFromFile(String path){
        try {
            var list = listJsonToListOfPojos(path);
            for (var elem:list
            ) {
                this.add(elem);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
