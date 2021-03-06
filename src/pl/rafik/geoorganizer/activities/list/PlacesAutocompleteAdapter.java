package pl.rafik.geoorganizer.activities.list;

import java.util.ArrayList;

import pl.rafik.geoorganizer.services.data.AutocompleteService;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

public class PlacesAutocompleteAdapter extends ArrayAdapter<String> implements
		Filterable {
	private ArrayList<String> resultList;
	private AutocompleteService autoService;

	public PlacesAutocompleteAdapter(Context context, int textViewResourceId,
			AutocompleteService service) {
		super(context, textViewResourceId);
		this.autoService = service;
	}

	@Override
	public int getCount() {
		return resultList.size();
	}

	@Override
	public String getItem(int index) {
		return resultList.get(index);
	}

	@Override
	public Filter getFilter() {
		Filter filter = new Filter() {
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				FilterResults filterResults = new FilterResults();
				if (constraint != null) {
					// Retrieve the autocomplete results.
					resultList = autoService
							.autocomplete(constraint.toString());

					// Assign the data to the FilterResults
					filterResults.values = resultList;
					filterResults.count = resultList.size();
				}
				return filterResults;
			}

			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				if (results != null && results.count > 0) {
					notifyDataSetChanged();
				} else {
					notifyDataSetInvalidated();
				}
			}
		};
		return filter;
	}
}
