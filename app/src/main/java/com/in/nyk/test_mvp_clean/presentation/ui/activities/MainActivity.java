package com.in.nyk.test_mvp_clean.presentation.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.in.nyk.test_mvp_clean.R;
import com.in.nyk.test_mvp_clean.domain.executor.impl.ThreadExecutor;
import com.in.nyk.test_mvp_clean.presentation.presenters.MainPresenter;
import com.in.nyk.test_mvp_clean.presentation.presenters.impl.MainPresenterImpl;
import com.in.nyk.test_mvp_clean.storage.WelcomeMessageRepository;
import com.in.nyk.test_mvp_clean.threading.MainThreadImpl;


public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    private MainPresenter mPresenter;
    private TextView mWelcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWelcomeTextView = (TextView)findViewById(R.id.text);
        mPresenter = new MainPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new WelcomeMessageRepository()
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        // let's start welcome message retrieval when the app resumes
        mPresenter.resume();
    }

    @Override
    public void showProgress() {
        mWelcomeTextView.setText("Retrieving...");
    }

    @Override
    public void hideProgress() {
        Toast.makeText(this, "Retrieved!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {
        mWelcomeTextView.setText(message);
    }

    @Override
    public void displayWelcomeMessage(String msg) {
        mWelcomeTextView.setText(msg);
    }
}
