package com.cesar.steambot.listener;

import com.cesar.steambot.commands.CommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Component
public class DiscordEventListener extends ListenerAdapter {

    private final CommandManager commandManager;

    public DiscordEventListener(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        if (event.getMessage().getContentRaw().startsWith("!")){
            commandManager.handleCommand(event);
        }
    }
}
