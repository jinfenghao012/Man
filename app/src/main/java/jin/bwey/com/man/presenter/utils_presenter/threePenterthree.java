package jin.bwey.com.man.presenter.utils_presenter;

import jin.bwey.com.man.modle.net.HttpUtils;
import jin.bwey.com.man.modle.net.NetDataCallBack;
import jin.bwey.com.man.view.Iview.threeCallback;

/**
 * Created by Administrator on 2017/7/20.
 */

public class threePenterthree<T> {

    private threeCallback threeCallback;
    private HttpUtils httpUtils;

    public threePenterthree(threeCallback threeCallback){
        this.threeCallback = threeCallback;
            httpUtils = new HttpUtils();

    }



    public void loadDatethree(String url ,Class<T> t){

        httpUtils.loadDataFromServer(url, new NetDataCallBack() {
            @Override
            public void callback(Object o) {
                threeCallback.onSuccessthree(o);
            }

            @Override
            public void err(int errCode, String errMsg) {

            }
        },t);

    }

}
