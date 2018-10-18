package com.aruistar.studyvertx

import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.core.json.JsonObject
import io.vertx.core.spi.cluster.ClusterManager
import io.vertx.spi.cluster.zookeeper.ZookeeperClusterManager

JsonObject zkConfig = new JsonObject();
zkConfig.put("zookeeperHosts", "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183");
zkConfig.put("rootPath", "io.vertx");
zkConfig.put("retry", new JsonObject()
        .put("initialSleepTime", 3000)
        .put("maxTimes", 3));

ClusterManager mgr = new ZookeeperClusterManager(zkConfig);
VertxOptions options = new VertxOptions()
        .setClustered(true)
        .setClusterManager(mgr);


Vertx.clusteredVertx(options, { res ->
    if (res.succeeded()) {
        System.out.println("connect zookeeper success.");
        Vertx vertx = res.result();

        vertx.sharedData().getClusterWideMap("test", {
            def map = it.result()
            map.put("a", "a", {
                println(it.succeeded())
            })
        })



        vertx.setPeriodic(1000, { v ->

            vertx.sharedData().getClusterWideMap("test", {
                def map = it.result()
                map.get("a", {
                    println(it.result())
                })
            })


        });

    } else {
        System.out.println("connect zookeeper error.");
    }
})