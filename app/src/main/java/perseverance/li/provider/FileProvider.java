package perseverance.li.provider;

import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;

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

public class FileProvider extends BaseProvider {

    //TODO:这里针对应用中的某些文件需要对外提供，测试外部应用可传递所需文件名称，然后通过openFile来读取文件。
    //TODO:对外提供的文件可以是sdcard下也可以是/data/data/youer_pacakgename/xx下的文件

    @Nullable
    @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        if (uri == null) {
            throw new FileNotFoundException("uri is null");
        }
        //TODO：这里以sdcard下的文件为例
        String logoPath = Environment.getExternalStorageDirectory().getPath();
        String fileName = uri.getEncodedPath();
        if (TextUtils.isEmpty(logoPath) || TextUtils.isEmpty(fileName)) {
            throw new FileNotFoundException("logo path or file name is null");
        }
        if (fileName.startsWith("/") || fileName.startsWith("\\")) {
            fileName = fileName.substring(1);
        }
        if (TextUtils.isEmpty(fileName)) {
            throw new FileNotFoundException("file name is null");
        }
        String filePath = logoPath.concat("/").concat(fileName);
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        }
        return ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
    }
}
