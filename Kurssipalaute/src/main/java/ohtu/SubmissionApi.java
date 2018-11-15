package ohtu;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

public class SubmissionApi {
    public static Map<String, List<Submission>> submissionsByCourse(String studentId) throws IOException {
        var endpoint = String.format("https://studies.cs.helsinki.fi/courses/students/%s/submissions", studentId);
        var body = Request.Get(endpoint).execute().returnContent().asString();
        Gson mapper = new Gson();
        Submission[] submissions = mapper.fromJson(body, Submission[].class);
        return stream(submissions).collect(groupingBy(Submission::getCourse));
    }
}
