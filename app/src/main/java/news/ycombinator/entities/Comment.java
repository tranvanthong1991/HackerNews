package news.ycombinator.entities;

import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import news.ycombinator.utils.InputValidationUtil;

public class Comment extends BaseEntity {

    public static final String COMMENT_JSON_ID = "id";
    public static final String COMMENT_JSON_BY = "by";
    public static final String COMMENT_JSON_PARENT = "parent";
    public static final String COMMENT_JSON_TIME = "time";
    public static final String COMMENT_JSON_TEXT = "text";
    public static final String COMMENT_JSON_TYPE = "type";

    private int id;
    private String by;
    private int parent;
    private int time;
    private String text;
    private String type;

    public Comment() {
        this.id = 0;
        this.by = "";
        this.parent = 0;
        this.time = 0;
        this.text = "";
        this.type = "";
    }

    @Override
    public Object initWithAPIJsonData(JSONObject jsonItem) throws JSONException {

        Comment comment = new Comment();
        if(jsonItem.has(Comment.COMMENT_JSON_ID))
            comment.setId(InputValidationUtil.getInt(jsonItem.getString(Comment.COMMENT_JSON_ID)));
        if(jsonItem.has(Comment.COMMENT_JSON_BY))
            comment.setBy(jsonItem.getString(Comment.COMMENT_JSON_BY));
        if(jsonItem.has(Comment.COMMENT_JSON_PARENT))
            comment.setParent(InputValidationUtil.getInt(jsonItem.getString(Comment.COMMENT_JSON_PARENT)));
        if(jsonItem.has(Comment.COMMENT_JSON_TIME))
            comment.setTime(InputValidationUtil.getInt(jsonItem.getString(Comment.COMMENT_JSON_TIME)));
        if(jsonItem.has(Comment.COMMENT_JSON_TEXT))
            comment.setText(jsonItem.getString(Comment.COMMENT_JSON_TEXT));
        if(jsonItem.has(Comment.COMMENT_JSON_TYPE))
            comment.setType(jsonItem.getString(Comment.COMMENT_JSON_TYPE));

        return comment;
    }

    @Override
    public Object initWithCursorResultSet(Cursor cursor) {
        return super.initWithCursorResultSet(cursor);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
