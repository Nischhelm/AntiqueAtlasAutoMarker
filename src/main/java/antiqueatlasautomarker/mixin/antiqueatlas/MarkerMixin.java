package antiqueatlasautomarker.mixin.antiqueatlas;

import antiqueatlasautomarker.util.IMarkerConstructor;
import hunternif.mc.atlas.marker.Marker;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Marker.class)
public abstract class MarkerMixin implements IMarkerConstructor {
    @Shadow(remap = false) @Final private int id;
    @Shadow(remap = false) @Final private String type;
    @Shadow(remap = false) @Final private String label;
    @Shadow(remap = false) @Final private int dim;
    @Shadow(remap = false) @Final private int x;
    @Shadow(remap = false) @Final private int z;
    @Shadow(remap = false) @Final private boolean visibleAhead;

    @Override @Unique
    public Marker structureMarkerCopy(String clientType, String clientLabel){
        //Acts like global markers in having negative ids
        //client setting label is used except if client uses DEFAULT, then the structure info from server is used
        return new Marker(
                -this.id,
                clientType,
                clientLabel,
                this.dim, this.x, this.z, this.visibleAhead
        );
    }
}
