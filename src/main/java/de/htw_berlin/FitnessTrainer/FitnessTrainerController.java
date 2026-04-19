package de.htw_berlin.FitnessTrainer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class FitnessTrainerController {

    @GetMapping("/fitnesstrainer")
    public List<String> getUebungen() {
        return List.of(
                "Liegestütze",
                "Kniebeugen",
                "Sit-ups",
                "Plank",
                "Burpees"
        );
    }
}