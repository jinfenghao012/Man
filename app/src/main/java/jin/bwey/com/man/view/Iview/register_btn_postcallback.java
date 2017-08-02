package jin.bwey.com.man.view.Iview;

/**
 * Created by Administrator on 2017/7/18.
 */

public interface register_btn_postcallback<T> {
    void err(Exception e);
    void soress(T t);
}
