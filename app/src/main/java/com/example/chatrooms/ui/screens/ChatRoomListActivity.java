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
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.example.chatrooms.R;
import com.example.chatrooms.model.ChatRoom;
import com.example.chatrooms.model.ChatRoomType;
import com.example.chatrooms.model.adapters.ChatRoomAdapter;

import java.util.ArrayList;

public class ChatRoomListActivity extends AppCompatActivity {
    private ChatRoomAdapter chatRoomAdapter;
    private ChatRoomAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room_list);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.chat_room_list_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // Chat rooms ArrayList
        final ArrayList<ChatRoom> chatRooms = new ArrayList<>();
        // TODO: Go through database and fill the ArrayList.
        for (int i = 0; i < 50; i++) {
            if (i % 3 == 0) {
                // Group
                chatRooms.add(new ChatRoom(
                        "Group",
                        "Ori: Hello there!Hello there!Hello there!Hello there!Hello there!Hello there!Hello there!",
                        "11:30",
                        "blank_avatar",
                        5,
                        ChatRoomType.GROUP
                        )
                );
            } else {
                // Private
                chatRooms.add(new ChatRoom(
                                "LisaLisaLisaLisaLisaLisaLisaLisaLisaLisaLisaLisaLisaLisaLisaLisaLisaLisaLisaLisa",
                                "Good.",
                                "14:33",
                                "blank_avatar",
                                0,
                        ChatRoomType.PRIVATE
                        )
                );
            }
        }

        // Chat rooms RecyclerView
        RecyclerView chatRoomsRecyclerView = findViewById(R.id.chat_room_list_activity_recyclerview_chat_rooms);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        chatRoomsRecyclerView.setLayoutManager(layoutManager);

        // put listener on recycler view
        listener = new ChatRoomAdapter.RecyclerViewClickListener() {
            /**
             * @param position
             * recognise when item on the list has been click and start chat with this person
             */
            @Override
            public void OnClick (int position) {
                // move to the chosen chat
                Intent intent = new Intent(ChatRoomListActivity.this, ChatRoomActivity.class);
                intent.putExtra("chatRoomType", chatRooms.get(position).getType());
                startActivity(intent);
                finish();
            }
        };
        chatRoomAdapter = new ChatRoomAdapter(chatRooms, listener);
        chatRoomsRecyclerView.setAdapter(chatRoomAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat_room_list, menu);

        MenuItem searchItem = menu.findItem(R.id.chat_room_lst_menu_menuitem_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                chatRoomAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ChatRoomListActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}