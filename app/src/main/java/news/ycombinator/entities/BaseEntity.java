package news.ycombinator.entities;

import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseEntity {

    public Object initWithAPIJsonData(JSONObject jsonItem) throws JSONException {
        return null;
    }

    public Object initWithCursorResultSet(Cursor cursor) {
        return null;
    }

}
