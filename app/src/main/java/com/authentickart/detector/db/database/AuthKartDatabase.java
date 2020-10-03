package com.authentickart.detector.db.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.authentickart.detector.db.dao.UserDao;
import com.authentickart.detector.db.entities.User;
import com.authentickart.detector.utils.Constants;

/**
 * Created by arif on 5/8/17.
 */

@Database(entities = {User.class}, version = Constants.IntegerConstants.DB_VERSION)
public abstract class AuthKartDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
