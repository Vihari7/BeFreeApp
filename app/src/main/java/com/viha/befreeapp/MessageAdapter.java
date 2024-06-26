package com.viha.befreeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
public class MessageAdapter extends BaseAdapter {

    private Context context;
    private int layoutResourceId;
    private List<ChatMessage> messages;
    private LayoutInflater inflater;

    public MessageAdapter(Context context, int layoutResourceId, List<ChatMessage> messages) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.messages = messages;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(layoutResourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewMessage = convertView.findViewById(R.id.textViewMessage);
            viewHolder.textViewTimestamp = convertView.findViewById(R.id.textViewTimestamp);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ChatMessage message = messages.get(position);
        viewHolder.textViewMessage.setText(message.getMessage());
        viewHolder.textViewTimestamp.setText(formatTimestamp(message.getTimestamp()));

        return convertView;
    }

    private String formatTimestamp(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    private static class ViewHolder {
        TextView textViewMessage;
        TextView textViewTimestamp;
    }
}