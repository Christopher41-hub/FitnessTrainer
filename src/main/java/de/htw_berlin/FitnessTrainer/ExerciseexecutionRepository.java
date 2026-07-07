package de.htw_berlin.FitnessTrainer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseexecutionRepository extends JpaRepository<Exerciseexecution, Long> {
    List<Exerciseexecution> findByUser(User user);
}