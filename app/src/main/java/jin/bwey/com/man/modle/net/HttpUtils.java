package jin.bwey.com.man.modle.net;


import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 曹军 get on 2017/7/16.
 */

public class HttpUtils {

    private NetDataCallBack netDataCallBack;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:

                    //    netDataCallBack.err();

                    break;

                case 1:
                    netDataCallBack.callback(msg.obj);
                    break;

            }

        }
    };


    public <T> void loadDataFromServer(String url, final NetDataCallBack netDataCallBack, final Class<T> clazz) {

        this.netDataCallBack = netDataCallBack;
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = Message.obtain();
                message.obj = e.getMessage();
             //   handler.sendMessage(message);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                Message message = handler.obtainMessage();

                message.what = 1 ;

                Gson gson = new Gson();
                String a = response.body().string();

                    try {
                        T  t  =   gson.fromJson(a,clazz);
                        message.obj =t ;
                        handler.sendMessage(message);
                    }
                    catch (Exception e)
                    {
                        System.out.println("json 解析错误");
                    }








            }


        });


    }




}
