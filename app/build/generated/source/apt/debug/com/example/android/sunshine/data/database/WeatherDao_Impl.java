package com.example.android.sunshine.data.database;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Date;

@SuppressWarnings("unchecked")
public class WeatherDao_Impl implements WeatherDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfWeatherEntry;

  public WeatherDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWeatherEntry = new EntityInsertionAdapter<WeatherEntry>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `weather`(`id`,`weatherIconId`,`date`,`min`,`max`,`humidity`,`pressure`,`wind`,`degrees`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WeatherEntry value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getWeatherIconId());
        final Long _tmp;
        _tmp = DateConverter.toTimestamp(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
        stmt.bindDouble(4, value.getMin());
        stmt.bindDouble(5, value.getMax());
        stmt.bindDouble(6, value.getHumidity());
        stmt.bindDouble(7, value.getPressure());
        stmt.bindDouble(8, value.getWind());
        stmt.bindDouble(9, value.getDegrees());
      }
    };
  }

  @Override
  public void bulkInsert(WeatherEntry... weather) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfWeatherEntry.insert(weather);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public WeatherEntry getWeatherByDate(Date date) {
    final String _sql = "SELECT * FROM weather WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final Long _tmp;
    _tmp = DateConverter.toTimestamp(date);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfWeatherIconId = _cursor.getColumnIndexOrThrow("weatherIconId");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfMin = _cursor.getColumnIndexOrThrow("min");
      final int _cursorIndexOfMax = _cursor.getColumnIndexOrThrow("max");
      final int _cursorIndexOfHumidity = _cursor.getColumnIndexOrThrow("humidity");
      final int _cursorIndexOfPressure = _cursor.getColumnIndexOrThrow("pressure");
      final int _cursorIndexOfWind = _cursor.getColumnIndexOrThrow("wind");
      final int _cursorIndexOfDegrees = _cursor.getColumnIndexOrThrow("degrees");
      final WeatherEntry _result;
      if(_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final int _tmpWeatherIconId;
        _tmpWeatherIconId = _cursor.getInt(_cursorIndexOfWeatherIconId);
        final Date _tmpDate;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfDate);
        }
        _tmpDate = DateConverter.toDate(_tmp_1);
        final double _tmpMin;
        _tmpMin = _cursor.getDouble(_cursorIndexOfMin);
        final double _tmpMax;
        _tmpMax = _cursor.getDouble(_cursorIndexOfMax);
        final double _tmpHumidity;
        _tmpHumidity = _cursor.getDouble(_cursorIndexOfHumidity);
        final double _tmpPressure;
        _tmpPressure = _cursor.getDouble(_cursorIndexOfPressure);
        final double _tmpWind;
        _tmpWind = _cursor.getDouble(_cursorIndexOfWind);
        final double _tmpDegrees;
        _tmpDegrees = _cursor.getDouble(_cursorIndexOfDegrees);
        _result = new WeatherEntry(_tmpId,_tmpWeatherIconId,_tmpDate,_tmpMin,_tmpMax,_tmpHumidity,_tmpPressure,_tmpWind,_tmpDegrees);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
