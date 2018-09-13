package com.aruistar.studyvertx

import io.vertx.core.Vertx
import io.vertx.ext.unit.TestContext
import io.vertx.ext.unit.junit.VertxUnitRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(VertxUnitRunner.class)
class VertxUnitTest {

    @Test
    void testSomething(TestContext context) {
        def async = context.async()
        Vertx.vertx().setTimer(3000, {
            context.assertFalse(false)
            async.complete()
        })
    }
}
