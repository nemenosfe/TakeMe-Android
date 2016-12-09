package com.pes.takemelegends;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.pes.takemelegends.Controller.ControllerFactory;

import org.junit.Test;

import cz.msebera.android.httpclient.Header;

import static org.junit.Assert.*;

public class UserController {
    private com.pes.takemelegends.Controller.UserController userController = ControllerFactory.getInstance().getUserController();
    private String uid = "1", provider = "provTestAnd", token = "abcd";

    @Test
    public void getPreferences() throws Exception {
        userController.getPreferences(
                jsonHttp,
                uid,
                provider,
                token
        );
    }

    @Test
    public void postPreferences() throws Exception {
        //
    }

    @Test
    public void putPreferences() throws Exception {
        //
    }

}
