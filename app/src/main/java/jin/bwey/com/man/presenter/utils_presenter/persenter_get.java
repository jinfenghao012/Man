package jin.bwey.com.man.presenter.utils_presenter;

import java.io.IOException;
import java.util.Map;

import jin.bwey.com.man.modle.net.HttpCallBack;
import jin.bwey.com.man.modle.net.OkHttpUtils;
import jin.bwey.com.man.view.Iview.clothesCallback;

/**
 * Created by Administrator on 2017/7/19.
 */

public class persenter_get<T> {

        private OkHttpUtils okHttpUtils;
        private clothesCallback clothesCallback;
    public persenter_get(clothesCallback clothesCallback){
        okHttpUtils = OkHttpUtils.getInstance();
        this.clothesCallback = clothesCallback;
    }


    public  void getcallback(String url , Map<String,Object>map,Class<T>clazz ){

        okHttpUtils.getAsyn(url, map, clazz, new HttpCallBack<T>() {
            @Override
            public void onSuccess(T t) {
                clothesCallback.onSuccess(t);
            }

            @Override
            public void onFailure(IOException e) {
                clothesCallback.onFailure(e);
            }
        });

    }

}
