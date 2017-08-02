package jin.bwey.com.man.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import jin.bwey.com.man.R;
import jin.bwey.com.man.view.fragment.homeFragment;
import jin.bwey.com.man.view.fragment.myfragment;
import jin.bwey.com.man.view.fragment.shopfragment;
import jin.bwey.com.man.view.fragment.typefragment;

public class MainActivity extends FragmentActivity {

    private ViewPager pager;
    private RadioGroup rd_group;
    private List<Fragment> list_fragment;
    private  int i ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      test();
        initview();
        initData();
        initconfig();
    }
// 测试工具的类 没啥用 就当看不见
    private void test() {



    }


    /**
     * 找到view
     */
    private void initview() {
        pager = (ViewPager) findViewById(R.id.main_viewpager);
        rd_group = (RadioGroup) findViewById(R.id.main_radiogroup);
    }

    /**
     * 数据初始化
     */
    private void initData() {
        // 初始化四个fragment
        list_fragment = new ArrayList<Fragment>();
        list_fragment.add(new homeFragment());
        list_fragment.add(new typefragment());
        list_fragment.add(new shopfragment());
        list_fragment.add(new myfragment());

    }

    /**
     * fragment 配置
     */
    private void initconfig() {

        // pager 设置adapter
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;

                switch (position) {
                    case 0:
                        fragment = list_fragment.get(0);
                        break;
                    case 1:
                        fragment = list_fragment.get(1);
                        break;
                    case 2:
                        fragment = list_fragment.get(2);
                        break;
                    case 3:
                        fragment = list_fragment.get(3);


                        break;

                    default:
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return list_fragment != null ? list_fragment.size() : 0;
            }
        });

// pager 监听
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            //    getSupportFragmentManager().beginTransaction().replace(new homeFragment(),new shop_secend_fragment()).commit();

                rd_group.check(rd_group.getChildAt(position).getId());


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // radiogoup 设置监听
        rd_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                for (int i = 0; i < list_fragment.size(); i++) {

                    if (group.getChildAt(i).getId() == checkedId) {
                        pager.setCurrentItem(i, false);
                    }
                }
            }
        });


    }


}
