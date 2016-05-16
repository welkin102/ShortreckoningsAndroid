package rgp.com.shortreckonings.ui.dialog;

import android.app.Dialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import rgp.com.shortreckonings.R;
import rgp.com.shortreckonings.model.Sheet;
import rgp.com.shortreckonings.ui.activity.HomeActivity;
import rgp.com.shortreckonings.ui.appconstants.DummyConstants;

/**
 * Created by khyagupt on 14-05-2016.
 */
public class AddSheetDialog {

    private HomeActivity mHomeActivity;
    private AddSheetListner listner;
    private Dialog mDialog;
    private TextView clickAdd, clickCancel;
    private EditText mSheetTitle;


    public AddSheetDialog(HomeActivity mHomeActivity) {
        this.mHomeActivity = mHomeActivity;
        this.listner = (AddSheetListner) mHomeActivity;
    }

    public void showDialog() {
        if (this.mDialog == null) {
            this.mDialog = new Dialog(this.mHomeActivity, R.style.CustomDialogTheme);
        }
        this.mDialog.setContentView(R.layout.dialog_add_sheet);
        this.mDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        this.mDialog.show();
        clickAdd = (TextView) this.mDialog.findViewById(R.id.add);
        clickCancel = (TextView) this.mDialog.findViewById(R.id.cancel);
        mSheetTitle = (EditText) this.mDialog.findViewById(R.id.title);

        clickCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissDialog();
            }
        });

        clickAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissDialog();
                listner.addSheet(mSheetTitle.getText().toString());
                DummyConstants.sheetsAdded.add(new Sheet(mSheetTitle.getText().toString()));
            }
        });

        initDialogButtons();
    }

    private void initDialogButtons() {
    }

    public void dismissDialog() {
        this.mDialog.dismiss();
    }

    public interface AddSheetListner {

        public void addSheet(String sheetTitle);
    }
}

