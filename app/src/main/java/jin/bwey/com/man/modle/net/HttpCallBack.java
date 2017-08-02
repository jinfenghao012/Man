package jin.bwey.com.man.modle.net;

import java.io.IOException;


/**
 * Created by Administrator on 2017/7/18.
 */

public interface HttpCallBack<T> {

    void onSuccess(T t);

    void onFailure(IOException e);

}
