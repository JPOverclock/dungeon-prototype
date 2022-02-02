package com.jpoverclock.dungeon.regions;

import java.util.List;
import java.util.Objects;

public class Region {

    public static class InvalidBisectionException extends RuntimeException { }

    private final Point origin;
    private final int width;
    private final int height;

    public Region(Point origin, int width, int height) {
        this.origin = origin;
        this.width = width;
        this.height = height;
    }

    public Point getOrigin() {
        return origin;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Region> bisectHorizontally(int split) {
        if (split <= 0 || split >= height) throw new InvalidBisectionException();

        return List.of(
                new Region(origin, width, split),
                new Region(new Point(origin.getX(), origin.getY() + split), width, height - split)
        );
    }

    public List<Region> bisectVertically(int split) {
        if (split <= 0 || split >= width) throw new InvalidBisectionException();

        return List.of(
                new Region(origin, split, height),
                new Region(new Point(origin.getX() + split, origin.getY()), width - split, height)
        );
    }

    public int area() {
        return width * height;
    }

    public int perimeter() {
        return (width << 1) + (height << 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return width == region.width && height == region.height && origin.equals(region.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, width, height);
    }
}
