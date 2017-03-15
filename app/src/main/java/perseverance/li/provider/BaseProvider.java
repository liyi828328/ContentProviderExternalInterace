package perseverance.li.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

/**
 * ---------------------------------------------------------------
 * Author: Perseverance.Li
 * Create: 17-3-15 10:00
 * ---------------------------------------------------------------
 * Describe:
 * ---------------------------------------------------------------
 * Changes:
 * ---------------------------------------------------------------
 * 17-3-15 10 : Create by Perseverance.Li
 * ---------------------------------------------------------------
 */

public class BaseProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return null;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }

    /**
     * 校验Bundle的合法性
     *
     * @param extras
     */
    protected void checkBundle(Bundle extras) {
        if (extras == null) {
            throw new IllegalArgumentException("Bundle is null , please check call bundle");
        }
    }

    /**
     * 校验方法的合法性
     *
     * @param method
     */
    protected void checkMethod(String method) {
        if (TextUtils.isEmpty(method)) {
            throw new IllegalArgumentException("Method is null , please check call method");
        }
    }
}
