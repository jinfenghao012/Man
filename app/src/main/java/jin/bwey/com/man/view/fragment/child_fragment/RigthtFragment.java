package jin.bwey.com.man.view.fragment.child_fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jin.bwey.com.man.R;

/**
 * Created by Administrator on 2017/7/19.
 */

public class RigthtFragment extends Fragment {

    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_child_right, container, false);


        return view;
    }


}
