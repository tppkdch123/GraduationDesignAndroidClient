package com.example.huangshizhe.graduationdesignclient.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;

import com.example.huangshizhe.graduationdesignclient.IndexActivity;
import com.example.huangshizhe.graduationdesignclient.R;
import com.example.huangshizhe.graduationdesignclient.RoomActivity;
import com.example.huangshizhe.graduationdesignclient.adapter.RoomAdapter;
import com.example.huangshizhe.graduationdesignclient.enums.RequestEnum;
import com.example.huangshizhe.graduationdesignclient.utils.CommenUtil;
import com.example.huangshizhe.graduationdesignclient.utils.HttpConnectionUtil;
import com.example.huangshizhe.graduationdesignclient.utils.MyApplication;
import com.example.huangshizhe.graduationdesignclient.vo.Room;
import com.example.huangshizhe.graduationdesignclient.vo.RoomExtend;
import com.fasterxml.jackson.databind.JsonNode;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexFragment extends Fragment {

    private static final String prefix = "[主页面] ";

    private GridView gridView;

    private RoomAdapter roomAdapter;

    private AutoCompleteTextView autoCompleteTextView;

    private List<Room> rooms;

    public IndexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_index, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        gridView = getView().findViewById(R.id.rooms);
        new GetRoomsByCity().execute(1, 10);
    }

    class GetRoomsByCity extends AsyncTask<Integer, Void, RoomAdapter> {

        @Override
        protected RoomAdapter doInBackground(Integer... num) {
            Map<String, String> params = new HashMap<>();
            params.put("cityId", "1");
            params.put("pageNum", num[0].toString());
            params.put("size", num[1].toString());
            String result = HttpConnectionUtil.getJsonResult(RequestEnum.ROOM_CITY, params);
            try {
                if (StringUtils.isEmpty(result)) {
                    Log.i("info", "qqq");
                    return null;
                }
                JsonNode jsonNode = CommenUtil.toJsonNode(result);
                JsonNode data = jsonNode.get("data").get("list");
                List list = CommenUtil.toBean(data.toString(), List.class);
                Log.i("info", prefix + "总数为" + list.size());
                List<Room> rooms = convertObject2Room(list);
                Map<Long, Bitmap> map = new HashMap<>();
                for (int i = 0; i < rooms.size(); i++) {
                    List<RoomExtend> roomExtends = CommenUtil.getAllExtend(CommenUtil.getPictureByRoomId(rooms.get(i).getId()));
                    map.put(rooms.get(i).getId(), HttpConnectionUtil.getImage(roomExtends.get(0).getValue()));
                }
                return new RoomAdapter(rooms,map,getContext());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }


        @Override
        protected void onPostExecute(RoomAdapter roomAdapter) {
            IndexFragment.this.roomAdapter=roomAdapter;
            gridView.setAdapter(IndexFragment.this.roomAdapter);
            gridView.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent = new Intent(IndexFragment.this.getContext(), RoomActivity.class);
                intent.putExtra("roomId", roomAdapter.getData().get(position).getId());
                startActivity(intent);
            });
        }
    }

    private List<Room> convertObject2Room(List<Object> objects) {
        List<Room> rooms = new ArrayList<>();
        try {
            for (Object object : objects) {
                Room room = CommenUtil.toBean(CommenUtil.toJsonStr(object), Room.class);
                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
