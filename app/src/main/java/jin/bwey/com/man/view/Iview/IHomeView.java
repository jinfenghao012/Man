package jin.bwey.com.man.view.Iview;

/**
 * Created by Administrator on 2017/7/16.
 */

public interface IHomeView<T> {
    void successCallback(T t);
    void  errcallback(int code , String msg);
}
