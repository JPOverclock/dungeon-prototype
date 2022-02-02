package com.jpoverclock.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NodeGrid {

    public interface Node { }

    public static class VoidNode implements Node { }
    public static class RoomNode implements Node { }
    public static class PassageNode implements Node { }

    private final int width;
    private final int height;

    public Node[][] grid;

    public NodeGrid(int width, int height) {
        this.width = width;
        this.height = height;

        initializeGrid();
    }

    private void initializeGrid() {
        grid = new Node[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new VoidNode();
            }
        }
    }

    public void populate(long seed, int numberOfRooms) {
        var random = new Random(seed);

        // The number of rooms cannot be bigger than the number of cells in the grid
        if (numberOfRooms > width * height) return;

        // Put rooms in random locations
        for (int placedRooms = 0; placedRooms < numberOfRooms;) {
            // Select a random location to place the room
            int i = random.nextInt(height);
            int j = random.nextInt(width);

            if (grid[i][j] instanceof VoidNode) {
                grid[i][j] = new RoomNode();
                placedRooms++;
            }
        }

        // Connect rooms; Rooms must be connected by passage nodes
        // Start by selecting a random room node by searching the grid space
        List<RoomNode> allRooms = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] instanceof RoomNode) {
                    allRooms.add((RoomNode) grid[i][j]);
                }
            }
        }

        var startingRoom = allRooms.remove(random.nextInt(allRooms.size()));
    }
}
