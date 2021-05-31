package razerdp.basepopup.extension.demo.app;

import android.app.Application;
import android.content.Context;

import com.pgyersdk.crash.PgyCrashManager;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by 大灯泡 on 2018/12/4.
 */
public class PopupExtensionDemoApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        BasePopupWindow.setDebugMode(true);
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        PgyCrashManager.register();
    }
}
