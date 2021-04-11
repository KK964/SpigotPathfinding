package net.justminecraft.pathfinding;

public class Node {
    private final int rowNumber;
    private final int colNumber;
    private int x;
    private int y;
    private int z;
    private int length;
    private int width;
    private int height;
    private int score;
    private Node parent;
    private boolean open;

    public Node(int x, int y, int z, int length, int width, int height) {
        rowNumber = x;
        colNumber = z;
        this.x = x;
        this.y = y;
        this.z = z;
        this.length = length;
        this.width = width;
        this.height = height;
        score = 0;
        parent = null;
        open = true;
    }

    public Node(Node node) {
        rowNumber = node.rowNumber;
        colNumber = node.colNumber;
        this.x = node.getX();
        this.y = node.getY();
        this.z = node.getZ();
        this.height = node.getHeight();
        this.width = node.getWidth();
        score = 0;
        parent = null;
        open = true;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getColNumber() {
        return colNumber;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Node getParent() {
        return parent;
    }

    public boolean isOpen() {
        return open;
    }
}
