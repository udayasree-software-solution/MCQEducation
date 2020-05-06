package com.udayasreesoftwaresolution.mcqeducation.progressutils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.SpriteFactory;
import com.github.ybq.android.spinkit.Style;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.udayasreesoftwaresolution.mcqeducation.R;

public class USProgressBar extends Dialog {

    private Context context;
    private SpinKitView spinKitView;
    private TextView descriptionText;
    private TextView titleText;
    private Style style;
    private Sprite spriteDrawable;

    public USProgressBar(@NonNull Context context) {
        super(context);
        setContentView(R.layout.progressbar);

        this.context = context;
        initView();
    }

    private void initView() {
        try {
            View view = getWindow().getDecorView();
            view.setBackgroundResource(android.R.color.transparent);

            spinKitView = findViewById(R.id.progress_bar_spinkit_id);
            titleText = findViewById(R.id.progress_bar_loading_id);
            descriptionText = findViewById(R.id.progress_bar_description_id);

            setCancelable(false);
            setCanceledOnTouchOutside(false);

            style = Style.values()[ProgressStyle.WAVE];
            setSpriteDrawable();
            spinKitView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
            spinKitView.setColor(ContextCompat.getColor(context, R.color.white));

            titleText.setVisibility(View.GONE);
            descriptionText.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDialog() {
        if (!isShowing()) {
            setSpriteDrawable();
            show();
        }
    }

    public void dismissDialog() {
        if (isShowing()) {
            stopSprite();
            dismiss();
        }
    }

    public void showDescription(boolean isVisible) {
        descriptionText.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    public void setDescriptionText(int description) {
        showDescription(true);
        descriptionText.setText(description);
    }

    public void showTitle(boolean isVisible) {
        titleText.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    public void setTitleText(int title) {
        showTitle(true);
        titleText.setText(title);
    }

    public void setProgressColor(int color) {
        spinKitView.setColor(color);
        spinKitView.invalidate();
    }

    public void setProgressStyle(int style) {
        this.style = Style.values()[style];
        setSpriteDrawable();
    }

    private void setSpriteDrawable() {
        spriteDrawable = SpriteFactory.create(style);
        spinKitView.setIndeterminateDrawable(spriteDrawable);
    }

    private void stopSprite() {
        if (spriteDrawable != null) {
            spriteDrawable.stop();
        }
    }

}
