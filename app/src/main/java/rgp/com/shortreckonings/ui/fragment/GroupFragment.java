package rgp.com.shortreckonings.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rgp.com.shortreckonings.R;
import rgp.com.shortreckonings.adapter.PersonAdapter;
import rgp.com.shortreckonings.model.Person;
import rgp.com.shortreckonings.ui.appconstants.DummyConstants;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends Fragment {

    private RecyclerView recyclerView;
    private PersonAdapter personAdapter;
    private RelativeLayout layout;

    public GroupFragment() {
        // Required empty public constructor
    }

    private AddPersonListenr listenr;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        listenr = (AddPersonListenr) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_group, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.people_list);
        layout = (RelativeLayout) root.findViewById(R.id.relative);

        // This is to get quick feel of empty and filled-data fragment
        if(DummyConstants.showPeople) {
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
                    DummyConstants.showPeople = true;
                }
            });
        }

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
//        listenr.showAFB();
    }

    private void initRecyclerView() {
        personAdapter = new PersonAdapter(getActivity(), getAllPerson());
        recyclerView.setAdapter(personAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private List<Person> getAllPerson() {
        List<Person> group = new ArrayList<>();
        String[] comments = {"Comment goes here for this one", " ", "Never give up on not paying", "Party head", "Never bother about money, literally!"};
        String[] names = {"Jon Snow", "Arya Stark", "Ned Stark", "Tyrion Lanister","Khalisi Mother of dragon",};
        String[] nicknames = {"Jon","Arya","Ned","Tyrion","Khalisi"};

        for (int i=0 ; i < names.length ; i++ ) {
            Person s = new Person();
            s.name = names[i];
            s.nickname = nicknames[i];
            s.comment = comments[i];

            group.add(s);
        }
        for (Person p : DummyConstants.peropleAdded){
            group.add(p);
        }
        return group;
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public interface AddPersonListenr {
        public void method();
        public void showAFB();
    }

}
