package rgp.com.shortreckonings.ui.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rgp.com.shortreckonings.R;
import rgp.com.shortreckonings.adapter.SheetAdapter;
import rgp.com.shortreckonings.model.Sheet;
import rgp.com.shortreckonings.ui.appconstants.DummyConstants;
import rgp.com.shortreckonings.ui.dialog.AddSheetDialog;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, AddSheetDialog.AddSheetListner {

    private Toolbar toolbar;
    private FloatingActionButton mFab;
    private RecyclerView recyclerView;
    private SheetAdapter sheetAdapter;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = (RecyclerView) findViewById(R.id.sheets_list);
        layout = (RelativeLayout) findViewById(R.id.relative);
        if (DummyConstants.showSheets) {
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
                    DummyConstants.showSheets = true;
                }
            });
        }
        mFab.setOnClickListener(this);
        setSupportActionBar(toolbar);
    }

    private void initRecyclerView() {
        sheetAdapter = new SheetAdapter(this, getSheets());
        recyclerView.setAdapter(sheetAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    List<Sheet> sheets = new ArrayList<>();
    String[] titles = {"Expense sheet 1", "Picnic with neighbours expenses", "Aliya birthday party expenses", "Convocation party kharcha", "Cook and food expenses"};
    String[] people = {"Jon, arya, ned, hodor", "Riya, Manoj, hodor", "Barty, cercie, tyrion, harry, everyone", "Hodor, hodor, hodor, cercie, tyrion, harry", "Hodor, hodor,cercie, tyrion, harry, hodor",};
    String[] dates = {"25/06/2017", "25/06/2017", "25/06/2017", "25/06/2017", "25/06/2017"};
    double[] amount = {00.00, 932.67, -234.98, 367, -0.823};

    private List<Sheet> getSheets() {
        for (int i = 0; i < titles.length; i++) {
            Sheet s = new Sheet();
            s.title = titles[i];
            s.date = dates[i];
            s.people = people[i];
            s.amount = amount[i];
            sheets.add(s);
        }
        return sheets;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.filter) {
            Toast.makeText(HomeActivity.this, "Filter Sheets", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                new AddSheetDialog(this).showDialog();
                break;
        }
    }

    @Override
    public void addSheet(String sheetTitle) {
        Toast.makeText(HomeActivity.this, sheetTitle, Toast.LENGTH_SHORT).show();
        Sheet s = new Sheet(sheetTitle);
        sheets.add(s);
        sheetAdapter.notifyItemChanged(sheets.size() - 1);
    }
}
