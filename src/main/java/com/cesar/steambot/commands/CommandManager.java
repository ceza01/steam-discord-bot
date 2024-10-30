package com.cesar.steambot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class CommandManager {

    private final Map<String, Command> commandMap;

    @Autowired
    public CommandManager(Map<String, Command> commandMap){
        this.commandMap = commandMap;
    }

    public void handleCommand(MessageReceivedEvent event){
        String[] commandMessage = event.getMessage().getContentRaw().split(" ");
        String commandName = commandMessage[0];

        Command command = commandMap.get(commandName);
        if (command != null){
            command.execute(event);
        } else {
            event.getChannel().sendMessage("Unknow command. Type !help for a list of availabe commands.");
        }
    }
}
