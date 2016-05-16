package rgp.com.shortreckonings.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rgp.com.shortreckonings.R;
import rgp.com.shortreckonings.adapter.FinalSplitsAdapter;
import rgp.com.shortreckonings.model.Expense;
import rgp.com.shortreckonings.model.FinalSplit;
import rgp.com.shortreckonings.ui.appconstants.DummyConstants;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalculateFragment extends Fragment {

    private RecyclerView recyclerView;
    private FinalSplitsAdapter splitsAdapter;
    private RelativeLayout layout;
    private TextView totalAmount, debtAmount, desAmount, desDebt;


    public CalculateFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_calculate, container, false);

        totalAmount = (TextView) root.findViewById(R.id.total_amount);
        desAmount = (TextView) root.findViewById(R.id.des_amount);
        debtAmount = (TextView) root.findViewById(R.id.stats);
        desDebt = (TextView) root.findViewById(R.id.des_stat);
        recyclerView = (RecyclerView) root.findViewById(R.id.final_split_list);
        layout = (RelativeLayout) root.findViewById(R.id.relative);
        if(DummyConstants.showExchange) {
            showDataOnWindow();
        } else {
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDataOnWindow();
                    DummyConstants.showExchange = true;
                }
            });
        }

        return root;
    }

    private void showDataOnWindow(){
        totalAmount.setVisibility(View.VISIBLE);
        desAmount.setVisibility(View.VISIBLE);
        desDebt.setVisibility(View.VISIBLE);
        debtAmount.setVisibility(View.VISIBLE);
        layout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        initRecyclerView();
    }

    private void initRecyclerView() {
        splitsAdapter = new FinalSplitsAdapter(getActivity(), getAllSplits());

        recyclerView.setAdapter(splitsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private List<FinalSplit> getAllSplits() {
        List<FinalSplit> splits = new ArrayList<>();
        double[] amounts = {123, 2532, 931,61, 39, 132, 391,711,11,234,27,743,345};
        String[] pays = {"Ned","Tyrion","Dragon","Arya","Tim","Tyrion","Dragon","Arya","Tim","Ned","Tyrion","Ronn","Akshay"};
        String[] takes = {"Jon","Drake","Mountain","Hodor","Jon", "Tyrion","Dragon","Arya", "Joffry","Drake","Mountain","Hodor","Jon"};

        for (int i=0 ; i < amounts.length ; i++ ) {
            FinalSplit e = new FinalSplit();
            e.amount = amounts[i];
            e.personGives = pays[i];
            e.personTakes = takes[i];

            splits.add(e);
        }
        return splits;
    }

    @Override
    public void onResume() {
        super.onResume();
//        listenr.hideAFB();
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
