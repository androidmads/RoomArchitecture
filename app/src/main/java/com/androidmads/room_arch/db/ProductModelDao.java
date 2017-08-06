package com.androidmads.room_arch.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by AJ on 7/29/2017.
 */

@Dao
@TypeConverters(DateConverter.class)
public interface ProductModelDao {
    
    @Query("select * from ProductModel")
    LiveData<List<ProductModel>> getAllProducts();

    @Query("select * from ProductModel where itemId = :itemId")
    ProductModel getProductById(int itemId);

    @Insert(onConflict = REPLACE)
    void addProduct(ProductModel ProductModel);

    @Update(onConflict = REPLACE)
    void updateProduct(ProductModel ProductModel);

    @Delete
    void deleteProduct(ProductModel ProductModel);
    
}
