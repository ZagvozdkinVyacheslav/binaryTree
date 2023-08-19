package Deserializer;

import Abstract.TreeNode;
import Inheritance.DataNode1;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.rmi.NoSuchObjectException;

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

        if (node.has("className")) {//node.isArray == True
            return mapper.treeToValue(node, DataNode1.class);
        }
        else throw new NoSuchObjectException("The creation of such an object is prohibited");

    }
}
