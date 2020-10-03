package com.authentickart.detector.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.authentickart.detector.db.entities.User;

import io.reactivex.Single;

/**
 * Created by arif on 5/8/17.
 */

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(User user);

    @Query("SELECT * FROM user LIMIT 1")
    Single<User> get();

    @Delete
    void delete(User user);

    @Delete
    void deleteAll(User... users);

    @Query("SELECT COUNT(*) FROM user")
    int getCount();
}
