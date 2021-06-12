package com.abhishek.model;

public class Player {
    private final int id;
    private boolean turn;
    private int score;

    public Player(int id) {
        this.id = id;
        this.turn = true;
        this.score = 0;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
