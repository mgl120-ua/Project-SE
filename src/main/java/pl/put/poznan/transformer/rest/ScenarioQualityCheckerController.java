package pl.put.poznan.transformer.rest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.put.poznan.transformer.logic.ScenarioQualityChecker;

public class ScenarioQualityCheckerController {

    @GetMapping("/")
    public String showForm(Model model) {
        // Crear un nuevo objeto ScenarioQualityChecker
        ScenarioQualityChecker scenario = new ScenarioQualityChecker();

        // Añadir el objeto scenario al modelo, para poder usarlo en la vista
        model.addAttribute("scenario", scenario);

        // Retornar el nombre de la vista que será mostrada
        return "form";
    }

    @PostMapping("/submit")
    public String submitForm(ScenarioQualityChecker scenario, Model model) {
        // Aquí procesas el escenario, por ejemplo, podrías establecer algún valor o realizar una operación
        scenario.setScenario("Nuevo Valor del Escenario");

        // Luego añades el objeto scenario actualizado al modelo para pasarlo a la vista
        model.addAttribute("scenario", scenario);

        // Finalmente, retornas el nombre de la vista que mostrará el resultado
        return "result";
    }
}
