package ddwucom.mobile.report01;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MyCursorAdapter extends CursorAdapter {

    LayoutInflater inflater;
    int layout;

    public MyCursorAdapter(Context context, int layout, Cursor c) {
        super(context, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = inflater.inflate(layout, parent,  false);
        ViewHolder holder = new ViewHolder();
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();

        if(holder.tvContactName == null) {
            holder.tvContactName = view.findViewById(R.id.tvName);
            holder.tvContactTitle = view.findViewById(R.id.tvTitle);
            holder.tvContactArtist = view.findViewById(R.id.tvArtist);
        }

        holder.tvContactName.setText(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_NAME)));
        holder.tvContactTitle.setText(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_MUSIC_TITLE)));
        holder.tvContactArtist.setText(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_ARTIST)));
    }

    static class ViewHolder {
        public ViewHolder() {
            tvContactName = null;
            tvContactTitle = null;
            tvContactArtist = null;
        }
        TextView tvContactName;
        TextView tvContactTitle;
        TextView tvContactArtist;
    }
}
