package razerdp.basepopup.extension.utils;

import android.os.Looper;

/**
 * Created by 大灯泡 on 2020/4/11.
 */
public class ToolUtils {
    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
