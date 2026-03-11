package edu.touro.las.mcon364.test;

import java.util.*;

public class StudyTracker {

    private final Map<String, List<Integer>> scoresByLearner = new HashMap<>();
    private final Deque<UndoStep> undoStack = new ArrayDeque<>();
    // Helper methods already provided for tests and local inspection.
    public Optional<List<Integer>> scoresFor(String name) {
        return Optional.ofNullable(scoresByLearner.get(name));
    }

    public Set<String> learnerNames() {
        return scoresByLearner.keySet();
    }
    /**
     * Problem 11
     * Add a learner with an empty score list.
     *
     * Return:
     * - true if the learner was added
     * - false if the learner already exists
     *
     * Throw IllegalArgumentException if name is null or blank.
     */
    public boolean addLearner(String name) {

        boolean added = false;
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException();
        }
        if(scoresByLearner.containsKey(name)) {
            return added;
        }else{
            scoresByLearner.put(name, new ArrayList<>());
            added = true;
        }
        return added;
    }

    /**
     * Problem 12
     * Add a score to an existing learner.
     *
     * Return:
     * - true if the score was added
     * - false if the learner does not exist
     *
     * Valid scores are 0 through 100 inclusive.
     * Throw IllegalArgumentException for invalid scores.
     *
     * This operation should be undoable.
     */
    public boolean addScore(String name, int score) {
        boolean added = false;
        if(score>100 || score<0){
            throw new IllegalArgumentException();
        }
        if(scoresByLearner.containsKey(name)) {
            added = true;
            scoresByLearner.get(name).add(score);
            undoStack.push(new UndoStep() {
                @Override
                public void undo() {
                    scoresByLearner.get(name).remove(score);
                }
            });
        }else{
            return added;
        }
        return added;
    }

    /**
     * Problem 13
     * Return the average score for one learner.
     *
     * Return Optional.empty() if:
     * - the learner does not exist, or
     * - the learner has no scores
     */
    public Optional<Double> averageFor(String name) {
        int sum = 0;
        Optional<List<Integer>> scores = scoresFor(name);
        if(scores.isEmpty()){
            return Optional.empty();
        }
            for(Integer score: scores.get()){
                sum += score;
            }

        return Optional.of((double)sum/scores.get().size());

    }

    /**
     * Problem 14
     * Convert a learner average into a letter band.
     *
     * A: 90+
     * B: 80-89.999...
     * C: 70-79.999...
     * D: 60-69.999...
     * F: below 60
     *
     * Return Optional.empty() when no average exists.
     */
    public Optional<String> letterBandFor(String name) {
        if(averageFor(name).isEmpty()){
            return Optional.empty();
        }
        double average = averageFor(name).get();

                switch ((int) average / 10) {
                    case 10, 9 -> {
                        return Optional.of("A");
                    }
                    case 8 -> {
                        return Optional.of("B");
                    }
                    case 7 -> {
                        return Optional.of("C");
                    }
                    case 6 -> {
                        return Optional.of("D");
                    }
                    default -> {
                        return Optional.of("F");
                    }
                }
    }

    /**
     * Problem 15
     * Undo the most recent state-changing operation.
     *
     * Return true if something was undone.
     * Return false if there is nothing to undo.
     */
    public boolean undoLastChange() {
        if(undoStack.isEmpty()){
            return false;
        }
        undoStack.pop().undo();
        return true;
    }


}
