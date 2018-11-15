package ohtu;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CourseApi {
    public static List<Course> courses() throws IOException {
        var endpoint = "https://studies.cs.helsinki.fi/courses/courseinfo";
        var content = Request.Get(endpoint).execute().returnContent().asString();
        var gson = new Gson();
        return Arrays.asList(gson.fromJson(content, Course[].class));
    }
}
