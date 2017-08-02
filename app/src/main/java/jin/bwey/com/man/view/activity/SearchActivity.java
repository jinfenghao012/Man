package jin.bwey.com.man.view.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import jin.bwey.com.man.R;

/**
 * Created by Administrator on 2017/7/22.
 */

public class SearchActivity extends AppCompatActivity {


    private String typeString;
    private String keywordString;

    private ImageView leftImageView;
    private EditText keywordEditText;
    private ImageView rightImageView;

    private Spinner mSpinner;
    private ListView mListView;
    private TextView mTextView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<HashMap<String, String>> mArrayList;
    private ArrayList<HashMap<String, String>> tempArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        createControl();
    }

    private void createControl() {
        //控件实例化
        leftImageView = (ImageView) findViewById(R.id.leftImageView);
        keywordEditText = (EditText) findViewById(R.id.keywordEditText);
        rightImageView = (ImageView) findViewById(R.id.rightImageView);
        mSpinner = (Spinner) findViewById(R.id.typeSpinner);
        mListView = (ListView) findViewById(R.id.mainListView);
        mTextView = (TextView) findViewById(R.id.statusTextView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mainSwipeRefreshLayout);

        mArrayList = new ArrayList<>();
        tempArrayList = new ArrayList<>();


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"宝贝", "店铺"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView) view;
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(14.0f);
                typeString = textView.getText().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        leftImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        keywordEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}