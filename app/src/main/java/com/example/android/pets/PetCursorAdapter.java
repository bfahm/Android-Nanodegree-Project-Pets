package com.example.android.pets;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.pets.data.PetContract;

/**
 * {@link PetCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of pet data as its data source. This adapter knows
 * how to create list items for each row of pet data in the {@link Cursor}.
 */
public class PetCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link PetCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public PetCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvPetName = (TextView)view.findViewById(R.id.name);
        TextView tvPetSummary = (TextView)view.findViewById(R.id.summary);
        TextView tvPetWeight = (TextView)view.findViewById(R.id.weight_id);

        String petName = cursor.getString(cursor.getColumnIndexOrThrow(PetContract.PetEntry.COLUMN_PET_NAME));
        String petBreed = cursor.getString(cursor.getColumnIndexOrThrow(PetContract.PetEntry.COLUMN_PET_BREED));
        String petWeight = cursor.getString(cursor.getColumnIndexOrThrow(PetContract.PetEntry.COLUMN_PET_WEIGHT));
        int petGender = cursor.getInt(cursor.getColumnIndexOrThrow(PetContract.PetEntry.COLUMN_PET_GENDER));

        tvPetName.setText(petName);
        tvPetWeight.setText(petWeight);
        if(TextUtils.isEmpty(petBreed)){
            tvPetSummary.setText(R.string.unknown_breed);
        }else{
            tvPetSummary.setText(petBreed);
        }

        View genderCircle = view.findViewById(R.id.genderCircle);
        GradientDrawable genderCircleBackground = (GradientDrawable)genderCircle.getBackground();
        genderCircleBackground.setColor(colorSelector(petGender, context));
    }

    private int colorSelector(int petGender, Context context){
        int resultColor;
        switch (petGender){
            case PetContract.PetEntry.GENDER_MALE:
                resultColor = R.color.malePet;
                break;
            case PetContract.PetEntry.GENDER_FEMALE:
                resultColor = R.color.colorAccent;
                break;
            default:
                resultColor = R.color.unknknownPet;
                break;
        }
        return ContextCompat.getColor(context, resultColor);
    }
}