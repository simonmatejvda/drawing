package pro1.swingComponents;

import pro1.drawingModel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private DisplayPanel displayPanel;

    private List<Point> centers = new ArrayList<>();
    private int currentRadius = 20;
    private boolean showBoundingBox = true;

    public MainFrame() {
        this.setTitle("PRO1 Drawing");
        this.setVisible(true);
        this.setSize(800, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.displayPanel = new DisplayPanel();
        this.add(this.displayPanel, BorderLayout.CENTER);


        JPanel leftPanel = new JPanel();

        leftPanel.setPreferredSize(new Dimension(250, 0));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JLabel radiusLabel = new JLabel("Poloměr koleček: " + currentRadius);
        radiusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(radiusLabel);

        JSlider radiusSlider = new JSlider(5, 100, currentRadius);
        radiusSlider.addChangeListener(e -> {
            currentRadius = radiusSlider.getValue();
            radiusLabel.setText("Poloměr koleček: " + currentRadius);
            updateScene();
        });
        leftPanel.add(radiusSlider);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JCheckBox boxCheckBox = new JCheckBox("Zobrazit obdélník", showBoundingBox);
        boxCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxCheckBox.addActionListener(e -> {
            showBoundingBox = boxCheckBox.isSelected();
            updateScene();
        });
        leftPanel.add(boxCheckBox);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton resetButton = new JButton("Resetovat plátno");
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetButton.addActionListener(e -> {
            centers.clear();
            updateScene();
        });
        leftPanel.add(resetButton);

        this.add(leftPanel, BorderLayout.WEST);


        this.displayPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                centers.add(e.getPoint());
                updateScene();
            }
        });

        updateScene();
    }

    private void updateScene() {
        List<Drawable> drawables = new ArrayList<>();

        if (showBoundingBox && centers.size() >= 2) {
            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;

            for (Point p : centers) {
                if (p.x < minX) minX = p.x;
                if (p.y < minY) minY = p.y;
                if (p.x > maxX) maxX = p.x;
                if (p.y > maxY) maxY = p.y;
            }

            String redColor = "#FF0000";
            int thickness = 2;
            drawables.add(new Line(minX, minY, maxX, minY, thickness, redColor));
            drawables.add(new Line(maxX, minY, maxX, maxY, thickness, redColor));
            drawables.add(new Line(maxX, maxY, minX, maxY, thickness, redColor));
            drawables.add(new Line(minX, maxY, minX, minY, thickness, redColor));
        }

        for (Point p : centers) {
            int topLeftX = p.x - currentRadius;
            int topLeftY = p.y - currentRadius;
            drawables.add(new Ellipse(topLeftX, topLeftY, currentRadius * 2, currentRadius * 2, "#000000"));
        }

        Drawable[] drawablesArr = drawables.toArray(new Drawable[0]);
        Group sceneGroup = new Group(drawablesArr, 0, 0, 0, 1, 1);

        displayPanel.setDrawable(sceneGroup);
    }
}