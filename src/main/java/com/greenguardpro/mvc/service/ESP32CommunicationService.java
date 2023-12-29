package com.greenguardpro.mvc.service;
import org.springframework.beans.factory.annotation.Autowired;
//ESP32CommunicationService.java
import org.springframework.stereotype.Service;

import com.greenguardpro.mvc.model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

@Service
public class ESP32CommunicationService {

 private static final String ESP32_IP_ADDRESS = "162.168.10.0"; // Subject to a change !!
 private static final int ESP32_PORT = 8088;
 @Autowired
 private UserService userservice;
 

 
 public void sendToESP32(User user) {
	 
     try (Socket socket = new Socket(ESP32_IP_ADDRESS, ESP32_PORT)) {
         OutputStream outputStream = socket.getOutputStream();

         // Send 8 humidity Max and Min short values (or less)
          for(int i = 0; i < 4; i++) {
        	  //if(user.getHumiditySensors().get(i).isExisting()) {
        		  outputStream.write("plant:".getBytes());
        		  outputStream.write(String.valueOf(user.getHumiditySensors().get(i).getId() + 1).getBytes());
        		  outputStream.write(",".getBytes());
        		  outputStream.write(String.valueOf(user.getHumiditySensors().get(i).getHumidityMaxValue()).getBytes());
        		  outputStream.write(",".getBytes());
        		  outputStream.write(String.valueOf(user.getHumiditySensors().get(i).getHumidityMinValue()).getBytes());
        		  outputStream.write(",".getBytes());
        		  outputStream.write(String.valueOf(user.getHumiditySensors().get(i).isExisting()).getBytes());
        		  outputStream.write("\n".getBytes());
        	  //}
        	 
         }
          
        /* for (int i = 0; i < 8; i++) {
             outputStream.write(String.valueOf(shortValues[i]).getBytes());
             if (i < 7) {
                 outputStream.write(",".getBytes());
             }
         }*/

         // Send 1 interval int value
         
         outputStream.write("interval:".getBytes());
         //outputStream.write(",".getBytes());
         outputStream.write(String.valueOf(user.getUpdateInterval()).getBytes());
         outputStream.write("\n".getBytes());
     } catch (Exception e) {
         e.printStackTrace();
     }
 }
 

 public void receiveFromESP32(User user) {
     try (Socket socket = new Socket(ESP32_IP_ADDRESS, ESP32_PORT);
          BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

         // Read response data
         String responseData = reader.readLine();

         // Process the received response
         processESP32Response(responseData,user);

     } catch (Exception e) {
         e.printStackTrace();
     }
 }
 
 private void processESP32Response(String responseData, User user) {
	    // Split the response data based on the labels "plant:" and "waterLevelLow:"
	    String[] dataParts = responseData.split("plant:|waterLevelLow:");

	    // Initialize variables to store water level low information
	    boolean waterLevelLow ;

	    // Iterate through the data parts
	    for (String dataPart : dataParts) {
	        // Check if the data part starts with "waterLevelLow:"
	        if (dataPart.startsWith("waterLevelLow:")) {
	            // Extract the value after the label "waterLevelLow:"
	            waterLevelLow = Integer.parseInt(dataPart.substring("waterLevelLow:".length())) == 1;
	            user.getWaterLevelSensor().setWaterState(waterLevelLow);
	        }
	        // Check if the data part starts with a comma ","
	        else if (dataPart.startsWith(",")) {
	            // Remove the leading comma ","
	            dataPart = dataPart.substring(1);

	            // Split the remaining part based on the comma ","
	            String[] plantInfoArray = dataPart.split(",");

	            // Iterate through plant entries (each entry has an index and a reading)
	            for (int i = 0; i < plantInfoArray.length; i += 2) {
	                // Ensure there are enough elements for both index and reading
	                if (i + 1 < plantInfoArray.length) {
	                    // Extract the plant index and moisture value
	                    int plantIndex = Integer.parseInt(plantInfoArray[i]);
	                    short moistureValue = Short.parseShort(plantInfoArray[i + 1]);
	                    // Process plant information (index and moisture value)
	                    user.getHumiditySensors().get(plantIndex).setHumidityValue(moistureValue);
	                }
	            }
	        }
	    }
	    try {
			this.userservice.updateUser(user.getIdUser(), user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

