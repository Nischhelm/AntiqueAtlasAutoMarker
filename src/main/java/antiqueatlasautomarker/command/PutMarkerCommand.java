package antiqueatlasautomarker.command;

import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.registry.MarkerRegistry;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

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
        return "[aaam putmarker x z marker_type marker_label] will put a marker at the specified position in the current dimension for all atlasses in inventory";
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
        if (args.length < 5) throw new CommandException("commands.aaam.invalidusage");

        int x = args[1].equals("~") ? player.getPosition().getX() : Integer.parseInt(args[1]);
        int z = args[2].equals("~") ? sender.getPosition().getZ() : Integer.parseInt(args[2]);

        String type = args[3];

        StringBuilder label = new StringBuilder(args[4]);
        for (int i = 5; i < args.length; i++) label.append(" ").append(args[i]);

        AtlasAPI.getPlayerAtlases(player).forEach(atlasId ->
                AtlasAPI.getMarkerAPI().putMarker(player.world, true, atlasId, type, label.toString(), x, z)
        );
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
            completions.add("putmarker");
            //return CommandBase.getListOfStringsMatchingLastWord(args, completions); //only needed once there's multiple commands
        } else if (args.length == 2 || args.length == 3)
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
