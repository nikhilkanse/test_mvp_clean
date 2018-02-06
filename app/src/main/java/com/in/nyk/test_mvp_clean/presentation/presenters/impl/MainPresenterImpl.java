package com.in.nyk.test_mvp_clean.presentation.presenters.impl;


import com.in.nyk.test_mvp_clean.domain.executor.Executor;
import com.in.nyk.test_mvp_clean.domain.executor.MainThread;
import com.in.nyk.test_mvp_clean.domain.interactors.WelcomingInteractor;
import com.in.nyk.test_mvp_clean.domain.interactors.impl.WelcomingInteractorImpl;
import com.in.nyk.test_mvp_clean.domain.repository.MessageRepository;
import com.in.nyk.test_mvp_clean.presentation.presenters.MainPresenter;
import com.in.nyk.test_mvp_clean.presentation.presenters.base.AbstractPresenter;


public class MainPresenterImpl extends AbstractPresenter implements MainPresenter,
        WelcomingInteractor.Callback {

    private MainPresenter.View mView;
    private MessageRepository mMessageRepository;

    public MainPresenterImpl(Executor executor,
                             MainThread mainThread,
                             View view, MessageRepository messageRepository) {
        super(executor, mainThread);
        mView = view;
        mMessageRepository = messageRepository;
    }

    @Override
    public void resume() {
        mView.showProgress();

        // initialize the interactor
        WelcomingInteractor interactor = new WelcomingInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mMessageRepository
        );

        // run the interactor
        interactor.execute();
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {
        mView.showError(message);
    }

    @Override
    public void onMessageRetrieved(String message) {
        mView.hideProgress();
        mView.displayWelcomeMessage(message);
    }

    @Override
    public void onRetrievalFailed(String error) {
        mView.hideProgress();
        onError(error);
    }
}
