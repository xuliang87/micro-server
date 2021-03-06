package app.publisher.binder.direct;

import com.aol.micro.server.MicroserverApp;
import com.aol.micro.server.config.Microserver;
import com.aol.micro.server.module.ConfigurableModule;
import com.aol.micro.server.rest.jersey.AsyncBinder;
import com.aol.micro.server.testing.RestAgent;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@Microserver
public class AsyncPublisherTest {
    RestAgent rest = new RestAgent();
    MicroserverApp server;

    @Before
    public void startServer() {

        server = new MicroserverApp(() -> "binder");
        server.start();

    }

    @After
    public void stopServer() {
        server.stop();
    }

    @Test
    public void runAppAndBasicTest() throws InterruptedException, ExecutionException {
        assertThat(rest.get("http://localhost:8080/binder/test/myEndPoint"), is("hello world!"));
    }
    @Test
    public void runAppAndBasicTest2() throws InterruptedException, ExecutionException {
        assertThat(rest.get("http://localhost:8080/binder/test/async2"), is("hello"));
    }
}
