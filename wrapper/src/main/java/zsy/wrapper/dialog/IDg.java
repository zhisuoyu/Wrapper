package zsy.wrapper.dialog;

import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.View;

public interface IDg<T extends IDg> {


    T setTitle(String title);

    T setIcon(Drawable icon);

    T setMessage(String message);

    T setCancelable(boolean cancelable);


    T setPositiveButton(CharSequence text, IDg.OnClickListener listener);

    T setNegativeButton(CharSequence text, IDg.OnClickListener listener);

    T setNeutralButton(CharSequence text, IDg.OnClickListener listener);

    T setView(View view);

    T setOnKeyListener(OnKeyListener listener);

    boolean isShowing();

    /**
     * The identifier for the positive button.
     */
    int BUTTON_POSITIVE = -1;

    /**
     * The identifier for the negative button.
     */
    int BUTTON_NEGATIVE = -2;

    /**
     * The identifier for the neutral button.
     */
    int BUTTON_NEUTRAL = -3;


    void show();

    /**
     * Cancels the dialog, invoking the {@link OnCancelListener}.
     * <p>
     * The {@link OnDismissListener} may also be called if cancellation
     * dismisses the dialog.
     */
    void cancel();

    /**
     * Dismisses the dialog, invoking the {@link OnDismissListener}.
     */
    void dismiss();

    /**
     * Interface used to allow the creator of a dialog to run some code when the
     * dialog is canceled.
     * <p>
     * This will only be called when the dialog is canceled, if the creator
     * needs to know when it is dismissed in general, use
     * {@link IDg.OnDismissListener}.
     */
    interface OnCancelListener {
        /**
         * This method will be invoked when the dialog is canceled.
         *
         * @param dialog the dialog that was canceled will be passed into the
         *               method
         */
        void onCancel(IDg dialog);
    }

    /**
     * Interface used to allow the creator of a dialog to run some code when the
     * dialog is dismissed.
     */
    interface OnDismissListener {
        /**
         * This method will be invoked when the dialog is dismissed.
         *
         * @param dialog the dialog that was dismissed will be passed into the
         *               method
         */
        void onDismiss(IDg dialog);
    }

    /**
     * Interface used to allow the creator of a dialog to run some code when the
     * dialog is shown.
     */
    interface OnShowListener {
        /**
         * This method will be invoked when the dialog is shown.
         *
         * @param dialog the dialog that was shown will be passed into the
         *               method
         */
        void onShow(IDg dialog);
    }

    /**
     * Interface used to allow the creator of a dialog to run some code when an
     * item on the dialog is clicked.
     */
    interface OnClickListener {
        /**
         * This method will be invoked when a button in the dialog is clicked.
         *
         * @param dialog the dialog that received the click
         * @param which  the button that was clicked (ex.
         *               {@link IDg#BUTTON_POSITIVE}) or the position
         *               of the item clicked
         */
        void onClick(IDg dialog, int which);
    }

    /**
     * Interface used to allow the creator of a dialog to run some code when an
     * item in a multi-choice dialog is clicked.
     */
    interface OnMultiChoiceClickListener {
        /**
         * This method will be invoked when an item in the dialog is clicked.
         *
         * @param dialog    the dialog where the selection was made
         * @param which     the position of the item in the list that was clicked
         * @param isChecked {@code true} if the click checked the item, else
         *                  {@code false}
         */
        void onClick(IDg dialog, int which, boolean isChecked);
    }

    /**
     * Interface definition for a callback to be invoked when a key event is
     * dispatched to this dialog. The callback will be invoked before the key
     * event is given to the dialog.
     */
    interface OnKeyListener {
        /**
         * Called when a key is dispatched to a dialog. This allows listeners to
         * get a chance to respond before the dialog.
         *
         * @param dialog  the dialog the key has been dispatched to
         * @param keyCode the code for the physical key that was pressed
         * @param event   the KeyEvent object containing full information about
         *                the event
         * @return {@code true} if the listener has consumed the event,
         * {@code false} otherwise
         */
        boolean onKey(IDg dialog, int keyCode, KeyEvent event);
    }
}