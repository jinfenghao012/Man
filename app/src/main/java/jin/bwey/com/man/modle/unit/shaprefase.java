package jin.bwey.com.man.modle.unit;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Administrator on 2017/7/18.
 */

public class shaprefase {

    public SharedPreferences sp;

    public shaprefase (Context context){
        if(sp == null){
            sp=  context.getSharedPreferences("login",Context.MODE_PRIVATE);
        }
    }

    public void setstring(String key,String value){

        SharedPreferences.Editor edite = sp.edit();
        edite.putString(key, value).commit();
        String str =  sp.getString(key,null);
        System.out.println("输入的是shap key 是：" + str);

    }


    public  String getstring(String key){
     String value =  sp.getString(key,null);
        System.out.println("取出的是shap key 是：" + value);

        return value;
    }

    public  void delete(String key){
        sp.edit().remove(key).commit();

    }

}
