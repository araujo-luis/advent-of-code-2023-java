package com.adventofcode.day08;

public class StartNode {
    String startNode;
    String currentNode;
    boolean isDone;

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    String previous;
    String previousInstruction;

    public String getPreviousInstruction() {
        return previousInstruction;
    }

    public void setPreviousInstruction(String previousInstruction) {
        this.previousInstruction = previousInstruction;
    }

    @Override
    public String toString() {
        return "StartNode{" +
                "startNode='" + startNode + '\'' +
                ", currentNode='" + currentNode + '\'' +
                ", isDone=" + isDone +
                '}';
    }

    public StartNode(String startNode) {
        this.startNode = startNode;
        this.currentNode = null;
        this.isDone = false;
    }
    public void setStartNode(String startNode) {
        this.startNode = startNode;
    }

    public void setCurrentNode(String currentNode) {
        this.currentNode = currentNode;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStartNode() {
        return startNode;
    }

    public String getCurrentNode() {
        return currentNode;
    }

    public boolean isDone() {
        return isDone;
    }
}
