package com.assessment.productinfo.model.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface  ProductDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    public void saveAll(List<ProductTable> productTableList);

    @Insert
    public void insertProduct(ProductTable productTable);

    @Query("Select * from product_add_table ")
    List<ProductTable> getAllProducts();

    @Query("DELETE FROM product_add_table")
    public void clearAll();

    @Delete
    void delete(ProductTable productTable);
}
