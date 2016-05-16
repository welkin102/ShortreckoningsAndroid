package rgp.com.shortreckonings.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hanks.library.AnimateCheckBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import rgp.com.shortreckonings.R;
import rgp.com.shortreckonings.ui.fragment.AddExpenseFragment;

/**
 * Created by khyagupt on 14-05-2016.
 */
public class PaidForDialog implements View.OnClickListener {

    private AddExpenseFragment addExpenseFragment;
    private PaidForListner listner;
    private Dialog mDialog;
    private TextView clickOk, splitEven, splituneven;
    private Context c;


    public PaidForDialog(AddExpenseFragment addExpenseFragment, Context c) {
        this.addExpenseFragment = addExpenseFragment;
        this.c = c;
        this.listner = (PaidForListner) addExpenseFragment;
    }

    public void showDialog() {
        if (this.mDialog == null) {
            this.mDialog = new Dialog(this.c, R.style.CustomDialogTheme);
        }
        this.mDialog.setContentView(R.layout.dialog_paid_for);
        bindViews(this.mDialog);
        this.mDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        this.mDialog.show();
        clickOk = (TextView) this.mDialog.findViewById(R.id.ok);
        splitEven = (TextView) this.mDialog.findViewById(R.id.even_text);
        splituneven = (TextView) this.mDialog.findViewById(R.id.uneven_text);
        clickOk.setOnClickListener(this);
        splitEven.setOnClickListener(this);
        splituneven.setOnClickListener(this);

        initDialogButtons();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.even_text:
                selectEven();
                break;
            case R.id.uneven_text:
                selectUneven();
                break;
            case R.id.ok:
                dismissDialog();
                break;
        }
    }

    private void selectUneven() {
        splituneven.setBackgroundColor(c.getResources().getColor(R.color.appblue));
        splituneven.setTextColor(c.getResources().getColor(R.color.colorPrimary));
        splitEven.setBackgroundColor(c.getResources().getColor(R.color.transparent));
        splitEven.setTextColor(c.getResources().getColor(R.color.gray1));
    }

    private void selectEven() {
        splitEven.setBackgroundColor(c.getResources().getColor(R.color.appblue));
        splitEven.setTextColor(c.getResources().getColor(R.color.colorPrimary));
        splituneven.setBackgroundColor(c.getResources().getColor(R.color.transparent));
        splituneven.setTextColor(c.getResources().getColor(R.color.gray1));
    }

    ArrayList<String> names = new ArrayList<>(Arrays.asList("Ned", "Jon", "La Plata", "Hodor", "Tony"));
    private Set<String> checkedSet = new HashSet<>();

    private void bindViews(Dialog root) {
        ListView listView = (ListView) root.findViewById(R.id.paid_for_list);
        listView.setAdapter(new BaseAdapter() {
            @Override public int getCount() {
                return names.size();
            }

            @Override public Object getItem(int position) {
                return null;
            }

            @Override public long getItemId(int position) {
                return 0;
            }

            @Override public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.checkbox_paidby, parent, false);
                }

                TextView text = (TextView) convertView.findViewById(R.id.text);
                final AnimateCheckBox checkBox = (AnimateCheckBox) convertView.findViewById(R.id.checkbox);

                final String item = names.get(position);
                text.setText(item);
                if(checkedSet.contains(item)){
                    checkBox.setChecked(true);
                }else {
                    checkBox.setChecked(false); //has animation
                    checkBox.setUncheckStatus();
                }
                checkBox.setOnCheckedChangeListener(new AnimateCheckBox.OnCheckedChangeListener() {
                    @Override public void onCheckedChanged(View buttonView, boolean isChecked) {
                        if (isChecked) {
                            checkedSet.add(item);
                        } else {
                            checkedSet.remove(item);
                        }
                    }
                });

                return convertView;
            }
        });
    }

    private void initDialogButtons() {
    }

    public void dismissDialog() {
        this.mDialog.dismiss();
    }


    public interface PaidForListner {
        public void getPeopleInDebt(String people);
    }
}

