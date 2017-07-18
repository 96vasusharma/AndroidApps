package com.example.android.miwoklanguageconverter2;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vasu Sharma on 28-08-2016.
 */
public class WordAdapter extends ArrayAdapter<Word> {
    int specificColor;
    MediaPlayer mediaPlayer;
    public WordAdapter(Context context, ArrayList<Word> wordArrayAdapter
            , int color){
        super(context,0,wordArrayAdapter);
        specificColor = color;
    }
    @NonNull
   @Override
    public View getView(int position, View convertView,@NonNull ViewGroup parent) {
       // Check if the existing view is being reused, otherwise inflate the view
       View listItemView = convertView;
       if(listItemView == null) {
           listItemView = LayoutInflater.from(getContext()).inflate(
                   R.layout.list_item, parent, false);
       }


       // Get the {@link AndroidFlavor} object located at this position in the list
       final Word currentWord = getItem(position);
       //specific picture of each element
       ImageView imageView =(ImageView)listItemView.findViewById(R.id.image);
       if(currentWord.getImageResourceId()!=0){

           imageView.setImageResource(currentWord.getImageResourceId());

       }
       else {
            imageView.setVisibility(View.GONE);
       }

        //specific audio of each element
//        ImageView audio = (ImageView)listItemView.findViewById(R.id.play_button);
//        audio.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mediaPlayer= MediaPlayer.create(v.getContext(),
//                        currentWord.getAudioResourceId());
//                mediaPlayer.start();
//
//            }
//        });


       // Find the TextView in the list_item.xml layout with the ID version_name
       TextView defaultTextView =
               (TextView) listItemView.findViewById(R.id.default_text_view);
       // Get the version name from the current AndroidFlavor object and
       // set this text on the name TextView
       defaultTextView.setText(currentWord.getDefaultTranslation());

       // Find the TextView in the list_item.xml layout with the ID version_number
       TextView miwokTextView =
               (TextView) listItemView.findViewById(R.id.miwok_text_view);
       // Get the version number from the current AndroidFlavor object and
       // set this text on the number TextView
       miwokTextView.setText(currentWord.getMiwokTranslation());

//       // Find the ImageView in the list_item.xml layout with the ID list_item_icon
//       ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
//       // Get the image resource ID from the current AndroidFlavor object and
//       // set the image to iconView
//       iconView.setImageResource(currentAndroidFlavor.getImageResourceId());

       // Return the whole list item layout (containing 2 TextViews and an ImageView)
       // so that it can be shown in the ListView


       // Set the theme color for the list item
       View textContainer = listItemView.findViewById(R.id.variableBackground);
       // Find the color that the resource ID maps to
       int color = ContextCompat.getColor(getContext(), specificColor);
       // Set the background color of the text container View
       textContainer.setBackgroundColor(color);

       return listItemView;
    }
}
