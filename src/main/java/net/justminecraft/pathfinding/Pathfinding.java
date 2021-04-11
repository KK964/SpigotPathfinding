package net.justminecraft.pathfinding;

import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Pathfinding {

    private Node[][][] nodes;
    private Node currentNode;
    private int[] endPosition;
    private int maxWidth;
    private int maxLength;
    private int maxHeight;

    private World world;

    private final NodeScoreComparator nodeScoreComparator = new NodeScoreComparator();

    public List<Node> getRoute(Node[][][] nodes, int[] startPosition, int[] endPosition, World world) {
        this.world = world;
        this.nodes = nodes;
        this.maxWidth = nodes.length;
        this.maxLength = nodes[0].length;
        this.maxHeight = nodes[1].length;
        this.endPosition = endPosition;

        resetAllNodes();

        PriorityQueue<Node> queue = new PriorityQueue<>(nodeScoreComparator);
        queue.add(nodes[startPosition[0]][startPosition[1]][startPosition[2]]);

        boolean routeAvailable = false;

        while (!queue.isEmpty()) {
            do {
                if (queue.isEmpty()) break;
                currentNode = queue.remove();
            } while (!currentNode.isOpen());

            currentNode.setOpen(false);

            int currentX = currentNode.getX();
            int currentY = currentNode.getY();
            int currentZ = currentNode.getZ();
            int currentScore = currentNode.getScore();

            if (currentNode.getRowNumber() == endPosition[0] && currentNode.getColNumber() == endPosition[2]) {
                routeAvailable = true;
                break;
            }

            int smallestScore = 9999999;
            for (int x = -1; x <= 1; x+=2) {
                int nextX = currentX + x;
                if (validNode(nextX, currentY, currentZ)) {
                    int score = getScoreOfNode(nodes[nextX][currentY][currentZ], currentScore);
                    if (score < smallestScore)
                        smallestScore = score;
                    Node thisNode = nodes[nextX][currentY][currentZ];
                    thisNode.setScore(score);
                    queue.add(thisNode);
                    thisNode.setParent(currentNode);
                }
            }
            for (int y = -1; y <= 1; y+=2) {
                int nextY = currentY + y;
                if (validNode(currentX, nextY, currentZ)) {
                    int score = getScoreOfNode(nodes[currentX][nextY][currentZ], currentScore);
                    if (score < smallestScore)
                        smallestScore = score;
                    Node thisNode = nodes[currentX][nextY][currentZ];
                    thisNode.setScore(score);
                    queue.add(thisNode);
                    thisNode.setParent(currentNode);
                }
            }
            for (int z = -1; z <= 1; z+=2) {
                int nextZ = currentZ + z;
                if (validNode(currentX, currentY, nextZ)) {
                    int score = getScoreOfNode(nodes[currentX][currentY][nextZ], currentScore);
                    if (score < smallestScore)
                        smallestScore = score;
                    Node thisNode = nodes[currentX][currentY][nextZ];
                    thisNode.setScore(score);
                    queue.add(thisNode);
                    thisNode.setParent(currentNode);
                }
            }
        }

        if (routeAvailable) return getPath(currentNode);
        return new ArrayList<>();
    }

    private List<Node> getPath(Node currentNode) {
        List<Node> path = new ArrayList<>();
        while (currentNode != null) {
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }
        return path;
    }

    private int distanceScoreAway(Node currentNode) {
        return Math.abs(endPosition[0] - currentNode.getColNumber()) + Math.abs(endPosition[1] - currentNode.getRowNumber());
    }

    private int getScoreOfNode(Node node, int currentScore) {
        int guessScoreLeft = distanceScoreAway(node);
        int extraMovementCost = 0;
        int movementScore = currentScore + 1;
        return guessScoreLeft + movementScore + extraMovementCost;
    }

    private boolean validNode(int x, int y, int z) {
        Block b = world.getBlockAt(x, y, z);
        Block uB = world.getBlockAt(x, y-1, z);
        if(!b.getType().isSolid() && uB.getType().isSolid()) return true;
        return false;
    }

    private void resetAllNodes() {
        for (Node[][] node : nodes) {
            for (int col = 0; col < nodes[0].length; col++) {
                node[col][0].setOpen(true);
                node[col][0].setParent(null);
                node[col][0].setScore(0);
            }
        }
    }
}
