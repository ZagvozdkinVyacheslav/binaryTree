package org.example.Jackson;

import org.example.Abstract.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class AlgDeserialization {
    public AlgDeserialization(){

    }
    public List<String> jsonToListOfString(String path) throws IOException {
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
    public List<TreeNode> listStringJsonToListOfPojos(String path) throws IOException {//One Elem Deserialize
        final ObjectMapper mapper = new ObjectMapper();
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(TreeNode.class, new CustomOneElemDeserializer());
        mapper.registerModule(module);
        List<String> listJson = jsonToListOfString(path);
        List<TreeNode>  typeList = new ArrayList<>();
        for (int i = 0; i < listJson.size(); i++) {
            try {
                typeList.add(mapper.readValue(listJson.get(i), TreeNode.class));
            }catch (IllegalArgumentException e){
                log.error(e.getMessage());
            }
            catch (IOException e){
                log.error("Wrong data when expected another className");
            }
            catch (NullPointerException e){
                log.error("Haven't className data field");
            }
        }
        return typeList;
    }
}
