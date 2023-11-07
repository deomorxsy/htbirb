// java default package standard
package com.htbirb;
import com.htbirb.*;

// network IO and BSD/Bekerley socket libraries
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// relative path libraries
//
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// collections (array) and utils
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/*
 * ISI task 1:
 * 1. create http server,
 * 2. return at least 3 HTTP codes,
 * 3. // feat: serve simple static webpage as response
 */

public class server {

    private Integer PORT = 8080;

    public static void main(String args[]) throws IOException {

    }

    public void start(int port) {
        ServerSocket servo = new ServerSocket(PORT);
        System.out.println("Listening for connection on port %s", Integer.toString(PORT));
        Socket client
    }
}

