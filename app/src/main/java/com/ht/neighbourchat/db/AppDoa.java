package com.ht.neighbourchat.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.ht.neighbourchat.models.Message;
import com.ht.neighbourchat.models.User;
import com.ht.neighbourchat.models.UserDoa;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface AppDoa {
    @Delete
    void deleteUser(UserDoa userDoa);

    @Insert(onConflict = IGNORE)
    void addUser(UserDoa userDoa);

    @Insert(onConflict = IGNORE)
    void addMessage(Message message);

    @Query("SELECT * from USERDOA")
    List<UserDoa> loadUsers();

    @Query("SELECT * from Message")
    List<Message> loadMessages();

    @Query("SELECT * from Message WHERE `user-id` LIKE :id")
    List<Message> loadMessagesForUser(String id);

    @Query("SELECT `user-id`,`user-name`,`last-seen` FROM USERDOA WHERE `user-id` LIKE :id ")
    List<UserDoa> findUsersById(String id);
}
