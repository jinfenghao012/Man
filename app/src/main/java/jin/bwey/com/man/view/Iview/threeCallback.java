package jin.bwey.com.man.view.Iview;

import java.io.IOException;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface threeCallback<T> {
    void onSuccessthree(T t);

    void onFailurethree(IOException e);


}
