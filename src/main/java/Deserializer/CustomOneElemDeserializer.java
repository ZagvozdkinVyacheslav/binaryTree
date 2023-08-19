package Deserializer;

import Abstract.TreeNode;
import Inheritance.DataNode1;
import Inheritance.DataNode2;
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
    public TreeNode deserialize(final JsonParser parser, final DeserializationContext context) throws IOException, JacksonException,NoSuchObjectException {
        final JsonNode node = parser.getCodec().readTree(parser);
        final ObjectMapper mapper = (ObjectMapper)parser.getCodec();

        String temp = node.get("className").toString();

            switch(temp){
                case "\"DataNode1\"":
                    return mapper.treeToValue(node, DataNode1.class);
                case "\"DataNode2\"":
                    return mapper.treeToValue(node, DataNode2.class);
                default:
                    throw new IllegalArgumentException("The creation of such an object is prohibited");
            }
    }
}
