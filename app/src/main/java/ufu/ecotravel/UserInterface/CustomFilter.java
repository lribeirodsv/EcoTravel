package ufu.ecotravel.UserInterface;

import android.widget.Filter;
import java.util.ArrayList;
import ufu.ecotravel.Classes.City;

/**
 * Created by Lucas on 03/12/2017.
 */

public class CustomFilter extends Filter {
    DisplayListCitiesAdapter adapter;
    ArrayList<City> filterList;
    public CustomFilter(ArrayList<City> filterList,DisplayListCitiesAdapter adapter)
    {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        if(constraint != null && constraint.length() > 0)
        {
            constraint=constraint.toString().toUpperCase();
            ArrayList<City> filteredCities = new ArrayList<>();
            for (int i=0;i<filterList.size();i++)
            {
                if(filterList.get(i).getNome().toUpperCase().contains(constraint))
                {
                    filteredCities.add(filterList.get(i));
                }
            }
            results.count=filteredCities.size();
            results.values=filteredCities;
        } else
        {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.arrayList = (ArrayList<City>) results.values;
        adapter.notifyDataSetChanged();
    }
}