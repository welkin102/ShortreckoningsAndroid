package rgp.com.shortreckonings.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hanks.library.AnimateCheckBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import rgp.com.shortreckonings.R;
import rgp.com.shortreckonings.ui.activity.HomeActivity;
import rgp.com.shortreckonings.ui.activity.SheetUpdateActivity;
import rgp.com.shortreckonings.ui.appconstants.DummyConstants;
import rgp.com.shortreckonings.ui.fragment.AddExpenseFragment;

/**
 * Created by khyagupt on 14-05-2016.
 */
public class PaidByDialog {

    private AddExpenseFragment addExpenseFragment;
    private PaidByListner listner;
    private Dialog mDialog;
    private Context c;


    public PaidByDialog(AddExpenseFragment addExpenseFragment, Context c) {
        this.addExpenseFragment = addExpenseFragment;
        this.c = c;
        this.listner = (PaidByListner) addExpenseFragment;
    }

    public void showDialog() {
        if (this.mDialog == null) {
            this.mDialog = new Dialog(this.c, R.style.CustomDialogTheme);
        }
        this.mDialog.setContentView(R.layout.dialog_paid_by);
        bindViews(this.mDialog);
        this.mDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        this.mDialog.show();


        initDialogButtons();
    }

    ArrayList<String> names = new ArrayList<>(Arrays.asList("Ned", "Jon", "La Plata", "Hodor", "Tony"));
    private String paidBy;



    private void bindViews(Dialog root) {
        final ListView listView = (ListView) root.findViewById(R.id.paid_by_list);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return names.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
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
                if(item.equals(paidBy)){
                    checkBox.setChecked(true);
                }else {
                    checkBox.setChecked(false); //has animation
                    checkBox.setUncheckStatus();
                }
                checkBox.setOnCheckedChangeListener(new AnimateCheckBox.OnCheckedChangeListener() {
                    @Override public void onCheckedChanged(View buttonView, boolean isChecked) {
                        paidBy = item;
                        listner.getPeople(paidBy);
                        dismissDialog();
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

    public interface PaidByListner {
        public void getPeople(String paidBy);
    }
}

