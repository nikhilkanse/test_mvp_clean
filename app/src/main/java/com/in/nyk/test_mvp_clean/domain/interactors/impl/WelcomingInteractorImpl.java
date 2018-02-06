package com.in.nyk.test_mvp_clean.domain.interactors.impl;

import com.in.nyk.test_mvp_clean.domain.executor.Executor;
import com.in.nyk.test_mvp_clean.domain.executor.MainThread;
import com.in.nyk.test_mvp_clean.domain.interactors.WelcomingInteractor;
import com.in.nyk.test_mvp_clean.domain.interactors.base.AbstractInteractor;
import com.in.nyk.test_mvp_clean.domain.repository.MessageRepository;

/**
 * Created by nikhilkanse on 06/02/18.
 */

public class WelcomingInteractorImpl extends AbstractInteractor implements WelcomingInteractor {

    private MessageRepository mMessageRepository;
    private Callback mCallback;

    public WelcomingInteractorImpl(Executor threadExecutor, MainThread mainThread, Callback callback, MessageRepository messageRepository) {
        super(threadExecutor, mainThread);
        this.mCallback = callback;
        this.mMessageRepository = messageRepository;
    }



    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRetrievalFailed("Nothing to welcome you with :(");
            }
        });
    }

    private void postMessage(final String msg) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onMessageRetrieved(msg);
            }
        });
    }

    @Override
    public void run() {
// retrieve the message
        final String message = mMessageRepository.getWelcomeMessage();

        // check if we have failed to retrieve our message
        if (message == null || message.length() == 0) {

            // notify the failure on the main thread
            notifyError();

            return;
        }

        // we have retrieved our message, notify the UI on the main thread
        postMessage(message);
    }

    @Override
    public void execute() {
        super.execute();
    }
}
