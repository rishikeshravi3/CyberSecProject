package com.example.xssdemo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class DataCaptureController {

    @PostMapping("/capture-data")
    public String captureData(@RequestBody DataPayload payload) {
        try {
            String fileName = "captured_data_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";
            FileWriter writer = new FileWriter(fileName);
            writer.write("Captured Data:\n");
            writer.write(payload.getData());
            writer.close();
            return "Data captured successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to capture data: " + e.getMessage();
        }
    }
}

class DataPayload {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}