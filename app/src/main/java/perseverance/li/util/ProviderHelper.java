package perseverance.li.util;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import perseverance.li.provider.SettingsProvider;

/**
 * ---------------------------------------------------------------
 * Author: Perseverance.Li
 * Create: 17-3-15 10:06
 * ---------------------------------------------------------------
 * Describe:
 * ---------------------------------------------------------------
 * Changes:
 * ---------------------------------------------------------------
 * 17-3-15 10 : Create by Perseverance.Li
 * ---------------------------------------------------------------
 */

public class ProviderHelper {

    public static final Uri SETTINGS_URL =
            Uri.parse("content://perseverance.li.provider.SettingsProvider");

    private static ProviderHelper mInstance;

    private ProviderHelper() {
    }

    private static synchronized void syncInit() {
        if (mInstance == null) {
            mInstance = new ProviderHelper();
        }
    }

    public static ProviderHelper getInstance() {
        if (mInstance == null) {
            syncInit();
        }
        return mInstance;
    }

    /**
     * 设置
     *
     * @param context
     * @param value
     * @return
     */
    public boolean setSettings(Context context, String value) {
        Bundle bundle = new Bundle();
        bundle.putString(SettingsProvider.SET_VALUE, value);
        bundle = context.getContentResolver().call(SETTINGS_URL, SettingsProvider.SET_METHOD, "", bundle);
        return bundle.getBoolean(SettingsProvider.SET_STATUS);
    }

    public String getSettings(Context context) {
        Bundle bundle = new Bundle();
        bundle = context.getContentResolver().call(SETTINGS_URL, SettingsProvider.GET_METHOD, "", bundle);
        return bundle.getString(SettingsProvider.GET_VALUE, "");
    }
}
