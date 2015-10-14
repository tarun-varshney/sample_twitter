package social.twittersample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tarun Varshney on 10/14/2015.
 */
public class IssuesAdapter extends ArrayAdapter<IssuesData> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<IssuesData> issuesDataList;

    public IssuesAdapter(Context context, int layoutResourceId, ArrayList<IssuesData> issuesDataList) {
        super(context, layoutResourceId, issuesDataList);

        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.issuesDataList = issuesDataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.issueTitle = (TextView) row.findViewById(R.id.issue_title);
            holder.issueBody = (TextView) row.findViewById(R.id.issue_body);
            holder.noOfComments = (TextView) row.findViewById(R.id.comment_tv);
            holder.updatedAt = (TextView) row.findViewById(R.id.updated_tv);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        IssuesData issuesData = issuesDataList.get(position);
        holder.issueTitle.setText(issuesData.getIssueTitle());
        holder.issueBody.setText(issuesData.getIssueBody());
        holder.updatedAt.setText(context.getString(R.string.updated_at,issuesData.getUpdateAt()));
        holder.noOfComments.setText(context.getString(R.string.comment, issuesData.getNoOfComments()));

        return row;
    }

    static class ViewHolder {
        TextView issueTitle;
        TextView issueBody;
        TextView noOfComments;
        TextView updatedAt;
    }
}