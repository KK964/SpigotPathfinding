package net.justminecraft.pathfinding;

import java.util.Comparator;

public class NodeScoreComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return o1.getScore() - o2.getScore();
    }
}
