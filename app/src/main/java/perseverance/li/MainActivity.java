package perseverance.li;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import perseverance.li.util.ProviderHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mLogoView;
    private EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mEdit = (EditText) findViewById(R.id.setting_edit);
        mLogoView = (ImageView) findViewById(R.id.logo_view);
        findViewById(R.id.open_file).setOnClickListener(this);
        findViewById(R.id.set_value).setOnClickListener(this);
        findViewById(R.id.get_value).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.open_file) {
            OpenFileTask openFileTask = new OpenFileTask();
            openFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else if (id == R.id.set_value) {
            boolean status = ProviderHelper.getInstance().setSettings(this, mEdit.getText().toString());
            Toast.makeText(this, "status : " + status, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.get_value) {
            String getValue = ProviderHelper.getInstance().getSettings(this);
            Toast.makeText(this, "get value : " + getValue, Toast.LENGTH_SHORT).show();
        }
    }

    private class OpenFileTask extends AsyncTask<Void, Void, Bitmap> {

        //TODO:这里是放到sdcard下的一个图片
        public final Uri LOGO_URI =
                Uri.parse("content://perseverance.li.provider.FileProvider/test.jpeg");

        @Override
        protected Bitmap doInBackground(Void... params) {
            InputStream is = null;
            try {
                is = getContentResolver().openInputStream(LOGO_URI);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                mLogoView.setImageBitmap(bitmap);
            }
        }
    }
}
