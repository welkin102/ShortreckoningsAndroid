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
import rgp.com.shortreckonings.model.Person;
import rgp.com.shortreckonings.model.Person;
import rgp.com.shortreckonings.ui.activity.SheetUpdateActivity;

/**
 * Created by khyagupt on 14-05-2016.
 */
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.CustomVH> {

    private LayoutInflater inflater;
    List<Person> items = Collections.emptyList();
    Context context;

    public PersonAdapter(Context context , List<Person> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
        this.context = context;
    }

    @Override
    public CustomVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_person, parent, false);
        return new CustomVH(view);
    }

    @Override
    public void onBindViewHolder(CustomVH holder, int position) {
        Person currentItem = items.get(position);
        holder.name.setText(currentItem.name);
        holder.nickname.setText(currentItem.nickname);
        holder.comment.setText(currentItem.comment);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class CustomVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, nickname, comment;
        ImageView edit;

        public CustomVH(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            nickname = (TextView) itemView.findViewById(R.id.nickname);
            comment = (TextView) itemView.findViewById(R.id.comment);
            edit = (ImageView) itemView.findViewById(R.id.edit);
            
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Edit person detail", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            Person p = items.get(position);
            Toast.makeText(context, "Edit info of "+ p.nickname, Toast.LENGTH_SHORT).show();
        }



    }


}
