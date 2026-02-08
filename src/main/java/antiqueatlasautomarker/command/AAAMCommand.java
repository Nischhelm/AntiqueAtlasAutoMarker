package antiqueatlasautomarker.command;

import antiqueatlasautomarker.util.IDeletedMarkerList;
import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.marker.Marker;
import hunternif.mc.atlas.marker.MarkersData;
import hunternif.mc.atlas.registry.MarkerRegistry;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AAAMCommand implements ICommand {
    @Override
    @Nonnull
    public String getName() {
        return "aaam";
    }

    @Override
    @Nonnull
    public String getUsage(@Nonnull ICommandSender sender) {
        return "[aaam putmarker x z marker_type marker_label] puts a marker at the specified position in the current dimension for all atlases in inventory\n" +
               "[aaam removemarkers atlas_id marker_type range marker_label] removes all matching markers in range in the current dimension for specified owned atlas";
    }

    @Override
    @Nonnull
    public List<String> getAliases() {
        return Collections.emptyList();
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
        if(!(sender instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer) sender;

        List<Integer> atlases = AtlasAPI.getPlayerAtlases(player);
        if(atlases.isEmpty()) throw new CommandException("commands.aaam.missingatlas");

        if("putmarker".equals(args[0])){
            if (args.length < 5) throw new CommandException("commands.aaam.invalidusage");

            int x = args[1].equals("~") ? player.getPosition().getX() : Integer.parseInt(args[1]);
            int z = args[2].equals("~") ? sender.getPosition().getZ() : Integer.parseInt(args[2]);

            String type = args[3].contains(":") ? args[3] : "antiqueatlas:" + args[3];

            final String builtLabel;
            StringBuilder label = new StringBuilder(args[4]);
            for (int i = 5; i < args.length; i++) label.append(" ").append(args[i]);
            builtLabel = label.toString();

            int addedCount = 0;
            for(int atlasId : atlases) {
                List<Marker> markersAtChunk = AntiqueAtlasMod.markersData
                        .getMarkersData(atlasId, player.world)
                        .getMarkersDataInDimension(player.dimension)
                        .getMarkersAtChunk((x >> 4) / MarkersData.CHUNK_STEP, (z >> 4) / MarkersData.CHUNK_STEP);
                if(markersAtChunk == null || markersAtChunk.stream().noneMatch(marker ->
                                                    marker.getX() == x &&
                                                    marker.getZ() == z &&
                                                    marker.getType().equals(type) &&
                                                    marker.getLabel().equals(builtLabel)
                                            )
                ) { //if not already in atlas
                    AtlasAPI.getMarkerAPI().putMarker(player.world, true, atlasId, type, builtLabel, x, z);
                    addedCount++;
                }
            }
            if(addedCount > 0) player.sendMessage(new TextComponentTranslation("commands.aaam.putmarker.success",builtLabel));
            else player.sendMessage(new TextComponentTranslation("commands.aaam.putmarker.fail",builtLabel));
        }
        else if ("removemarkers".equals(args[0])) {
            if (args.length < 2) throw new CommandException("commands.aaam.invalidusage");

            String typeToMatch = args[1];
            final int range, targetAtlasID;
            try {
                targetAtlasID = args.length >= 3 ? Integer.parseInt(args[2]) : -1;
                range = args.length >= 4 ? Integer.parseInt(args[3]) : -1;
            } catch (NumberFormatException e) {
                throw new CommandException("commands.aaam.invalidusage");
            }
            // cleanup any spaced args
            String labelToMatch;
            if(args.length >= 5) {
                StringBuilder labelMatch = new StringBuilder();
                for (int i = 4; i < args.length; i++) labelMatch.append(args[i]).append(" ");
                if (labelMatch.length() == 0) labelMatch.append("*"); //no entry = any
                else labelMatch.deleteCharAt(labelMatch.length() - 1); //remove last separator
                labelToMatch = labelMatch.toString();
            } else
                labelToMatch = "*";

            int removedCount = 0;
            for(int atlasID : atlases) {
                if(targetAtlasID != -1 && atlasID != targetAtlasID) continue;
                List<Marker> markersToRemove = AntiqueAtlasMod.markersData
                        .getMarkersData(atlasID, player.world)
                        .getMarkersDataInDimension(player.world.provider.getDimension())
                        .getAllMarkers()
                        .stream()
                        .filter(marker -> range == -1 || Math.abs(marker.getX() - player.posX) < range && Math.abs(marker.getZ() - player.posZ) < range)
                        .filter(marker -> marker.getType().equals(typeToMatch))
                        .filter(marker -> labelToMatch.equals("*") || marker.getLabel().contentEquals(labelToMatch))
                        .collect(Collectors.toList());

                removedCount += markersToRemove.size();
                markersToRemove.forEach(marker -> {
                    AtlasAPI.getMarkerAPI().deleteMarker(player.world, atlasID, marker.getId());

                    //Also save deleted structure markers to not re-add
                    if(marker.getId() >= 0) return;
                    if(marker.isGlobal()) return; //can't remove global markers anyway but just safety check
                    MarkersData data = AntiqueAtlasMod.markersData.getMarkersData(atlasID, player.getEntityWorld());
                    if(data == null) return; //shouldn't be necessary, since AA would crash anyway
                    ((IDeletedMarkerList) data).addDeletedMarker(marker.getId());
                });
            }
            if(removedCount > 0) sender.sendMessage(new TextComponentTranslation("commands.aaam.removemarkers.success", removedCount));
            else sender.sendMessage(new TextComponentTranslation("commands.aaam.removemarkers.fail"));
        }
    }

    @Override
    public boolean checkPermission(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender) {
        return true;
    }

    @Override
    @Nonnull
    public List<String> getTabCompletions(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            return CommandBase.getListOfStringsMatchingLastWord(args, "putmarker", "removemarkers");
        }
        else if("putmarker".equals(args[0])) {
            if (args.length == 2 || args.length == 3)
                completions.add("~");
            else if(args.length == 4) {
                //All possible marker types
                completions.addAll(MarkerRegistry.getValues().stream()
                        .map(m -> {
                            String modid = m.getRegistryName().getNamespace();
                            if (modid.equals("antiqueatlas")) return m.getRegistryName().getPath();
                            else return m.getRegistryName().toString();
                        })
                        .collect(Collectors.toList())
                );
            } else
                completions.add("yourlabelhere");
        }
        else if("removemarkers".equals(args[0])) {
            if(args.length == 2) completions.addAll(CommandBase.getListOfStringsMatchingLastWord(args, MarkerRegistry.getKeys()));
            else if(args.length == 3) completions.addAll(CommandBase.getListOfStringsMatchingLastWord(args, "16", "32", "64"));
            else if(args.length == 4) completions.addAll(CommandBase.getListOfStringsMatchingLastWord(args, "*", "labeltomatch"));
        }
        return completions;
    }

    @Override
    public boolean isUsernameIndex(@Nonnull String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand other) {
        return this.getName().compareTo(other.getName());
    }
}
