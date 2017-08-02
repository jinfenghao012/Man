package jin.bwey.com.man.modle.net;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 靳风浩 post on 2017/7/18.
 */

public class OkHttpUtils {

    private static final int SUCCESS_CODE = 1;
    private static final int ERROR_CODE = 2;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;
    private Gson mGson;
    private volatile static OkHttpUtils mOkHttpUtils;


    /*
    本类初始化的时候首先执行本方法，只是不对外提供而已 初始化只能本类使用
  私有构造方法后外部不能在new OkHttpUtils（）的方法获得实列，只能通过
     本类中提供的getinstance 来获得

    私有构造 创建handler client gosn 实列*/
    private OkHttpUtils() {

        mOkHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();

        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case SUCCESS_CODE:
                        if( null == (HandlerData)msg.obj){
                            return;
                        }

                        ((HandlerData) msg.obj).success();
                        break;
                    case ERROR_CODE:
                        ((HandlerData) msg.obj).error();
                        break;
                }
            }
        };
        mGson = new Gson();

    }

    //回调接口   对回调的一个封装而已  就是在便于在方法中直接掉回
    class HandlerData<T> {
        HttpCallBack<T> httpCallBack;
        T t;
        IOException e;

        public void success() {
            if (httpCallBack != null) {

                httpCallBack.onSuccess(t);
            }
        }

        public void error() {
            if (httpCallBack != null) {
                httpCallBack.onFailure(e);
            }
        }
    }


    // 单例模式
    public static OkHttpUtils getInstance() {

        if (mOkHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                mOkHttpUtils = new OkHttpUtils();
            }
        }
        return mOkHttpUtils;
    }


    /**
     * 把map和url拼接到一起的方法
     *
     * @param url 请求的接口
     * @param map 拼接的参数
     * @return string 拼接好的接口
     */
    private String getUrl(String url, Map<String, Object> map) {
        //当map集合为空的时候，直接返回url
        if (map == null || map.size() == 0) {
            return url;
        }
        StringBuffer sb = new StringBuffer();
        for (String key : map.keySet()) {
            sb.append(key + "=" + map.get(key) + "&");
        }
        return url + "?" + sb.substring(0, sb.length() - 1);
    }


    /**
     * get 用到的Request
     *
     * @param url 接口地址
     * @param map 拼接参数
     * @return Request
     */
    private Request getRequest(String url, Map<String, Object> map) {
        return new Request.Builder()
                .url(getUrl(url, map))
                .build();
    }

    /**
     * post 用到的Request
     *
     * @param url 接口地址
     * @param map 拼接参数
     * @return Request
     */
    private Request PostRequest(String url, Map<String, Object> map) {
        if (map == null || map.size() == 0) {
            return new Request.Builder()
                    .url(url)
                    .build();
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            builder.add(entry.getKey(), (String) entry.getValue());
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(getUrl(url, null))
                .post(requestBody)
                .build();
    }


    /**
     * handler 消息
     *
     * @param what 消息标识
     * @param obj  消息携带的数据
     * @return Message
     */
    private Message getMessage(int what, Object obj) {
        Message message = Message.obtain();
        message.what = what;
        message.obj = obj;
        return message;
    }

    /**
     * 请求数据用的,需要bean类
     *
     * @param httpCallBack 接口回调
     * @param classType    bean反射
     * @param request      okhttp3
     * @param <T>          泛型
     */
    private <T> void deliveryResult(final HttpCallBack<T> httpCallBack, final Class<T> classType, Request request) {
        final HandlerData<T> hd = new HandlerData<>();
        hd.httpCallBack = httpCallBack;
        mOkHttpClient.newCall(request).enqueue(new Callback() {
                                                   @Override
                                                   public void onFailure(Call call, IOException e) {
                                                       hd.e = e;
                                                       mHandler.sendMessage(getMessage(ERROR_CODE, hd));
                                                   }

                                                   @Override
                                                   public void onResponse(Call call, Response response) throws IOException {
                                                       try {
                                                           String json = response.body().string();
                                                           if (json != null) {
                                                               T t = mGson.fromJson(json, classType);
                                                               hd.t = t;
                                                               mHandler.sendMessage(getMessage(SUCCESS_CODE, hd));
                                                           } else {
                                                               hd.e = new IOException("没有数据!");
                                                               mHandler.sendMessage(getMessage(ERROR_CODE, hd));
                                                           }
                                                       } catch (IOException e) {
                                                           mHandler.sendMessage(getMessage(ERROR_CODE, hd));
                                                       } catch (com.google.gson.JsonParseException e)//Json解析的错误
                                                       {
                                                           mHandler.sendMessage(getMessage(ERROR_CODE, hd));
                                                       }
                                                   }
                                               }
        );

    }


    private <T> void _postAsyn(String url, Map<String, Object> map, Class<T> classType, HttpCallBack<T> httpCallBack) {
        Request request = PostRequest(url, map);
        deliveryResult(httpCallBack, classType, request);
    }


    /**
     * 异步post
     * 用于外部
     *
     * @param url          接口地址
     * @param map          拼接参数
     * @param classType    bean反射
     * @param httpCallBack 回调接口
     * @param <T>          泛型
     */
    public <T> void postAsyn(String url, Map<String, Object> map, Class<T> classType, HttpCallBack<T> httpCallBack) {
        getInstance()._postAsyn(url, map, classType, httpCallBack);
    }


    /**
     * 异步get
     * 用于内部
     *
     * @param url          请求的地址（可以是拼接过得，也可以不是）
     * @param map          用于拼接的参数，之前在url拼接过的可以传入null
     * @param classType    反射，该网络请求的地址中的json对应的bean类
     * @param httpCallBack 接口回调，两个方法，成功和失败
     * @param <T>          泛型
     */
    private <T> void _getAsyn(String url, Map<String, Object> map, final Class<T> classType, HttpCallBack<T> httpCallBack) {

        Request request = getRequest(url, map);
        final HandlerData<T> hd = new HandlerData<>();
        hd.httpCallBack = httpCallBack;
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                hd.e = e;
                mHandler.sendMessage(getMessage(ERROR_CODE, hd));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                try {
                    if (json != null) {
                        T t = mGson.fromJson(json, classType);
                        hd.t = t;
                        mHandler.sendMessage(getMessage(SUCCESS_CODE, hd));
                    } else {
                        hd.e = new IOException("没有数据");
                        mHandler.sendMessage(getMessage(ERROR_CODE, hd));
                    }
                } catch (com.google.gson.JsonParseException e)//Json解析的错误
                {
                    mHandler.sendMessage(getMessage(ERROR_CODE, hd));
                }
            }
        });

    }



    /**
     * 异步get
     * 用于外部
     *
     * @param url          请求的地址
     * @param map          拼接的参数,允许为null
     * @param classType    bean类的反射
     * @param httpCallBack 接口回调
     * @param <T>          泛型
     */
    public <T> void getAsyn(String url, Map<String, Object> map, Class<T> classType, HttpCallBack<T> httpCallBack) {
        getInstance()._getAsyn(url, map, classType, httpCallBack);
    }

}
