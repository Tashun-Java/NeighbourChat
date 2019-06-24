package com.ht.neighbourchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ht.neighbourchat.db.AppDatabase;
import com.ht.neighbourchat.db.DataHandler;
import com.ht.neighbourchat.models.Message;

import java.util.List;
import java.util.logging.Logger;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItemListActivity}.
 */
public class ItemDetailActivity extends AppCompatActivity {
    public static final String ARG_ITEM_ID = "item_id";
    public static AppDatabase appDatabase;
    private boolean mTwoPane = false;
    private Message message;
    private Logger logger = Logger.getLogger(ItemDetailActivity.class.toString());
    private int message_count = 0;
    private String stringExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        appDatabase = DataHandler.getAppDatabase(this);

        stringExtra = getIntent().getStringExtra(ARG_ITEM_ID);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (savedInstanceState == null) {
            onCreateView();
        }
        View recyclerView = findViewById(R.id.item_detail_messages);
        assert recyclerView != null;
        System.out.println("Check" + recyclerView == null);
        getMessages((RecyclerView) recyclerView);


        Button button = findViewById(R.id.button_send);
        final EditText sending_message = findViewById(R.id.edit_text_out);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = sending_message.getText().toString();
                logger.info("Received Message : " + text);
                persistMessage(text);

            }
        });

    }

    private void setupRecyclerView(RecyclerView recyclerView, List<Message> messages) {
        recyclerView.setAdapter(new MessageRecycleViewAdapter(messages, false));
    }

    private void getMessages(final RecyclerView recyclerView) {

        final String stringExtra = getIntent().getStringExtra(ARG_ITEM_ID);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Message> messages = appDatabase.appDoa().loadMessagesForUser(stringExtra);
                logger.info(ItemDetailActivity.class + " : Message is :" + messages.size());
                for (Message message : messages) {
                    logger.info(ItemDetailActivity.class + " : Message Content :" + message.getMessageContent());
                    message_count++;
                }
                setupRecyclerView(recyclerView, messages);
            }
        });
        thread.start();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, ItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public synchronized void onCreateView() {
        CollapsingToolbarLayout appBarLayout = findViewById(R.id.toolbar_layout);
        System.out.println("This is the extra string" + stringExtra);

        if (appBarLayout != null) {
            appBarLayout.setTitle(stringExtra);
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Message> messages = appDatabase.appDoa().loadMessagesForUser(stringExtra);
                logger.info(ItemDetailActivity.class + " : Message is :" + messages.size());

            }
        });
        thread.start();
    }

    public synchronized void persistMessage(final String text) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.setId(stringExtra);
                message_count++;
                message.setMessageContent(text);
                message.setMessageId("" + message_count);
                message.setSender("User Test0");
                message.setStatus(1);
                appDatabase.appDoa().addMessage(message);
                System.out.println("Count " + message_count);
            }
        }).start();
        finish();
        startActivity(getIntent());
    }


}
