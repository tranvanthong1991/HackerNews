package news.ycombinator.utils;

import com.android.volley.VolleyError;

public interface NotificationReponseListener {

    void requestStarted();

    void requestCompleted(Object object);

    void requestEndedWithError(VolleyError error);
}
