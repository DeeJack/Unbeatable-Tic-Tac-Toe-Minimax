package me.deejack.tris.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Optional;

public class LocalNetworkRoom {
    private ServerSocket server;

    public LocalNetworkRoom() {
        openServer().ifPresentOrElse(server -> this.server = server, () -> {
            System.err.println("Couldn't open server on port: 9999");
        });
    }

    public LocalNetworkRoom(String address, int port) {
        // TODO: network room builder? Factory? Leave like this?
    }

    private Optional<ServerSocket> openServer() {
        try {
            return Optional.of(new ServerSocket(9999, 1));
        } catch (IOException ex) {

            return Optional.empty();
        }
    }
}
