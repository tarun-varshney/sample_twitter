package social.twittersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ListView mIssueList;
    private ArrayList<IssuesData> issuesDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        fetchTheIssues();
    }

    private void setViews() {
        mIssueList = (ListView) findViewById(R.id.issue_list_view);
        mIssueList.setOnItemClickListener(onItemClickListener);
    }

    private void fetchTheIssues() {
        NetworkRequestTask networkRequestTask = new NetworkRequestTask(callbackWithObject);
        networkRequestTask.execute(Constants.API_ISSUES);
    }

    CallbackWithObject callbackWithObject = new CallbackWithObject() {
        @Override
        public void onProgress() {

        }

        @Override
        public void onResult(String result) {
            if (result != null) {
                issuesDataList = JsonParser.getIssuesList(result);
                Collections.sort(issuesDataList);
                IssuesAdapter issuesAdapter = new IssuesAdapter(MainActivity.this, R.layout.view_issue, issuesDataList);
                mIssueList.setAdapter(issuesAdapter);
            }
        }
    };

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            CommentDialogFragment.newInstance(issuesDataList.get(position).getCommentsUrl()).show(getSupportFragmentManager(), "Dialog");
        }
    };

}
