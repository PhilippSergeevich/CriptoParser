package org.example;

import org.example.Cripta.Parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;


public class Main {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Future future = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                String apiUrl = "https://api.kriptomat.io/public/prices";
                Parser parser = new Parser();
                FileHandler fileHandler = new FileHandler();
                try {
                    String json = parser.doRequest(apiUrl);
                    String currentData = formatData();
                    parser.parseJsonString(json);
                    String price = parser.parseJsonString(json);
                    String dataWithPrice = currentData + " " + price;
                    fileHandler.quotesWriter(dataWithPrice);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 18, TimeUnit.SECONDS);

    }


    public static String formatData() {

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }
}
