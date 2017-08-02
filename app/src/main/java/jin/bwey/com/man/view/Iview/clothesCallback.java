package jin.bwey.com.man.view.Iview;

import java.io.IOException;

/**
 * Created by Administrator on 2017/7/19.
 */

public interface clothesCallback<T> {

    void onSuccess(T t);

    void onFailure(IOException e);
}
