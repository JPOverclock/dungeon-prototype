package com.jpoverclock.dungeon.debug;

import com.jpoverclock.dungeon.regions.Region;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class DebugUi extends JFrame {

    public static class Canvas extends JPanel implements ActionListener {

        private final Timer timer;
        private final Collection<Region> regions;

        public Canvas(Collection<Region> regions) {
            this.regions = regions;
            this.timer = new Timer(150, this);
            timer.start();
        }

        public Timer getTimer() {
            return timer;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            draw(g);
        }

        public void draw(Graphics g) {
            for (var region : regions) {
                g.drawRect(region.getOrigin().getX(), region.getOrigin().getY(), region.getWidth(), region.getHeight());
            }
        }
    }

    public DebugUi(Collection<Region> regions) {
        final var canvas = new Canvas(regions);
        add(canvas);

        setTitle("Dungeon");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
