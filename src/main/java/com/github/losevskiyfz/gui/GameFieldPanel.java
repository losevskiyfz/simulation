package com.github.losevskiyfz.gui;

import com.github.losevskiyfz.map.Map;

import javax.swing.*;
import java.awt.*;

public class GameFieldPanel extends JPanel {
    private final Map map;

    public GameFieldPanel(Map map) {
        this.map = map;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        int cellSize = calculateCellSize();
        int fontSize = calculateFontSize(cellSize);
        graphics2D.setFont(new Font("Segoe UI Emoji", Font.PLAIN, fontSize));
        drawGrid(graphics2D, cellSize);
    }

    private int calculateCellSize() {
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        return Math.min(panelWidth / map.getCols(), panelHeight / map.getRows());
    }

    private int calculateFontSize(int cellSize) {
        return cellSize / 2;
    }

    private void drawGrid(Graphics2D g2d, int cellSize) {
        int gridWidth = map.getCols() * cellSize;
        int gridHeight = map.getRows() * cellSize;
        int offsetX = (getWidth() - gridWidth) / 2;
        int offsetY = (getHeight() - gridHeight) / 2;
        for (int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getCols(); col++) {
                int x = offsetX + col * cellSize;
                int y = offsetY + row * cellSize;
                drawCell(x, y, cellSize, g2d);
                addEntityInCell(x, y, cellSize, map.getCell(col, row).toString(), g2d);
            }
        }
    }

    private void drawCell(int x, int y, int cellSize, Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, cellSize, cellSize);
    }

    private void addEntityInCell(int x, int y, int cellSize, String emoji, Graphics2D g2d) {
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(emoji);
        int textHeight = fm.getAscent();
        int centeredX = x + (cellSize - textWidth) / 2;
        int centeredY = y + (cellSize + textHeight) / 2;
        g2d.drawString(emoji, centeredX, centeredY);
    }
}
