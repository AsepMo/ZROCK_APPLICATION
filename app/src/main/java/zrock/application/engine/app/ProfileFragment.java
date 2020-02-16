package zrock.application.engine.app;

import zrock.application.R;
import zrock.application.engine.widget.SyncTextPathView;
import zrock.application.engine.widget.painter.PenPainter;
import zrock.application.engine.widget.transition.DragLayout;
import zrock.application.engine.widget.transition.CustPagerTransformer;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment
{
	private SyncTextPathView profile;
	private TextView indicatorTv;
    private View positionView;
    private ViewPager viewPager;
    private List<CommonFragment> fragments = new ArrayList<>(); // 供ViewPager使用
    private final int[] imageArray = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5
	};
	public View rootView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		rootView = inflater.inflate(R.layout.fragment_application_profile ,container ,false);
		positionView = rootView.findViewById(R.id.position_view);
        //dealStatusBar(); // 调整状态栏高度


        fillViewPager(rootView);
		return rootView;
	}

	private void fillViewPager(View view) {
		float pf = 1000f;
		profile = (SyncTextPathView) view.findViewById(R.id.tv_country_2);
		profile.setPathPainter(new PenPainter());
		profile.drawPath(pf);
		profile.setFillColor(true);
		profile.startAnimation(0,1);
		
        indicatorTv = (TextView) view.findViewById(R.id.indicator_tv);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        // 1. viewPager添加parallax效果，使用PageTransformer就足够了
        viewPager.setPageTransformer(false, new CustPagerTransformer(getActivity()));

        // 2. viewPager添加adapter
        for (int i = 0; i < 10; i++) {
            // 预先准备10个fragment
            fragments.add(new CommonFragment());
        }

        viewPager.setAdapter(new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
				@Override
				public Fragment getItem(int position) {
					CommonFragment fragment = fragments.get(position % 10);
					fragment.bindData(imageArray[position % imageArray.length]);
					return fragment;
				}

				@Override
				public int getCount() {
					return 666;
				}
			});


        // 3. viewPager滑动时，调整指示器
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
				@Override
				public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				}

				@Override
				public void onPageSelected(int position) {
					updateIndicatorTv();
				}

				@Override
				public void onPageScrollStateChanged(int state) {

				}
			});

        updateIndicatorTv();
    }

    /**
     * 更新指示器
     */
    private void updateIndicatorTv() {
        int totalNum = viewPager.getAdapter().getCount();
        int currentItem = viewPager.getCurrentItem() + 1;
        indicatorTv.setText(Html.fromHtml("<font color='#12edf0'>" + currentItem + "</font>  /  " + totalNum));
    }

}
