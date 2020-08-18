package com.assessment.productinfo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.assessment.productinfo.MainApplication;
import com.assessment.productinfo.R;
import com.assessment.productinfo.model.roomdatabase.AppDatabase;
import com.assessment.productinfo.model.roomdatabase.DatabaseClient;
import com.assessment.productinfo.model.roomdatabase.ProductTable;
import com.assessment.productinfo.model.webservice.APIService;
import com.assessment.productinfo.model.webservice.ProductAddResponse;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class ProductActionActivity extends AppCompatActivity {

    private CardView addProductCard;
    private CardView syncProductCard;
    private AppDatabase appDatabase;
    private ProgressDialog progressDialog;
    public String mobile_number = "7350346623";
    private APIService apiService;
    private MainApplication mainApplication;
    private boolean isUploaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_action);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .penaltyLog()
                .build());

        appDatabase = DatabaseClient.getInstance(this).getAppDatabase();
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading....");
        progressDialog.setIndeterminate(true);
        addProductCard = (CardView) findViewById(R.id.card_1);
        syncProductCard = (CardView) findViewById(R.id.card_2);
        mainApplication = MainApplication.create(this);
        apiService = mainApplication.getAPIService();
        addProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductActionActivity.this, ProductAddActivity.class));
            }
        });

        syncProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiCallSyncProduct();
            }
        });
    }

    @SuppressLint("LongLogTag")
    public void apiCallSyncProduct() {

        try {
            progressDialog.show();
            //first get all products
//            while (true)
//            {
                List<ProductTable> list = appDatabase.productDao().getAllProducts();
                Log.d("Product sync list size: ", String.valueOf(list.size()));
                if(list.size()>0)
                {
                    for (ProductTable iterateProduct : list)
                    {
                        Log.d("Product sync iterateProduct: ", String.valueOf(iterateProduct.getProduct_name()));
                        if(!syncEachProduct(iterateProduct));
                        {
                            Toast.makeText(ProductActionActivity.this, "Error - Sync Failed ", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            return;
                        }
                    }
                    Toast.makeText(ProductActionActivity.this, "Data sync successfully", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(ProductActionActivity.this, "Data not available to sync", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            //}
        } catch (Exception e) {
            Toast.makeText(ProductActionActivity.this, "Error - Sync Failed ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            progressDialog.dismiss();
        }


    }
    @SuppressLint("LongLogTag")
    public boolean syncEachProduct(ProductTable productTable)
    {
        isUploaded=false;
        apiService.addProductApiCall(productTable.getProduct_name(),productTable.getProduct_desc(),productTable.getProduct_quantity(),
                productTable.getProduct_price(),mobile_number)
                .subscribeOn(mainApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProductAddResponse>() {

                    @Override
                    public void accept(ProductAddResponse response) throws Exception {
                        try {
                            if(response.getResults().getStatus().equalsIgnoreCase("1"))
                            {
                                Log.d("Product sync iterateProduct response: ", String.valueOf(response.getResults().getMsg()));
                                appDatabase.productDao().delete(productTable);
                                isUploaded = true;
                            }else {
                                Toast.makeText(ProductActionActivity.this, response.getResults().getMsg().toString(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();

                    }
                });
        return isUploaded;
    }
}
