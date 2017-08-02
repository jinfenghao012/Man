package jin.bwey.com.man.modle.unit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/7/16.
 */

public class registerUtils {


/**
 * 判断字符串是否相等
 * */
    public boolean equals_string(String str_one,String str_two){
        if(null == str_one || null == str_two){
            return false;
        }

         if(str_one.equals(str_two)){
             return true;
         }

        return false;
    }


    //判断email格式是否正确

    public boolean isEmail(String email) {

        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

        Pattern p = Pattern.compile(str);

        Matcher m = p.matcher(email);
        System.out.println("utils:  "+  m.matches());
        return m.matches();

    }

}
