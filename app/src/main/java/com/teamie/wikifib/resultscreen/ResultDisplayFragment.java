package com.teamie.wikifib.resultscreen;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.teamie.wikifib.R;
import com.teamie.wikifib.bean.GameData;
import com.teamie.wikifib.bean.MyWord;
import com.teamie.wikifib.playscreen.PlayFragment;
import com.teamie.wikifib.playscreen.PlayModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.teamie.wikifib.utils.Constants.BLANK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultDisplayFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static String TAG = "ResultDisplayFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private GameData gameData;
    @BindView(R.id.text)
    TextView score;
    @BindView(R.id.content)
    TextView content;
    Unbinder unbinder;
    @BindView(R.id.submit)
    Button submit;
    @BindColor(R.color.dark_green)
    int darkGreen;


    public ResultDisplayFragment() {
        // Required empty public constructor
    }

    public static ResultDisplayFragment newInstance(GameData param1) {
        ResultDisplayFragment fragment = new ResultDisplayFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            gameData = getArguments().getParcelable(ARG_PARAM1);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_blank2, container, false);
        unbinder = ButterKnife.bind(this, viewGroup);
        submit.setOnClickListener(this);
        if (savedInstanceState != null && savedInstanceState.getParcelable(getString(R.string.state)) != null) {
            gameData = savedInstanceState.getParcelable(getString(R.string.state));
        }
        display(viewGroup);
        return viewGroup;
    }

    private void display(ViewGroup viewGroup) {

        int count = 0;
        List<Spannable> spannableList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String s = gameData.getTextViewHashMap().get(i);
            Spannable wordtoSpan = new SpannableString(s);
            MyWord userSelectedWord = gameData.getUserSelectedWords().get(i);
            String correctWord = gameData.getEditTextHashMap().get(i);

            int i1 = s.indexOf(BLANK, 0);
            if (correctWord.equals(userSelectedWord.getText())) {
                count++;

                SpannableStringBuilder ssb = new SpannableStringBuilder();
                ssb.append(wordtoSpan);
                ssb.replace(i1, i1 + 7, correctWord);
                ssb.setSpan(new ForegroundColorSpan(Color.GREEN), userSelectedWord.getStart(), userSelectedWord.getEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                wordtoSpan = SpannableString.valueOf(ssb);

            } else {
                SpannableStringBuilder ssb = new SpannableStringBuilder();
                String[] split = s.split(BLANK);
                if (split.length == 1) {
                    if (i1 == 0) {
                        ssb.append(userSelectedWord.getText());
                        ssb.setSpan(new StrikethroughSpan(), 0, userSelectedWord.getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ssb.setSpan(new ForegroundColorSpan(Color.RED), 0, userSelectedWord.getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ssb.append(" " + correctWord);
                        int offset = userSelectedWord.getText().length() + 2;
                        ssb.setSpan(new ForegroundColorSpan(darkGreen), offset, offset + correctWord.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ssb.append(split[0]);
                    } else {
                        ssb.append(split[0]);
                        ssb.append(userSelectedWord.getText());
                        int offset = split[0].length();
                        ssb.setSpan(new StrikethroughSpan(), offset, offset + userSelectedWord.getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ssb.setSpan(new ForegroundColorSpan(Color.RED), offset, offset + userSelectedWord.getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ssb.append(" " + correctWord);
                        offset = offset + userSelectedWord.getText().length() + 2;
                        ssb.setSpan(new ForegroundColorSpan(darkGreen), offset, offset + correctWord.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                } else {
                    ssb.append(split[0]);
                    ssb.append(userSelectedWord.getText());
                    int offset = split[0].length();
                    ssb.setSpan(new StrikethroughSpan(), offset, offset + userSelectedWord.getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssb.setSpan(new ForegroundColorSpan(Color.RED), offset, offset + userSelectedWord.getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssb.append(correctWord);
                    offset = offset + userSelectedWord.getText().length();
                    ssb.setSpan(new ForegroundColorSpan(darkGreen), offset, offset + correctWord.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssb.append(split[1]);
                    wordtoSpan = SpannableString.valueOf(ssb);
                }

            }
            spannableList.add(wordtoSpan);


        }

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (Spannable spannable : spannableList) {
            spannableStringBuilder.append(spannable);
        }
        SpannableString spannableString = SpannableString.valueOf(spannableStringBuilder);
        PlayModel.getInstance().setLevel(count);
        score.setText(String.format(getString(R.string.score), count));

        if (count == 10)
            submit.setText(getString(R.string.next));
        else {
            submit.setText(getString(R.string.replay));
        }

        content.setText(spannableString, TextView.BufferType.SPANNABLE);
    }

    @Override
    public void onClick(View view) {

        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(PlayFragment.TAG);
        if (fragment == null)
            fragment = PlayFragment.getInstance();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, PlayFragment.TAG).commit();
    }
}
