package de.htw_berlin.FitnessTrainer;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fitnesstrainer")
public class FitnessTrainerController {

    private final ExerciseexecutionRepository repository;

    public FitnessTrainerController(ExerciseexecutionRepository repository) {
        this.repository = repository;
    }

    // Alle Übungen des eingeloggten Nutzers anzeigen
    @GetMapping
    public List<Exerciseexecution> getExercises(@AuthenticationPrincipal User user) {
        return repository.findByUser(user);
    }

    // Neue Übung für den eingeloggten Nutzer hinzufügen
    @PostMapping
    public Exerciseexecution addExercise(@RequestBody Exerciseexecution exercise,
                                         @AuthenticationPrincipal User user) {
        exercise.setUser(user);
        return repository.save(exercise);
    }

    // Übung bearbeiten (nur wenn sie dem Nutzer gehört)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExercise(@PathVariable Long id,
                                            @RequestBody Exerciseexecution updatedExercise,
                                            @AuthenticationPrincipal User user) {

        Exerciseexecution exercise = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Übung nicht gefunden"));

        if (!exercise.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }

        exercise.setTitle(updatedExercise.getTitle());
        exercise.setMuscleGroup(updatedExercise.getMuscleGroup());
        exercise.setDate(updatedExercise.getDate());
        exercise.setReps(updatedExercise.getReps());
        exercise.setDone(updatedExercise.isDone());

        return ResponseEntity.ok(repository.save(exercise));
    }

    // Übung löschen (nur wenn sie dem Nutzer gehört)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExercise(@PathVariable Long id,
                                            @AuthenticationPrincipal User user) {

        Exerciseexecution exercise = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Übung nicht gefunden"));

        if (!exercise.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }

        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}