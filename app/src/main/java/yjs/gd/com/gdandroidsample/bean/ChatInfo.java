package yjs.gd.com.gdandroidsample.bean;

import android.graphics.Bitmap;

/**
 * Created by Jesson_Yi on 2017/7/12.
 */

public class ChatInfo {
    public String message;
    public Bitmap mIcon;
    public int Type;


    public ChatInfo() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Bitmap getmIcon() {
        return mIcon;
    }

    public void setmIcon(Bitmap mIcon) {
        this.mIcon = mIcon;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
