package com.stremio.vlcvideo;

import android.app.Application;
import android.view.View;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import org.videolan.libvlc.LibVLC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class VLCVideoPackage implements ReactPackage {

    private static final ArrayList<String> VLC_OPTIONS = new ArrayList<>(Arrays.asList("--no-audio-time-stretch", "--avcodec-skip-frame", "--avcodec-skip-idct=2", "--stats", "--http-reconnect", "--aout=android_audiotrack", "--spdif"));

    private LibVLC mLibVLC;

    public VLCVideoPackage() {}

    @Override
    public List<NativeModule> createNativeModules(final ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(final ReactApplicationContext reactApplicationContext) {

        if(mLibVLC == null) {
            mLibVLC = new LibVLC(reactApplicationContext, VLC_OPTIONS);
        }

        return Arrays.<ViewManager>asList(new VLCVideoViewManager(null, mLibVLC, null));
    }

}
