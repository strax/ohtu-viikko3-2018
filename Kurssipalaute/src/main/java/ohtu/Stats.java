package ohtu;

public class Stats {
    private int submissions;
    private int submittedExercises;
    private int usedHours;

    public Stats(int submissions, int submittedExercises, int usedHours) {
        this.submissions = submissions;
        this.submittedExercises = submittedExercises;
        this.usedHours = usedHours;
    }

    public int getSubmissions() {
        return submissions;
    }

    public void setSubmissions(int submissions) {
        this.submissions = submissions;
    }

    public int getSubmittedExercises() {
        return submittedExercises;
    }

    public void setSubmittedExercises(int submittedExercises) {
        this.submittedExercises = submittedExercises;
    }

    public int getUsedHours() {
        return usedHours;
    }

    public void setUsedHours(int usedHours) {
        this.usedHours = usedHours;
    }
}
