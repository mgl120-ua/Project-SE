package pl.put.poznan.transformer.logic;

public class ScenarioQualityChecker {
    private String scenario;       // Escenario
    private String actor;          // Actor
    private String systemActor;    // Actor del sistema
    private String[] steps;        // Pasos del escenario

    // Constructor vacío
    public ScenarioQualityChecker() {
    }

    // Constructor con todos los campos
    public ScenarioQualityChecker(String scenario, String actor, String systemActor, String[] steps) {
        this.scenario = scenario;
        this.actor = actor;
        this.systemActor = systemActor;
        this.steps = steps;
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

    public String[] getSteps() {
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

    public void setSteps(String[] steps) {
        this.steps = steps;
    }
}
