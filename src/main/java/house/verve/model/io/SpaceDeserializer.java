package house.verve.model.io;

import house.verve.model.Space;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;

public class SpaceDeserializer  extends JsonDeserializer<Space> {

    @Override
    public Space deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
    	
    	  JsonNode node = jp.getCodec().readTree(jp);
          String oid = node.get("id").asText();
          return new Space(oid);

    }
 }