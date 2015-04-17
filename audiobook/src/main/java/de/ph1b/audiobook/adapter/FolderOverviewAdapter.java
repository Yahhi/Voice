package de.ph1b.audiobook.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import de.ph1b.audiobook.R;

public class FolderOverviewAdapter extends RecyclerView.Adapter<FolderOverviewAdapter.ViewHolder> {

    @NonNull
    private final ArrayList<String> folders;
    @NonNull
    private final OnFolderMoreClickedListener listener;

    public FolderOverviewAdapter(@NonNull final ArrayList<String> folders,
                                 @NonNull final OnFolderMoreClickedListener listener) {
        this.folders = folders;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.activity_folder_overview_row_layout, parent, false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String folder = folders.get(position);
        holder.textView.setText(folder);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return folders.size();
    }

    public void removeItem(final int position) {
        folders.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    public String getItem(final int position) {
        return folders.get(position);
    }

    public interface OnFolderMoreClickedListener {
        void onFolderMoreClicked(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final ImageButton imageButton;
        final TextView textView;

        public ViewHolder(final View itemView, final OnFolderMoreClickedListener listener) {
            super(itemView);
            imageButton = (ImageButton) itemView.findViewById(R.id.remove);
            textView = (TextView) itemView.findViewById(R.id.containing);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onFolderMoreClicked(getAdapterPosition());
                }
            });
        }
    }
}
