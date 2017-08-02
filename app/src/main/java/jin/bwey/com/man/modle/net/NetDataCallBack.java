package jin.bwey.com.man.modle.net;

/**
 * 接口回调
 */

public interface NetDataCallBack<T> {

    void callback(T t);
    void err(int errCode , String errMsg);

}
