package com.mobintum.listintentsamples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rick on 07/05/15.
 */
public class ListIntentAdapter extends ArrayAdapter {


    private ArrayList<ActionsIntent> actionsIntent;
    private Context context;

    public ListIntentAdapter(Context context, int resource, ArrayList<ActionsIntent> actionsIntent) {
        super(context, resource, actionsIntent);
        this.actionsIntent = actionsIntent;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.item_list_intent, parent, false);

        ViewHolder holder = new ViewHolder();

        holder.textDescription = (TextView) rowView.findViewById(R.id.textDescription);
        holder.btnAction = (ImageButton) rowView.findViewById(R.id.btnIntent);

        holder.textDescription.setText(actionsIntent.get(position).getDescription());
        holder.btnAction.setImageDrawable(actionsIntent.get(position).getIcon());

        holder.btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(actionsIntent.get(position).getIntent());
            }
        });

        return rowView;
    }

    class ViewHolder{
        
        TextView textDescription;
        ImageButton btnAction;
    }
}
