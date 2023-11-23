package com.lambda.demo.lambdaspring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.crac.Context;
import org.crac.Core;
import org.crac.Resource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnapshotConfiguration  {

    private static final Logger log = LogManager.getLogger(SnapshotConfiguration.class);

    /*   public SnapshotConfiguration()
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

    }*/
}
