package com.in.nyk.test_mvp_clean.presentation.presenters;


import com.in.nyk.test_mvp_clean.presentation.presenters.base.BasePresenter;
import com.in.nyk.test_mvp_clean.presentation.ui.BaseView;

public interface MainPresenter extends BasePresenter {

    interface View extends BaseView {
        void displayWelcomeMessage(String msg);
    }

    // TODO: Add your presenter methods

}
