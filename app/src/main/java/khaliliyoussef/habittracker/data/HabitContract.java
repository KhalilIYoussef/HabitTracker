package khaliliyoussef.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Khalil on 9/26/2017.
 */

public class HabitContract {
    // make the constructor private so that no one can make an object
    private HabitContract() {
    }

    public static class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "habits";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_LOCATION = "location";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_DURATION = "duration";

    }
}