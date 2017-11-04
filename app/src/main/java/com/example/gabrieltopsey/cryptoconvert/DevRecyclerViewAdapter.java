package com.example.gabrieltopsey.cryptoconvert;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class DevRecyclerViewAdapter extends RecyclerView.Adapter<DevRecyclerViewAdapter.MenuViewHolder>
{
    List<DevData> contents;
    private Context context;

    static final int TYPE_NORMAL = 0;
    static final int TYPE_PICTURE = 1;

    public DevRecyclerViewAdapter(Context con, List<DevData> contents)
    {
        this.contents = contents;
        this.context = con;
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout cv;
        TextView countryName;
        TextView countryCode;
        ImageView thumbnail;


        MenuViewHolder(View itemView)
        {
            super(itemView);
            cv = (LinearLayout)itemView.findViewById(R.id.layout_card);
            countryName = (TextView)itemView.findViewById(R.id.country_name);
            countryCode = (TextView)itemView.findViewById(R.id.country_code);
            thumbnail = (ImageView)itemView.findViewById(R.id.country_image);
        }

    }

    @Override
    public int getItemViewType(int position)
    {
        switch (position)
        {
            default:
                return TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount()
    {
        return contents.size();
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = null;

        switch (viewType)
        {
            case TYPE_NORMAL:
            {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_card_view, parent, false);
                MenuViewHolder pvh = new MenuViewHolder(view);
                return pvh;
            }

        }
        return null;
    }


    @Override
    public void onBindViewHolder(final MenuViewHolder holder,final int position)
    {
        switch (getItemViewType(position))
        {
            case TYPE_NORMAL:
            {
                holder.countryName.setText(contents.get(position).country_name);
                holder.thumbnail.setImageResource(contents.get(position).image);
                holder.countryCode.setText(contents.get(position).codeName);
//                Picasso.with(context)
//                        .load(contents.get(position).image)
//                        .into(holder.thumbnail);
            }
            break;

        }

        //set the onClicklistener for the card
        holder.cv.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                Intent i = new Intent(context, ConvertionActivity.class);
                                i.putExtra("code",contents.get(position).codeName);
                                context.startActivity(i);


                            }
                        }
                );
    }

}
