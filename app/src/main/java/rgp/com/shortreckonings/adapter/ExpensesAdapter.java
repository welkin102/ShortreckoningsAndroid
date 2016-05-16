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
import rgp.com.shortreckonings.model.Expense;

/**
 * Created by khyagupt on 14-05-2016.
 */
public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.CustomVH> {

    private LayoutInflater inflater;
    List<Expense> items = Collections.emptyList();
    Context context;

    public ExpensesAdapter(Context context , List<Expense> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
        this.context = context;
    }

    @Override
    public CustomVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_expense, parent, false);
        return new CustomVH(view);
    }

    @Override
    public void onBindViewHolder(CustomVH holder, int position) {
        Expense currentItem = items.get(position);
        holder.title.setText(currentItem.title);
        holder.paidBy.setText("Paid by "+currentItem.paidBy + " on 25/07/2016");
//        holder.dateCreated.setText(currentItem.dateCreated);
        holder.amount.setText(currentItem.amount+"");
//        holder.peopleInvolved.setText(currentItem.peopleInvolved);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class CustomVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, amount, paidBy;
//        TextView paidBy, dateCreated,  peopleInvolved;
        ImageView edit;

        public CustomVH(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            paidBy = (TextView) itemView.findViewById(R.id.paidBy);
//            dateCreated = (TextView) itemView.findViewById(R.id.people);
            amount = (TextView) itemView.findViewById(R.id.amount);
//            peopleInvolved = (TextView) itemView.findViewById(R.id.people);
            edit = (ImageView) itemView.findViewById(R.id.edit);
            
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Edit expense", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            Expense e = items.get(position);
            Toast.makeText(context, "Edit expense: "+ e.title, Toast.LENGTH_SHORT).show();
        }



    }
}
