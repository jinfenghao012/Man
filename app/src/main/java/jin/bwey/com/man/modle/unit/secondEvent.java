package jin.bwey.com.man.modle.unit;


import java.util.List;

import jin.bwey.com.man.modle.bean.clothes;

/**
 * Created by Administrator on 2017/7/19.
 */

public class secondEvent {


    private List<clothes.DatasBean.ClassListBean> mMsg;

    public secondEvent(List<clothes.DatasBean.ClassListBean> msg) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
    }

    public List<clothes.DatasBean.ClassListBean> getMsgclothes(){
        return mMsg;
    }

}