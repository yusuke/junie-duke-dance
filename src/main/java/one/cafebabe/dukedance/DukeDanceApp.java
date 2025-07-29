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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A Swing application that shows Duke (Java mascot) dancing.
 */
public class DukeDanceApp extends JFrame {
    
    private DukePanel dukePanel;
    private Timer animationTimer;
    private JButton startButton;
    private JButton stopButton;
    private final int ANIMATION_DELAY = 100; // milliseconds between frames
    
    public DukeDanceApp() {
        setTitle("Dancing Duke");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create the panel where Duke will dance
        dukePanel = new DukePanel();
        add(dukePanel, BorderLayout.CENTER);
        
        // Create control panel with buttons
        JPanel controlPanel = new JPanel();
        startButton = new JButton("Start Dancing");
        stopButton = new JButton("Stop Dancing");
        stopButton.setEnabled(false);
        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAnimation();
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
            }
        });
        
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopAnimation();
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
            }
        });
        
        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        add(controlPanel, BorderLayout.SOUTH);
        
        // Set up the animation timer
        animationTimer = new Timer(ANIMATION_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dukePanel.nextFrame();
                dukePanel.repaint();
            }
        });
        
        // Set window size and position
        setSize(400, 500);
        setLocationRelativeTo(null); // Center on screen
    }
    
    private void startAnimation() {
        animationTimer.start();
    }
    
    private void stopAnimation() {
        animationTimer.stop();
    }
    
    public static void main(String[] args) {
        // Use the Event Dispatch Thread for Swing applications
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DukeDanceApp app = new DukeDanceApp();
                app.setVisible(true);
            }
        });
    }
}