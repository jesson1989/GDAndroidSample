package yjs.gd.com.gdandroidsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import yjs.gd.com.gdandroidsample.R;
import yjs.gd.com.gdandroidsample.bean.TrackingInfo;

/**
 * Created by Jesson_Yi on 2017/8/1.
 */

public class SlideDelAdapter extends
        RecyclerView.Adapter<SlideDelAdapter.MyViewHolder> {

    List<TrackingInfo> lists;
    Context mContext;

    public SlideDelAdapter(List<TrackingInfo> lists, Context mContext) {
        super();
        this.lists = lists;
        this.mContext = mContext;
    }

    /*
     * 覆盖方法
     */
    @Override
    public int getItemCount() {
        // TODO 自动生成的方法存根
        return lists.size();
    }

    /*
     * 覆盖方法
     */
    @Override
    public SlideDelAdapter.MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        // TODO 自动生成的方法存根
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_slide_del, arg0, false);
        SlideDelAdapter.MyViewHolder holder = new SlideDelAdapter.MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,time;
//        View lineNorma, lineHiLight;
//        ImageView image;

        /**
         * @param itemView
         */
        public MyViewHolder(View itemView) {
            super(itemView);
            // TODO 自动生成的构造函数存根
            title = (TextView) itemView.findViewById(R.id.content);
//            time= (TextView) itemView.findViewById(R.id.show_time);
//            lineNorma = itemView.findViewById(R.id.line_normal);
//            lineHiLight = itemView.findViewById(R.id.line_highlight);
//            image = (ImageView) itemView.findViewById(R.id.image);
        }

    }

    /*
     * 覆盖方法
     */
    @Override
    public void onBindViewHolder(SlideDelAdapter.MyViewHolder item, int position) {
        // TODO 自动生成的方法存根
        item.title.setText(lists.get(position).getTitle());
//        item.time.setText(lists.get(position).getTime());

        //最后一项时，竖线不再显示
//        if (position == 0) {
//            item.lineNorma.setVisibility(View.INVISIBLE);
//            item.lineHiLight.setVisibility(View.INVISIBLE);
//            item.time.setVisibility(View.VISIBLE);
//        }else {
//            if (lists.get(position).getStatus() == 1) {
//                item.lineHiLight.setVisibility(View.VISIBLE);
//                item.image.setImageResource(R.drawable.bottom_btn_red_press);
//                item.time.setVisibility(View.VISIBLE);
//            }
//        }

    }

}