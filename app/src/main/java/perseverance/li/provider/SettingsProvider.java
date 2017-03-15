package perseverance.li.provider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings.System;
import android.support.annotation.Nullable;

/**
 * ---------------------------------------------------------------
 * Author: Perseverance.Li
 * Create: 17-3-15 10:11
 * ---------------------------------------------------------------
 * Describe:
 * ---------------------------------------------------------------
 * Changes:
 * ---------------------------------------------------------------
 * 17-3-15 10 : Create by Perseverance.Li
 * ---------------------------------------------------------------
 */

public class SettingsProvider extends BaseProvider {

    private static final String KEY = "setting_key";
    public static final String SET_METHOD = "set_method";
    public static final String GET_METHOD = "get_method";
    public static final String SET_VALUE = "set_value_key";
    public static final String GET_VALUE = "get_value_key";
    public static final String SET_STATUS = "set_status_key";
    //这里用变量来临时存储
    private static String value = "";

    @Nullable
    @Override
    public Bundle call(String method, String arg, Bundle extras) {

        checkBundle(extras);
        checkMethod(method);

        if (SET_METHOD.equals(method)) {
            value = extras.getString(SET_VALUE);
            extras.putBoolean(SET_STATUS, true);
            return extras;
        } else if (GET_METHOD.equals(method)) {
            extras.putString(GET_VALUE, value);
            return extras;
        }
        return super.call(method, arg, extras);
    }
}
