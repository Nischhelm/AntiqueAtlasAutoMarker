package antiqueatlasautomarker.handlers;

import antiqueatlasautomarker.compat.ModCompat;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber
public class PlayerLoginHandler {
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (ModCompat.reccomplex.isLoaded() && !ModCompat.aarc.isLoaded()) {
            ITextComponent clickableLink = new TextComponentString("AARC Addon");
            clickableLink.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/antiqueatlas-recurrentcomplex-compatability"));
            clickableLink.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentTranslation("msg.aaam.aarcaddon.link")));
            clickableLink.getStyle().setColor(TextFormatting.AQUA);
            clickableLink.getStyle().setUnderlined(true);
            event.player.sendMessage(new TextComponentTranslation("msg.aaam.aarcaddon.missing", clickableLink));
        }
    }
}
