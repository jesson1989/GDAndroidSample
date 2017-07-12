package yjs.gd.com.gdandroidsample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import yjs.gd.com.gdandroidsample.R;
import yjs.gd.com.gdandroidsample.bean.ChatInfo;

import java.util.List;

/**
 * Created by Jesson_Yi on 2017/7/12.
 */

public class ChatAdapter extends BaseAdapter {
    public List<ChatInfo> myData;
    public Context context;
    public LayoutInflater mInflater;

    public ChatAdapter(Context context, List<ChatInfo>data) {
        this.context=context;
        myData=data;
        mInflater= LayoutInflater.from(context);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int type =myData.get(position).getType();
        return type;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return myData.size();
    }

    @Override
    public Object getItem(int position) {
        return myData.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
            if(getItemViewType(position)==0){
                vh=new ViewHolder();
                convertView=mInflater.inflate(R.layout.chat_in,null);
                vh.Icon=(ImageView) convertView.findViewById(R.id.image_in);
                vh.Message=(TextView)convertView.findViewById(R.id.message);
            }else{
                vh=new ViewHolder();
                convertView=mInflater.inflate(R.layout.chat_out,null);
                vh.Icon=(ImageView) convertView.findViewById(R.id.image_out);
                vh.Message=(TextView)convertView.findViewById(R.id.message);
            }
            convertView.setTag(vh);
        }else{

            vh=(ViewHolder) convertView.getTag();
        }
        vh.Icon.setImageBitmap(myData.get(position).getmIcon());
        vh.Message.setText(myData.get(position).getMessage());
        return convertView;
    }

    class ViewHolder{
        public ImageView Icon;
        public TextView Message;

    }
}
