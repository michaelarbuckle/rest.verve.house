package house.verve.model.io;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import house.verve.model.Space;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator; 
import com.fasterxml.jackson.core.JsonProcessingException;
 

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
public class SpaceSerializer  extends JsonSerializer<Space> {

    @Override
    public void serialize(Space value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeString(value.getId());
    }
}

 