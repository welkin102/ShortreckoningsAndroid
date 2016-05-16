package rgp.com.shortreckonings.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import rgp.com.shortreckonings.R;
import rgp.com.shortreckonings.model.FinalSplit;

/**
 * Created by khyagupt on 14-05-2016.
 */
public class FinalSplitsAdapter extends RecyclerView.Adapter<FinalSplitsAdapter.CustomVH> {

    private LayoutInflater inflater;
    List<FinalSplit> items = Collections.emptyList();
    Context context;

    public FinalSplitsAdapter(Context context , List<FinalSplit> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
        this.context = context;
    }

    @Override
    public CustomVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_fianl_split, parent, false);
        return new CustomVH(view);
    }

    @Override
    public void onBindViewHolder(CustomVH holder, int position) {
        FinalSplit currentItem = items.get(position);
        holder.personGives.setText(currentItem.personGives);
        holder.personTakes.setText(currentItem.personTakes);
        holder.amount.setText(currentItem.amount+"");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class CustomVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView personGives, personTakes, amount;
        ImageView edit;

        public CustomVH(View itemView) {
            super(itemView);
            personGives = (TextView) itemView.findViewById(R.id.personpays);
            personTakes = (TextView) itemView.findViewById(R.id.persontakes);
            amount = (TextView) itemView.findViewById(R.id.amount);
//            edit = (ImageView) itemView.findViewById(R.id.edit);
//
//            edit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context, "Edit here", Toast.LENGTH_SHORT).show();
//                }
//            });
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            Toast.makeText(context, "Settle payment "+ position, Toast.LENGTH_SHORT).show();
        }
    }
}
