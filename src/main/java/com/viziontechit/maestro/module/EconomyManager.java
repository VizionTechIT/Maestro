package com.viziontechit.maestro.module;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.UUID;

public class EconomyManager {

    // initialize default balance
    private Float default_balance = 0.00F;

    // create volatile economy
    private HashMap<UUID, Float> bank = new HashMap<UUID, Float>();

    // bank account creation strings
    private final String econ_create_success = "§bMaestro | §oBank account has successfully been created!" +
            "\n§7MaestroEconomy | §oCreated account for §l%PLAYER%§o, set balance to §b$%BALANCE%§o.";

    private final String econ_create_syntax = "§cMaestro | §oLooks like somebody needs to brush up on their syntax!" +
            "\n§7MaestroHelp | §oTo create accounts, try §l/econ create <player>.";

    private final String econ_create_exists = "§bMaestro | §oSorry, that player already has a bank account!" +
            "\n§7MaestroHelp | §oTo view accounts, try /econ details <player>";

    // bank account details strings
    private final String econ_account_details = "§bMaestro | §oViewing account details for selected player." +
            "\n§7MaestroEconomy | §oAccount for  §l%PLAYER%§o, has a balance of §b$%BALANCE%§o.";

    // create boolean operator for existent acc.
    private boolean exists(UUID target){
        if(!bank.containsKey(target)) {
            return false;
        }
        return true;
    }

    // create string operator for replacing placeholders
    private String replace(String string, UUID target, Float balance) {
        return string.replace("%PLAYER%", Bukkit.getPlayer(target).getName()).replace("%BALANCE%", balance.toString());
    }

    // ------------------------
    // BEGIN ACCESSABLE METHODS
    // ------------------------

    // method for creating accounts
    public void createAccount(CommandSender sender, UUID target) {
        if(!exists(target)) {
            sender.sendMessage(replace(econ_create_success, target, default_balance));
            bank.put(target, default_balance);
        }
        sender.sendMessage(econ_create_exists);
    }

    // method for showing account details
    public void showAccountDetails(CommandSender sender, UUID target) {
        if(exists(target)) {
            sender.sendMessage(replace(econ_account_details, target, bank.get(target)));
        }
        sender.sendMessage(econ_create_syntax);
    }

}
