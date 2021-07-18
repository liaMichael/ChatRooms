package com.example.chatrooms.ui.screens;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.chatrooms.R;
import com.example.chatrooms.model.ChatBubble;
import com.example.chatrooms.model.ChatRoomType;
import com.example.chatrooms.model.adapters.ChatBubbleAdapter;

import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {
    private ChatRoomType chatRoomType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.chat_room_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // Check the kind of chat room
        chatRoomType = (ChatRoomType) getIntent().getSerializableExtra("chatRoomType");
        if (chatRoomType == null) {
            chatRoomType = ChatRoomType.PRIVATE;
        }

        // Chat bubbles ArrayList
        ArrayList<ChatBubble> chatBubbles = new ArrayList<>();
        // TODO: Go through database and fill the ArrayList.
        for (int i = 0; i < 50; i++) {
            if (i % 3 == 0) {
                switch (chatRoomType) {
                    case GROUP:
                        chatBubbles.add(new ChatBubble(
                                "hello",
                                "15:54",
                                "blank_avatar",
                                "Lia")
                        );
                        break;
                    case PRIVATE:
                        chatBubbles.add(new ChatBubble(
                                false,
                                true,
                                "hello",
                                "15:54")
                        );
                        break;
                }
            } else {
                chatBubbles.add(new ChatBubble(
                        true,
                        true,
                        "hi",
                        "12:37")
                );
            }
        }

        // Chat bubbles RecyclerView
        RecyclerView chatBubblesRecyclerView = findViewById(R.id.chat_room_activity_recyclerview_messages);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true);
        chatBubblesRecyclerView.setLayoutManager(layoutManager);
        ChatBubbleAdapter chatBubbleAdapter = new ChatBubbleAdapter(chatBubbles);
        chatBubblesRecyclerView.setAdapter(chatBubbleAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat_room_private, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ChatRoomActivity.this, ChatRoomListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}