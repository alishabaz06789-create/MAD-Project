package com.example.smartnotes;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note> notesList;
    private OnNoteListener onNoteListener;

    public interface OnNoteListener {
        void onNoteClick(int position);
        void onNoteLongClick(int position);
    }

    public NotesAdapter(List<Note> notesList, OnNoteListener onNoteListener) {
        this.notesList = notesList;
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notesList.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvContent.setText(note.getContent());
        holder.tvDate.setText(note.getDate());
        holder.tvCategory.setText(note.getCategory());

        // Apply Category Colors
        int color = getCategoryColor(note.getCategory());
        holder.cardView.setCardBackgroundColor(color);

        // Apply tint to the category text background badge
        if (holder.tvCategory.getBackground() != null) {
            holder.tvCategory.getBackground().setTint(darkenColor(color));
        }

        holder.itemView.setOnClickListener(v -> onNoteListener.onNoteClick(position));
        holder.itemView.setOnLongClickListener(v -> {
            onNoteListener.onNoteLongClick(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    private int getCategoryColor(String category) {
        if (category == null) return Color.parseColor("#F5F5F5");
        switch (category) {
            case "Work": return Color.parseColor("#FFE0B2");
            case "Personal": return Color.parseColor("#C8E6C9");
            case "Study": return Color.parseColor("#BBDEFB");
            default: return Color.parseColor("#F5F5F5");
        }
    }

    private int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvTitle, tvContent, tvDate, tvCategory;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            tvTitle = itemView.findViewById(R.id.tv_note_title);
            tvContent = itemView.findViewById(R.id.tv_note_content);
            tvDate = itemView.findViewById(R.id.tv_note_date);
            tvCategory = itemView.findViewById(R.id.tv_note_category);
        }
    }
}
