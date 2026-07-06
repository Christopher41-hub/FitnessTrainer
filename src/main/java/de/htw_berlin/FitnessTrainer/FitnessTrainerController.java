package de.htw_berlin.FitnessTrainer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fitnesstrainer")

public class FitnessTrainerController {

    private final ExerciseexecutionRepository repository;

    public FitnessTrainerController(ExerciseexecutionRepository repository) {
        this.repository = repository;
    }

    // Alle Übungen anzeigen
    @GetMapping
    public List<Exerciseexecution> getExercises() {
        return repository.findAll();
    }

    // Neue Übung hinzufügen
    @PostMapping
    public Exerciseexecution addExercise(@RequestBody Exerciseexecution exercise) {
        return repository.save(exercise);
    }

    // Übung bearbeiten
    @PutMapping("/{id}")
    public Exerciseexecution updateExercise(@PathVariable Long id,
                                            @RequestBody Exerciseexecution updatedExercise) {

        Exerciseexecution exercise = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Übung nicht gefunden"));

        exercise.setTitle(updatedExercise.getTitle());
        exercise.setMuscleGroup(updatedExercise.getMuscleGroup());
        exercise.setDate(updatedExercise.getDate());
        exercise.setReps(updatedExercise.getReps());
        exercise.setDone(updatedExercise.isDone());

        return repository.save(exercise);
    }

    // Übung löschen
    @DeleteMapping("/{id}")
    public void deleteExercise(@PathVariable Long id) {
        repository.deleteById(id);
    }
}