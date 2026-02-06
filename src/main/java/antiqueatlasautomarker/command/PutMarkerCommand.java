package antiqueatlasautomarker.command;

import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
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

public class PutMarkerCommand implements ICommand {
    @Override
    @Nonnull
    public String getName() {
        return "aaam";
    }

    @Override
    @Nonnull
    public String getUsage(@Nonnull ICommandSender sender) {
        return "[aaam putmarker x z marker_type marker_label] will put a marker at the specified position in the current dimension for all atlasses in inventory\n" +
                "[aaam removemarkers marker_type x marker_label] Remove all specified markers for the current dimension for all atlases in inventory";
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
        if("putmarker".equals(args[0])){
            if (args.length < 5) throw new CommandException("commands.aaam.invalidusage");

            int x = args[1].equals("~") ? player.getPosition().getX() : Integer.parseInt(args[1]);
            int z = args[2].equals("~") ? sender.getPosition().getZ() : Integer.parseInt(args[2]);

            String type = args[3].contains(":") ? args[3] : "antiqueatlas:" + args[3];

            StringBuilder label = new StringBuilder(args[4]);
            for (int i = 5; i < args.length; i++) label.append(" ").append(args[i]);

            AtlasAPI.getPlayerAtlases(player).forEach(atlasId ->
                    AtlasAPI.getMarkerAPI().putMarker(player.world, true, atlasId, type, label.toString(), x, z)
            );
        }
        else if ("removemarkers".equals(args[0])) {
            if (args.length < 4) throw new CommandException("commands.aaam.invalidusage");

            String markerMatch = args[1];
            final int range;
            try {
                range = Integer.parseInt(args[2]);
            }
            catch (NumberFormatException e) {
                throw new CommandException("commands.aaam.invalidusage");
            }
            // cleanup any spaced args
            StringBuilder labelMatch = new StringBuilder(args[3]);
            for (int i = 4; i < args.length; i++) labelMatch.append(" ").append(args[i]);

            List<Integer> atlases = AtlasAPI.getPlayerAtlases(player);
            if(atlases.isEmpty()) throw new CommandException("commands.aaam.missingatlas");

            for(int atlasID : atlases) {
                AntiqueAtlasMod.markersData
                        .getMarkersData(atlasID, player.world)
                        .getMarkersDataInDimension(player.world.provider.getDimension())
                        .getAllMarkers()
                        .stream()
                        .filter(marker -> Math.abs(marker.getX() - player.posX) < range && Math.abs(marker.getZ() - player.posZ) < range)
                        .filter(marker -> marker.getType().equals(markerMatch))
                        .filter(marker -> labelMatch.toString().equals("*") || marker.getLabel().contentEquals(labelMatch))
                        .forEach(marker -> AtlasAPI.getMarkerAPI().deleteMarker(player.world, atlasID, marker.getId()));
            }
            sender.sendMessage(new TextComponentTranslation("commands.aaam.removemarkers.success"));
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
            if(args.length == 2) {
                completions.addAll(CommandBase.getListOfStringsMatchingLastWord(args, MarkerRegistry.getKeys()));
            }
            else if(args.length == 3){
                completions.addAll(CommandBase.getListOfStringsMatchingLastWord(args, "16", "32", "64"));
            }
            else if(args.length == 4){
                completions.addAll(CommandBase.getListOfStringsMatchingLastWord(args, "*", "labeltomatch"));
            }
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
