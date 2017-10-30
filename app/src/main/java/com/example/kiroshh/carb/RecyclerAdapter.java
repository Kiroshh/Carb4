package com.example.kiroshh.carb;
import android.app.Activity;
import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Details> detailses;
    private Activity activity;

    public RecyclerAdapter(Activity activity, List<Details> detailses) {
        this.detailses = detailses;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.itemrecycler, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder viewHolder, int position) {

        //setting data to view holder elements
        viewHolder.name.setText(detailses.get(position).getType());
        viewHolder.time.setText(detailses.get(position).getTime());
        viewHolder.date.setText(detailses.get(position).getDate());
        viewHolder.imageView.setImageResource(R.drawable.ic_camera_alt_black_24dp);
        //set on click listener for each element
        viewHolder.container.setOnClickListener(onClickListener(position));
    }

    private void setDataToView(TextView name, TextView time, TextView date,ImageView genderIcon, int position) {
        name.setText(detailses.get(position).getType());
        time.setText(detailses.get(position).getTime());
        date.setText(detailses.get(position).getDate());
        genderIcon.setImageResource(R.drawable.ic_camera_alt_black_24dp);

    }

    @Override
    public int getItemCount() {
        return (null != detailses ? detailses.size() : 0);
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.itemrecycler);
                dialog.setTitle("Position " + position);
                dialog.setCancelable(true);
                ImageView icon = (ImageView) dialog.findViewById(R.id.image);
                TextView name = (TextView) dialog.findViewById(R.id.name);
                TextView time = (TextView) dialog.findViewById(R.id.time);
                TextView date = (TextView) dialog.findViewById(R.id.date);

                setDataToView(name, date,time, icon, position);
                dialog.show();
            }
        };
    }

    protected  class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name;
        private TextView time;
        private View container;
        private TextView date;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image);
            name = (TextView) view.findViewById(R.id.name);
            time = (TextView) view.findViewById(R.id.time);
            date = (TextView) view.findViewById(R.id.date);

            container = view.findViewById(R.id.card_view);
        }
    }
}