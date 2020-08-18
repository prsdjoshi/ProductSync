package com.assessment.productinfo;

import android.app.Application;
import android.content.Context;
import com.assessment.productinfo.model.webservice.APIFactory;
import com.assessment.productinfo.model.webservice.APIService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class MainApplication extends Application {
    private APIService apiService;
    private Scheduler scheduler;
    private static MainApplication get(Context context) {
        return (MainApplication) context.getApplicationContext();
    }

    public static MainApplication create(Context context) {
        return MainApplication.get(context);
    }

    public APIService getAPIService() {
        if (apiService == null) {
            apiService = APIFactory.create();
        }
        return apiService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
