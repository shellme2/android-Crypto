package com.eebbk.bfc.demo.crypto.util;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

    public static void copyFromAssets(Context context,File file){
        AssetManager am=context.getAssets();
        InputStream in=null;
        OutputStream out=null;
        try {
            in=am.open("test.jpg");
            out=new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            while ((in.read(buffer)) != -1) {
                out.write(buffer);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public static String getPathFormSelector(@NonNull Context context, @NonNull Uri uri) {
//        String filePath = null;
//
//        if ("content".equalsIgnoreCase(uri.getScheme())) {
//            String[] projection = { "_data" };
//            Cursor cursor = null;
//            try {
//                cursor = context.getContentResolver().query(uri, projection,null, null, null);
//                assert cursor != null;
//                int column_index = cursor.getColumnIndexOrThrow("_data");
//                if (cursor.moveToFirst()) {
//                    filePath =cursor.getString(column_index);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                Log.i("liuyewu", "getPathFormSelector==" + e.getMessage());
//            } finally{
//                if(null != cursor){
//                    cursor.close();
//                }
//            }
//        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
//            filePath = uri.getPath();
//        }
//
//        return filePath;
//    }

    /***
     * 从Uri中获取文件的路径
     * @return 文件在本地的路径; 如果文件并不在本地,返回 null
     */
    public static String getPathFormSelector(@NonNull Context context, @NonNull Uri uri) {
        if (null == uri) return null;

        if (Build.VERSION.SDK_INT > 19 && DocumentsContract.isDocumentUri( context, uri )){
            final String authority = uri.getAuthority();
            String docId = DocumentsContract.getDocumentId(uri);

            if ("com.android.providers.media.documents".equals( authority )) {
                final String[] split = docId.split(":");
                final String type = split[0];
                final String id = split[1];

                final String selection = MediaStore.Images.Media._ID + "=" + id;

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                return getDataColumn( context, contentUri, null, null );
            } else if ("com.android.providers.downloads.documents".equals( authority )) {
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf( docId ) );

                return getDataColumn(context, contentUri, null, null);
            }else if ( "com.android.externalstorage.documents".equals( authority ) ){
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }else {
                    // TODO: 2016/12/7 获取不是主磁盘的文件夹, 还需要确定一下, 没找到明确的文档, 目前可以用
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

            }
        }

        final String scheme = uri.getScheme();
        if (scheme == null) {
            return uri.getPath();
        }
        if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            return uri.getPath();
        }

        String data = null;
        if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

}
