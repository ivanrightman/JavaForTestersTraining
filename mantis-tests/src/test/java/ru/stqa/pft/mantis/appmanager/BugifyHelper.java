package ru.stqa.pft.mantis.appmanager;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jayway.restassured.RestAssured;

import static com.google.gson.JsonParser.parseString;

public class BugifyHelper {
    private final ApplicationManager app;

    public BugifyHelper(ApplicationManager app) {
        this.app = app;
    }

    public String getIssueByID(int issueId) {
        RestAssured.authentication = RestAssured.basic(app.getProperty("bugify.auth"), app.getProperty("bugify.password"));
        String json = RestAssured.get(app.getProperty("bugify.issuesUrl") + "/" + issueId + ".json").asString();
        JsonElement parsed = parseString(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonObject issue = issues.getAsJsonArray().get(0).getAsJsonObject();
        return issue.get("state_name").getAsString();
    }
}
