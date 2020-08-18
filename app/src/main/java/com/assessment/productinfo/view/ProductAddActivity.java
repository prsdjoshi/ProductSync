package com.assessment.productinfo.view;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.assessment.productinfo.R;
import com.assessment.productinfo.viewmodel.AddProductViewModel;
import com.assessment.productinfo.databinding.ActivityToolbarAddproductBinding;


public class ProductAddActivity extends AppCompatActivity {

    private ActivityToolbarAddproductBinding activityToolbarAddproductBinding;
    private AddProductViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .penaltyLog()
                .build());

        setupBindings(savedInstanceState);
        setUpTool();
    }
    private void setupBindings(Bundle savedInstanceState) {
        activityToolbarAddproductBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_toolbar_addproduct);
        setSupportActionBar(activityToolbarAddproductBinding.addproductToolbar);
        viewModel = new AddProductViewModel();
        if (savedInstanceState == null) {
            viewModel.init(this);
        }
        activityToolbarAddproductBinding.setAddProductViewModel(viewModel);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.reset();
    }

    private void setUpTool() {
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
