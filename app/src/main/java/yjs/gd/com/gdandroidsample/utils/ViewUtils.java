package yjs.gd.com.gdandroidsample.utils;

import android.content.Context;
import android.util.TypedValue;

import java.text.NumberFormat;

/**
 * Created by Jesson_Yi on 2017/7/5.
 */

public class ViewUtils {


    public static float dp2px(Context context, float dpValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    public static float sp2px(Context context,float spValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }


    public static NumberFormat mSizeFormat;
    static {
        mSizeFormat = NumberFormat.getInstance();
        mSizeFormat.setMaximumFractionDigits(1);
    }
    /**
     * return a string in human readable format
     *
     * @param bytes 单位 B
     */
    public static String getHumanReadableSize(long bytes) {
        if (bytes == 0) {
            return "0M";
        } else if (bytes < 1024) {
            return bytes + "B";
        } else if (bytes < 1048576) {
            return mSizeFormat.format(bytes / 1024f) + "KB";
        } else if (bytes < 1048576 * 1024) {
            return mSizeFormat.format(bytes / 1024f / 1024f) + "MB";
        } else {
            return mSizeFormat.format(bytes / 1024f / 1024f / 1024f) + "GB";
        }
    }
}
