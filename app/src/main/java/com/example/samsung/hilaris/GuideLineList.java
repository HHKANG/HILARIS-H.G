package com.example.samsung.hilaris;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import com.example.expandable3.R;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class GuideLineList extends AppCompatActivity {

    List<NLevelItem> list;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guideline_list);
        listView = (ListView) findViewById(R.id.listView1);
        list = new ArrayList<NLevelItem>();
        Random rng = new Random();
        final LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 1; i < 6; i++) {

            final NLevelItem grandParent = new NLevelItem(new SomeObject("Date "+i),null, new NLevelView() {

                @Override
                public View getView(NLevelItem item) {
                    View view = inflater.inflate(R.layout.list_item, null);
                    TextView tv = (TextView) view.findViewById(R.id.textView);
                    String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                    tv.setText(name);
                    return view;
                }
            });
            list.add(grandParent);
            int numChildren = rng.nextInt(4) + 1;
            for (int j = 1; j < numChildren+1; j++) {
                NLevelItem parent = new NLevelItem(new SomeObject("->Guideline "+j),grandParent, new NLevelView() {

                    @Override
                    public View getView(NLevelItem item) {
                        View view = inflater.inflate(R.layout.list_item, null);
                        TextView tv = (TextView) view.findViewById(R.id.textView);
                        tv.setPadding(50,0,0,0);
                        String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                        tv.setText(name);
                        return view;
                    }
                });

                list.add(parent);
                int grandChildren = rng.nextInt(5)+1;
                for( int k = 1; k < grandChildren+1; k++) {
                    NLevelItem child = new NLevelItem(new SomeObject("-->routine "+k),parent, new NLevelView() {

                        @Override
                        public View getView(NLevelItem item) {
                            View view = inflater.inflate(R.layout.list_item, null);
                            TextView tv = (TextView) view.findViewById(R.id.textView);
                            tv.setPadding(100,0,0,0);
                            String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                            tv.setText(name);
                            return view;
                        }
                    });

                    list.add(child);
                }
            }
        }

        NLevelAdapter adapter = new NLevelAdapter(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                ((NLevelAdapter)listView.getAdapter()).toggle(arg2);
                ((NLevelAdapter)listView.getAdapter()).getFilter().filter();

            }
        });
    }

    class SomeObject {
        public String name;

        public SomeObject(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

}
