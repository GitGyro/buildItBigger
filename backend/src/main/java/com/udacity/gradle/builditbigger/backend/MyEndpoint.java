/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.udacity.gradle.jokes.Joker;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(HttpServletRequest req, @Named("name") String name) {
        MyBean response = new MyBean();

        System.out.println(req.getRemoteAddr() + ":" + req.getRemotePort() + " Calling me:" + req.getLocalAddr()+":"+req.getLocalPort());
        //System.out.println(req.getLocalAddr() +":"+req.getLocalPort() + " : Calling me");
        Joker jk = new Joker();
        response.setData("Hi, " + name+"New Joke "+jk.getJoke());

        return response;
    }

}
