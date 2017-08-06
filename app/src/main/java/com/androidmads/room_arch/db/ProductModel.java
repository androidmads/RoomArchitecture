package com.androidmads.room_arch.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

/**
 * Created by AJ on 7/29/2017.
 */

@Entity
public class ProductModel {

    @PrimaryKey(autoGenerate = true)
    public int itemId;
    private String itemName;
    private String itemQty;
    @TypeConverters(DateConverter.class)
    private Date itemAddedDate;

    public ProductModel(int itemId, String itemName, String itemQty, Date itemAddedDate) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemQty = itemQty;
        this.itemAddedDate = itemAddedDate;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }

    public void setItemAddedDate(Date itemAddedDate) {
        this.itemAddedDate = itemAddedDate;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemQty() {
        return itemQty;
    }

    public Date getItemAddedDate() {
        return itemAddedDate;
    }

    public int getItemId() {
        return itemId;
    }
}
