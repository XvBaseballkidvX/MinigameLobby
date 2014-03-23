package me.XvPROTECTEDvX.Economy;

import me.XvPROTECTEDvX.VyzonHub.Main;
import net.minecraft.util.org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Created by joshuabetz on 3/13/14.
 */
public class CoinsCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("coins")) {
                Player player = (Player) sender;
                if (player.hasPermission("vyzon.coins.command")) {
                    if (args.length == 0) {
                        player.sendMessage("§6/coins - Displays this screen");
                        player.sendMessage("§6/coins give [player] [amount] - Give coins to a player");
                    } else if (args.length == 3) {
                        if (args[0].equalsIgnoreCase("give")) {
                            Player target = Bukkit.getPlayerExact(args[1]);
                            if (target == null) {
                                player.sendMessage(Main.getInstance().prefix + "§6" + args[1] + " §7is offline!");
                            } else {
                                if (NumberUtils.isNumber(args[2])) {
                                    int amount = Integer.parseInt(args[2]);
                                    Coins.getManager().addCoins(target.getName(), amount);
                                    player.sendMessage(Main.getInstance().prefix + "You have added §6" + args[2] + "§7 coins to §6" + args[1] + "'s §7account.");
                                } else {
                                    player.sendMessage(Main.getInstance().prefix + "§6" + args[2] + " §7is not a number!");
                                }
                            }
                        }
                    }
                } else {
                    player.sendMessage(Main.getInstance().prefix + "You do not have permission to use this command!");
                }
            }
        } else if (sender instanceof ConsoleCommandSender) {
            if (cmd.getName().equalsIgnoreCase("coins")) {
                ConsoleCommandSender console = (ConsoleCommandSender) sender;
                if (args.length == 0) {
                    console.sendMessage("§6/coins - Displays this screen");
                    console.sendMessage("§6/coins give [player] [amount] - Give coins to a player");
                } else if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("give")) {
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if (target == null) {
                            console.sendMessage(Main.getInstance().prefix + "§6" + args[1] + " §7is offline!");
                        } else {
                            if (NumberUtils.isNumber(args[2])) {
                                int amount = Integer.parseInt(args[2]);
                                Coins.getManager().addCoins(target.getName(), amount);
                                console.sendMessage(Main.getInstance().prefix + "You have added §6" + args[2] + "§7 coins to §6" + args[1] + "'s §7account.");
                            } else {
                                console.sendMessage(Main.getInstance().prefix + "§6" + args[2] + " §7is not a number!");
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
