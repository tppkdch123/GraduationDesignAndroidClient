package com.example.huangshizhe.graduationdesignclient.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huangshizhe.graduationdesignclient.R;
import com.example.huangshizhe.graduationdesignclient.vo.Room;

import java.util.List;
import java.util.Map;

/**
 * Created by huangshizhe on 2018/4/20.
 */

public class RoomAdapter extends BaseAdapter {

    private Map<Long, Bitmap> map;

    private List<Room> data;

    private Context context;

    public RoomAdapter(List<Room> data, Map<Long, Bitmap> map, Context context) {
        this.map = map;
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    public Map<Long, Bitmap> getMap() {
        return map;
    }

    public List<Room> getData() {
        return data;
    }

    public Context getContext() {
        return context;
    }

    @Override

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holdView ;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.room_adapter, null);
            holdView = new HoldView();
            holdView.setPitcure(convertView.findViewById(R.id.picture));
            holdView.setTitle(convertView.findViewById(R.id.title));
            holdView.setDesc(convertView.findViewById(R.id.desc));
            holdView.setPrice(convertView.findViewById(R.id.price));
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        holdView.getTitle().setText(data.get(position).getTitle());
        holdView.getDesc().setText(data.get(position).getHostMessage());
        holdView.getPrice().setText("￥" + data.get(position).getDefaultPrice()/100.0f);
        holdView.getPitcure().setImageBitmap(map.get(data.get(position).getId()));
        return convertView;
    }

    class HoldView {
        ImageView pitcure;
        TextView title;
        TextView desc;

        TextView price;

        public TextView getPrice() {
            return price;
        }

        public void setPrice(TextView price) {
            this.price = price;
        }

        public void setPitcure(ImageView pitcure) {
            this.pitcure = pitcure;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public void setDesc(TextView desc) {
            this.desc = desc;
        }

        public ImageView getPitcure() {
            return pitcure;
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getDesc() {
            return desc;
        }
    }
}
