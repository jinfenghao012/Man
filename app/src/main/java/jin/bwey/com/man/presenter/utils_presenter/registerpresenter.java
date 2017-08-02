package jin.bwey.com.man.presenter.utils_presenter;

import jin.bwey.com.man.modle.unit.registerUtils;
import jin.bwey.com.man.view.Iview.equest_stringCallBack;

/**
 * Created by Administrator on 2017/7/16.
 */

public class registerpresenter {

    private registerUtils registerUtils;
private equest_stringCallBack equest_stringCallBack;
    public   registerpresenter(equest_stringCallBack equest_stringCallBack){
        registerUtils = new registerUtils();
        this.equest_stringCallBack = equest_stringCallBack;

    }

    public void registerCallBack(String string_one , String string_two){
        boolean bool = registerUtils.equals_string(string_one, string_two);
        equest_stringCallBack.equstcallback(bool);
    }

public void isEmail(String email){

    boolean bool = registerUtils.isEmail(email);
    equest_stringCallBack.isemeilCallback(bool);
}

}
