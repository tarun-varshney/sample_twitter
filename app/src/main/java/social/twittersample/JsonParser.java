package social.twittersample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Tarun Varshney on 10/14/2015.
 */
public class JsonParser {

    public static ArrayList<IssuesData> getIssuesList(String jsonData) {
        ArrayList<IssuesData> issuesDataList=new ArrayList<>();
        try {
            JSONArray issuesJsonArray = new JSONArray(jsonData);
            for (int i = 0; i < issuesJsonArray.length(); i++) {
                IssuesData issuesData = new IssuesData();
                JSONObject issueObject = (JSONObject)issuesJsonArray.get(i);
                issuesData.setIssueTitle(issueObject.getString("title"));
                issuesData.setIssueBody(issueObject.getString("body"));
                issuesData.setCommentsUrl(issueObject.getString("comments_url"));
                issuesData.setUpdateAt(issueObject.getString("updated_at"));
                issuesData.setNoOfComments(issueObject.getString("comments"));
                issuesDataList.add(issuesData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return issuesDataList;
    }
    public static ArrayList<CommentData> getCommentsList(String jsonData) {
        ArrayList<CommentData> commentsDataList=new ArrayList<>();
        try {
            JSONArray commentsJsonArray = new JSONArray(jsonData);
            for (int i = 0; i < commentsJsonArray.length(); i++) {
                CommentData commentData = new CommentData();
                JSONObject commentObject = (JSONObject)commentsJsonArray.get(i);
                JSONObject userObject=(JSONObject)commentObject.get("user");
                commentData.setUserName(userObject.getString("login"));
                commentData.setCommentBody(commentObject.getString("body"));
                commentsDataList.add(commentData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return commentsDataList;
    }
}
