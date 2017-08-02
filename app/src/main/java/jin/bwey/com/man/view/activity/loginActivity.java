package jin.bwey.com.man.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import jin.bwey.com.man.R;
import jin.bwey.com.man.modle.bean.login_bean;
import jin.bwey.com.man.modle.unit.shaprefase;
import jin.bwey.com.man.presenter.utils_presenter.reister_btn_persenter;
import jin.bwey.com.man.view.Iview.register_btn_postcallback;

/**
 * Created by Administrator on 2017/7/17.
 */

public class loginActivity extends Activity implements register_btn_postcallback {

    private ImageView login_iamge_finsh;
    private EditText login_edit_user;
    private EditText login_edit_pswd;
    private Button long_btn;
    private TextView login_register;
    private TextView login_come_pwd;
    private Map<String, String> map;
    String url = "http://169.254.32.47/mobile/index.php?act=login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loginactivityxml);
        initview();
        initData();

    }


    private void initview() {

        login_iamge_finsh = (ImageView) findViewById(R.id.login_iamge_finsh);
        login_edit_user = (EditText) findViewById(R.id.login_edit_user);
        login_edit_pswd = (EditText) findViewById(R.id.login_edit_pswd);
        long_btn = (Button) findViewById(R.id.long_btn);
        login_register = (TextView) findViewById(R.id.login_register);
        login_come_pwd = (TextView) findViewById(R.id.login_come_pwd);
    }

    private void initData() {


        // 关闭当前页面
        login_iamge_finsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 注册btn
        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this, registerAcitvity.class));
            }
        });

        long_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                map = new HashMap<String ,String>();
                map.put("act", "login");
                map.put("username",login_edit_user.getText().toString().trim());
                map.put("password", login_edit_pswd.getText().toString().trim());
                map.put("client", "android");
                new reister_btn_persenter(loginActivity.this).resqont_post(url,map,login_bean.class);

            }
        });

}


    @Override
    public void err(Exception e) {

    }

    @Override
    public void soress(Object o) {
        if(o != null) {
            login_bean bean = (login_bean) o;
            if (bean.getCode() == 200) {
                Toast.makeText(loginActivity.this,"登陆成功",Toast.LENGTH_LONG).show();

                shaprefase sh = new shaprefase(loginActivity.this);

      //TODO
                sh.setstring("on","1");
       //          new shopfragment().visib();


                finish();

            }
            if (bean.getCode() == 400) {
                Toast.makeText(loginActivity.this,"登陆失败",Toast.LENGTH_LONG).show();
            }


        }

    }
}
