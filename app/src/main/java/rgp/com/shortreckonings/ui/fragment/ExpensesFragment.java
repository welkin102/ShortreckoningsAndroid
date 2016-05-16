package rgp.com.shortreckonings.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import rgp.com.shortreckonings.R;
import rgp.com.shortreckonings.adapter.ExpensesAdapter;
import rgp.com.shortreckonings.adapter.PersonAdapter;
import rgp.com.shortreckonings.model.Expense;
import rgp.com.shortreckonings.model.Person;
import rgp.com.shortreckonings.ui.appconstants.DummyConstants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpensesFragment extends Fragment {

    private RecyclerView recyclerView;
    private ExpensesAdapter expensesAdapter;
    private RelativeLayout layout;


    public ExpensesFragment() {
        // Required empty public constructor
    }

//    private AddExpenseListenr listenr;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        listenr = (AddExpenseListenr) getActivity();
    }
    @Override
    public void onResume() {
        super.onResume();
//        listenr.showAFB();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_expenses, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.expense_list);
        layout = (RelativeLayout) root.findViewById(R.id.relative);
        if(DummyConstants.showExpenses) {
            layout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            initRecyclerView();
        } else {
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    initRecyclerView();
                    DummyConstants.showExpenses = true;
                }
            });
        }
        return root;
    }

    private void initRecyclerView() {
        expensesAdapter = new ExpensesAdapter(getActivity(), getAllExpenses());
        recyclerView.setAdapter(expensesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        double[] amounts = {123.00, 2132.50, 931.43,61.00, 39.61};
        String[] titles = {"Breakfast","Lunch at Taj","Water park Ride","3D movie at bay","Night out at PU"};
        String[] paidBy = {"Jon","Titan","Rob","Jimm","Bradd"};

        for (int i=0 ; i < titles.length ; i++ ) {
            Expense e = new Expense();
            e.title = titles[i];
            e.amount = amounts[i];
            e.paidBy = paidBy[i];
            expenses.add(e);
        }
        for(Expense e: DummyConstants.expenses){
            expenses.add(e);
        }
        return expenses;
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
