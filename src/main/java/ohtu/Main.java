package ohtu;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;

import static java.util.Arrays.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String studentId;
        if (args.length > 0) {
            studentId = args[0];
        } else {
            studentId = "012345678";
        }
        System.out.println(String.format("Opiskelijanumero: %s", studentId));

        var url = "https://studies.cs.helsinki.fi/courses/students/" + studentId + "/submissions";
        var bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        for (Submission submission : subs) {
            System.out.println("  " + submission);
        }

        System.out.println(String.format(
            "Yhteensä: %d tehtävää, %d tuntia",
            stream(subs).mapToInt(x -> x.getExercises().size()).sum(),
            stream(subs).mapToInt(x -> x.getHours()).sum())
        );
    }
}
