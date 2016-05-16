package rgp.com.shortreckonings.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import rgp.com.shortreckonings.R;
import rgp.com.shortreckonings.model.Expense;
import rgp.com.shortreckonings.ui.appconstants.DummyConstants;
import rgp.com.shortreckonings.ui.dialog.PaidByDialog;
import rgp.com.shortreckonings.ui.dialog.PaidForDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddExpenseFragment extends Fragment implements View.OnClickListener, PaidByDialog.PaidByListner, PaidForDialog.PaidForListner{


    public AddExpenseFragment() {
        // Required empty public constructor
    }
    private AddExpenseFragListner listner;
    private EditText title, amount;
    private CardView paidBy, paidFor;
    private TextView addMore, add, cancel, paidByPerson;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listner = (AddExpenseFragListner) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_expense, container, false);
        title = (EditText) root.findViewById(R.id.title);
        amount = (EditText) root.findViewById(R.id.amount);
        paidBy = (CardView) root.findViewById(R.id.card_view);
        paidFor = (CardView) root.findViewById(R.id.card_involved);
        cancel = (TextView) root.findViewById(R.id.cancel);
        paidByPerson = (TextView) root.findViewById(R.id.textView);
        addMore = (TextView) root.findViewById(R.id.add_more);
        add = (TextView) root.findViewById(R.id.add);

        add.setOnClickListener(this);
        addMore.setOnClickListener(this);
        cancel.setOnClickListener(this);
        paidBy.setOnClickListener(this);
        paidFor.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                listner.onCancel(this.getClass().getName());
                break;
            case R.id.card_view:
                new PaidByDialog(this, getActivity()).showDialog();
                break;
            case R.id.card_involved:
                new PaidForDialog(this, getActivity()).showDialog();
                break;
            case R.id.add_more:
                DummyConstants.expenses.add(new Expense(title.getText().toString(),paidByPerson.getText().toString(),Double.parseDouble(amount.getText().toString())));
                Toast.makeText(getContext(), "Expense added " + title.getText().toString(), Toast.LENGTH_SHORT).show();
                title.requestFocus();
                title.setText("");
                amount.setText("");
                break;
            case R.id.add:
                DummyConstants.expenses.add(new Expense(title.getText().toString(),paidByPerson.getText().toString(),Double.parseDouble(amount.getText().toString())));
                Toast.makeText(getContext(), "Expense added " + title.getText().toString(), Toast.LENGTH_SHORT).show();
                listner.onCancel(this.getClass().getName());
                break;
        }
    }

    @Override
    public void getPeople(String people) {
        Toast.makeText(getActivity(), "paid by " + people, Toast.LENGTH_SHORT).show();
        paidByPerson.setText(people);

    }

    @Override
    public void getPeopleInDebt(String people) {
        Toast.makeText(getActivity(), "People to pay for selected", Toast.LENGTH_SHORT).show();

    }

    public interface AddExpenseFragListner {
        void onCancel(String tag);
//        void onDone();
//        void onAddMorePerson(String name, String nickname, String comment);
    }

}
