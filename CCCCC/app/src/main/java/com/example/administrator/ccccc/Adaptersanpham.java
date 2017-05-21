package com.example.administrator.ccccc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 5/19/2017.
 */
public class Adaptersanpham extends RecyclerView.Adapter<Adaptersanpham.sanphamHolder> {
    public Adaptersanpham(List<sanpham> msp, Context context) {
        this.msp = msp;
        this.context=context;
    }
    private  Context context;
    private List<sanpham> msp;
    @Override
    public sanphamHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.rowsp,parent,false);
        //return new sanphamHolder(itemview);
        return  new sanphamHolder(itemview);
    }

    @Override
    public void onBindViewHolder(sanphamHolder holder, int position) {
        sanpham listsp=msp.get(position);
        holder.idsp.setText(String.valueOf(listsp.idsp));
        holder.tensp.setText(listsp.tensp);
        //holder.hinh.setText(listsp.hinh);
        Picasso.with(context).load(listsp.getimage_url()).resize(60,60).into(holder.imgview);
    }

    @Override
    public int getItemCount() {
        return msp.size();
    }

    public class sanphamHolder extends RecyclerView.ViewHolder{
        TextView idsp,tensp,hinh;ImageView imgview;
        public sanphamHolder(final View itemView) {
            super(itemView);
            idsp=(TextView)itemView.findViewById(R.id.idsp);
            tensp=(TextView)itemView.findViewById(R.id.tensp);
            hinh=(TextView)itemView.findViewById(R.id.hinh);
            imgview=(ImageView)itemView.findViewById(R.id.imgview);
            context=itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=getAdapterPosition();
                    //Toast.makeText(itemView.getContext(),msp.get(pos).toString(),Toast.LENGTH_LONG).show();
                    //Toast.makeText(itemView.getContext(),msp.get(pos).idsp.toString(),Toast.LENGTH_SHORT).show();
                    Intent detailsp=new Intent(context,Main2Activity.class);
                    detailsp.putExtra("ID",msp.get(pos).idsp.toString());
                    context.startActivity(detailsp);
                }
            });
        }
    }
}
