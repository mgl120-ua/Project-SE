package pl.put.poznan.transformer.app;

import pl.put.poznan.transformer.logic.ScenarioQualityChecker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ScenarioQualityCheckerApp extends JFrame {
    private JTextField scenarioField;
    private JTextField actorField;
    private JTextField systemActorField;
    private JTextArea stepsArea;
    private JButton calculateTotalStepsButton;      // Botón para calcular el total de pasos
    private JButton calculateConditionalStepsButton; // Botón para calcular pasos condicionales
    private JButton validateStepsButton;
    private JLabel resultTotalLabel;
    private JLabel resultConditionalLabel;
    private JTextArea resultValidationArea;
    private JButton downloadFormattedStepsButton;

    public ScenarioQualityCheckerApp() {
        setTitle("Scenario Quality Checker");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

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
        stepsArea.setBorder(BorderFactory.createTitledBorder("Steps (separated by lines, no space and no special characters)"));
        JScrollPane stepsScrollPane = new JScrollPane(stepsArea);  // Cambiado a stepsScrollPane
        add(stepsScrollPane);

        calculateTotalStepsButton = new JButton("Calculate total steps");
        calculateTotalStepsButton.addActionListener(e -> calculateTotalSteps());
        add(calculateTotalStepsButton);

        calculateConditionalStepsButton = new JButton("Calculate conditional steps");
        calculateConditionalStepsButton.addActionListener(e -> countConditionalSteps());
        add(calculateConditionalStepsButton);

        validateStepsButton = new JButton("Validate Step Start");
        validateStepsButton.addActionListener(e -> validateSteps());
        add(validateStepsButton);

        resultValidationArea = new JTextArea(5, 20);
        resultValidationArea.setBorder(BorderFactory.createTitledBorder("Invalid Steps"));
        JScrollPane validationScrollPane = new JScrollPane(resultValidationArea);  // Cambiado a validationScrollPane
        add(validationScrollPane);

        resultTotalLabel = new JLabel("Total steps: 0");
        add(resultTotalLabel);

        resultConditionalLabel = new JLabel("Conditional steps: 0");
        add(resultConditionalLabel);

        downloadFormattedStepsButton = new JButton("Download Formatted Steps");
        downloadFormattedStepsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadFormattedSteps();
            }
        });
        add(downloadFormattedStepsButton);

        pack();
        setVisible(true);
    }

    private void calculateTotalSteps() {
        String[] steps = stepsArea.getText().split("\n");
        if (stepsArea.getText().isEmpty()) {
            resultTotalLabel.setText("Total steps: 0");
        } else {
            ScenarioQualityChecker checker = new ScenarioQualityChecker(
                    scenarioField.getText(),
                    actorField.getText(),
                    systemActorField.getText(),
                    steps
            );
            int totalSteps = checker.countTotalSteps();
            resultTotalLabel.setText("Total steps: " + totalSteps);
        }
    }

    private void countConditionalSteps() {
        String[] steps = stepsArea.getText().split("\n");
        if (stepsArea.getText().isEmpty()) {
            resultConditionalLabel.setText("Conditional steps: 0");
        } else {
            ScenarioQualityChecker checker = new ScenarioQualityChecker(
                    scenarioField.getText(),
                    actorField.getText(),
                    systemActorField.getText(),
                    steps
            );
            int conditionalSteps = checker.countConditionalSteps();
            resultConditionalLabel.setText("Conditional steps: " + conditionalSteps);
        }
    }

    private void validateSteps() {
        String[] steps = stepsArea.getText().split("\n");
        ScenarioQualityChecker checker = new ScenarioQualityChecker(
                scenarioField.getText(),
                actorField.getText(),
                systemActorField.getText(),
                steps
        );
        List<String> invalidSteps = checker.validateStepsStartWithActor();
        if (invalidSteps.isEmpty()) {
            resultValidationArea.setText("All steps correctly start with an actor.");
        } else {
            resultValidationArea.setText(String.join("\n", invalidSteps));
        }
    }

    private void downloadFormattedSteps() {
        String[] steps = stepsArea.getText().split("\n");
        ScenarioQualityChecker checker = new ScenarioQualityChecker(
                scenarioField.getText(),
                actorField.getText(),
                systemActorField.getText(),
                steps
        );
        String formattedSteps = checker.formatStepsForDocumentation();
        File file = new File("FormattedScenarioSteps.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(formattedSteps);
            JOptionPane.showMessageDialog(this, "Steps downloaded successfully!\nFile path: " + file.getAbsolutePath());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScenarioQualityCheckerApp());
    }
}
