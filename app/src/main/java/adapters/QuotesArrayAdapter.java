package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.imcodebased.quotesmate.R;

import java.util.List;

import model.Quote;

public class QuotesArrayAdapter extends ArrayAdapter<Quote> {

    private final LayoutInflater inflater;
    private final int resource;

    public QuotesArrayAdapter(Context context, int resource, List<Quote> objects) {
        super(context, resource, objects);

        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.resource = resource;
    }

    public class Holder {
        TextView headerView;
        TextView subHeaderView;
        ImageView img;
    }

//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//
//        Holder holder;
//        if (convertView == null) {
//            holder = new Holder();
//            convertView = inflater.inflate(resource, null);
//            convertView.setTag(holder);
//        } else {
//            holder = (Holder) convertView.getTag();
//        }
//
//
//        holder.headerView = (TextView) convertView.findViewById(R.id.headerView);
//        holder.subHeaderView = (TextView) convertView.findViewById(R.id.subHeaderView);
//        holder.img = (ImageView) convertView.findViewById(R.id.leftImageView);
//        holder.headerView.setText(getItem(position).getQuote());
//        holder.subHeaderView.setText(getItem(position).getAuthor());
////        rowView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                // TODO Auto-generated method stub
////                Toast.makeText(getContext(), "You Clicked " + getItem(position).getQuote(), Toast.LENGTH_LONG).show();
////            }
////        });
//
//        return convertView;
//    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        Holder holder;
        holder = new Holder();
        convertView = inflater.inflate(R.layout.list_item, null);
        holder.headerView = (TextView) convertView.findViewById(R.id.headerView);
        holder.subHeaderView = (TextView) convertView.findViewById(R.id.subHeaderView);
        holder.img = (ImageView) convertView.findViewById(R.id.leftImageView);
        holder.headerView.setText(getItem(position).getQuote());
        holder.subHeaderView.setText(getItem(position).getAuthor());
//        rowView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Toast.makeText(getContext(), "You Clicked " + getItem(position).getQuote(), Toast.LENGTH_LONG).show();
//            }
//        });

        return convertView;
    }
}
