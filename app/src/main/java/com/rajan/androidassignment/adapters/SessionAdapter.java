package com.rajan.androidassignment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rajan.androidassignment.R;
import com.rajan.androidassignment.models.FocusSession;

import java.util.List;

public class SessionAdapter extends ArrayAdapter<FocusSession> {

    private Context context;
    private List<FocusSession> sessions;

    public SessionAdapter(Context context, List<FocusSession> sessions) {
        super(context, 0, sessions);
        this.context = context;
        this.sessions = sessions;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_session, parent, false);
            holder = new ViewHolder();
            holder.tvSessionTitle = convertView.findViewById(R.id.tvSessionTitle);
            holder.tvSessionDateTime = convertView.findViewById(R.id.tvSessionDateTime);
            holder.tvFocusTime = convertView.findViewById(R.id.tvFocusTime);
            holder.tvCompletionBadge = convertView.findViewById(R.id.tvCompletionBadge);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        FocusSession session = sessions.get(position);

        // Set session details
        holder.tvSessionTitle.setText(session.getSessionType());
        holder.tvSessionDateTime.setText(session.getFormattedDateTime());
        holder.tvFocusTime.setText(session.getActualFocusMinutes() + " min");

        // Show/hide completion badge
        if (session.isCompleted()) {
            holder.tvCompletionBadge.setVisibility(View.VISIBLE);
        } else {
            holder.tvCompletionBadge.setVisibility(View.GONE);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView tvSessionTitle;
        TextView tvSessionDateTime;
        TextView tvFocusTime;
        TextView tvCompletionBadge;
    }
}
