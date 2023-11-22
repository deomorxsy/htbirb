public class MockSocket extends Socket {
     public MockSocket() {}
     public InputStream getInputStream() {
         return new ByteArrayInputStream("GET / HTTP/1.1\nHost: localhost".getBytes());
     }

     public OutputStream getOutputStream() {
         return new OutputStream() {
             @Override
             public void write(int b) throws IOException {

             }
         };
     }
 }
