package ohtu;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String studentId;
        if (args.length > 0) {
            studentId = args[0];
        } else {
            studentId = "012345678";
        }
        System.out.println(String.format("opiskelijanumero %s", studentId));

        var courses = CourseApi.courses();
        var submissionsByCourse = SubmissionApi.submissionsByCourse(studentId);

        for (var pair : submissionsByCourse.entrySet()) {
            var courseName = pair.getKey();
            var submissions = pair.getValue();
            var course = courses.stream()
                    .filter(x -> x.getName().equals(courseName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(String.format("Could not find course with the name %s", courseName)));

            System.out.println();
            System.out.println(String.format("%s %s %s", course.getFullName(), course.getTerm(), course.getYear()));
            System.out.println();

            for (var submission : submissions) {
                System.out.println(String.format("Viikko %d:", submission.getWeek()));
                var maxExercises = course.getExercises().get(submission.getWeek());
                System.out.println(String.format(
                        " tehtyjä tehtäviä %d/%d aikaa kului %d tehdyt tehtävät: %s",
                        submission.getExercises().size(),
                        maxExercises,
                        submission.getHours(),
                        mkStringList(submission.getExercises())
                ));
            }

            var totalMaxExercises = course.getExercises().stream().mapToInt(Integer::intValue).sum();
            var totalExercises = submissions.stream().flatMap(s -> s.getExercises().stream()).count();
            var totalHours = submissions.stream().mapToInt(Submission::getHours).sum();
            System.out.println();
            System.out.println(String.format("yhteensä: %d/%d tehtävää %d tuntia", totalExercises, totalMaxExercises, totalHours));

            var stats = StatsApi.stats(course.getName());
            System.out.println();
            System.out.println(String.format(
                    "kurssilla yhteensä %d palautusta, palautettuja tehtäviä %d kpl, aikaa käytetty yhteensä %d tuntia",
                    stats.getSubmissions(),
                    stats.getSubmittedExercises(),
                    stats.getUsedHours()
            ));
        }
    }

    private static String mkStringList(List<?> xs) {
        return String.join(", ", xs.stream().map(Objects::toString).collect(toList()));
    }
}
