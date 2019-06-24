package com.ht.neighbourchat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ht.neighbourchat.db.AppDatabase;
import com.ht.neighbourchat.db.DataHandler;
import com.ht.neighbourchat.models.Message;
import com.ht.neighbourchat.models.UserDoa;

import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private static AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        appDatabase = DataHandler.getAppDatabase(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        persist(this);
        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        getUsers((RecyclerView) recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.secure_connect_scan:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView,List<UserDoa> users) {
        recyclerView.setAdapter(new UserRecyclerViewAdapter(this,users , mTwoPane));
    }

    public void getUsers(@NonNull final RecyclerView recyclerView) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<UserDoa> userDoas = appDatabase.appDoa().loadUsers();
                System.out.println("Users number :"+userDoas.size());
                setupRecyclerView(recyclerView,userDoas);

            }
        }).start();

    }

    public void persist(final Context context) {
        new Thread(new Runnable() {

            @Override
            public void run() {
//                appDatabase = AppDatabase.getInMemoryDatabase(context);
                for (int i = 0; i < 10; i++) {
                    Message message = new Message();
                    message.setId("0");
                    message.setMessageContent("Message :" + i);
                    message.setMessageId("12" + i);
                    message.setSender("User Test0");
                    message.setStatus(1);
                    appDatabase.appDoa().addMessage(message);

                }
                List<Message> messages=appDatabase.appDoa().loadMessages();
                System.out.println("Added!! "+messages.size());
//                        Toast.makeText(v.getContext(), "The Uer got persisteted", Toast.LENGTH_LONG).show();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                appDatabase = AppDatabase.getInMemoryDatabase(context);
                for (int i = 0; i < 10; i++) {
                    UserDoa userDoa = new UserDoa();
                    userDoa.setUserName("User Test" + i);
                    userDoa.setLastSeen("0" + i);
                    userDoa.setId("" + i);
                    appDatabase.appDoa().addUser(userDoa);
                }
                List<UserDoa>userDoas=appDatabase.appDoa().loadUsers();
                System.out.println("Added!! "+userDoas.size());

            }
        }).start();
    }


}
