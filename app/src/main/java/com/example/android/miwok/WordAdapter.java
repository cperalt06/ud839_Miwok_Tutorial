package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word>{

    /* Resource ID for the background color for the list of words. */
    private int mBackgroundColor;

    public WordAdapter(@NonNull Context context, ArrayList<Word> words, int backgroundColor) {
        super(context, 0, words);
        mBackgroundColor = backgroundColor;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        // Check if the view is currently being reused, otherwise inflate the view.
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false
            );
        }


        // Get the {@link WordAdapter} object located at this position in the list.
        Word currentWord = getItem(position);

        // Find the textView in the list_item.xml layout with the ID default_text_view
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the textView in the list_item.xml layout with the ID default_text_view
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);

        if(currentWord.getHasImage()) {
            iconView.setImageResource(currentWord.getImageResourceId());
            iconView.setVisibility(View.VISIBLE);
        }
        else {
            iconView.setVisibility(View.GONE);
        }

        // Set the theme color for the list item.
        View list_background = listItemView.findViewById(R.id.list_background);
        // Find the color that the resource ID maps to.
        int color = ContextCompat.getColor(getContext(), mBackgroundColor);
        // Set the background color of the text container view.
        list_background.setBackgroundColor(color);

        return listItemView;
    }
}
