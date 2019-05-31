package com.precious.ocr;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;

public class SettingsDialog extends AppCompatDialogFragment {

    private SeekBar mPitchSeekbar, mSpeedSeekbar;
    private DialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Settings")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        float pitch = (float) mPitchSeekbar.getProgress() / 50;
                        float speed = (float) mSpeedSeekbar.getProgress() / 50;

                        listener.applySettings(pitch, speed);
                    }
                });

        mPitchSeekbar = view.findViewById(R.id.seekbarPitch);
        mSpeedSeekbar = view.findViewById(R.id.seekbarSpeed);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement listener");
        }
    }

    public interface DialogListener{
        void applySettings(float pitch, float speed);
    }
}
