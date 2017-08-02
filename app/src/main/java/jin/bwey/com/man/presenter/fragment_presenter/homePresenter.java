package jin.bwey.com.man.presenter.fragment_presenter;

import jin.bwey.com.man.modle.net.HttpUtils;
import jin.bwey.com.man.modle.net.NetDataCallBack;
import jin.bwey.com.man.view.Iview.IHomeView;

/**
 * Created by Administrator on 2017/7/16.
 */

public class homePresenter<T>{

    private   HttpUtils httpUtils;
private  IHomeView iHomeView;
    public homePresenter(IHomeView iHomeView){
        httpUtils = new HttpUtils();
        this.iHomeView = iHomeView;
    }

    public void loadDataFromServer(String url ,Class<T> t){

        httpUtils.loadDataFromServer(url, new NetDataCallBack() {
            @Override
            public void callback(Object o) {
            iHomeView.successCallback(o);
            }

            @Override
            public void err(int errCode, String errMsg) {

            }
        },t);

    }



}
