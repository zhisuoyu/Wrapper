package zsy.wrapper.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import zsy.wrapper.R;


public class Dg implements IDg<Dg> {

    private static IDgCreator iDgCreator = new IDgCreator() {
        @NonNull
        @Override
        public IDg create(Context context) {
            return new AlertDg(context);
        }
    };

    private IDg iDg;

    public static void setIDgCreator(@NonNull IDgCreator iDgCreator) {
        Dg.iDgCreator = iDgCreator;
    }

    public Dg(Context context) {
        iDg = iDgCreator.create(context);
    }

    //
//    public static Dg newInstance(Context context) {
//        Dg dg = new Dg();
//        dg.iDg =iDgCreator.create(context);
//        return dg;
//    }
//
    public Dg setTitle(String title) {
        iDg.setTitle(title);
        return this;
    }

    public Dg setIcon(Drawable icon) {
        iDg.setIcon(icon);
        return this;
    }

    public Dg setMessage(String message) {
        iDg.setMessage(message);
        return this;
    }

    public Dg setCancelable(boolean cancelable) {
        iDg.setCancelable(cancelable);
        return this;
    }

    public Dg setPositiveButton(CharSequence text, OnClickListener listener) {
        iDg.setPositiveButton(text, listener);
        return this;
    }

    public Dg setNegativeButton(CharSequence text, OnClickListener listener) {
        iDg.setNegativeButton(text, listener);
        return this;
    }

    @Override
    public Dg setNeutralButton(CharSequence text, OnClickListener listener) {
        iDg.setNeutralButton(text, listener);
        return this;
    }

    @Override
    public Dg setView(View view) {
        iDg.setView(view);
        return this;
    }

    @Override
    public Dg setOnKeyListener(OnKeyListener listener) {
        iDg.setOnKeyListener(listener);
        return this;
    }

    @Override
    public boolean isShowing() {
        return iDg.isShowing();
    }

    @Override
    public void show() {
        iDg.show();
    }

    @Override
    public void cancel() {
        iDg.cancel();
    }

    @Override
    public void dismiss() {
        iDg.dismiss();
    }


    public static Dg showConfirmDg(Context context, String message, String positiveText, OnClickListener positiveListener, String negativeText, OnClickListener negativeListener) {
        Dg dg = new Dg(context)
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton(positiveText, positiveListener)
                .setNegativeButton(negativeText, negativeListener);
        dg.show();
        return dg;
    }

    public static Dg showLoadDg(Context context, String des,OnKeyListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_load, null);
        TextView tv = view.findViewById(R.id.tv);
        tv.setText(des);
        Dg dg = new Dg(context)
                .setCancelable(false)
                .setView(view)
                .setOnKeyListener(listener);
        dg.show();
        return dg;
    }

//

//    public static class Builder {
//
//        private AbsDg absDg;
//
//        public Builder(Context context) {
//            absDg = iDgCreator.create(context);
//        }
//
//        public Builder setTitle(String title) {
//            absDg.setTitle(title);
//            return this;
//        }
//
//
//        public Builder setIcon(Drawable icon) {
//            absDg.setIcon(icon);
//            return this;
//        }
//
//
//        public Builder setMessage(String message) {
//            absDg.setMessage(message);
//            return this;
//        }
//
//
//        public Builder setPositiveButton(CharSequence text, Dg.OnClickListener listener) {
//            absDg.setPositiveButton(text, listener);
//            return this;
//        }
//
//
//        public Builder setNegativeButton(CharSequence text, Dg.OnClickListener listener) {
//            setNeutralButton(text, listener);
//            return this;
//        }
//
//
//        public Builder setNeutralButton(CharSequence text, Dg.OnClickListener listener) {
//            setNeutralButton(text, listener);
//            return this;
//        }
//
//
//        public Builder setCancelable(boolean cancelable) {
//            setCancelable(cancelable);
//            return this;
//        }
//
//        public Dg build() {
//            return new Dg(this);
//        }
//    }
}
