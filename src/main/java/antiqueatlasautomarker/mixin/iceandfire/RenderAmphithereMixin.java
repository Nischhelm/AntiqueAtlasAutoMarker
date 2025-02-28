package antiqueatlasautomarker.mixin.iceandfire;

import com.github.alexthe666.iceandfire.client.render.entity.RenderAmphithere;
import com.github.alexthe666.iceandfire.entity.EntityAmphithere;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderAmphithere.class)
public abstract class RenderAmphithereMixin extends RenderLiving<EntityAmphithere> {
    public RenderAmphithereMixin(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    }

    @Unique private static final ResourceLocation TEXTURE_PURPLE = new ResourceLocation("antiqueatlasautomarker:amphi_purple.png");
    @Unique private static final ResourceLocation TEXTURE_PURPLE_BLINK = new ResourceLocation("antiqueatlasautomarker:amphi_purple_blink.png");
    @Unique private static final ResourceLocation TEXTURE_BLANK = new ResourceLocation("antiqueatlasautomarker:amphi.png");
    @Unique private static final ResourceLocation TEXTURE_BLANK_BLINK = new ResourceLocation("antiqueatlasautomarker:amphi_blink.png");

    @Inject(
            method = "preRenderCallback(Lcom/github/alexthe666/iceandfire/entity/EntityAmphithere;F)V",
            at = @At("HEAD"),
            remap = false
    )
    protected void changeRotation(EntityAmphithere entityLiving, float partialTickTime, CallbackInfo ci) {
        String s = TextFormatting.getTextWithoutFormattingCodes(entityLiving.getName());
        if (s != null && s.equals("Nischhelm")) {
            GlStateManager.translate(0, -entityLiving.height, 0);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        }
    }

    @ModifyReturnValue(
            method = "getEntityTexture(Lcom/github/alexthe666/iceandfire/entity/EntityAmphithere;)Lnet/minecraft/util/ResourceLocation;",
            at = @At("RETURN"),
            remap = false
    )
    public ResourceLocation changeTexture(ResourceLocation original, @Local(argsOnly = true) EntityAmphithere entity){
        String s = TextFormatting.getTextWithoutFormattingCodes(entity.getName());
        if (s != null && s.equals("Purple")) {
            if(entity.isBlinking()) return TEXTURE_PURPLE_BLINK;
            else return TEXTURE_PURPLE;
        }
        return original;
    }
}
