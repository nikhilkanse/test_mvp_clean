package com.in.nyk.test_mvp_clean.storage;

import com.in.nyk.test_mvp_clean.domain.repository.MessageRepository;

/**
 * Created by nikhilkanse on 06/02/18.
 */

public class WelcomeMessageRepository implements MessageRepository {
    @Override
    public String getWelcomeMessage() {
        String msg = "Welcome, friend!"; // let's be friendly


        // let's simulate some network/database lag
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return msg;
    }
}
