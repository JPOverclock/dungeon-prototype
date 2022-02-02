package com.jpoverclock.dungeon;

import com.jpoverclock.dungeon.debug.DebugUi;
import com.jpoverclock.dungeon.regions.Point;
import com.jpoverclock.dungeon.regions.Region;
import com.jpoverclock.dungeon.regions.RegionPartitioner;

import java.awt.*;
import java.util.Collection;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        var region = new Region(Point.ORIGIN, 600, 400);
        var partitioner = new RegionPartitioner(new Random());

        Collection<Region> regions = partitioner.partition(region);

        EventQueue.invokeLater(() -> {
            var ui = new DebugUi(regions);
            ui.setVisible(true);
        });
    }
}
