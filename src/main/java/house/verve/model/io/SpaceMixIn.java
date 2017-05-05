package house.verve.model.io;

import house.verve.model.Space;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public interface  SpaceMixIn {

    @JsonSerialize(using=SpaceSerializer.class)
    public Space getSpace();

    @JsonDeserialize(using=SpaceDeserializer.class)
    public void setSpace(Space space);

}

