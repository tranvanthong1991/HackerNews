package news.ycombinator.entities;

import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import news.ycombinator.utils.InputValidationUtil;
import news.ycombinator.utils.StringUtil;

public class Story extends BaseEntity {

    public static final String TOP_STORY_JSON_ID = "id";
    public static final String TOP_STORY_JSON_DESCENDANTS = "descendants";
    public static final String TOP_STORY_JSON_BY = "by";
    public static final String TOP_STORY_JSON_SCORE = "score";
    public static final String TOP_STORY_JSON_TIME = "time";
    public static final String TOP_STORY_JSON_TITLE = "title";
    public static final String TOP_STORY_JSON_TYPE = "type";
    public static final String TOP_STORY_JSON_URL = "url";

    private int id;
    private int descendants;
    private String by;
    private int score;
    private int time;
    private String title;
    private String type;
    private String url;

    public Story() {
        this.id = 0;
        this.descendants = 0;
        this.by = "";
        this.score = 0;
        this.time = 0;
        this.title = "";
        this.type = "";
        this.url = "";
    }

    @Override
    public Object initWithAPIJsonData(JSONObject jsonItem) throws JSONException {

        Story story = new Story();
        if(jsonItem.has(Story.TOP_STORY_JSON_ID))
            story.setId(InputValidationUtil.getInt(jsonItem.getString(Story.TOP_STORY_JSON_ID)));
        if(jsonItem.has(Story.TOP_STORY_JSON_DESCENDANTS))
            story.setDescendants(InputValidationUtil.getInt(jsonItem.getString(Story.TOP_STORY_JSON_DESCENDANTS)));
        if(jsonItem.has(Story.TOP_STORY_JSON_BY))
            story.setBy(jsonItem.getString(Story.TOP_STORY_JSON_BY));
        if(jsonItem.has(Story.TOP_STORY_JSON_SCORE))
            story.setScore(InputValidationUtil.getInt(jsonItem.getString(Story.TOP_STORY_JSON_SCORE)));
        if(jsonItem.has(Story.TOP_STORY_JSON_TIME))
            story.setTime(InputValidationUtil.getInt(jsonItem.getString(Story.TOP_STORY_JSON_TIME)));
        if(jsonItem.has(Story.TOP_STORY_JSON_TITLE))
            story.setTitle(jsonItem.getString(Story.TOP_STORY_JSON_TITLE));
        if(jsonItem.has(Story.TOP_STORY_JSON_TYPE))
            story.setType(jsonItem.getString(Story.TOP_STORY_JSON_TYPE));
        if(jsonItem.has(Story.TOP_STORY_JSON_URL))
            story.setUrl(jsonItem.getString(Story.TOP_STORY_JSON_URL));

        return story;
    }

    @Override
    public Object initWithCursorResultSet(Cursor cursor) {
        return super.initWithCursorResultSet(cursor);
    }

    public String getWebFromURL(String url) {
        String[] result = new String[] {};
        result = StringUtil.split(url,"//");
        result = StringUtil.split(result[result.length - 1],"/");
        return result[0];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDescendants() {
        return descendants;
    }

    public void setDescendants(int descendants) {
        this.descendants = descendants;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
