package com.lambda.demo.lambdaspring;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;

import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.crac.Context;
import org.crac.Core;
import org.crac.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamLambdaHandler implements RequestStreamHandler, Resource {
    private static final Logger log = LogManager.getLogger(StreamLambdaHandler.class);
    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    static {
        try {
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(LambdaSpringApplication.class);
            // If you are using HTTP APIs with the version 2.0 of the proxy model, use the getHttpApiV2ProxyHandler
            // method: handler = SpringBootLambdaContainerHandler.getHttpApiV2ProxyHandler(Application.class);
        } catch (ContainerInitializationException e) {
            // if we fail here. We re-throw the exception to force another cold start
            e.printStackTrace();
            throw new RuntimeException("Could not initialize Spring Boot application", e);
        }
    }

    public StreamLambdaHandler()
    {
        log.info("Snapshot config constructor");
        Core.getGlobalContext().register(this);
    }

    @Override
    public void beforeCheckpoint(Context<? extends Resource> context) throws Exception {
        log.info("Before Checkpoint is called ....");
        System.out.println("Before Checkpoint is called .... We can do any memory clean up ");
    }

    @Override
    public void afterRestore(Context<? extends Resource> context) throws Exception {
        log.info("After Restore ....");
        System.out.println(" After Restore we can update any credential as there might be some " +
                " credential that got rotated ... ");
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, com.amazonaws.services.lambda.runtime.Context context) throws IOException {
        handler.proxyStream(inputStream, outputStream, context);
    }
}
