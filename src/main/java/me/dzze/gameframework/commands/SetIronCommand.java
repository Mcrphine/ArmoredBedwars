package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetIronCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MessageUtils.message(sender, "&cThis command may only be executed by players.");
            return;
        }

        final Player player = (Player) sender;

        if (args.length == 1) {
            MessageUtils.message(sender, "&cInvalid arguments! Try: /" + label + " iron <1-4>");
            return;
        }

        final String numberStr = args[1];

        try {
            int number = Integer.parseInt(numberStr);
            if (number < 1 || number > 4) {
                MessageUtils.message(sender, "Argument must be 1-4!");
            } else {
                final FileConfiguration configuration = Main.getInstance().getConfig();

                configuration.set("Iron." + numberStr, player.getLocation());
                Main.getInstance().saveConfig();

                MessageUtils.message(sender, "&6BEDWARS &8| &aSet iron gen " + numberStr + " to your location.");
            }
        } catch (NumberFormatException ignored) {
            MessageUtils.message(sender, "Argument must be a number!");
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"ig"};
    }

    @Override
    public String getPermission() {
        return "bedwars.iron";
    }

    @Override
    public String getName() {
        return "iron";
    }
}
