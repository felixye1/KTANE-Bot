package ui;

import model.solvers.KeypadSolver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

// represents the Keypad module GUI
public class KeypadGui implements ActionListener {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 800;
    private static final int PANEL_SIZE = FRAME_WIDTH / 10;
    private static final int BUFFER = PANEL_SIZE / 10;
    private JFrame frame;
    private ArrayList<JLabel> labels;
    private ArrayList<JPanel> panels;
    private KeypadSolver keSolver;

    // EFFECTS: initializes the Keypad GUI
    public KeypadGui() {
        keSolver = KeypadSolver.getInstance();
        labels = new ArrayList<>();
        panels = new ArrayList<>();
        setLabels();
        setPanels();
        addButtons(keSolver.getImageFiles());
        setFrame();
    }

    // EFFECTS: sets labels of Keypad GUI
    private void setLabels() {
        JLabel displayLabel = new JLabel("Select the 4 symbols that are on the keypad:",
                SwingConstants.CENTER);
        JLabel solutionLabel = new JLabel("Press the symbols in this order (left to right):",
                SwingConstants.CENTER);
        JLabel errorLabel = new JLabel("A solution could not be found, please check your inputs.",
                SwingConstants.CENTER);
        displayLabel.setBounds(0, 0, FRAME_WIDTH, PANEL_SIZE);
        solutionLabel.setBounds(0, 5 * FRAME_HEIGHT / 8, FRAME_WIDTH, PANEL_SIZE);
        errorLabel.setBounds(0, 5 * FRAME_HEIGHT / 8, FRAME_WIDTH, PANEL_SIZE);
        displayLabel.setFont(new Font("Sans Serif", Font.PLAIN, 30));
        solutionLabel.setFont(new Font("Sans Serif", Font.PLAIN, 30));
        errorLabel.setFont(new Font("Sans Serif", Font.PLAIN, 30));
        displayLabel.setVisible(true);
        solutionLabel.setVisible(false);
        errorLabel.setVisible(false);
        labels.add(displayLabel);
        labels.add(solutionLabel);
        labels.add(errorLabel);
        setImageLabels();
    }

    // EFFECTS: sets labels containing images of Keypad GUI
    private void setImageLabels() {
        JLabel firstPressLabel = new JLabel();
        JLabel secondPressLabel = new JLabel();
        JLabel thirdPressLabel = new JLabel();
        JLabel fourthPressLabel = new JLabel();
        firstPressLabel.setBounds(FRAME_WIDTH / 2 - 2 * PANEL_SIZE,
                13 * FRAME_HEIGHT / 16, PANEL_SIZE, PANEL_SIZE);
        secondPressLabel.setBounds(FRAME_WIDTH / 2 - PANEL_SIZE,
                13 * FRAME_HEIGHT / 16, PANEL_SIZE, PANEL_SIZE);
        thirdPressLabel.setBounds(FRAME_WIDTH / 2,
                13 * FRAME_HEIGHT / 16, PANEL_SIZE, PANEL_SIZE);
        fourthPressLabel.setBounds(FRAME_WIDTH / 2 + PANEL_SIZE,
                13 * FRAME_HEIGHT / 16, PANEL_SIZE, PANEL_SIZE);
        firstPressLabel.setVisible(true);
        secondPressLabel.setVisible(true);
        thirdPressLabel.setVisible(true);
        fourthPressLabel.setVisible(true);
        labels.add(firstPressLabel);
        labels.add(secondPressLabel);
        labels.add(thirdPressLabel);
        labels.add(fourthPressLabel);
    }

    // EFFECTS: sets panels of Keypad GUI
    private void setPanels() {
        int startX = FRAME_WIDTH / 20;
        int currentX = startX;
        int currentY = startX + PANEL_SIZE;
        for (int x = 0; x < keSolver.getImageFiles().size(); x++) {
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setBounds(currentX, currentY, PANEL_SIZE, PANEL_SIZE);
            panel.setBackground(Color.white);
            panel.setVisible(true);
            panels.add(panel);
            currentX += PANEL_SIZE;
            if (currentX >= FRAME_WIDTH - PANEL_SIZE) {
                currentX = startX;
                currentY += PANEL_SIZE;
            }
        }
    }

    // EFFECTS: adds button to the panel
    private void addButtons(Set<String> files) {
        int index = 0;
        for (String file : files) {
            JPanel panel = panels.get(index);
            JButton button = new JButton();
            ImageIcon image = new ImageIcon(file);
            Image symbol = image.getImage().getScaledInstance(PANEL_SIZE - 2 * BUFFER
                    , PANEL_SIZE - 2 * BUFFER, java.awt.Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(symbol, file);
            button.setIcon(icon);
            button.setBounds(BUFFER, BUFFER,
                    PANEL_SIZE - 2 * BUFFER, PANEL_SIZE - 2 * BUFFER);
            button.addActionListener(this);
            button.setVisible(true);
            panel.add(button);
            index++;
        }
    }

    // EFFECTS: sets frame of Keypad GUI
    private void setFrame() {
        frame = new JFrame();
        frame.setLayout(null);
        frame.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("Keypad GUI");
        for (JPanel panel : panels) {
            frame.add(panel);
        }
        for (JLabel label : labels) {
            frame.add(label);
        }
        frame.setVisible(true);
    }

    // EFFECTS: processes button clicks
    public void actionPerformed(ActionEvent ae) {
        JButton pressed = (JButton) ae.getSource();
        ImageIcon icon = (ImageIcon) pressed.getIcon();
        String description = icon.getDescription();
        Set<String> selected = keSolver.getSelected();
        if (selected.contains(description)) {
            selected.remove(description);
        } else {
            selected.add(description);
        }
        JPanel panel = (JPanel) pressed.getParent();
        if (panel.getBackground().equals(Color.white)) {
            panel.setBackground(Color.red);
        } else {
            panel.setBackground(Color.white);
        }
        if (selected.size() == keSolver.getNumKeys()) {
            ArrayList<String> solution = keSolver.solve();
            if (solution.size() == 0) {
                displayError();
            } else {
                displaySolution(solution);
            }
        } else {
            resetLabels();
        }
    }

    // EFFECTS: displays solution of the keypad module with the given input
    private void displaySolution(ArrayList<String> solution) {
        resetLabels();
        labels.get(1).setVisible(true);
        int x = 3;
        for (String file : solution) {
            ImageIcon image = new ImageIcon(file);
            Image symbol = image.getImage().getScaledInstance(PANEL_SIZE, PANEL_SIZE, java.awt.Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(symbol, file);
            labels.get(x).setIcon(icon);
            x++;
        }
    }

    // EFFECTS: displays error message in case of incorrect input
    private void displayError() {
        resetLabels();
        labels.get(2).setVisible(true);
    }

    // EFFECTS: removes solution and error message
    private void resetLabels() {
        labels.get(1).setVisible(false);
        labels.get(2).setVisible(false);
        for (int x = 3; x < labels.size(); x++) {
            labels.get(x).setIcon(null);
        }
    }
}
