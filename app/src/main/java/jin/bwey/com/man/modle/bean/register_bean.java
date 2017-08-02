package jin.bwey.com.man.modle.bean;

/**
 * Created by Administrator on 2017/7/17.
 */

public class register_bean {


    /**
     * code : 200
     * datas : {"username":"jin12345512557","userid":"3","key":"5946c3776d82394a36ef9261bda80b70"}
     */

    private int code;
    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * username : jin12345512557
         * userid : 3
         * key : 5946c3776d82394a36ef9261bda80b70
         */

        private String username;
        private String userid;
        private String key;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
