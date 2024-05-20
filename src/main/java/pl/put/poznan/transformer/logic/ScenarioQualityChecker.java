package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScenarioQualityChecker {
    private String scenario;       // Scenario
    private String actor;          // Actor
    private String systemActor;    // System Actor
    private List<String> steps;    // Scenario Steps

    // Constructor
    public ScenarioQualityChecker(String scenario, String actor, String systemActor, String[] stepsArray) {
        this.scenario = scenario;
        this.actor = actor;
        this.systemActor = systemActor;
        this.steps = new ArrayList<>(Arrays.asList(stepsArray));  // Convert array to List
    }

    // Getters
    public String getScenario() {
        return scenario;
    }

    public String getActor() {
        return actor;
    }

    public String getSystemActor() {
        return systemActor;
    }

    public List<String> getSteps() {
        return steps;
    }

    // Setters
    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public void setSystemActor(String systemActor) {
        this.systemActor = systemActor;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    // Method to count the total number of steps in a scenario
    public int countTotalSteps() {
        int totalSteps = steps.size();
        for (String step : steps) {
            if (step.contains("IF") || step.contains("ELSE") || step.contains("FOR EACH")) {
                totalSteps++; // Increment if the step contains keywords
            }
        }
        return totalSteps;
    }

    public int countConditionalSteps() {
        int controlSteps = 0;
        for (String step : steps) {
            if (step.contains("IF") || step.contains("ELSE") || step.contains("FOR EACH")) {
                controlSteps++;
            }
        }
        return controlSteps;
    }

    // Method to validate actors in the scenario steps
    public List<String> validateActors() {
        List<String> incorrectSteps = new ArrayList<>();
        List<String> validActors = Arrays.asList(actor, systemActor); // Assuming these are the valid actors
        for (String step : steps) {
            boolean actorFound = false;
            for (String validActor : validActors) {
                if (step.startsWith(validActor + ":")) { // Assuming steps start with "Actor:"
                    actorFound = true;
                    break;
                }
            }
            if (!actorFound) {
                incorrectSteps.add(step);
            }
        }
        return incorrectSteps;
    }

    public List<String> validateStepsStartWithActor() {
        List<String> invalidSteps = new ArrayList<>();
        List<String> validPrefixes = Arrays.asList(actor, systemActor); // Ensure ":" to denote actor action
        List<String> controlKeywords = Arrays.asList("IF:", "ELSE:", "FOR EACH:"); // Keywords to ignore

<<<<<<< HEAD
        for (String step : steps) {
=======
        for (String step : steps) {m
>>>>>>> 0b20a329cc1409a7811ee4d9c842affa18c250ea
            // Check if step starts with a control keyword or an actor, skip if true
            boolean startsWithControlKeyword = controlKeywords.stream().anyMatch(step::startsWith);
            boolean startsWithActor = validPrefixes.stream().anyMatch(step::startsWith);

            if (!startsWithControlKeyword && !startsWithActor) {
                // If it does not start with a control keyword or an actor name, then add to invalidSteps
                invalidSteps.add(step);
            }
        }
        return invalidSteps;
    }

    public String formatStepsForDocumentation() {
        StringBuilder formattedSteps = new StringBuilder();
        formatStepsRecursive(this.steps, "", 1, formattedSteps);
        return formattedSteps.toString();
    }

    private void formatStepsRecursive(List<String> steps, String prefix, int level, StringBuilder formattedSteps) {
        int stepNumber = 1;
        for (String step : steps) {
            String currentPrefix = prefix.isEmpty() ? Integer.toString(stepNumber) : prefix + "." + stepNumber;
            formattedSteps.append(currentPrefix).append(". ").append(step).append("\n");

            // Asumir que los subpasos se pasan como parte de la lista de pasos de alguna manera
            // Aquí, eliminaremos el código que añade subpasos ficticios
            // Solo incrementamos el número de paso si no es un subpaso
            stepNumber++;
        }
    }
}
