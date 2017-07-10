package eu.opengovintelligence.admin.opengovintelligence;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Admin on 28/6/2017.
 */

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment,null);

        Fragment childFragment = new ParametersFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame2, childFragment).commit();


        TabLayout tabs = (TabLayout) v.findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Parameters"));
        tabs.addTab(tabs.newTab().setText("Graph"));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                /*Bundle bundle = new Bundle();
                bundle.putString("today_option",(String)tab.getText());
                mFirebaseAnalytics.logEvent("today_tabs",bundle);*/
                if (tab.getPosition()==0){
                    if(CallHolder.getChildFragment()==null)
                        CallHolder.setChildFragment(new ParametersFragment()) ;
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right);
                    transaction.replace(R.id.mainFrame2, CallHolder.getChildFragment()).commit();
                }
                else if(tab.getPosition()==1){
                    CallHolder.MakeTableCall(getContext());
                    CallHolder.setLoadingDialog(new ProgressDialog(getActivity()));
                    CallHolder.getLoadingDialog().setTitle("Loading data");
                    CallHolder.getLoadingDialog().setMessage("Loading Data");
                    CallHolder.getLoadingDialog().setCancelable(false);
                    CallHolder.getLoadingDialog().show();
                    Fragment childFragment = new GraphFragment();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
                    transaction.replace(R.id.mainFrame2, childFragment).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        return v;
    }

}
