package com.adventofcode.day02;

import java.util.List;

public class Game{
    int id;
    List<GameSet> games;
    boolean isValid;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", games=" + games +
                ", isValid=" + isValid +
                '}';
    }

    public Game(int id, List<GameSet> games) {
        this.id = id;
        this.games = games;
        this.isValid = true;
    }

    public int getId() {
        return id;
    }

    public List<GameSet> getGames() {
        return games;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGames(List<GameSet> games) {
        this.games = games;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
