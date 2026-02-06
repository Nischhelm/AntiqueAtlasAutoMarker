package antiqueatlasautomarker.network;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import hunternif.mc.atlas.registry.MarkerRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;

public class PacketExportPutMarker implements IMessage {

    private String playerName;
    private int atlastID = -1;
    private int x;
    private int z;
    private String type;
    private String label;

    public PacketExportPutMarker() {}
    public PacketExportPutMarker(String playerName, int atlasID, int x, int z, String type, String label) {
        this.atlastID = atlasID;
        this.playerName = playerName;
        this.x = x;
        this.z = z;
        this.type = type;
        this.label = label;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        PacketBuffer packet = new PacketBuffer(buf);
        this.atlastID = packet.readInt();
        this.x = packet.readInt();
        this.z = packet.readInt();
        this.playerName = packet.readString(64);
        this.type = packet.readString(128);
        this.label = packet.readString(128);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        PacketBuffer packet = new PacketBuffer(buf);
        packet.writeInt(this.atlastID);
        packet.writeInt(this.x);
        packet.writeInt(this.z);
        packet.writeString(this.playerName);
        packet.writeString(this.type);
        packet.writeString(this.label);
    }

    public static class ServerHandler implements IMessageHandler<PacketExportPutMarker, IMessage> {

        @Override
        public IMessage onMessage(final PacketExportPutMarker message, final MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private static void handle(PacketExportPutMarker message, MessageContext ctx) {
            PacketHandler.instance.sendToDimension(new PacketExportPutMarker(
                    message.playerName,
                    message.atlastID,
                    message.x,
                    message.z,
                    message.type,
                    message.label
                    ),
                ctx.getServerHandler().player.dimension
            );
        }
    }

    @SideOnly(Side.CLIENT)
    public static class ClientHandler implements IMessageHandler<PacketExportPutMarker, IMessage> {

        @Override
        public IMessage onMessage(PacketExportPutMarker message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                if(MarkerRegistry.hasKey(message.type)){
                    String command = "/aaam putmarker" +
                            " " + message.x +
                            " " + message.z +
                            " " + message.type +
                            " " + message.label;
                    command = command.replaceAll("§.", "");
                    ITextComponent clickableLink = new TextComponentTranslation(message.label);
                    clickableLink.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
                    clickableLink.getStyle().setColor(TextFormatting.AQUA);
                    clickableLink.getStyle().setUnderlined(true);
                    Minecraft.getMinecraft().player.sendMessage(new TextComponentTranslation("gui.antiqueatlas.copymarker.packetcommand", message.playerName, message.atlastID, clickableLink));
                }
                else {
                    AntiqueAtlasAutoMarker.LOGGER.log(Level.INFO, "{} tried to share a marker type not client: {}", message.playerName, message.type);
                }
            });
            return null;
        }
    }
}
