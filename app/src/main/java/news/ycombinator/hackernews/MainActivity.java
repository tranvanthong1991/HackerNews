package news.ycombinator.hackernews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import news.ycombinator.adapters.StoryAdapter;
import news.ycombinator.appheaders.AppHeaderkey;
import news.ycombinator.entities.Story;
import news.ycombinator.models.StoryModel;
import news.ycombinator.utils.NotificationReponseListener;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Story> arrStory;

    private ListView lvStory;
    private StoryAdapter adapterStory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvStory = (ListView) findViewById(R.id.lvStory);

        arrStory = new ArrayList<Story>();

        new Thread(new Runnable() {

            @Override
            public void run() {

                StoryModel storyModel = new StoryModel(MainActivity.this);
                storyModel.setOnRequestListener(new NotificationReponseListener() {

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
                                Story story = new Story();
                                story = (Story) jsonObject.get(AppHeaderkey.STORY);
                                arrStory.add(story);
                                arrStory.add(story);

                                adapterStory = new StoryAdapter(getBaseContext(), arrStory);
                                lvStory.setAdapter(adapterStory);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });
                storyModel.apiGetTopStory(getBaseContext());
            }
        }).start();

        lvStory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, StoryDetailActivity.class);
                intent.putExtra(Story.TOP_STORY_JSON_ID, arrStory.get(i).getId());
                intent.putExtra(Story.TOP_STORY_JSON_TITLE, arrStory.get(i).getTitle());
                intent.putExtra(Story.TOP_STORY_JSON_URL, arrStory.get(i).getUrl());
                intent.putExtra(Story.TOP_STORY_JSON_TIME, arrStory.get(i).getTime());
                intent.putExtra(Story.TOP_STORY_JSON_BY, arrStory.get(i).getBy());
                intent.putExtra(Story.TOP_STORY_JSON_DESCENDANTS, arrStory.get(i).getDescendants());
                startActivity(intent);
            }
        });
    }


}
