package com.abhishek.game;

import com.abhishek.model.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DiceGame {
    public static int no_of_players;
    public static int game_point;
    private static List<Player> playersList;
    private static List<Integer> playerTurns;
    private static List<Player> ranking;

    private static void initializeGame() {
        playersList = new ArrayList<>();
        for (int i = 1; i <= no_of_players; i++) {
            playersList.add(new Player(i));
        }
        ranking = new ArrayList<>(playersList);
    }

    public static void setPlayerTurns() {
        initializeGame();
        playerTurns = new ArrayList<>();
        for (int i = 1; i <= no_of_players; i++) {
            playerTurns.add(i);
        }
        Collections.shuffle(playerTurns);

        System.out.println("Following is the player turn sequence:-");
        int t = 1;
        for (int p : playerTurns) {
            System.out.println(t + ": Player-" + p);
            t++;
        }
    }

    public static void startGame(BufferedReader br) {
        String rollIt;
        int current_score;
        int p;
        Player current_player;
        int playing_players_count = no_of_players;

        Random dice = new Random();
        try {
            for (int i = 0; i < playerTurns.size(); i = (i + 1) % playerTurns.size()) {
                if(playing_players_count == 0) {
                    return;
                }
                p = playerTurns.get(i);
                current_player = playersList.get(p-1);

                // Current player has completed the game
                if(current_player.getScore() == game_point) {
                    continue;
                }

                // Check if player has got 1 in the previous turn
                if(!current_player.isTurn()) {
                    current_player.setTurn(true);
                    System.out.println("Player-" + p + " you can play in the next turn");
                    continue;
                }

                // For player p, roll the dice
                System.out.println("Player-" + p + " it is your turn (Press 'r/R' to roll the dice)");
                rollIt = br.readLine();
                while (!rollIt.equalsIgnoreCase("r")) {
                    System.out.println("Player-" + p + " it is your turn (Press 'r/R' to roll the dice)");
                    rollIt = br.readLine();
                }
                current_score = dice.nextInt(6) + 1;
                System.out.println("Dice Score: " + current_score);

                int player_score = current_score + current_player.getScore();

                // If the player achieves game point, then the game is completed for the player
                if(player_score >= game_point) {
                    current_player.setTurn(false);
                    current_player.setScore(game_point);
                    System.out.println("**********************************************************************");
                    System.out.println("Player-" + p + " has completed the game and position is " + (no_of_players - playing_players_count + 1));
                    getPlayerRanking();
                    playing_players_count--;
                    continue;
                }

                // Assign score to the player
                current_player.setScore(player_score);
                System.out.println("Player-" + p + " your current score is: " + player_score);

                // If the current dice score is 6, then the player gets another turn
                if (current_score == 6) {
                    System.out.println("Congrats Player-" + p + " you got a 6! You get another turn");
                    i--;
                    if(i == -1) {
                        i = playerTurns.size() - 1;
                    }
                }

                // If the current dice score is 1, then the next turn for the player is skipped
                if(current_score == 1 && playing_players_count > 1) {
                    System.out.println("Sorry Player-" + p + " you will miss your turn for next round!!");
                    current_player.setTurn(false);
                }

                // Print the player ranking
                getPlayerRanking();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getPlayerRanking() {
        ranking.sort((a, b) -> b.getScore() - a.getScore());
        System.out.println("\n**********************************************************************");
        System.out.println("Current player ranking:");
        for(int i = 0; i < ranking.size(); i++) {
            Player p = ranking.get(i);
            System.out.println((i + 1) + ": Player-" + p.getId() + ", Score: " + p.getScore());
        }
        System.out.println("\n**********************************************************************");
    }
}
