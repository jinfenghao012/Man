package jin.bwey.com.man.view.Iview;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface right_child_callback<T> {

    void successCallback(T t);
    void  errcallback( String msg  , int code  );
}
