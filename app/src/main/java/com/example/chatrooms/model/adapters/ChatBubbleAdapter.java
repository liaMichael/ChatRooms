package com.example.chatrooms.model.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatrooms.R;
import com.example.chatrooms.model.ChatBubble;
import com.example.chatrooms.model.utils.Utils;

import java.util.ArrayList;

public class ChatBubbleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ChatBubble> chatBubbles;

    public ChatBubbleAdapter(ArrayList<ChatBubble> chatBubbles) {
        this.chatBubbles = chatBubbles;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatBubbleView;
        switch (viewType) {
            case 0: // sender
                chatBubbleView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.recyclerviewitem_chat_bubble_sender,
                        parent,
                        false
                );
                return new ChatBubbleSenderViewHolder(chatBubbleView);
            case 1: // receiver (private chat room)
                chatBubbleView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.recyclerviewitem_chat_bubble_receiver,
                        parent,
                        false
                );
                return new ChatBubbleReceiverViewHolder(chatBubbleView);
            default: // receiver (group chat room)
                chatBubbleView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.recyclerviewitem_chat_bubble_receiver_group,
                        parent,
                        false
                );
                return new ChatBubbleReceiverGroupViewHolder(chatBubbleView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatBubble currentChatBubble = chatBubbles.get(position);
        switch (holder.getItemViewType()) {
            case 0: // sender
                ChatBubbleSenderViewHolder senderViewHolder = (ChatBubbleSenderViewHolder) holder;
                senderViewHolder.msgTextView.setText(currentChatBubble.getMsg());
                senderViewHolder.timeTextView.setText(currentChatBubble.getTime());
                break;
            case 1: // receiver (private chat room)
                ChatBubbleReceiverViewHolder receiverViewHolder = (ChatBubbleReceiverViewHolder) holder;
                receiverViewHolder.msgTextView.setText(currentChatBubble.getMsg());
                receiverViewHolder.timeTextView.setText(currentChatBubble.getTime());
                break;
            default: // receiver (group chat room)
                ChatBubbleReceiverGroupViewHolder receiverGroupViewHolder = (ChatBubbleReceiverGroupViewHolder) holder;
                receiverGroupViewHolder.msgTextView.setText(currentChatBubble.getMsg());
                receiverGroupViewHolder.timeTextView.setText(currentChatBubble.getTime());
                receiverGroupViewHolder.avatarImageView.setImageResource(Utils.findDrawableResourceByName(
                        receiverGroupViewHolder.avatarImageView.getContext(),
                        currentChatBubble.getAvatar())
                );
                receiverGroupViewHolder.nameTextView.setText(currentChatBubble.getName());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return chatBubbles.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (chatBubbles.get(position).isSender()) {
            return 0; // sender
        } else if (chatBubbles.get(position).isPrivate()) {
            return 1; // receiver (private chat room)
        }
        return 2; // receiver (group chat room)
    }

    public static class ChatBubbleSenderViewHolder extends RecyclerView.ViewHolder {
        public TextView msgTextView;
        public TextView timeTextView;

        public ChatBubbleSenderViewHolder(@NonNull View itemView) {
            super(itemView);
            msgTextView = itemView.findViewById(R.id.recyclerviewitem_chat_bubble_sender_textview_message);
            timeTextView = itemView.findViewById(R.id.recyclerviewitem_chat_bubble_sender_textview_time);
        }
    }

    public static class ChatBubbleReceiverViewHolder extends RecyclerView.ViewHolder {
        public TextView msgTextView;
        public TextView timeTextView;

        public ChatBubbleReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            msgTextView = itemView.findViewById(R.id.recyclerviewitem_chat_bubble_receiver_textview_message);
            timeTextView = itemView.findViewById(R.id.recyclerviewitem_chat_bubble_receiver_textview_time);
        }
    }

    public static class ChatBubbleReceiverGroupViewHolder extends RecyclerView.ViewHolder {
        public TextView msgTextView;
        public TextView timeTextView;
        public ImageView avatarImageView;
        public TextView nameTextView;

        public ChatBubbleReceiverGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            msgTextView = itemView.findViewById(R.id.recyclerviewitem_chat_bubble_receiver_group_textview_message);
            timeTextView = itemView.findViewById(R.id.recyclerviewitem_chat_bubble_receiver_group_textview_time);
            avatarImageView = itemView.findViewById(R.id.recyclerviewitem_chat_bubble_receiver_group_imageview_avatar);
            nameTextView = itemView.findViewById(R.id.recyclerviewitem_chat_bubble_receiver_group_textview_name);
        }
    }
}
