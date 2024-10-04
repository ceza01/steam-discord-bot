package com.cesar.steambot;

import com.cesar.steambot.listener.DiscordEventListener;
import com.cesar.steambot.service.SteamService;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SteamBotApplication {

	@Value("${DISCORD.BOT.TOKEN}")
	private String discordToken;

	@Autowired
	private SteamService steamService;

	public static void main(String[] args) {
		SpringApplication.run(SteamBotApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return args -> {
			try {
				JDABuilder builder = JDABuilder.createDefault(discordToken);
				builder.addEventListeners(new DiscordEventListener(steamService));
				builder.enableIntents(GatewayIntent.MESSAGE_CONTENT)
						.build();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		};
	}
}
