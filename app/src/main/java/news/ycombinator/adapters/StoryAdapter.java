package news.ycombinator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import news.ycombinator.entities.Story;
import news.ycombinator.hackernews.R;
import news.ycombinator.utils.TimeUtil;

public class StoryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Story> arrStory;

    public StoryAdapter(Context context, ArrayList<Story> arrStory) {
        this.context = context;
        this.arrStory = arrStory;
    }

    @Override
    public int getCount() {
        return arrStory.size();
    }

    @Override
    public Object getItem(int i) {
        return arrStory.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.story_item, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.txtStoryTextSTT = (TextView) view.findViewById(R.id.txtStoryTextSTT);
            viewHolder.txtStoryTextScore = (TextView) view.findViewById(R.id.txtStoryTextScore);
            viewHolder.txtStoryTextTitle = (TextView) view.findViewById(R.id.txtStoryTextTitle);
            viewHolder.txtStoryTextLinkWeb = (TextView) view.findViewById(R.id.txtStoryTextLinkWeb);
            viewHolder.txtStoryTextTimeAndAuthor = (TextView) view.findViewById(R.id.txtStoryTextTimeAndAuthor);
            viewHolder.txtStoryTextDescendants = (TextView) view.findViewById(R.id.txtStoryTextDescendants);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Story story = new Story();
        story = arrStory.get(i);
        if(story != null) {
            viewHolder.txtStoryTextSTT.setText(String.valueOf(i + 1));
            viewHolder.txtStoryTextTitle.setText(story.getTitle());
            viewHolder.txtStoryTextLinkWeb.setText(story.getWebFromURL(story.getUrl()));
            viewHolder.txtStoryTextTimeAndAuthor.setText(TimeUtil.parseDate(String.valueOf(story.getTime())) + " - " + story.getBy());
            viewHolder.txtStoryTextScore.setText("+" + String.valueOf(story.getScore()));
            viewHolder.txtStoryTextDescendants.setText(String.valueOf(story.getDescendants()));
        }

        return view;
    }

    private static class ViewHolder {
        TextView txtStoryTextSTT;
        TextView txtStoryTextScore;
        TextView txtStoryTextTitle;
        TextView txtStoryTextLinkWeb;
        TextView txtStoryTextTimeAndAuthor;
        TextView txtStoryTextDescendants;
    }
}
