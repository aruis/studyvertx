package com.aruistar.studyvertx

import groovy.util.logging.Slf4j
import io.vertx.core.AbstractVerticle


@Slf4j
class MainVerticle extends AbstractVerticle {
    @Override
    void start() throws Exception {
        log.info("verticle is starting")
    }
}
