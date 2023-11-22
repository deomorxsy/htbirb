// java default package standard
package com.htbirb;
import com.htbirb.*;

// network IO and BSD/Bekerley socket libraries
import java.io.*;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;

// relative path libraries
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// handle endpoint routes
import java.net.URL;

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
        // 1. calls function start
        start(PORT);

    }

    public static void start(int port) throws IOException {
        ServerSocket servo = new ServerSocket(port);
        System.out.format("Listening for connection on port %s", Integer.toString(PORT));
        while (true) {

            Socket socket = servo.accept();
            try {
                URL url = new URL("http://localhost:8080/");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int rspCode = connection.getResponseCode();
                System.out.println("Response code: " + rspCode);

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();

                // 2. calls birbHandler, a socket handler
                birbHandler(socket, url);  // handle client socket


                } catch (IOException e){ //Exception handling
                    e.printStackTrace();
                } finally { // code that will be executed whether an exception occurs or not
                    if (socket != null && !socket.isClosed())  {
                        socket.close();
                    }
            }
        //servo.close();

        }}

    private static void birbHandler(Socket socks, String url) throws IOException {

        String relativePath = "./src/main/webapp/layouts/index.html";
        Path currentPath = Paths.get("").toAbsolutePath();
        Path absolutePath = currentPath.resolve(relativePath).normalize();
        System.out.println("\nAbsolute path: " + absolutePath);

        String css_relativePath = "./src/main/webapp/assets/style.css";
        Path   css_currentPath = Paths.get("").toAbsolutePath();
        Path css_absolutePath = css_currentPath.resolve(css_relativePath).normalize();
        System.out.println("\nCSS absolute path: " + css_absolutePath);


        //ok
        Path filePath = Paths.get(relativePath);

        //if exists, return verb 200 OK
        if (Files.exists(filePath)) {
            // check MIME, type of file
            String contentType = guessMIMEType(filePath);
            String content = new String(Files.readAllBytes(filePath));

            // send response based on home
            sendResp(socks, "200 OK", contentType, content);
        } else {
            // if does not, return verb 404 not found
            String contentType = "text/html";
            String notFoundContent = "<h1>404 not found :(</h1>";
            sendResp(socks, "404 not found", contentType, notFoundContent); //last type had .getBytes()
        }
    }

    private static void sendResp(Socket greensocks, String status, String contentType, String content) throws IOException { // last type was byte[]
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




    private static String guessMIMEType(Path filePath) throws IOException {
        // somehow it seems prone to error with probeContentType, so lets infer
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

