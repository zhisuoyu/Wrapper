package zsy.wrapper.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;

public class AlertDg implements IDg<AlertDg> {


    private static final boolean isMaskTransparent = false;

    private AlertDialog.Builder builder;
    private boolean isCustomView = false;
    private AlertDialog dialog;

    public AlertDg(@NonNull Context context) {
        builder = new AlertDialog.Builder(context);
    }

    @Override
    public AlertDg setTitle(String title) {
        builder.setTitle(title);
        return this;
    }

    @Override
    public AlertDg setIcon(Drawable icon) {
        builder.setIcon(icon);
        return this;
    }

    @Override
    public AlertDg setMessage(String message) {
        builder.setMessage(message);
        return this;
    }

    @Override
    public AlertDg setCancelable(boolean cancelable) {
        builder.setCancelable(cancelable);
        return this;
    }


    @Override
    public AlertDg setPositiveButton(CharSequence text, final OnClickListener listener) {
        builder.setPositiveButton(text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClick(AlertDg.this, which);
            }
        });
        return this;
    }

    @Override
    public AlertDg setNegativeButton(CharSequence text, final OnClickListener listener) {
        builder.setNegativeButton(text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClick(AlertDg.this, which);
            }
        });
        return this;
    }

    @Override
    public AlertDg setNeutralButton(CharSequence text, final OnClickListener listener) {
        builder.setNeutralButton(text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClick(AlertDg.this, which);
            }
        });
        return this;
    }

    @Override
    public AlertDg setView(View view) {
        builder.setView(view);
        isCustomView = true;
        return this;
    }

    @Override
    public AlertDg setOnKeyListener(final OnKeyListener listener) {
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return listener.onKey(AlertDg.this, keyCode, event);
            }
        });
        return this;
    }

    @Override
    public boolean isShowing() {
        return dialog != null && dialog.isShowing();
    }


    @Override
    public void show() {
        if (dialog == null) {
            dialog = builder.create();
        }
        dialog.show();
        if (isCustomView) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        }
        if (isMaskTransparent) {
            dialog.getWindow().setDimAmount(0);
        }
    }

    @Override
    public void cancel() {
        if (dialog != null) {
            dialog.cancel();
        }
    }

    @Override
    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
