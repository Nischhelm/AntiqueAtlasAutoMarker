package antiqueatlasautomarker.mixin.otg;

import antiqueatlasautomarker.config.data.AutoMarkSetting;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import com.pg85.otg.common.LocalWorld;
import com.pg85.otg.customobjects.bo3.BO3;
import com.pg85.otg.customobjects.structures.CustomStructure;
import com.pg85.otg.customobjects.structures.StructuredCustomObject;
import com.pg85.otg.forge.world.ForgeWorld;
import com.pg85.otg.util.ChunkCoordinate;
import com.pg85.otg.util.bo3.Rotation;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.Random;

@Mixin(BO3.class)
public abstract class BO3_Mixin implements StructuredCustomObject {

    @Shadow(remap = false)
    @Final private String name;

    @Inject(
            method = "spawnFromSapling",
            at = @At(value = "INVOKE", target = "Lcom/pg85/otg/customobjects/bo3/ObjectExtrusionHelper;extrude(Lcom/pg85/otg/common/LocalWorld;Ljava/util/Random;IIILcom/pg85/otg/util/ChunkCoordinate;Z)V"),
            remap = false
    )
    private void aaam_otgBO3_onSpawnFromSaplingMark(LocalWorld world, Random random, Rotation rotation, int x, int y, int z, CallbackInfoReturnable<Boolean> cir){
        this.aaam$markBO3(null, world, x, z);
    }

    @Inject(
            method = "spawnForced",
            at = @At(value = "INVOKE", target = "Lcom/pg85/otg/customobjects/bo3/ObjectExtrusionHelper;extrude(Lcom/pg85/otg/common/LocalWorld;Ljava/util/Random;IIILcom/pg85/otg/util/ChunkCoordinate;Z)V"),
            remap = false
    )
    private void aaam_otgBO3_onSpawnForcedMark(LocalWorld world, Random random, Rotation rotation, int x, int y, int z, CallbackInfoReturnable<Boolean> cir){
        this.aaam$markBO3(null, world, x, z);
    }

    @Inject(
            method = "trySpawnAt",
            at = @At(value = "INVOKE", target = "Lcom/pg85/otg/customobjects/bo3/ObjectExtrusionHelper;extrude(Lcom/pg85/otg/common/LocalWorld;Ljava/util/Random;IIILcom/pg85/otg/util/ChunkCoordinate;Z)V"),
            remap = false
    )
    private void aaam_otgBO3_onTrySpawnMark(CustomStructure structure, LocalWorld world, Random random, Rotation rotation, int x, int y, int z, int minY, int maxY, int baseY, ChunkCoordinate chunkBeingPopulated, boolean replaceBlocks, CallbackInfoReturnable<Boolean> cir){
        this.aaam$markBO3(structure, world, x, z);
    }

    @Unique
    private void aaam$markBO3(@Nullable CustomStructure structure, LocalWorld localWorld, int x, int z) {
        World mcWorld = localWorld instanceof ForgeWorld
                ? ((ForgeWorld) localWorld).getWorld()
                :  null;

        if(mcWorld != null) {
            AutoMarkSetting.Data settings;
            if(structure == null) {
                // Try to get fileName
                settings = AutoMarkSetting.get(this.name);
                // Don't debug null structures, they are from tree gen trigger and will packet overload
            }
            else {
                // Try to get structureName:branchName
                settings = AutoMarkSetting.get(structure.start.bo3Name + ":" + this.name);
                if(settings == null) {
                    if(this == structure.start.getObject()) {
                        // Try to get structureName
                        settings = AutoMarkSetting.get(structure.start.bo3Name);
                        if(settings == null) {
                            // Try to get DEBUG only start
                            settings = AutoMarkSetting.get("BO3:START");
                        }
                    }
                    else {
                        // Try to get branchName
                        settings = AutoMarkSetting.get(":" + this.name);
                        if(settings == null) {
                            // Try to get DEBUG all branches
                            settings = AutoMarkSetting.get("BO3:BRANCH");
                        }
                    }
                }
            }

            if(settings != null && settings.enabled) {
                String label = settings.label.equals("DEFAULT") ? "gui.aaam.marker.otg." + this.name : settings.label;
                StructureMarkersDataHandler.markStructure(
                        mcWorld,
                        x,
                        z,
                        settings.type,
                        label,
                        settings.context
                );
            }
        }
    }
}
