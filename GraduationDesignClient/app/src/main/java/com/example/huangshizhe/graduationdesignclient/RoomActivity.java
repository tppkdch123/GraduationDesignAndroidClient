package com.example.huangshizhe.graduationdesignclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.huangshizhe.graduationdesignclient.enums.RequestEnum;
import com.example.huangshizhe.graduationdesignclient.utils.CommenUtil;
import com.example.huangshizhe.graduationdesignclient.utils.HttpConnectionUtil;
import com.example.huangshizhe.graduationdesignclient.vo.RoomExtend;
import com.example.huangshizhe.graduationdesignclient.vo.RoomWithBLOBs;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomActivity extends AppCompatActivity {

    private SliderLayout sliderLayout;

    private TextView title;

    private TextView description;

    private TextView introduction;

    private TextView money;

    private TextView needKnow;

    private TextView around;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        sliderLayout=findViewById(R.id.slider);

        title=findViewById(R.id.room_title);

        description=findViewById(R.id.description);

        introduction=findViewById(R.id.introduction);

        money=findViewById(R.id.money);

        needKnow=findViewById(R.id.need_know);

        around=findViewById(R.id.around);

        new LoadPictures().execute(getIntent().getLongExtra("roomId",1107534));

        new LoadRoomMessage().execute(getIntent().getLongExtra("roomId",100000));
    }

    class LoadPictures extends AsyncTask<Long,Void,List<RoomExtend>>{

        @Override
        protected List<RoomExtend> doInBackground(Long...longs) {
            List<RoomExtend> roomExtends=CommenUtil.getAllExtend(CommenUtil.getPictureByRoomId(longs[0]));
            return roomExtends;
        }

        @Override
        protected void onPostExecute(List<RoomExtend> roomExtends){
            for(RoomExtend roomExtend:roomExtends){
                TextSliderView textSliderView = new TextSliderView(RoomActivity.this);
                textSliderView
                        .description(roomExtend.getInfo())//描述
                        .image(roomExtend.getValue())//image方法可以传入图片url、资源id、File
                        .setScaleType(BaseSliderView.ScaleType.Fit);//图片缩放类型
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle().putString("extra",roomExtend.getInfo());//传入参数
                sliderLayout.addSlider(textSliderView);//添加一个滑动页面
                sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);//滑动动画
//        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);//默认指示器样式
                sliderLayout.setCustomIndicator((PagerIndicator)findViewById(R.id.custom_indicator2));//自定义指示器
                sliderLayout.setCustomAnimation(new DescriptionAnimation());//设置图片描述显示动画
                sliderLayout.setDuration(4000);//设置滚动时间，也是计时器时间
                //sliderLayout.addOnPageChangeListener(onPageChangeListener);
            }
        }
    }
    class LoadRoomMessage extends AsyncTask<Long,Void,RoomWithBLOBs>{

        @Override
        protected RoomWithBLOBs doInBackground(Long...roomIds) {
            Long roomId=roomIds[0];
            String result=HttpConnectionUtil.getJsonResult(RequestEnum.ROOM.getUrl()+roomId.toString(),new HashMap<>());
            try {
                JsonNode jsonNode=CommenUtil.toJsonNode(result);
                if(jsonNode==null){
                    return null;
                }
                if(jsonNode.get("status").asInt()==0){
                    jsonNode=jsonNode.get("data");
                    if(jsonNode!=null){
                       RoomWithBLOBs roomWithBLOBs = CommenUtil.toBean(jsonNode.toString(), RoomWithBLOBs.class);
                       return roomWithBLOBs;
                    }
                }
                else{
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(RoomWithBLOBs roomWithBLOBs){
            if(roomWithBLOBs==null){
                return;
            }
            title.setText(roomWithBLOBs.getTitle());
            introduction.setText(roomWithBLOBs.getHostMessage()+"|可住"+roomWithBLOBs.getMaxCapacity()+"人|"+roomWithBLOBs.getStructure());
            description.setText(roomWithBLOBs.getDescription());
            money.setText("￥"+roomWithBLOBs.getDefaultPrice()/100.0);
            needKnow.setText("todo");
            around.setText(roomWithBLOBs.getAroundInfo());
        }
    }
}
