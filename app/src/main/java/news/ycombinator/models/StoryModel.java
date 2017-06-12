package news.ycombinator.models;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import news.ycombinator.appheaders.AppHeaderUrl;
import news.ycombinator.appheaders.AppHeaderkey;
import news.ycombinator.entities.Story;
import news.ycombinator.utils.NotificationReponseListener;

public class StoryModel {

    private Context context;

    public static NotificationReponseListener notificationReponseListener;

    public void setOnRequestListener(NotificationReponseListener notificationReponseListener) {
        this.notificationReponseListener = notificationReponseListener;
    }

    public StoryModel(Context _context) {
        this.context = _context;
    }

    private static JSONObject parseJsonDetail(JSONObject jsonObject) throws JSONException {

        Story story = new Story();
        story = (Story) story.initWithAPIJsonData(jsonObject);

        JSONObject jsonResult = new JSONObject();
        jsonResult.put(AppHeaderkey.STORY, story);

        return jsonResult;
    }

    public static void apiGetTopStory(Context context) {
        notificationReponseListener.requestStarted();

        final String URL = AppHeaderUrl.URL_ROOT_API + AppHeaderUrl.URL_STORY;

        StringRequest stringRequest = new StringRequest( Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONObject jsonResult = new JSONObject();
                            jsonResult = parseJsonDetail(jsonObject);

                            notificationReponseListener.requestCompleted(jsonResult);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        notificationReponseListener.requestEndedWithError(error);
                    }
                }) {

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                return super.parseNetworkResponse(response);
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=utf-8");

                return headers;
            }

        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }
}

