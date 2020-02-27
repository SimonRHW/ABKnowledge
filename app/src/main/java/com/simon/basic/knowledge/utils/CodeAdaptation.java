package com.simon.basic.knowledge.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Build;
import android.provider.MediaStore;

/**
 * @author Simon
 * @date 2020/2/25 18:03
 * Desc ：
 */
public class CodeAdaptation {

    public Context mContext;

    /**
     * @Deprecated public boolean clipPath(@NonNull Path path, @NonNull Region.Op op) {
     * checkValidClipOp(op);
     * return nClipPath(mNativeCanvasWrapper, path.readOnlyNI(), op.nativeInt);
     * }
     * <p>
     * private static void checkValidClipOp(@NonNull Region.Op op) {
     * if (sCompatiblityVersion >= Build.VERSION_CODES.P
     * && op != Region.Op.INTERSECT && op != Region.Op.DIFFERENCE) {
     * throw new IllegalArgumentException(
     * "Invalid Region.Op - only INTERSECT and DIFFERENCE are allowed");
     * }
     * }
     * <p>
     * <p>
     * if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
     * canvas.clipPath(path);
     * } else {
     * canvas.clipPath(path, Region.Op.XOR);// REPLACE、UNION 等
     * }
     * 哈哈
     */

    /* 哈哈*/
    //用Path.op代替，先运算Path，再给canvas.clipPath
    public void clipPath() {
        Canvas canvas = new Canvas();
        Path path = new Path();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Path mPathXOR = new Path();
            mPathXOR.moveTo(0, 0);
            mPathXOR.lineTo(100, 0);
            mPathXOR.lineTo(100, 100);
            mPathXOR.lineTo(0, 100);
            mPathXOR.close();
            //以上根据实际的Canvas或View的大小，画出相同大小的Path即可
            mPathXOR.op(path, Path.Op.XOR);
            canvas.clipPath(mPathXOR);
        } else {
            canvas.clipPath(path, Region.Op.XOR);
        }
    }

    private static final String[] IMAGE_PROJECTION = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

    public void media() {
        Cursor imageCursor = mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                IMAGE_PROJECTION, null, null, IMAGE_PROJECTION[4] + " DESC");
        String path = null;
        if (imageCursor != null) {
            path = imageCursor.getString(imageCursor.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
            String name = imageCursor.getString(imageCursor.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
            int id = imageCursor.getInt(imageCursor.getColumnIndexOrThrow(IMAGE_PROJECTION[2]));
            String folderPath = imageCursor.getString(imageCursor.getColumnIndexOrThrow(IMAGE_PROJECTION[3]));
            String folderName = imageCursor.getString(imageCursor.getColumnIndexOrThrow(IMAGE_PROJECTION[4]));
            //Android Q 公有目录只能通过Content Uri + id的方式访问，以前的File路径全部无效，如果是Video，记得换成MediaStore.Videos
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                path = MediaStore.Images.Media
                        .EXTERNAL_CONTENT_URI
                        .buildUpon()
                        .appendPath(String.valueOf(id)).build().toString();
            }
            imageCursor.close();
        }
    }
}


