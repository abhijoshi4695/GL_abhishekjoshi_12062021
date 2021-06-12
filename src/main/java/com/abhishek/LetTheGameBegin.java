package com.abhishek;

import com.abhishek.game.DiceGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LetTheGameBegin {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to 'Roll a Dice'....");
        System.out.println("**********************************************************************");
        System.out.println("Please enter number of players(n) and com.abhishek.game point(m) separated by space. For eg:");
        System.out.println("n m");
        try {
            String[] input = br.readLine().split(" ");
            DiceGame.no_of_players = Integer.parseInt(input[0]);
            DiceGame.game_point = Integer.parseInt(input[1]);

            System.out.println("\n**********************************************************************");
            System.out.println("Initializing com.abhishek.game....Assigning turns");
            DiceGame.setPlayerTurns();

            System.out.println("\n*************************Let the com.abhishek.game begin*****************************");
            DiceGame.startGame(br);

            System.out.println("The com.abhishek.game has ended...");
            System.out.println("\n***************************Thank You**********************************");
        } catch (IOException e) {
            System.out.println("Error in the input!! Please RESTART the com.abhishek.game.");
            System.exit(1);
        }
    }
}
