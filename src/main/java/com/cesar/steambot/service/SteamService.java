package com.cesar.steambot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SteamService {
    @Value("${STEAM.API.KEY}")
    private String steamApiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public SteamService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public String getSteamIdFromUsername(String username) {
        String url = String.format("https://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?key=%s&vanityurl=%s", steamApiKey, username);
        String response = restTemplate.getForObject(url, String.class);

        try {
            JsonNode jsonResponse = objectMapper.readTree(response);
            String steamId = jsonResponse.path("response").path("steamid").asText();
            if (!steamId.isEmpty()) {
                return steamId;
            } else {
                return "User not found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to resolve username.";
        }
    }

    public String getUserInfo(String steamId) {
        String url = String.format("https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=%s&steamids=%s", steamApiKey, steamId);
        String response = restTemplate.getForObject(url, String.class);

        try {
            JsonNode jsonResponse = objectMapper.readTree(response);
            JsonNode playerData = jsonResponse.path("response").path("players").get(0);
            String realName = playerData.path("realname").asText();
            String profileUrl = playerData.path("profileurl").asText();

            return String.format("Player: %s\nProfile: %s \nGames Owned: %s",
                    realName, profileUrl, getUserOwnedGamesNumber(steamId));

        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to retrieve user data.";
        }
    }

    public String getUserOwnedGamesNumber(String steamId) {
        String url = String.format("https://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=%s&steamid=%s", steamApiKey, steamId);
        String response = restTemplate.getForObject(url, String.class);

        try {
            JsonNode jsonResponse = objectMapper.readTree(response);
            JsonNode playerGames = jsonResponse.path("response");
            String gamesOwned = playerGames.path("game_count").asText();

            return String.format(gamesOwned);
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to retrieve user games";
        }
    }

    public String getRecentGamesPlayedByUser(String steamId){
        String url = String.format("https://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v0001/?key=%s&steamid=%s", steamApiKey, steamId);
        String response = restTemplate.getForObject(url, String.class);

        try {
            JsonNode jsonResponse = objectMapper.readTree(response);
            String totalGames = jsonResponse.path("response").path("total_count").asText();

            return String.format("User played %s games in the last two weeks", totalGames);
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to retrieve user recent games.";
        }
    }
}
