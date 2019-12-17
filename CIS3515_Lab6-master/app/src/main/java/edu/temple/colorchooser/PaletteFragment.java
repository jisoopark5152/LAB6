package edu.temple.colorchooser;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PaletteFragment extends Fragment {

    String[] colors;

    public final static String PLANET_KEY = "colors";

    private ColorSelectedInterface fragmentParent;

    public PaletteFragment() {
        // Required empty public constructor
    }

    //create one instance of this class
    public  static PaletteFragment newInstance (String[] colors){
        PaletteFragment paletteFragment = new PaletteFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("colors", colors);
        paletteFragment.setArguments(bundle);

        return paletteFragment;
    }

    @Override   //pass color strings to this class
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle bundle = getArguments();
        if(bundle != null){
            colors = bundle.getStringArray(PLANET_KEY);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_palette, container, false);

        ListView listView = (ListView)inflater.inflate(R.layout.fragment_palette, container, false);

        ArrayAdapter adapter = new ArrayAdapter<String>((Context) fragmentParent, android.R.layout.simple_list_item_1, colors);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fragmentParent.colorSelected(parent.getItemAtPosition(position).toString());
            }
        });
        return listView;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof ColorSelectedInterface){
            fragmentParent = (ColorSelectedInterface) context;
        }

    }

    @Override
    public void onDetach(){
        super.onDetach();
        fragmentParent = null;
    }

    //interface definition
    public interface ColorSelectedInterface {
        void colorSelected(String colorName);
    }
}
