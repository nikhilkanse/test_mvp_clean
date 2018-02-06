package com.in.nyk.test_mvp_clean.domain.interactors;


import com.in.nyk.test_mvp_clean.domain.interactors.base.Interactor;

public interface WelcomingInteractor extends Interactor {

    interface Callback {
        void onMessageRetrieved(String message);

        void onRetrievalFailed(String error);
    }

    // TODO: Add interactor methods here
}
