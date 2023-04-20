package com.nils.nilsscreenshots;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;


public class FloatingWidgetService extends Service {

    private WindowManager mWindowManager;
    private View mFloatingWidget;

    @Override
    public void onCreate() {
        super.onCreate();

        // Erstelle einen neuen Floating Widget View
        mFloatingWidget = new View(this);
        mFloatingWidget.setBackgroundColor(Color.RED);

        // Legen Sie die Layout-Parameter für das Widget fest
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        // Initialisiere den WindowManager
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // Fügen Sie das Widget dem WindowManager hinzu
        mWindowManager.addView(mFloatingWidget, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Entferne das Widget, wenn der Service zerstört wird
        if (mFloatingWidget != null) {
            mWindowManager.removeView(mFloatingWidget);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

