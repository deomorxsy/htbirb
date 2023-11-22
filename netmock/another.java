public class another {
    public static void dump(InputStream is) throws Exception {
        try (
            var reader = new InputStreamReader(is);
            var buffered = new BufferedReader(reader);
            )
            var line = "";
            while ((line = buffered.readLine()) != null) {
                System.out.println(line);
            }}

    public static void main(String... args) throws Exception {
        var message = "GET / HTTP/1.1\n\n";
        try (var socket = new Socket("localhost", 8080)) {
            try(
                var is = socket.getInputStream();
                var os = socket.getOutputStream();
                ){
                os.write(message.getBytes());
                dump(is);
                }
        }
}}
