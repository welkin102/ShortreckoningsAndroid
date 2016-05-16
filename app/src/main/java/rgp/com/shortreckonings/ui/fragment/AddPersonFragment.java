package rgp.com.shortreckonings.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import rgp.com.shortreckonings.R;
import rgp.com.shortreckonings.model.Person;
import rgp.com.shortreckonings.ui.activity.SheetUpdateActivity;
import rgp.com.shortreckonings.ui.appconstants.DummyConstants;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPersonFragment extends Fragment implements View.OnClickListener{


    public AddPersonFragment() {
        // Required empty public constructor
    }
    private AddPersonFragListner listner;
    private EditText name, nickname, comment;
    private TextView addMore, ok, cancel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listner = (AddPersonFragListner) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_person, container, false);

        name = (EditText) root.findViewById(R.id.name);
        nickname = (EditText) root.findViewById(R.id.nickname);
        comment = (EditText) root.findViewById(R.id.comment);
        cancel = (TextView) root.findViewById(R.id.cancel);
        ok = (TextView) root.findViewById(R.id.add);
        addMore = (TextView) root.findViewById(R.id.add_more);
        cancel.setOnClickListener(this);
        ok.setOnClickListener(this);
        addMore.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                listner.onCancel(this.getClass().getName());
                break;
            case R.id.add:
                DummyConstants.peropleAdded.add(new Person(name.getText().toString(),
                        nickname.getText().toString(), comment.getText().toString()));
                listner.onCancel(this.getClass().getName());
                break;
            case R.id.add_more:
                DummyConstants.peropleAdded.add(new Person(name.getText().toString(),
                        nickname.getText().toString(), comment.getText().toString()));
                Toast.makeText(getContext(), name.getText().toString()+ " added", Toast.LENGTH_SHORT).show();
                name.requestFocus();
                name.setText("");
                nickname.setText("");
                comment.setText("");
                listner.onReset();
                break;
        }
    }



    public interface AddPersonFragListner {
        void onCancel(String tag);
        void onReset();
    }

}
