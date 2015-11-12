package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;
import android.util.Pair;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Aditya on 11/10/15.
 */
public class TestAsyncTaskResp extends AndroidTestCase implements EndpointsAsyncTask.Callback{
    @Override
    public void validateResponse(String resp){
      assertNotNull(resp);
      if (resp.equals("")){
          assertTrue(false);
      }

       doneCountDown.countDown();
    }
    CountDownLatch doneCountDown ;
    @Override
    protected void setUp() throws Exception{
        super.setUp();
        // wait for one thread to indicate completion
        doneCountDown = new CountDownLatch(1);
    }

    public void testCheckResponse() throws InterruptedException{
        EndpointsAsyncTask jokeAsyncTask =  new EndpointsAsyncTask();
        jokeAsyncTask.setCallback(this);
        jokeAsyncTask.execute(new Pair<Context, String>(new MockContext(), "To GCE Server"));
        doneCountDown.await();

    }
}
