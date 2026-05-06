package de.htw_berlin.FitnessTrainer;

public class Exerciseexecution {

        String title;
        String muscleGroup;
        String date;
        int reps;
        boolean done;

        public Exerciseexecution() {}

        public Exerciseexecution(String title, String muscleGroup, String date, int reps) {
            this.title = title;
            this.muscleGroup = muscleGroup;
            this.date = date;
            this.reps = reps;
        }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getMuscleGroup() { return muscleGroup; }
        public void setMuscleGroup(String muscleGroup) { this.muscleGroup = muscleGroup; }

        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }

        public int getReps() { return reps; }
        public void setReps(int reps) { this.reps = reps; }


    }