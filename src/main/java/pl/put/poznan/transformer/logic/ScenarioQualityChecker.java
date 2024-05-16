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

    public int countControlSteps() {
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
}
