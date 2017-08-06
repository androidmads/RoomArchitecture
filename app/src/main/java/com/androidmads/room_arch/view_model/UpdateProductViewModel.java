package com.androidmads.room_arch.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import com.androidmads.room_arch.db.AppDataBase;
import com.androidmads.room_arch.db.ProductModel;

import java.util.concurrent.ExecutionException;

/**
 * Created by AJ on 7/29/2017.
 */

public class UpdateProductViewModel extends AndroidViewModel {

    private AppDataBase appDatabase;

    public UpdateProductViewModel(Application application) {
        super(application);
        appDatabase = AppDataBase.getDatabase(this.getApplication());
    }

    public ProductModel readProduct(final int itemId) {
        try {
            return new readAsyncTask(appDatabase).execute(itemId).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateProduct(final ProductModel borrowModel) {
        new UpdateProductViewModel.updateAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class updateAsyncTask extends AsyncTask<ProductModel, Void, Void> {

        private AppDataBase db;

        updateAsyncTask(AppDataBase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final ProductModel... params) {
            db.itemAndPersonModel().updateProduct(params[0]);
            return null;
        }

    }

    private static class readAsyncTask extends AsyncTask<Integer, Void, ProductModel> {

        private AppDataBase db;

        readAsyncTask(AppDataBase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected ProductModel doInBackground(final Integer... params) {
            return db.itemAndPersonModel().getProductById(params[0]);
        }
    }
}