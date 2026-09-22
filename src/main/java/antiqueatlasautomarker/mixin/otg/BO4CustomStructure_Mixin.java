package antiqueatlasautomarker.mixin.otg;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import com.llamalad7.mixinextras.sugar.Local;
import com.pg85.otg.common.LocalWorld;
import com.pg85.otg.customobjects.structures.CustomStructure;
import com.pg85.otg.customobjects.structures.bo4.BO4CustomStructure;
import com.pg85.otg.customobjects.structures.bo4.BO4CustomStructureCoordinate;
import com.pg85.otg.forge.world.ForgeWorld;
import com.pg85.otg.util.ChunkCoordinate;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BO4CustomStructure.class)
public abstract class BO4CustomStructure_Mixin extends CustomStructure {

    @Inject(
            method = "spawnInChunk",
            at = @At(value = "FIELD", target = "Lcom/pg85/otg/customobjects/structures/bo4/BO4CustomStructureCoordinate;isSpawned:Z", ordinal = 2),
            remap = false
    )
    private void aaam_otgBO4_onSpawnMark(ChunkCoordinate chunkCoordinate, LocalWorld localWorld, ChunkCoordinate chunkBeingPopulated, CallbackInfo ci, @Local BO4CustomStructureCoordinate currentPiece){
        World mcWorld = localWorld instanceof ForgeWorld
                ? ((ForgeWorld) localWorld).getWorld()
                :  null;

        if(mcWorld == null)
            return;

        // Try to get structureName:branchName
        AutoMarkSetting settings = AutoMarkSetting.get(this.start.bo3Name + ":" + currentPiece.bo3Name);
        if(settings == null) {
            if(currentPiece == this.start) {
                // Try to get structureName
                settings = AutoMarkSetting.get(this.start.bo3Name);
                if(settings == null) {
                    // Try to get DEBUG only start
                    settings = AutoMarkSetting.get("BO4:START");
                }
            }
            else {
                // Try to get branchName
                settings = AutoMarkSetting.get(":" + currentPiece.bo3Name);
                if(settings == null) {
                    // Try to get DEBUG all branches
                    settings = AutoMarkSetting.get("BO4:BRANCH");
                }
            }
        }

        if(settings != null && settings.enabled) {
            String label = settings.label.equals("DEFAULT") ? "gui.aaam.marker.otg." + currentPiece.bo3Name : settings.label;
            StructureMarkersDataHandler.markStructure(
                    mcWorld,
                    currentPiece.getX(),
                    currentPiece.getZ(),
                    settings.type,
                    label,
                    settings.context
            );
        }
    }
}
