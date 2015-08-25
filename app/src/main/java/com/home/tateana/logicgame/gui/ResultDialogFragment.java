package com.home.tateana.logicgame.gui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.home.tateana.logicgame.R;


/**
 * sea links
 * https://gist.github.com/yaraki/11345093
 * https://github.com/codepath/android_guides/wiki/Using-DialogFragment
 *
 * interaction with activity
 * http://developer.android.com/reference/android/app/DialogFragment.html
 *
 */
public class ResultDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private static final String STATUS = "status";
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_FAILED = 2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param status int
     * @return A new instance of fragment ResultDialogFragment.
     */
    public static ResultDialogFragment newInstance(int status) {
        ResultDialogFragment fragment = new ResultDialogFragment();
        Bundle args = new Bundle();
        args.putInt(STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    public ResultDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int status = getArguments().getInt(STATUS);

        //ContextThemeWrapper context = new ContextThemeWrapper(getActivity(), R.style.MyDialog);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity(), R.style.MyDialog);
        if(status == STATUS_FAILED) {
            alertDialogBuilder.setTitle(R.string.result_dialog_failed_title);
        } else {
            alertDialogBuilder.setTitle(R.string.result_dialog_success_title);
        }

        alertDialogBuilder.setNeutralButton(R.string.ok, this);
        return alertDialogBuilder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
    }



}
