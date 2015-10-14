package social.twittersample;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tarun Varshney on 10/14/2015.
 */
public class IssuesData implements Comparable<IssuesData> {
    private String issueTitle;
    private String issueBody;
    private String commentsUrl;
    private String updateAt;
    private String noOfComments;

    @Override
    public String toString() {
        return "IssuesData{" +
                "issueTitle='" + issueTitle + '\'' +
                ", issueBody='" + issueBody + '\'' +
                ", commentsUrl='" + commentsUrl + '\'' +
                ", updateAt='" + updateAt + '\'' +
                ", noOfComments='" + noOfComments + '\'' +
                '}';
    }

    public String getIssueTitle() {
        return issueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }

    public String getIssueBody() {
        return issueBody;
    }

    public void setIssueBody(String issueBody) {
        this.issueBody = issueBody;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {

        try {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            Date date = sdf.parse(updateAt);

            DateFormat target_df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            this.updateAt = target_df.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public String getNoOfComments() {
        return noOfComments;
    }

    public void setNoOfComments(String noOfComments) {
        this.noOfComments = noOfComments;
    }


    @Override
    public int compareTo(IssuesData issuesData) {
        try{
            DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = sdf.parse(issuesData.getUpdateAt());
            Date currentDate = sdf.parse(getUpdateAt());
            Log.d("Date",date.toString());
            return date.compareTo(currentDate);
        }
        catch(Exception ex){
            Log.d("Date Exception:",ex.getMessage().toString());
        }
        return 0;
    }
}
