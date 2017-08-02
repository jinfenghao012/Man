package jin.bwey.com.man.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import jin.bwey.com.man.R;
import jin.bwey.com.man.modle.unit.shaprefase;
import jin.bwey.com.man.view.activity.loginActivity;

/**
购物车

 */

public class shopfragment extends Fragment {

    private View view;
    private TextView register;
    private RelativeLayout visib;
    private RelativeLayout layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.shopfragmentxml,container,false);


        initview();
        initData();
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

     //   visib();
    }

    private void initview() {
        register = (TextView) view.findViewById(R.id.register_text_shopfragment_To_registerActivity);
        visib = (RelativeLayout) view.findViewById(R.id.visib);
        layout = (RelativeLayout) view.findViewById(R.id.shoping_cart);


    }
public void  visib(){
    shaprefase sh = new shaprefase(getContext());
    String id = sh.getstring("on");
    System.out.println("id:"+id);
    if(null != id && "1".equals(id)){

        Toast.makeText(getContext(),"登陆成功",Toast.LENGTH_LONG).show();
        layout.setVisibility(View.VISIBLE);
        visib.setVisibility(View.INVISIBLE);

   // getFragmentManager().beginTransaction().replace(R.id.relative,new shop_secend_fragment());
    }



}
    private void initData() {



    register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getContext(), loginActivity.class));
        }
    });

    }

}
