package student.ppjava13v1.itstep.notecase.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.TimeUnit;

import student.ppjava13v1.itstep.notecase.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {


    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SplashTack asyncTask = new SplashTack();
        asyncTask.execute();
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    class SplashTack extends AsyncTask<Void ,Void , Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(getActivity() != null) {

                getActivity().getFragmentManager().popBackStack();
            }

            return null;
        }
    }


}
