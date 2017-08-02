package jin.bwey.com.man.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import jin.bwey.com.man.R;
import jin.bwey.com.man.modle.bean.register_bean;
import jin.bwey.com.man.presenter.utils_presenter.registerpresenter;
import jin.bwey.com.man.presenter.utils_presenter.reister_btn_persenter;
import jin.bwey.com.man.view.Iview.equest_stringCallBack;
import jin.bwey.com.man.view.Iview.register_btn_postcallback;

/**
 * Created by 靳风浩 on 2017/7/16.
 *
 * 注册
 *
 */

public class registerAcitvity extends Activity  implements equest_stringCallBack, View.OnClickListener ,register_btn_postcallback{



    private EditText user;
    private EditText pswd;
    private EditText pswd_two;
    private EditText mailbox;
    private Button register_btn;
    private ImageView iamge_finsh;
    String url = "http://169.254.32.47/mobile/index.php?act=login&op=register";
    boolean  unanimous= false;
    boolean email = false;
    private Map<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.registeractivity);
        initview();
        initData();

    }

    private void initview() {
        iamge_finsh = (ImageView) findViewById(R.id.iamge_finsh);
        user = (EditText) findViewById(R.id.user);
        pswd = (EditText) findViewById(R.id.pswd);
        pswd_two = (EditText) findViewById(R.id.pswd_two);
        mailbox = (EditText) findViewById(R.id.mailbox);
        register_btn = (Button) findViewById(R.id.register_btn);
        register_btn.setOnClickListener(this);

    }



    private void initData() {
        iamge_finsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public void equstcallback(boolean bool) {
 /*判断两次密码是否一致
*/
        if(!bool){
            Toast.makeText(registerAcitvity.this,"两次密码不一致",Toast.LENGTH_LONG).show();
        }
        else {
            unanimous = bool;
        }

    }

    @Override
    public void isemeilCallback(boolean bool) {

        if(!bool){
            Toast.makeText(registerAcitvity.this,"邮箱格式不对哦",Toast.LENGTH_LONG).show();
        }
        else {
            email = bool;
        }
    }

    @Override
    public void onClick(View v) {

        if(TextUtils.isEmpty(pswd.getText()) || TextUtils.isEmpty(pswd_two.getText()) )
        {
            Toast.makeText(registerAcitvity.this,"密码不能为空",Toast.LENGTH_LONG).show();
            return;
        }
      new  registerpresenter(this).registerCallBack(pswd.getText().toString().trim(),pswd_two.getText().toString().trim());
        new registerpresenter(this).isEmail(mailbox.getText().toString().trim());
        if(TextUtils.isEmpty(user.getText())){
            Toast.makeText(registerAcitvity.this,"用户名不能为空",Toast.LENGTH_LONG).show();
            return;
        }

        if(email &&unanimous ){
            map = new HashMap<String ,String>();
            map.put("act", "login");
            map.put("op", "register");
            map.put("username",user.getText().toString().trim());
            map.put("password", pswd.getText().toString().trim());
            map.put("password_confirm", pswd_two.getText().toString().trim());
            map.put("email", mailbox.getText().toString().trim());
            map.put("client", "android");
            new reister_btn_persenter(this).resqont_post(url,map,register_bean.class);
        }

    }

    @Override
    public void err(Exception e) {

    }

    @Override
    public void soress(Object o) {
        register_bean bean = (register_bean) o;

     //   Log.e("TAG",bean.getCode()+"<><><>"+bean.getDatas().getKey().toString()+bean.getDatas().getUserid().toString()
       // +bean.getDatas().getUsername());
        if(bean.getCode() == 200)
        {
            Toast.makeText(registerAcitvity.this,"注册成功",Toast.LENGTH_LONG).show();
            finish();

        }
            else  if(bean.getCode()==400){
            Toast.makeText(registerAcitvity.this,"注册失败，用户名已存在",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(registerAcitvity.this,"请认真核对下您的信息",Toast.LENGTH_LONG).show();

        }

    }
}

