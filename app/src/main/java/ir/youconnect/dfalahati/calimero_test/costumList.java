package ir.youconnect.dfalahati.calimero_test;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by d.falahati on 9/2/2015.
 */
public class costumList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    public costumList(Activity context , String[] web , Integer imageId[] ){
        super(context,R.layout.list_single,web);
        this.context=context;
        this.web=web;
        this.imageId=imageId;
    }
    @Override
    public View getView(int position , View view , ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single, null, true);
        TextView texTitle = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        imageView.setImageResource(imageId[position]);
        texTitle.setText(web[position]);
        return rowView;

    }

}
