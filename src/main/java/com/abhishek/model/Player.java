package com.abhishek.model;

public class Player {
    private final int id;
    private int turn;
    private int score;

    public Player(int id) {
        this.id = id;
        this.turn = 0;
        this.score = 0;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public int getTurn() {
        return turn;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
