package ufu.ecotravel.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ufu.ecotravel.R;

/**
 * Created by Lucas on 12/11/2017.
 */

public class FragmentRoteiro extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_display_detais_roteiro, container, false);

        return view;
    }

}
