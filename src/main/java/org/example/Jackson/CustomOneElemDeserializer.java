package org.example.Jackson;

import org.example.Abstract.TreeNode;
import org.example.Inheritance.DataNode1;
import org.example.Inheritance.DataNode2;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.rmi.NoSuchObjectException;
@Slf4j
public class CustomOneElemDeserializer extends StdDeserializer<TreeNode> {
    public CustomOneElemDeserializer() {
        this(null);
    }

    protected CustomOneElemDeserializer(final Class<?> vc) {
        super(vc);
    }
    @Override
    public TreeNode deserialize(final JsonParser parser, final DeserializationContext context) throws IOException,
            JacksonException,NoSuchObjectException,NullPointerException {
        final JsonNode node = parser.getCodec().readTree(parser);
        final ObjectMapper mapper = (ObjectMapper)parser.getCodec();
        String temp = node.get("className").toString();
        switch(temp){
            case "\"DataNode1\"":
                if(node.has("id")&&node.has("subId")&&node.has("data1"))
                    return mapper.treeToValue(node, DataNode1.class);
                else throw new IllegalArgumentException("Wrong data fields");
            case "\"DataNode2\"":
                if(node.has("id")&&node.has("subId")&&node.has("someStr"))
                    return mapper.treeToValue(node, DataNode2.class);
                else throw new IllegalArgumentException("Wrong data fields");

            default:
                throw new IllegalArgumentException("The creation of such an object is prohibited");
        }
    }
}
