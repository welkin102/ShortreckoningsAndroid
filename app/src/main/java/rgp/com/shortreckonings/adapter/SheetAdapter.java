package rgp.com.shortreckonings.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import rgp.com.shortreckonings.model.Sheet;
import rgp.com.shortreckonings.ui.activity.SheetUpdateActivity;

/**
 * Created by khyagupt on 14-05-2016.
 */
public class SheetAdapter extends RecyclerView.Adapter<SheetAdapter.Sheet_VH> {

    private LayoutInflater inflater;
    List<Sheet> serviceItems = Collections.emptyList();
    Context context;

    public SheetAdapter(Context context , List<Sheet> serviceItems) {
        inflater = LayoutInflater.from(context);
        this.serviceItems = serviceItems;
        this.context = context;
    }

    @Override
    public Sheet_VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_sheet, parent, false);
        return new Sheet_VH(view);
    }

    @Override
    public void onBindViewHolder(Sheet_VH holder, int position) {
        Sheet currentItem = serviceItems.get(position);
        holder.title.setText(currentItem.title);
        holder.date.setText(currentItem.date);
        holder.people.setText("Shred with "+currentItem.people);
        if(currentItem.amount>0) {
            holder.amount.setTextColor(context.getResources().getColor(R.color.get_green));
            holder.amount.setText(currentItem.amount+ " you get");
        } else if (currentItem.amount <0){
            holder.amount.setTextColor(context.getResources().getColor(R.color.debt_red));
            holder.amount.setText((currentItem.amount * -1)+" you owe");
        } else holder.amount.setText(currentItem.amount+"");

    }

    @Override
    public int getItemCount() {
        return serviceItems.size();
    }

    class Sheet_VH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, date, people, amount;
        ImageView edit;

        public Sheet_VH(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            date = (TextView) itemView.findViewById(R.id.date);
            people = (TextView) itemView.findViewById(R.id.people);
            amount = (TextView) itemView.findViewById(R.id.amount);
            edit = (ImageView) itemView.findViewById(R.id.edit);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SheetUpdateActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    context.startActivity(intent);
//                    ((Activity)context).finish();
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Edit sheet detail", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
