/*
 * Copyright 2025 DukeDance
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package one.cafebabe.dukedance;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Panel that displays the dancing Duke animation.
 */
public class DukePanel extends JPanel {
    
    private List<Image> dukeFrames;
    private int currentFrame = 0;
    private final Color BACKGROUND_COLOR = new Color(240, 240, 255);
    
    public DukePanel() {
        setBackground(BACKGROUND_COLOR);
        loadDukeImages();
    }
    
    private void loadDukeImages() {
        dukeFrames = new ArrayList<>();
        
        // Create Duke images for animation
        // We'll use simple colored shapes for now, but these could be replaced with actual Duke images
        for (int i = 0; i < 6; i++) {
            BufferedImage frame = createDukeFrame(i);
            dukeFrames.add(frame);
        }
    }
    
    private BufferedImage createDukeFrame(int frameNumber) {
        // Create a simple Duke representation using shapes
        // This is a placeholder until we can use actual Duke images
        BufferedImage img = new BufferedImage(200, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        
        // Draw Duke's body (blue oval)
        g2d.setColor(new Color(44, 93, 152));
        g2d.fillOval(50, 100, 100, 150);
        
        // Draw Duke's head (orange circle)
        g2d.setColor(new Color(239, 123, 0));
        g2d.fillOval(60, 40, 80, 80);
        
        // Draw Duke's eyes
        g2d.setColor(Color.WHITE);
        g2d.fillOval(75, 60, 20, 30);
        g2d.fillOval(105, 60, 20, 30);
        
        g2d.setColor(Color.BLACK);
        g2d.fillOval(80, 70, 10, 15);
        g2d.fillOval(110, 70, 10, 15);
        
        // Draw Duke's nose
        g2d.setColor(Color.BLACK);
        g2d.fillOval(90, 85, 20, 10);
        
        // Draw Duke's arms in different positions based on frame number
        g2d.setColor(new Color(44, 93, 152));
        
        // Left arm
        int leftArmAngle = (frameNumber % 3) * 30 - 30; // -30, 0, 30 degrees
        drawArm(g2d, 50, 130, leftArmAngle, true);
        
        // Right arm
        int rightArmAngle = ((frameNumber + 3) % 3) * 30 - 30; // -30, 0, 30 degrees
        drawArm(g2d, 150, 130, rightArmAngle, false);
        
        // Draw Duke's legs in different positions
        int legOffset = (frameNumber % 3) * 10 - 10; // -10, 0, 10 pixels
        
        // Left leg
        g2d.setColor(new Color(44, 93, 152));
        g2d.fillRect(75, 250, 20, 40 + legOffset);
        
        // Right leg
        g2d.fillRect(105, 250, 20, 40 - legOffset);
        
        g2d.dispose();
        return img;
    }
    
    private void drawArm(Graphics2D g2d, int x, int y, int angleDegrees, boolean isLeft) {
        // Save the original transform
        AffineTransform originalTransform = g2d.getTransform();
        
        // Translate to the shoulder position
        g2d.translate(x, y);
        
        // Rotate by the specified angle
        double angleRadians = Math.toRadians(angleDegrees);
        g2d.rotate(isLeft ? angleRadians : -angleRadians);
        
        // Draw the arm
        g2d.fillRect(isLeft ? 0 : -40, 0, 40, 15);
        
        // Restore the original transform
        g2d.setTransform(originalTransform);
    }
    
    public void nextFrame() {
        currentFrame = (currentFrame + 1) % dukeFrames.size();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (dukeFrames != null && !dukeFrames.isEmpty()) {
            // Center the Duke image in the panel
            Image currentImage = dukeFrames.get(currentFrame);
            int x = (getWidth() - currentImage.getWidth(null)) / 2;
            int y = (getHeight() - currentImage.getHeight(null)) / 2;
            
            g.drawImage(currentImage, x, y, null);
        }
    }
}