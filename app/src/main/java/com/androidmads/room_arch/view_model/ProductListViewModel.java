package com.androidmads.room_arch.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.androidmads.room_arch.db.AppDataBase;
import com.androidmads.room_arch.db.ProductModel;

import java.util.List;

/**
 * Created by AJ on 7/29/2017.
 */

public class ProductListViewModel extends AndroidViewModel {

    private final LiveData<List<ProductModel>> itemAndPersonList;
    private AppDataBase appDatabase;

    public ProductListViewModel(Application application) {
        super(application);
        appDatabase = AppDataBase.getDatabase(this.getApplication());
        itemAndPersonList = appDatabase.itemAndPersonModel().getAllProducts();
    }

    public LiveData<List<ProductModel>> getItemAndPersonList() {
        return itemAndPersonList;
    }

    public void deleteItem(ProductModel borrowModel) {
        new deleteAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class deleteAsyncTask extends AsyncTask<ProductModel, Void, Void> {

        private AppDataBase db;
        deleteAsyncTask(AppDataBase appDatabase) {
            db = appDatabase;
        }
        @Override
        protected Void doInBackground(final ProductModel... params) {
            db.itemAndPersonModel().deleteProduct(params[0]);
            return null;
        }

    }

}
