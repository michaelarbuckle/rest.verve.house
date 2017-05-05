package house.verve.model.io;

import house.verve.model.Space;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class SpaceModule  extends SimpleModule {
    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(Space.class, SpaceMixIn.class);
    }
}
