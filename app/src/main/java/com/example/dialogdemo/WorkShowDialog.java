package com.example.dialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

public class WorkShowDialog extends Dialog {

    private Context mContext;
    private DialogCallback callback;

    //  用来回调消息给原来的activity
    public interface DialogCallback {
        void callback(String text);
    }

    public WorkShowDialog(@NonNull Context context, DialogCallback callback) {
        super(context, R.style.BottomDialog);
        this.mContext = context;
        this.callback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_detail);
//        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        window.setWindowAnimations(R.style.BottomDialog_Animation);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.BOTTOM;
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = 800;
        window.setAttributes(attributes);
        setCancelable(false);

        EditText text = findViewById(R.id.text);

        //跳转activity
        Button confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = text.getText().toString();
                //EventBus用来给下一个activity传递消息
                EventBus.getDefault().postSticky(new MessageEvent(message));
                dismiss();

                Intent intent = new Intent();
                intent.setAction("Demo");
                mContext.startActivity(intent);
            }
        });

        //关闭回调
        Button cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = text.getText().toString();
                callback.callback(message);
                dismiss();
            }
        });
    }
}
