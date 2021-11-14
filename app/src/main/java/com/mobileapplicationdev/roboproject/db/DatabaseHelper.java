package com.mobileapplicationdev.roboproject.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobileapplicationdev.roboproject.models.RobotProfile;

import java.util.ArrayList;

/**
 * This class contains all connections to the Database
 * insert, update and delete all Settings of the Robot profiles
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "RoboController.db";
    private static final int DATABASE_VERSION = 13;

    //Constants for the profiles table
    //table name:
    private static final String PRO_TABLE_NAME = "profiles";
    //id:
    private static final String ID_PRO_NAME = "id";
    private static final String ID_PRO_TYPE = "INTEGER PRIMARY KEY AUTOINCREMENT";
    //name:
    private static final String NAME_PRO_NAME = "name";
    private static final String NAME_PRO_TYPE = "TEXT";
    //ipOne:
    private static final String IP_1_PRO_NAME = "ipOne";
    private static final String IP_1_PRO_TYPE = "TEXT";
    //ipTwo:
    private static final String IP_2_PRO_NAME = "ipTwo";
    private static final String IP_2_PRO_TYPE = "TEXT";
    //portOne:
    private static final String PORT_1_PRO_NAME = "portOne";
    private static final String PORT_1_PRO_TYPE = "INTEGER";
    //portTwo:
    private static final String PORT_2_PRO_NAME = "portTwo";
    private static final String PORT_2_PRO_TYPE = "INTEGER";
    //portThree:
    private static final String PORT_3_PRO_NAME = "portThree";
    private static final String PORT_3_PRO_TYPE = "INTEGER";
    //maxAngularSpeed:
    private static final String MAX_ANG_PRO_NAME = "maxAngularSpeed";
    private static final String MAX_ANG_PRO_TYPE = "FLOAT";
    //maxX:
    private static final String MAX_X_PRO_NAME = "maxX";
    private static final String MAX_X_PRO_TYPE = "FLOAT";
    //maxY:
    private static final String MAX_Y_PRO_NAME = "maxY";
    private static final String MAX_Y_PRO_TYPE = "FLOAT";
    //frequency:
    private static final String FREQ_PRO_NAME = "frequency";
    private static final String FREQ_PRO_TYPE = "FLOAT";

    //SQL-Command to create profiles table:
    private static final String PRO_TABLE_CREATE =
            "CREATE TABLE " + PRO_TABLE_NAME + "(" +
                    ID_PRO_NAME + " " + ID_PRO_TYPE + ", " +
                    NAME_PRO_NAME + " " + NAME_PRO_TYPE + ", " +
                    IP_1_PRO_NAME + " " + IP_1_PRO_TYPE + ", " +
                    IP_2_PRO_NAME + " " + IP_2_PRO_TYPE + ", " +
                    PORT_1_PRO_NAME + " " + PORT_1_PRO_TYPE + ", " +
                    PORT_2_PRO_NAME + " " + PORT_2_PRO_TYPE + ", " +
                    PORT_3_PRO_NAME + " " + PORT_3_PRO_TYPE + ", " +
                    MAX_ANG_PRO_NAME + " " + MAX_ANG_PRO_TYPE + ", " +
                    MAX_X_PRO_NAME + " " + MAX_X_PRO_TYPE + ", " +
                    MAX_Y_PRO_NAME + " " + MAX_Y_PRO_TYPE + ", " +
                    FREQ_PRO_NAME + " " + FREQ_PRO_TYPE + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(PRO_TABLE_CREATE);
        } catch (Exception ex) {
            Log.e(TAG, "Error creating table: " + PRO_TABLE_NAME + "!", ex);
        }
    }

    /**
     * Inserts a new profile into the database
     *
     * @param profile (without database-id) to insert into the database
     * @return Database-id of the new inserted profile or (-666) if there is an exception
     */
    public long insertProfile(RobotProfile profile) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(NAME_PRO_NAME, profile.getName());
            values.put(IP_1_PRO_NAME, profile.getControlIp());
            values.put(IP_2_PRO_NAME, profile.getDebugIp());
            values.put(PORT_1_PRO_NAME, profile.getPortOne());
            values.put(PORT_2_PRO_NAME, profile.getPortTwo());
            values.put(PORT_3_PRO_NAME, profile.getPortThree());
            values.put(MAX_ANG_PRO_NAME, profile.getMaxAngularSpeed());
            values.put(MAX_X_PRO_NAME, profile.getMaxX());
            values.put(MAX_Y_PRO_NAME, profile.getMaxY());
            values.put(FREQ_PRO_NAME, profile.getFrequency());

            //Insert values and get the generated id from the database
            return db.insert(PRO_TABLE_NAME, null, values);

        } catch (Exception ex) {
            Log.e(TAG, "Couldn't insert Profile: " + ex);
            return -666;
        }
    }

    /**
     * Updates a profile in the database
     *
     * @param profile to update in the database (Database-id required)
     * @return true = Profile is updated, false = Couldn't update profile
     */
    public boolean updateProfile(RobotProfile profile) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(NAME_PRO_NAME, profile.getName());
            values.put(IP_1_PRO_NAME, profile.getControlIp());
            values.put(IP_2_PRO_NAME, profile.getDebugIp());
            values.put(PORT_1_PRO_NAME, profile.getPortOne());
            values.put(PORT_2_PRO_NAME, profile.getPortTwo());
            values.put(PORT_3_PRO_NAME, profile.getPortThree());
            values.put(MAX_ANG_PRO_NAME, profile.getMaxAngularSpeed());
            values.put(MAX_X_PRO_NAME, profile.getMaxX());
            values.put(MAX_Y_PRO_NAME, profile.getMaxY());
            values.put(FREQ_PRO_NAME, profile.getFrequency());

            //Update values of profile by id
            db.update(PRO_TABLE_NAME, values, "id = ?", new String[]{String.valueOf(profile.getId())});
            return true;
        } catch (Exception ex) {
            Log.e(TAG, "Couldn't update profile" + ex);
            return false;
        }
    }

    /**
     * Returns all profiles in the Database
     *
     * @return Array list filled with all profiles from the database, null if there is an exception
     */
    public ArrayList<RobotProfile> getAllProfiles() {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            ArrayList<RobotProfile> profiles = new ArrayList<>();
            String selectQuery = "SELECT * FROM " + PRO_TABLE_NAME;

            //Get all raw database data by selectionQuery
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {
                do {
                    RobotProfile profile = new RobotProfile();

                    profile.setId(c.getInt(c.getColumnIndex(ID_PRO_NAME)));
                    profile.setName(c.getString(c.getColumnIndex(NAME_PRO_NAME)));
                    profile.setControlIp(c.getString(c.getColumnIndex(IP_1_PRO_NAME)));
                    profile.setDebugIp(c.getString(c.getColumnIndex(IP_2_PRO_NAME)));
                    profile.setPortOne(c.getInt(c.getColumnIndex(PORT_1_PRO_NAME)));
                    profile.setPortTwo(c.getInt(c.getColumnIndex(PORT_2_PRO_NAME)));
                    profile.setPortThree(c.getInt(c.getColumnIndex(PORT_3_PRO_NAME)));
                    profile.setMaxAngularSpeed(c.getFloat(c.getColumnIndex(MAX_ANG_PRO_NAME)));
                    profile.setMaxX(c.getFloat(c.getColumnIndex(MAX_X_PRO_NAME)));
                    profile.setMaxY(c.getFloat(c.getColumnIndex(MAX_Y_PRO_NAME)));
                    profile.setFrequency(c.getFloat(c.getColumnIndex(FREQ_PRO_NAME)));

                    //Add current profile to the profiles list
                    profiles.add(profile);

                } while (c.moveToNext());
            }

            return profiles;
        } catch (Exception ex) {
            Log.e(TAG, "Couldn't get all Profiles.\n" + ex);
            return null;
        }
    }

    /**
     * Deletes an profile in the database by id
     *
     * @param id of the profile that should be deleted
     * @return true = profile was deleted, false = Couldn't delete profile
     */
    public boolean deleteProfile(Integer id) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            //Delete profile by id
            db.delete(PRO_TABLE_NAME, "id = ?", new String[]{Integer.toString(id)});
            return true;
        } catch (Exception ex) {
            Log.e(TAG, "Couldn't delete profile with id=" + id);
            return false;
        }
    }

    /**
     * Checks if the profile database is empty.
     * If profiles table is empty it inserts the "Default"-profile
     * Only important if the App has been started for the first time or the database was cleared
     *
     * @return true = "Default"-profile is inserted, false = database is not empty
     */
    public boolean insertDefaultProfileIfDbIsEmpty(){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            String selectQuery = "SELECT * FROM " + PRO_TABLE_NAME;

            Cursor c = db.rawQuery(selectQuery, null);

            //if table is empty
            if(c.getCount() == 0) {
                ContentValues values = new ContentValues();
                values.put(NAME_PRO_NAME, "Default");
                values.put(IP_1_PRO_NAME, "192.168.0.29");
                values.put(IP_2_PRO_NAME, "192.168.0.29");
                values.put(PORT_1_PRO_NAME, 15002);
                values.put(PORT_2_PRO_NAME, 15002);
                values.put(PORT_3_PRO_NAME, 15002);
                values.put(MAX_ANG_PRO_NAME, 0.5f);
                values.put(MAX_X_PRO_NAME, 0.5f);
                values.put(MAX_Y_PRO_NAME, 0.6f);
                values.put(FREQ_PRO_NAME, 4.0f);

                //Insert "Default"-profile
                db.insert(PRO_TABLE_NAME, null, values);
                return true;
            }else{
                return false;
            }

        }catch (Exception ex) {
            Log.e(TAG, "Couldn't insert DEFAULT profile!");
            return false;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + PRO_TABLE_NAME);
        //create new tables
        onCreate(db);
    }

    //since API-level 11
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVer, int newVer) {
        //on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + PRO_TABLE_NAME);
        //create new tables
        onCreate(db);
    }

}
