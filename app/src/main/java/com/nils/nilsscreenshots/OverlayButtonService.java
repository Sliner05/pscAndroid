package com.nils.nilsscreenshots;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;


public class OverlayButtonService extends Service {
    private WindowManager windowManager;
    private Button overlayButton;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Erstelle einen WindowManager, um das Fenster-Overlay anzuzeigen
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // Erstelle den Button und positioniere ihn
        overlayButton = new Button(this);
        overlayButton.setText("Button");
        overlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click events
            }
        });

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.START;
        params.x = 0;
        params.y = 100;

        // Füge den Button dem WindowManager hinzu
        windowManager.addView(overlayButton, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Entferne den Button, wenn der Service zerstört wird
        if (overlayButton != null) {
            windowManager.removeView(overlayButton);
        }
    }
}

