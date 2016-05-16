package rgp.com.shortreckonings.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import rgp.com.shortreckonings.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppTuteFragment extends Fragment {
    private static final String ARG_POSITION = "position";
    private ImageView image;
    private TextView title, detail;
    private int position;

    public static AppTuteFragment newInstance(int position) {
        AppTuteFragment f = new AppTuteFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.position = getArguments().getInt(ARG_POSITION);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pp_tute, container, false);
        image = (ImageView) root.findViewById(R.id.tute_image);
        title = (TextView) root.findViewById(R.id.title);
        switch (position) {
            case 0:
                image.setImageResource(R.drawable.sheets);
                title.setText(R.string.tute1);
                break;
            case 1:
                image.setImageResource(R.drawable.people);
                title.setText(R.string.tute2);
                break;
            case 2:
                image.setImageResource(R.drawable.expense);
                title.setText(R.string.tute3);
                break;
            case 3:
                image.setImageResource(R.drawable.calculator);
                title.setText(R.string.tute4);
                break;
        }
        ViewCompat.setElevation(root, 50.0f);
        return root;
    }

}
