package news.ycombinator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import news.ycombinator.entities.Comment;
import news.ycombinator.entities.Story;
import news.ycombinator.hackernews.R;
import news.ycombinator.utils.TimeUtil;

public class CommentAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Comment> arrComment;

    public CommentAdapter(Context context, ArrayList<Comment> arrComment) {
        this.context = context;
        this.arrComment = arrComment;
    }

    @Override
    public int getCount() {
        return arrComment.size();
    }

    @Override
    public Object getItem(int i) {
        return arrComment.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder viewHolder;
        if(i%2 == 0) {
            if (view == null) {

                view = inflater.inflate(R.layout.story_detail_comment_item_1, viewGroup, false);

                viewHolder = new ViewHolder();
                viewHolder.txtStoryDetailCommentTextTime = (TextView) view.findViewById(R.id.txtStoryDetailCommentTextTime);
                viewHolder.txtStoryDetailCommentTextAuthor = (TextView) view.findViewById(R.id.txtStoryDetailCommentTextAuthor);
                viewHolder.txtStoryDetailCommentTextContent = (TextView) view.findViewById(R.id.txtStoryDetailCommentTextContent);
                view.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

        } else {
            if (view == null) {
                view = inflater.inflate(R.layout.story_detail_comment_item_2, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.txtStoryDetailCommentTextTime = (TextView) view.findViewById(R.id.txtStoryDetailCommentTextTime);
                viewHolder.txtStoryDetailCommentTextAuthor = (TextView) view.findViewById(R.id.txtStoryDetailCommentTextAuthor);
                viewHolder.txtStoryDetailCommentTextContent = (TextView) view.findViewById(R.id.txtStoryDetailCommentTextContent);
                view.setTag(viewHolder);

            }  else {
                viewHolder = (ViewHolder) view.getTag();
            }
        }

        Comment comment = new Comment();
        comment = arrComment.get(i);
        if(comment != null) {
            viewHolder.txtStoryDetailCommentTextTime.setText(TimeUtil.parseDate(String.valueOf(comment.getTime())) + " - ");
            viewHolder.txtStoryDetailCommentTextAuthor.setText(comment.getBy());
            viewHolder.txtStoryDetailCommentTextContent.setText(comment.getText());
        }

        return view;
    }

    private static class ViewHolder {
        TextView txtStoryDetailCommentTextTime;
        TextView txtStoryDetailCommentTextAuthor;
        TextView txtStoryDetailCommentTextContent;
    }
}

