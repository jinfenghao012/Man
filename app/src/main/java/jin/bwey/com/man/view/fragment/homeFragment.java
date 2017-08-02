package jin.bwey.com.man.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import jin.bwey.com.man.R;
import jin.bwey.com.man.Zxing.CaptureActivity;
import jin.bwey.com.man.view.activity.SearchActivity;

/**
 cosy by 靳风浩
首页
 */

public class homeFragment extends Fragment  {

    private View view;
    private ImageView leftImageView;
    private EditText titleEditText;
    private ImageView rightImageView;

    private ListView mListView;
    private TextView statusTextView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<HashMap<String, ArrayList<HashMap<String, String>>>> mArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homefragmnet,container,false);
        initview();
        createOnclick();




        return view;

    }



    private void initview() {
        leftImageView = (ImageView) view.findViewById(R.id.leftImageView);
        titleEditText = (EditText) view.findViewById(R.id.titleEditText);
        rightImageView = (ImageView) view.findViewById(R.id.rightImageView);
        mListView = (ListView) view.findViewById(R.id.mainListView);
        statusTextView = (TextView) view.findViewById(R.id.statusTextView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.mainSwipeRefreshLayout);

    }

    private void createOnclick() {
        leftImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getActivity(), CaptureActivity.class));
            }
        });

        titleEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getActivity(), SearchActivity.class));

            }
        });

    }


    public  void getJson() {



    }
}
