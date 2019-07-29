package zsy.wrapper.dialog;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;

public abstract class AbsDg<T extends AbsDg> implements IDg<T> {


    protected FragmentActivity fragmentActivity;
    private AlertDialog.Builder builder;
    protected View view;

    public AbsDg(@NonNull FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    private T get() {
        return (T) this;
    }

    protected AlertDialog.Builder getBuilder() {
        if (builder == null) {
            builder = new AlertDialog.Builder(fragmentActivity);
        }
        return builder;
    }

    @Override
    public T setTitle(String title) {
        getBuilder().setTitle(title);
        return get();
    }

    @Override
    public T setIcon(Drawable icon) {
        getBuilder().setIcon(icon);
        return get();
    }

    @Override
    public T setMessage(String message) {
        getBuilder().setMessage(message);
        return get();
    }

    @Override
    public T setCancelable(boolean cancelable) {
        getBuilder().setCancelable(cancelable);
        return get();
    }


    @Override
    public T setPositiveButton(CharSequence text, final OnClickListener listener) {
        getBuilder().setPositiveButton(text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClick(get(), which);
            }
        });
        return get();
    }

    @Override
    public T setNegativeButton(CharSequence text, final OnClickListener listener) {
        getBuilder().setNegativeButton(text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClick(get(), which);
            }
        });
        return get();
    }

    @Override
    public T setNeutralButton(CharSequence text, final OnClickListener listener) {
        getBuilder().setNeutralButton(text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClick(get(), which);
            }
        });
        return get();
    }

    @Override
    public T setView(View view) {
        this.view = view;
        getBuilder().setView(view);
        return get();
    }

}
