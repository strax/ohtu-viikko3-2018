package ohtu;

import com.google.common.collect.Streams;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Submission {
    private int week;
    private int hours;
    private List<Integer> exercises;
    private String course;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public List<Integer> getExercises() {
        return exercises;
    }

    public void setExercises(List<Integer> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return String.format("%s, viikko %d, tehtyjä tehtäviä yhteensä %d, aikaa kului %dh, tehdyt tehtävät: %s", course, week, exercises.size(), hours, String.join(", ", asStrings(exercises)));
    }

    private<A> Iterable<String> asStrings(Iterable<A> xs) {
        return Streams.stream(xs).map(Objects::toString).collect(Collectors.toList());
    }
}