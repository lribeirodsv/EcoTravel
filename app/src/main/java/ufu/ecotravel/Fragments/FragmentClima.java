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

public class FragmentClima extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_display_detais_clima, container, false);

        return view;
    }

}
