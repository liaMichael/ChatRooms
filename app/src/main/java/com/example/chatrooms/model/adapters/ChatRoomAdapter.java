package com.example.chatrooms.model.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatrooms.R;
import com.example.chatrooms.model.ChatRoom;
import com.example.chatrooms.model.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ChatRoomViewHolder> implements Filterable {
    private ArrayList<ChatRoom> chatRooms;
    private ArrayList<ChatRoom> chatRoomsFull;
    private RecyclerViewClickListener listener;
    private Filter chatRoomFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<ChatRoom> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(chatRoomsFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (ChatRoom chatRoom : chatRoomsFull) {
                    if (chatRoom.getName().toLowerCase().contains(filterPattern)) { // the view that contains the words we are searching for
                        filteredList.add(chatRoom);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            chatRooms.clear();
            chatRooms.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public ChatRoomAdapter(ArrayList<ChatRoom> chatRooms, RecyclerViewClickListener listener) {
        this.chatRooms = chatRooms;
        this.listener = listener;
        chatRoomsFull = new ArrayList<>(chatRooms);
    }

    @NonNull
    @Override
    public ChatRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ChatRoomView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recyclerviewitem_chat_room,
                parent,
                false
        );
        return new ChatRoomViewHolder(ChatRoomView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRoomViewHolder holder, int position) {
        ChatRoom currentChatRoom = chatRooms.get(position);
        holder.nameTextView.setText(currentChatRoom.getName());
        holder.lastMsgTextView.setText(currentChatRoom.getLastMsg());
        holder.timeTextView.setText(currentChatRoom.getTime());
        holder.avatarImageView.setImageResource(Utils.findDrawableResourceByName(
                holder.avatarImageView.getContext(),
                currentChatRoom.getAvatar())
        );
        if (currentChatRoom.getNumOfUnreadMsgs() <= 0) {
            holder.numOfUnreadMsgsTextView.setVisibility(View.INVISIBLE);
        } else {
            holder.numOfUnreadMsgsTextView.setVisibility(View.VISIBLE);
            String numOfUnreadMsgs = currentChatRoom.getNumOfUnreadMsgs() + "";
            holder.numOfUnreadMsgsTextView.setText(numOfUnreadMsgs);
        }
    }

    @Override
    public int getItemCount() {
        return chatRooms.size();
    }

    @Override
    public Filter getFilter() {
        return chatRoomFilter;
    }

    public static class ChatRoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameTextView;
        public TextView lastMsgTextView;
        public TextView timeTextView;
        public ImageView avatarImageView;
        public TextView numOfUnreadMsgsTextView;

        public RecyclerViewClickListener rListener;

        public ChatRoomViewHolder(@NonNull View itemView, RecyclerViewClickListener recyclerViewClickListener) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.recyclerviewitem_chat_room_textview_name);
            lastMsgTextView = itemView.findViewById(R.id.recyclerviewitem_chat_room_textview_last_message);
            timeTextView = itemView.findViewById(R.id.recyclerviewitem_chat_room_textview_time);
            avatarImageView = itemView.findViewById(R.id.recyclerviewitem_chat_room_imageview_avatar);
            numOfUnreadMsgsTextView = itemView.findViewById(R.id.recyclerviewitem_chat_room_textview_number_of_unread_messages);

            rListener = recyclerViewClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            rListener.OnClick(getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener {
        void OnClick(int position);
    }
}
