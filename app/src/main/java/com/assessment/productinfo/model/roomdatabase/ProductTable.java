package com.assessment.productinfo.model.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "product_add_table")
public class ProductTable implements Serializable {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "p_id")
    private int p_id;

    @ColumnInfo(name = "product_name")
    private String product_name;

    @ColumnInfo(name = "product_desc")
    private String product_desc;

    @ColumnInfo(name = "product_quantity")
    private int product_quantity;

    @ColumnInfo(name = "product_price")
    private int product_price;

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

}

