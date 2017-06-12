package news.ycombinator.hackernews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import news.ycombinator.adapters.CommentAdapter;
import news.ycombinator.appheaders.AppHeaderkey;
import news.ycombinator.entities.Comment;
import news.ycombinator.entities.Story;
import news.ycombinator.models.CommentModel;
import news.ycombinator.utils.NotificationReponseListener;
import news.ycombinator.utils.TimeUtil;

public class StoryDetailActivity extends AppCompatActivity {

    private TextView txtStoryDetailTextTitle;
    private TextView txtStoryDetailTextLinkWeb;
    private TextView txtStoryDetailTextTime;
    private TextView txtStoryDetailTextAuthor;
    private TextView txtStoryDetailTextDescendants;
    private ListView lvComment;

    private CommentAdapter adapterComment;
    private ArrayList<Comment> arrComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_detail);

        Story story = new Story();
        Intent intent = this.getIntent();
        story.setId(intent.getExtras().getInt(Story.TOP_STORY_JSON_ID));
        story.setTitle(intent.getExtras().getString(Story.TOP_STORY_JSON_TITLE));
        story.setUrl(intent.getExtras().getString(Story.TOP_STORY_JSON_URL));
        story.setTime(intent.getExtras().getInt(Story.TOP_STORY_JSON_TIME));
        story.setBy(intent.getExtras().getString(Story.TOP_STORY_JSON_BY));
        story.setDescendants(intent.getExtras().getInt(Story.TOP_STORY_JSON_DESCENDANTS));

        txtStoryDetailTextTitle = (TextView) findViewById(R.id.txtStoryDetailTextTitle);
        txtStoryDetailTextLinkWeb = (TextView) findViewById(R.id.txtStoryDetailTextLinkWeb);
        txtStoryDetailTextTime = (TextView) findViewById(R.id.txtStoryDetailTextTime);
        txtStoryDetailTextAuthor = (TextView) findViewById(R.id.txtStoryDetailTextAuthor);
        txtStoryDetailTextDescendants = (TextView) findViewById(R.id.txtStoryDetailTextDescendants);
        lvComment = (ListView) findViewById(R.id.lvStoryDetailComment);

        arrComment = new ArrayList<Comment>();

        txtStoryDetailTextTitle.setText(story.getTitle());
        txtStoryDetailTextLinkWeb.setText(story.getUrl());
        txtStoryDetailTextDescendants.setText(story.getDescendants() + " Comments");
        txtStoryDetailTextTime.setText(TimeUtil.parseDate(String.valueOf(story.getTime())) + " - ");
        txtStoryDetailTextAuthor.setText(story.getBy());

        new Thread(new Runnable() {

            @Override
            public void run() {

                CommentModel commentModel = new CommentModel(StoryDetailActivity.this);
                commentModel.setOnRequestListener(new NotificationReponseListener() {

                    @Override
                    public void requestStarted() {
                    }

                    @Override
                    public void requestEndedWithError(VolleyError error) {

                    }

                    @Override
                    public void requestCompleted(Object object) {

                        try {
                            JSONObject jsonObject = (JSONObject) object;
                            if (jsonObject == null) {

                            } else {
                                Comment comment = new Comment();
                                comment = (Comment) jsonObject.get(AppHeaderkey.COMMENT);
                                arrComment.add(comment);
                                arrComment.add(comment);

                                adapterComment = new CommentAdapter(getBaseContext(), arrComment);
                                lvComment.setAdapter(adapterComment);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });
                commentModel.apiGetComment(getBaseContext());
            }
        }).start();
    }
}
