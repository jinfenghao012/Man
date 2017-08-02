package jin.bwey.com.man.view.fragment.child_fragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import jin.bwey.com.man.R;
import jin.bwey.com.man.modle.bean.clothes;

/**
 * Created by Administrator on 2017/7/19.
 */

public class left_fragment_adapter extends BaseAdapter {
    Context context;
    List<clothes.DatasBean.ClassListBean> list;
    public left_fragment_adapter(Context context, List<clothes.DatasBean.ClassListBean> list) {

        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        //class_list
        return list != null ? list.size():0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = convertView.inflate(context,R.layout.left_fragment_item,null );
            holder.image = (ImageView) convertView.findViewById(R.id.item_image);
            holder.text = (TextView) convertView.findViewById(R.id.item_text);
            //设置参数提到这里，只有第一次的时候会执行，之后会复用
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 我们直接通过对象的getter方法代替刚才那些逻辑判断，那些逻辑判断放到别的地方去执行了
      //        System.out.println(bean.getDatas().getClass_list().get(0).getGc_name()+"<<<<<<<<>>>>>>>>>");

        holder.text.setText(list.get(position).getGc_name());
//        compile 'com.github.bumptech.glide:glide:4.0.0-RC1'
        Glide.with(context).load(list.get(position).getImage()).into(holder.image);

//                Glide.with(context).load(path).into(imageView);

        return convertView;
    }

    class ViewHolder{
        TextView text;
        ImageView image;

    }

}
