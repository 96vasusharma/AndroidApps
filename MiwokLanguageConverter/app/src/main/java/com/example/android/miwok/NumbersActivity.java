package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //array of numbers in english
        //String[] words= new String[10];

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti"));
        words.add(new Word("two", "otiiko"));
        words.add(new Word("three", "tolookosu"));
        words.add(new Word("four", "oyyisa"));
        words.add(new Word("five", "massokka"));
        words.add(new Word("six", "temmokka"));
        words.add(new Word("seven", "kenekaku"));
        words.add(new Word("eight", "kawinta"));
        words.add(new Word("nine", "wo’e"));
        words.add(new Word("ten", "na’aacha"));


        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this,words);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

//
//        Log.v("NumbersActivity","at index 0 the Word is "+words.get(0));
//        Log.v("NumbersActivity","at index 1 the Word is "+words.get(1));
//
//
//        int index=0;
//        while (index<10)
//        {
//            TextView textView =new TextView(this);
//            textView.setText(words.get(index));
//            rootView.addView(textView);
//            index+=1;
//        }
//
//
//
//
//       // LinearLayout rootView =(LinearLayout)findViewById(R.id.rootView);
//
//        for (int index=0;index<words.size();index++){
//            TextView textView = new TextView(this);
//            textView.setText(words.get(index));
//            rootView.addView(textView);
//        }
//
//
//        ArrayAdapter<Word> itemsAdapter = new
//                ArrayAdapter<Word>(this, R.layout.list_item, words);
//        //ArrayAdapter<Word> ad=new ArrayAdapter<Word>(this,R.layout.list_item,words);
//
//        ListView listView = (ListView) findViewById(R.id.list);
//
//        listView.setAdapter(itemsAdapter);
    }
}
