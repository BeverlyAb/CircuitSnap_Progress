package com.example.micaflor.cs2;

/**
 * Displays Circuit Schematic
 */

/*
public class Grid_Viewer extends AppCompatActivity {
    private Context mContext;
    public Grid_Viewer ( Context c ) { mContext = c; }
    public int getCount() { return mThumbIds.length; }
    public Object getItem ( int position ) { return null; }
    public long getItemId ( int position ) { return 0; }

    // create a new ImageView for each item referenced by the Adapter
    public View getView ( int position, View convertView, ViewGroup parent ) {
        ImageView imageView;

        if ( convertView == null ) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView ( mContext );
            imageView.setLayoutParams ( new GridView.LayoutParams ( 85, 85 ) );
            imageView.setScaleType ( ImageView.ScaleType.CENTER_CROP );
        }
        else
            imageView = ( ImageView ) convertView;

        imageView.setImageResource(mThumbIds [position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {};

    public void editint(int n) {
        int number_of_columns = ( 2 * n ) + 3;
        mThumbIds = new Integer [ number_of_columns * 3 ];	//Other color schemes exist for the components, and are available
        //Within the drawable files
        for ( int i = 0; i < number_of_columns * 3; i++ ) {
            if ( i < number_of_columns) //blank
                mThumbIds [ i ] = ( R.drawable.empty_blueprint );
            else if ( number_of_columns == i ) //power
                mThumbIds [ i ] = ( R.drawable.power_blueprint );
            else if ( number_of_columns < i && ( number_of_columns * 2 ) -1 > i ) {
                if ( i % 2 == 0 )
                    mThumbIds [ i ] = ( R.drawable.wire_horz_blueprint ); //wire
                if ( i % 2 == 1 )
                    mThumbIds [ i ] = ( R.drawable.resist_horz_blueprint ); //resistor
            }
            else if ( i == ( number_of_columns * 2 ) -1 )
                mThumbIds [ i ] = ( R.drawable.ground_blueprint ); //Ground
            else
                mThumbIds [ i ] = ( R.drawable.empty_blueprint ); //blank
        }
    } // grid
}
*/
