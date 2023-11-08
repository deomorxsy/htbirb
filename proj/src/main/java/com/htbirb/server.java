// java default package standard
package com.htbirb;
import com.htbirb.*;

// network IO and BSD/Bekerley socket libraries
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
// uri relative path
import java.net.URL;

// relative path libraries
//
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// collections (array) and utils
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Date;

/*
 * ISI task 1:
 * 1. create http server,
 * 2. return at least 3 HTTP codes,
 * 3. // feat: serve simple static webpage as response
 */

public class server {

    // PORT will be shared across all instances of class. It will also be read-only
    private static final Integer PORT = 8080;

    public static void main(String args[]) throws IOException {
        start(PORT);

    }

    public static void start(int port) throws IOException {
        ServerSocket servo = new ServerSocket(port);
        System.out.format("Listening for connection on port %s", Integer.toString(PORT));
        while (true) {

            Socket socket = servo.accept();
            try {
                birbHandler(socket);  // handle client socket
                                      //
                } finally {
                    if (socket != null && !socket.isClosed())  {
                        socket.close();
                    }
            }
        //servo.close();

        }}

    private static void birbHandler(Socket socks) throws IOException {
        //Date today = new Date();

        /* ======================
         *
         * hard-coded output sample
         *
        // use PrintWriter instead of OutpuStreams. The later is for binary data.
        PrintWriter out = new PrintWriter(socks.getOutputStream(), true);
        // send http response header
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html");
        out.println("\r\n"); // blank line separates header from body
        // http response body
        out.println("<h1>Hello, this is a response header</h1>");
        socks.close();*/


        /* ======================
         *
         * imported output sample from a layout
         *
         */
        String relativePath = "./src/main/webapp/layouts/index.html";
        Path currentPath = Paths.get("").toAbsolutePath();
        Path absolutePath = currentPath.resolve(relativePath).normalize();
        System.out.println("\nAbsolute path: " + absolutePath);


        Path filePath = Paths.get(relativePath);
        //Path filePath = absolutePath;
        //if exists, return verb 200 OK
        if (Files.exists(filePath)) {
            String contentType = guessMIMEType(filePath);
            String content = new String(Files.readAllBytes(filePath));
            sendResp(socks, "200 OK", contentType, content);
        } else {
            // if does not, return verb 404 not found
            String contentType = "text/html";
            String notFoundContent = "<h1>404 not found :(</h1>";
            sendResp(socks, "404 not found", contentType, notFoundContent); //last type had .getBytes()
        }
        //socks.close();
    }

    private static void sendResp(Socket greensocks, String status, String contentType, String content) throws IOException { // last type was byte[]
        /*OutputStream outgs = greensocks.getOutputStream();
        outgs.write(("HTTP/1.1" + status + "\r\n").getBytes());
        outgs.write(("ContentType: " + contentType + "\r\n").getBytes());
        outgs.write("\r\n".getBytes());
        outgs.write(content);
        outgs.write("\r\n\r\n".getBytes());
        outgs.flush();*/

        // use PrintWriter instead of OutpuStreams. The later is for binary data.
        PrintWriter out = new PrintWriter(greensocks.getOutputStream(), true);
        // send http response header
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html");
        out.println("\r\n"); // blank line separates header from body
        // http response body
        //out.println("<h1>Hello, this is a response header</h1>");
        out.println(content.toString());
        //greensocks.close();
        //out.flush();
    }


    private static Path getFilePath(String path) {
        if ("/".equals(path)) {
            path = "../../../webapp/layouts/index.html";
        }

        return Paths.get("./", path);
        // edit
    }

    private static String guessMIMEType(Path filePath) throws IOException {
        // somehow it seems prone to error with probeContentType, so lets infer
        //return Files.probeContentType(filePath);
        //
        String fileName = filePath.toString();
        if (fileName.endsWith(".html") || fileName.endsWith(".htm")) {
            return "text/html";
        } else if (fileName.endsWith(".css")) {
            return "text/css";
        } else if (fileName.endsWith(".js")) {
            return "text/javascript";
        } else { //default value for all other cases, but also unknown files
            return "application/octet-stream";
        }

    }
}

