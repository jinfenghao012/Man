package jin.bwey.com.man.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jin.bwey.com.man.R;
import jin.bwey.com.man.view.fragment.child_fragment.RigthtFragment;
import jin.bwey.com.man.view.fragment.child_fragment.left_fragment;

/**
个人

 */

public class typefragment  extends Fragment {

    private View view;
    private ViewPager viewPager;
    private ViewPager left;
    private ViewPager right;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.typefragment,container,false);

        initview();
        initdata();

        return view;

    }



    private void initview() {
        left = (ViewPager) view.findViewById(R.id.left_pager);
        right = (ViewPager) view.findViewById(R.id.right_pager);

    }

    private void initdata() {

        left.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new left_fragment();
            }

            @Override
            public int getCount() {
                return 1;
            }
        });

        right.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new RigthtFragment();
            }

            @Override
            public int getCount() {
                return 1;
            }
        });

    }

}
