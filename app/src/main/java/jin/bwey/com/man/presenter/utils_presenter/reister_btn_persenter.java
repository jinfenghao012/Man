package jin.bwey.com.man.presenter.utils_presenter;

import java.io.IOException;
import java.util.Map;

import jin.bwey.com.man.modle.net.HttpCallBack;
import jin.bwey.com.man.modle.net.OkHttpUtils;
import jin.bwey.com.man.view.Iview.register_btn_postcallback;

/**
 * Created by Administrator on 2017/7/18.
 */

public class reister_btn_persenter<T>  {
    register_btn_postcallback register_btn_postcallback;
  private OkHttpUtils okHttpUtils;
    public reister_btn_persenter(register_btn_postcallback register_btn_postcallback){
        this.register_btn_postcallback=register_btn_postcallback;
        okHttpUtils =OkHttpUtils.getInstance();
    }




    // 调用post 请求
public void resqont_post(String url , Map<String ,Object> map ,Class<T> clazz ){

    okHttpUtils.postAsyn(url, map, clazz, new HttpCallBack<T>() {
        @Override
        public void onSuccess(T t) {
            register_btn_postcallback.soress(t);
        }

        @Override
        public void onFailure(IOException e) {
        register_btn_postcallback.equals(e);
        }
    });

}


}
