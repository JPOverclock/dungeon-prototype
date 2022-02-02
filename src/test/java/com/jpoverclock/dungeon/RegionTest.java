package com.jpoverclock.dungeon;

import com.jpoverclock.dungeon.regions.Point;
import com.jpoverclock.dungeon.regions.Region;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegionTest {

    @Test
    void bisectHorizontally() {
        var region = new Region(Point.ORIGIN, 10, 10);
        List<Region> children = region.bisectHorizontally(5);

        assertEquals(Point.ORIGIN, children.get(0).getOrigin());
        assertEquals(5, children.get(0).getHeight());
        assertEquals(10, children.get(0).getWidth());

        assertEquals(new Point(0, 5), children.get(1).getOrigin());
        assertEquals(5, children.get(1).getHeight());
        assertEquals(10, children.get(1).getWidth());
    }

    @Test
    void bisectVertically() {
        var region = new Region(Point.ORIGIN, 10, 10);
        List<Region> children = region.bisectVertically(5);

        assertEquals(Point.ORIGIN, children.get(0).getOrigin());
        assertEquals(10, children.get(0).getHeight());
        assertEquals(5, children.get(0).getWidth());

        assertEquals(new Point(5, 0), children.get(1).getOrigin());
        assertEquals(10, children.get(1).getHeight());
        assertEquals(5, children.get(1).getWidth());
    }

    @Test
    void bisectHorizontallyAtZero() {
        assertThrows(Region.InvalidBisectionException.class, () -> {
            var region = new Region(Point.ORIGIN, 10, 10);
            region.bisectHorizontally(0);
        });
    }

    @Test
    void bisectVerticallyAtZero() {
        assertThrows(Region.InvalidBisectionException.class, () -> {
            var region = new Region(Point.ORIGIN, 10, 10);
            region.bisectVertically(0);
        });
    }

    @Test
    void bisectHorizontallyAtHeightEdge() {
        assertThrows(Region.InvalidBisectionException.class, () -> {
            var region = new Region(Point.ORIGIN, 10, 10);
            region.bisectHorizontally(10);
        });
    }

    @Test
    void bisectVerticallyAtWidthEdge() {
        assertThrows(Region.InvalidBisectionException.class, () -> {
            var region = new Region(Point.ORIGIN, 10, 10);
            region.bisectVertically(10);
        });
    }

    @Test
    void bisectHorizontallyAtAboveHeight() {
        assertThrows(Region.InvalidBisectionException.class, () -> {
            var region = new Region(Point.ORIGIN, 10, 10);
            region.bisectHorizontally(11);
        });
    }

    @Test
    void bisectVerticallyAtAboveWidth() {
        assertThrows(Region.InvalidBisectionException.class, () -> {
            var region = new Region(Point.ORIGIN, 10, 10);
            region.bisectVertically(11);
        });
    }

    @Test
    void area() {
        var region = new Region(Point.ORIGIN, 20, 5);
        assertEquals(100, region.area());
    }

    @Test
    void perimeter() {
        var region = new Region(Point.ORIGIN, 11, 7);
        assertEquals(36, region.perimeter());
    }
}