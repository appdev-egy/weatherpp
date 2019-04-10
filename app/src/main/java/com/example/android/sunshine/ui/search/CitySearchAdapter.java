package com.example.android.sunshine.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.android.sunshine.R;

import java.util.ArrayList;

public class CitySearchAdapter extends BaseAdapter implements Filterable {
public Context context;
public java.util.ArrayList<CityItem> cityArrayList;
public ArrayList<CityItem> orig;

public CitySearchAdapter(Context context, ArrayList<CityItem> cityArrayList) {
        super();
        this.context = context;
        this.cityArrayList = cityArrayList;
        }


public class SearchHolder
{
    TextView name;
    TextView age;
}

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<CityItem> results = new ArrayList<CityItem>();
                if (orig == null)
                    orig = cityArrayList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final CityItem g : orig) {
                            if (g.getName().toLowerCase()
                                    .contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                cityArrayList = (ArrayList<CityItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return cityArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return cityArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchHolder holder;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.row, parent, false);
            holder=new SearchHolder();
            holder.name=(TextView) convertView.findViewById(R.id.txtName);
            convertView.setTag(holder);
        }
        else
        {
            holder=(SearchHolder) convertView.getTag();
        }

        holder.name.setText(cityArrayList.get(position).getName());

        return convertView;

    }

}

