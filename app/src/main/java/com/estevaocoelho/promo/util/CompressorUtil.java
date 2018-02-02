package com.estevaocoelho.promo.util;

import android.net.Uri;

import java.io.File;

import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnCompressListener;

/**
 * Created by estevao on 06/10/17.
 */

public class CompressorUtil {
    public static void compress(String uri, final CompressListener compressListener) {
        Luban.compress(ApplicationUtil.getContext(), new File(uri.replace("file://", "")))
                .putGear(Luban.THIRD_GEAR)
                .launch(new OnCompressListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        compressListener.onCompressSuccess(file);

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    public static void compress(File file, final CompressListener compressListener) {
        Luban.compress(ApplicationUtil.getContext(), file)
                .putGear(Luban.THIRD_GEAR)
                .launch(new OnCompressListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        compressListener.onCompressSuccess(file);

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    public static void compress(Uri uri, final CompressListener compressListener) {
        Luban.compress(ApplicationUtil.getContext(), new File(uri.getPath()))
                .putGear(Luban.THIRD_GEAR)
                .launch(new OnCompressListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        compressListener.onCompressSuccess(file);

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }


    public interface CompressListener {
        void onCompressSuccess(File file);
    }
}
