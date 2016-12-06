package foo;

import io.reactivex.netty.protocol.http.server.HttpServer;
import rx.Observable;


public final class Application {

    public static void main(final String[] args) {

        HttpServer.newServer(8080).start((req, resp) -> {
            if (!req.getUri().contains("favicon.ico")) {
                return resp.writeString(Observable
                    .just("0123456789")
                    .repeat(Long.parseLong(req.getUri().replace("/", ""))));
            }
            return Observable.empty();
        }).awaitShutdown();
    }
}