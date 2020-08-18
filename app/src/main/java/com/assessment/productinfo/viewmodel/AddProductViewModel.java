package com.assessment.productinfo.viewmodel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.BaseObservable;

import com.assessment.productinfo.model.roomdatabase.AppDatabase;
import com.assessment.productinfo.model.roomdatabase.DatabaseClient;
import com.assessment.productinfo.model.roomdatabase.ProductTable;
import com.assessment.productinfo.model.webservice.ProductModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class AddProductViewModel extends BaseObservable {

    private ProductModel productModel;
    Context context;
    private ProgressDialog progressDialog;

    private AppDatabase appDatabase;

    public void init(Context context) {
        productModel = new ProductModel();
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading....");
        progressDialog.setIndeterminate(true);
        appDatabase = DatabaseClient.getInstance(context).getAppDatabase();
    }
    public ProductModel getProductModel() {
        return productModel;
    }


    public void onClickAddProduct()
    {
        if(validat())
        {
            saveProductInfo();
        }
    }

    private boolean validat() {
        boolean valid = true;
        try {
            if (productModel.getProductName().isEmpty() && productModel.getProductDesc().isEmpty()
                    && productModel.getProductQuantity().isEmpty() && productModel.getProductPrice().isEmpty()) {
                Toast.makeText(context, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
                valid = false;
            } else if (productModel.getProductName().isEmpty()) {
                Toast.makeText(context, "Please fill product name.", Toast.LENGTH_SHORT).show();
                valid = false;
            } else if (productModel.getProductDesc().equalsIgnoreCase("")) {
                Toast.makeText(context, "Please fill product description.", Toast.LENGTH_SHORT).show();
                valid = false;
            } else if (productModel.getProductQuantity().equalsIgnoreCase("")) {
                Toast.makeText(context, "Please fill product quantity.", Toast.LENGTH_SHORT).show();
                valid = false;
            } else if (productModel.getProductPrice().equalsIgnoreCase("")) {
                Toast.makeText(context, "Please fill product price.", Toast.LENGTH_SHORT).show();
                valid = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            valid =false;
        }
        return valid;
    }

    public void saveProductInfo()
    {
        try {
            progressDialog.show();
            ProductTable productTable = new ProductTable();
            productTable.setProduct_name(productModel.getProductName());
            productTable.setProduct_desc(productModel.getProductDesc());
            productTable.setProduct_price(Integer.parseInt(productModel.getProductPrice()));
            productTable.setProduct_quantity(Integer.parseInt(productModel.getProductQuantity()));
            try {
                appDatabase.productDao().insertProduct(productTable);
                Toast.makeText(context, "Product save successfully", Toast.LENGTH_SHORT).show();
                ((Activity)(context)).finish();
                List<ProductTable> list = appDatabase.productDao().getAllProducts();
                for (ProductTable productTable1: list)
                {
                    Log.d("Product saved name: ",productTable1.getProduct_name());
                }
                progressDialog.dismiss();
            } catch (Exception e) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                e.printStackTrace();
            }

//            MainApplication mainApplication = MainApplication.create(context);
//            APIService userService =mainApplication.getAPIService();
//            progressDialog.show();
//            userService.addProductApiCall(productModel.getProductName(),productModel.getProductDesc(),productModel.getProductQuantity(),
//                    productModel.getProductPrice(),mobile_number)
//                    .subscribeOn(mainApplication.subscribeScheduler())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Consumer<ProductAddResponse>() {
//                        @Override
//                        public void accept(ProductAddResponse response) throws Exception {
//                            try {
//                                progressDialog.dismiss();
//                                if(response.status.equalsIgnoreCase("1"))
//                                {
//                                    Toast.makeText(context, response.msg.toString() , Toast.LENGTH_SHORT).show();
//                                    ((Activity)(context)).finish();
//
//                                }else {
//                                    Toast.makeText(context, response.msg.toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }, new Consumer<Throwable>() {
//                        @Override
//                        public void accept(Throwable throwable) throws Exception {
//                            progressDialog.dismiss();
//                            throwable.printStackTrace();
//
//                        }
//                    });
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void reset()
    {
        context=null;
    }


}