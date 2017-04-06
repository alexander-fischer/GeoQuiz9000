package no.ntnu.tdt4240.geoquiz9000.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import no.ntnu.tdt4240.geoquiz9000.R;

public class PictureDialogFragment extends DialogFragment {

    public static final String NR_OF_QUESTION = "nr of question";
    public static final String IMAGE_PATH = "image path";

    private int mQuestionNr;
    private String mImagePath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mQuestionNr = getArguments().getInt(NR_OF_QUESTION);
        mImagePath = getArguments().getString(IMAGE_PATH);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Typeface textFont = UiUtils.getTextFont(getContext());

        View v = inflater.inflate(R.layout.picture_dialog, container, false);

        String titleText = getString(R.string.question_title1) + " " + String.valueOf(mQuestionNr + 1);

        TextView title = (TextView) v.findViewById(R.id.title);
        title.setTypeface(textFont);
        title.setText(titleText);

        TextView question = (TextView) v.findViewById(R.id.question);
        question.setTypeface(textFont);

        TextView callToAction = (TextView) v.findViewById(R.id.call_to_action);
        callToAction.setTypeface(textFont);

        ImageView iv = (ImageView) v.findViewById(R.id.question_picture);
        Bitmap pic = BitmapFactory.decodeFile(mImagePath);
        iv.setImageBitmap(pic);

        Button answerButton = (Button) v.findViewById(R.id.answer_button);
        answerButton.setTypeface(textFont);
        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return v;
    }
}