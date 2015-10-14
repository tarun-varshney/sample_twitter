package social.twittersample;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tarun Varshney on 10/15/2015.
 */
public class CommentDialogFragment extends DialogFragment {
    private static final String COMMENT_API = "comment_api";
    private TextView mCommentsTextView;
    private ProgressBar mProgressBar;

    public static CommentDialogFragment newInstance(String comment_api) {
        CommentDialogFragment commentDialogFragment = new CommentDialogFragment();
        Bundle args = new Bundle();
        args.putString(COMMENT_API, comment_api);
        commentDialogFragment.setArguments(args);
        return commentDialogFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_dialog, container, false);
        mCommentsTextView = (TextView) rootView.findViewById(R.id.comment_tv);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        String commentApi = getArguments().getString(COMMENT_API);
        getDialog().setTitle("Comments");
        fetchComments(commentApi);

        return rootView;
    }

    private void fetchComments(String commentApi) {
        NetworkRequestTask networkRequestTask = new NetworkRequestTask(commentsCallback);
        networkRequestTask.execute(commentApi);
    }

    CallbackWithObject commentsCallback = new CallbackWithObject() {
        @Override
        public void onProgress() {

        }

        @Override
        public void onResult(String result) {
            if (result != null) {
                String comments = "";

                ArrayList<CommentData> commentList = JsonParser.getCommentsList(result);

                mProgressBar.setVisibility(View.GONE);

                if (commentList.size() == 0) {
                    mCommentsTextView.setText(getString(R.string.no_comment));
                    return;
                }

                for (int i = 0; i < commentList.size(); i++) {
                    comments += commentList.get(i).getUserName() + " :\n" + commentList.get(i).getCommentBody() + "\n\n";
                }

                mCommentsTextView.setText(comments);
                mCommentsTextView.setMovementMethod(new ScrollingMovementMethod());
            } else
                mCommentsTextView.setText(getString(R.string.comment));

        }
    };
}
