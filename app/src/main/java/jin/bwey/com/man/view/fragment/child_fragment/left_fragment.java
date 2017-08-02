package jin.bwey.com.man.view.fragment.child_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jin.bwey.com.man.R;
import jin.bwey.com.man.modle.bean.clothes;
import jin.bwey.com.man.modle.bean.good_class;
import jin.bwey.com.man.modle.net.HttpUtils;
import jin.bwey.com.man.modle.net.NetDataCallBack;
import jin.bwey.com.man.presenter.utils_presenter.persenter_get;
import jin.bwey.com.man.view.Iview.clothesCallback;
import jin.bwey.com.man.view.fragment.child_fragment.adapter.left_fragment_adapter;


public class left_fragment extends Fragment implements clothesCallback ,NetDataCallBack {

    private View view;
    private ListView listview;

    private List<clothes.DatasBean.ClassListBean> list = new ArrayList<clothes.DatasBean.ClassListBean>();
    private String url = "http://169.254.32.47/mobile/index.php?act=goods_class";
    private left_fragment_adapter adapter;

    private List<String> list_two = new ArrayList<String>();
    private List<String> list_three = new ArrayList<String>();


    private ListView class1ListView;
//    private Class1ListAdapter class1Adapter;
    private ArrayList<HashMap<String, String>> class1ArrayList;

    private ListView class2ListView;
  //  private Class2ListAdapter class2Adapter;
    private ArrayList<HashMap<String, String>> class2ArrayList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_child_eft, container, false);


        initview();
        initData();
        return view;
    }

    private void initview() {

        listview = (ListView) view.findViewById(R.id.left_listview);

        //初始化参数
        class1ArrayList = new ArrayList<>();
      //  class1Adapter = new Class1ListAdapter(mActivity, class1ArrayList);
    //    class1ListView.setAdapter(class1Adapter);

        class2ArrayList = new ArrayList<>();
      //  class2Adapter = new Class2ListAdapter(mActivity, class2ArrayList);
     //   class2ListView.setAdapter(class2Adapter);

    }


    private void initData() {
        // 得到一级列表所有的值
        new persenter_get(this).getcallback(url, null, clothes.class);


        adapter = new left_fragment_adapter(getContext(), list);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clothes.DatasBean.ClassListBean haha = (clothes.DatasBean.ClassListBean) listview.getItemAtPosition(position);
                Toast.makeText(getContext(), haha.getGc_name() + "  <>", Toast.LENGTH_LONG).show();
            }
        });


        //http://169.254.32.47/mobile/index.php?act=goods_class
String url = "http://169.254.32.47/mobile/index.php?act=goods_class";

        new HttpUtils().loadDataFromServer(url,this, good_class.class);


    }

    @Override
    public void onSuccess(Object o) {
        clothes bean = (clothes) o;
        list.addAll(bean.getDatas().getClass_list());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(IOException e) {

    }

    @Override
    public void callback(Object o) {




    }

    @Override
    public void err(int errCode, String errMsg) {

    }
}
