package jin.bwey.com.man.presenter.utils_presenter;

import jin.bwey.com.man.modle.net.HttpUtils;
import jin.bwey.com.man.modle.net.NetDataCallBack;
import jin.bwey.com.man.view.Iview.right_child_callback;

/**
 * Created by Administrator on 2017/7/20.
 */

public class right_child_presenter<T> {

 private   HttpUtils httpUtils;
private right_child_callback right_child_callback;

    public right_child_presenter(right_child_callback right_child_presenter){

        httpUtils = new HttpUtils();
this.right_child_callback = right_child_presenter;

    }


    public void secondCallback(String url,Class<T> t ){
        httpUtils.loadDataFromServer(url, new NetDataCallBack() {
            @Override
            public void callback(Object o) {
                right_child_callback.successCallback(o);
            }

            @Override
            public void err(int errCode, String errMsg) {
         //   right_child_callback.errcallback(errMsg,errCode);
            }
        },t);


    }


}
