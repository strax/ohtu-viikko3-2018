package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.Map;

public class StatsApi {
    public static Stats stats(String courseName) throws IOException {
        var endpoint = String.format("https://studies.cs.helsinki.fi/courses/%s/stats", courseName);
        var body = Request.Get(endpoint).execute().returnContent().toString();
        var parser = new JsonParser();
        var json = parser.parse(body).getAsJsonObject();
        return json.entrySet().stream().map(Map.Entry::getValue).map(x -> parseStats(x))
                .reduce((acc, s) -> new Stats(
                        acc.getSubmissions() + s.getSubmissions(),
                        acc.getSubmittedExercises() + s.getSubmittedExercises(),
                        acc.getUsedHours() + s.getUsedHours()))
                .get();
    }

    private static Stats parseStats(JsonElement node) {
        var obj = node.getAsJsonObject();
        var submissions = obj.get("students").getAsInt();
        var submittedExercises = obj.get("exercise_total").getAsInt();
        var usedHours = obj.get("hour_total").getAsInt();
        return new Stats(submissions, submittedExercises, usedHours);
    }
}
