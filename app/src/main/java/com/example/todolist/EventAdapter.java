package com.example.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private Context mContext;
    private List<Event> mEventList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View eventView;
        CardView cardView;
        TextView cardViewTitle;
        TextView cardViewContent;
        ImageView cardViewGrade;
        ImageView cardViewFinished;
        public ViewHolder(View view){
            super(view);
            cardView=(CardView)view;
            cardViewTitle=(TextView)view.findViewById(R.id.card_view_title);
            cardViewContent=(TextView)view.findViewById(R.id.card_view_content);
            cardViewGrade=(ImageView)view.findViewById(R.id.card_view_grade);
            cardViewFinished=(ImageView) view.findViewById(R.id.is_finished_ic);
            eventView=view;
        }
    }
    public EventAdapter(List<Event> eventList){
        mEventList=eventList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.event_item
        ,parent,false);
        LitePal.getDatabase();
        final ViewHolder holder=new ViewHolder(view);
//        holder.eventView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position=holder.getAdapterPosition();
//                Intent intent=new Intent("com.example.todolist.ACTION_START");
//                intent.putExtra("extra_event_id",mEventList.get(position).getId());
//            }
//        });
//        holder.eventView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                AlertDialog.Builder dialog=new AlertDialog.Builder(v.getContext());
//                dialog.setTitle("删除事件");
//                dialog.setMessage("确定删除");
//                dialog.setCancelable(false);
//                dialog.setPositiveButton("删除", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        DataSupport.deleteAll(Event.class,"id=?"
//                                ,Integer.toString();
//                    }
//                });
//                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//                dialog.show();
//                return true;
//            }
//        });
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){
        Event event=mEventList.get(position);
        Log.d("atest",Integer.toString(position));
        holder.cardViewTitle.setText(event.getEventTitle());
        holder.cardViewContent.setText(event.getEventContent());
        holder.cardViewGrade.setImageResource(getGradeImageId(event.getEventGrade()));
        if (event.getFinished()==true){
            holder.cardViewFinished.setImageResource(R.drawable.ic_finished);
        }else {
            holder.cardViewFinished.setImageResource(R.drawable.ic_not_finished);
        }
        holder.eventView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Intent intent=new Intent("com.example.todolist.ACTION_START");
                intent.putExtra("extra_event_id",Integer
                        .toString(mEventList.get(position).getId()));
                v.getContext().startActivity(intent);

            }
        });
        holder.eventView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(v.getContext());
                dialog.setTitle("删除事件");
                dialog.setMessage("确定删除");
                dialog.setCancelable(false);
                dialog.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataSupport.deleteAll(Event.class,"id==?"
                                ,Integer.toString(mEventList.get(position).getId()));
                        mEventList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.show();
                return true;
            }
        });
    }
    @Override
    public int getItemCount(){
        return mEventList.size();
    }

    //根据事件等级返回图片id
    public int getGradeImageId(int grade){
        int imageId=R.drawable.ic_all_event;
        switch (grade){
            case 1:
                imageId=R.drawable.ic_urgent;
                break;
            case 2:
                imageId=R.drawable.ic_important;
                break;
            case 3:
                imageId=R.drawable.ic_common;
                break;
                default:
        }
        return  imageId;
    }
}
