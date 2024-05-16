package pl.put.poznan.transformer.app;

import pl.put.poznan.transformer.logic.ScenarioQualityChecker;

import javax.swing.*;

public class ScenarioQualityCheckerApp extends JFrame {
    private JTextField scenarioField;
    private JTextField actorField;
    private JTextField systemActorField;
    private JTextArea stepsArea;
    private JButton calculateButton;
    private JLabel resultLabel;

    public ScenarioQualityCheckerApp() {
        // Initial window configuration
        setTitle("Scenario auality checker");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Create and add components
        scenarioField = new JTextField();
        scenarioField.setBorder(BorderFactory.createTitledBorder("Scenario title"));
        add(scenarioField);

        actorField = new JTextField();
        actorField.setBorder(BorderFactory.createTitledBorder("Actor"));
        add(actorField);

        systemActorField = new JTextField();
        systemActorField.setBorder(BorderFactory.createTitledBorder("System actor"));
        add(systemActorField);

        stepsArea = new JTextArea(10, 20);
        stepsArea.setBorder(BorderFactory.createTitledBorder("Steps (separated by lines)"));
        JScrollPane scrollPane = new JScrollPane(stepsArea);
        add(scrollPane);

        calculateButton = new JButton("Calculate total steps");
        calculateButton.addActionListener(e -> calculateTotalSteps());
        add(calculateButton);

        resultLabel = new JLabel("Total steps: ");
        add(resultLabel);

        pack();
        setVisible(true);
    }

    private void calculateTotalSteps() {
        String[] steps = stepsArea.getText().split("\n");
        ScenarioQualityChecker checker = new ScenarioQualityChecker(
                scenarioField.getText(),
                actorField.getText(),
                systemActorField.getText(),
                steps
        );
        int totalSteps = checker.countTotalSteps();
        resultLabel.setText("Total steps:" + totalSteps);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScenarioQualityCheckerApp());
    }
}
