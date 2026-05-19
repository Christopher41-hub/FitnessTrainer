package de.htw_berlin.FitnessTrainer;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174",
        "https://fitnesstrainer-frontend.onrender.com"
})
public class FitnessTrainerController {

    @GetMapping("/fitnesstrainer")
    public List<Exerciseexecution> getExercises() {
        return List.of(
                new Exerciseexecution("Liegestütze", "Brust", "22.04.2026", 10),
                new Exerciseexecution("Kniebeugen", "Beine", "22.04.2026", 15),
                new Exerciseexecution("Sit-ups", "Bauch", "22.04.2026", 20)
        );
    }
}