package antiqueatlasautomarker.network;

import antiqueatlasautomarker.overhaul.OtherPlayersData;
import antiqueatlasautomarker.overhaul.OtherPlayersDataHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class PacketOtherAtlasHolders implements IMessage {

    private int atlasID;
    private final Map<UUID, double[]> playerPositions = new HashMap<>(); // xpos, zpos, rot

    public PacketOtherAtlasHolders() {}
    public PacketOtherAtlasHolders(@Nonnull EntityPlayer player, int atlasID) {
        if(!player.world.isRemote){
            List<EntityPlayerMP> visiblePlayers = player.world.getPlayers(EntityPlayerMP.class, entityPlayerMP ->
                    OtherPlayersDataHandler.INSTANCE.canSeeOtherPlayer(player, entityPlayerMP, atlasID));
            OtherPlayersData serverData = OtherPlayersDataHandler.INSTANCE.getData(atlasID);

            this.atlasID = atlasID;
            this.playerPositions.putAll(visiblePlayers.stream().collect(Collectors.toMap(
                    Entity::getUniqueID,
                    entityPlayerMP -> serverData.getOtherPlayerPosition(entityPlayerMP.getUniqueID()
            ))));
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        PacketBuffer packet = new PacketBuffer(buf);
        this.atlasID = packet.readInt();
        int size = packet.readInt();
        for(int i = 0; i < size; i++){
            this.playerPositions.put(
                    packet.readUniqueId(),
                    new double[]{
                            packet.readDouble(),
                            packet.readDouble(),
                            packet.readDouble()
                    }
            );
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        PacketBuffer packet = new PacketBuffer(buf);
        packet.writeInt(this.atlasID);
        packet.writeInt(this.playerPositions.size());
        this.playerPositions.forEach((uuid, doubles) -> {
            packet.writeUniqueId(uuid);
            packet.writeDouble(doubles[0]);
            packet.writeDouble(doubles[1]);
            packet.writeDouble(doubles[2]);
        });
    }

    public static class ServerHandler implements IMessageHandler<PacketOtherAtlasHolders, IMessage> {

        @Override
        public IMessage onMessage(PacketOtherAtlasHolders message, MessageContext ctx) {
            return null;
        }
    }

    @SideOnly(Side.CLIENT)
    public static class ClientHandler implements IMessageHandler<PacketOtherAtlasHolders, IMessage> {

        @Override
        public IMessage onMessage(PacketOtherAtlasHolders message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                // Replace client's set of player coords
                OtherPlayersDataHandler.INSTANCE.setData(
                        message.atlasID,
                        new OtherPlayersData(message.playerPositions));
            });
            return null;
        }
    }

}
