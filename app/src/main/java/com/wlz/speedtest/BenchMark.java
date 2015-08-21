package com.wlz.speedtest;

import android.content.Context;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by WaiLynnZaw on 8/16/15.
 */
public class BenchMark {
    Context ctx;
    public BenchMark(Context ctx){
        this.ctx = ctx;
    }
    public void hourly(){
        final SpeedTest speedTest = new SpeedTest();
        final Calendar calendar = Calendar.getInstance();
        Realm realm = Realm.getInstance(ctx);
        BenchMarkObject benchMarkObject = new BenchMarkObject();
        benchMarkObject.setHour(calendar.get(Calendar.HOUR_OF_DAY));
        benchMarkObject.setDate(String.format("%s-%s-%s", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1 < 10 ? "0" + (calendar.get(Calendar.MONTH) + 1) : (calendar.get(Calendar.MONTH) + 1), calendar.get(Calendar.DAY_OF_MONTH)));
        benchMarkObject.setPing(speedTest.getPing());
        benchMarkObject.setDownload_speed(speedTest.getDownloadSpeed());
        benchMarkObject.setUpload_speed(speedTest.getUploadSpeed());

        realm.beginTransaction();
        realm.copyToRealm(benchMarkObject);
        realm.commitTransaction();
    }

    public RealmResults<BenchMarkObject> getAllResultsWithDate(String date){
        Realm realm = Realm.getInstance(ctx);
        RealmQuery<BenchMarkObject> query = realm.where(BenchMarkObject.class);
        query.equalTo("date", String.valueOf(date));
        return query.findAll();
    }
    public RealmResults<BenchMarkObject> getAllResults(){
        Realm realm = Realm.getInstance(ctx);
        RealmQuery<BenchMarkObject> query = realm.where(BenchMarkObject.class);
        return query.findAll();
    }


    public float getAveragePing(String date){
        return (float) getAllResultsWithDate(date).average("ping");
    }
    public float getAverageDownload(String date){
        return (float) getAllResultsWithDate(date).average("download_speed");
    }
    public float getAverageUpload(String date){
        return (float) getAllResultsWithDate(date).average("upload_speed");
    }

    public RealmResults<BenchMarkObject> getPings(String date) {
        return getAllResultsWithDate(date);
    }
    public RealmResults<BenchMarkObject> getDownloads(String date) {
        return getAllResultsWithDate(date);
    }
    public RealmResults<BenchMarkObject> getUploads(String date) {
        return getAllResultsWithDate(date);
    }


}
