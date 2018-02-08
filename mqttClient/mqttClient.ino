#include <ESP8266WiFi.h>
#include "Adafruit_MQTT.h"
#include "Adafruit_MQTT_Client.h"
#include <ArduinoJson.h>
#include "dht.h"
#include <stdio.h>
#include <Time.h>
//#include <TimeLib.h>

/************************* WiFi Access Point *********************************/

#define WLAN_SSID       "ARDIC_GUEST"
#define WLAN_PASS       "w1Cm2ardC"

/************************* Adafruit.io Setup *********************************/

#define AIO_SERVER      "192.168.2.54"
#define AIO_SERVERPORT  1883                   // use 8883 for SSL
#define AIO_USERNAME    "ardic.smart.office"
#define AIO_CLIENTID    "ardic.smart.office"

#define AIO_KEY         "ardic12345"

/************ Global State (you don't need to change this!) ******************/

// Create an ESP8266 WiFiClient class to connect to the MQTT server.
WiFiClient client;
// or... use WiFiFlientSecure for SSL
//WiFiClientSecure client;

// Setup the MQTT client class by passing in the WiFi client and MQTT server and login details.
Adafruit_MQTT_Client mqtt(&client, AIO_SERVER, AIO_SERVERPORT,AIO_CLIENTID, AIO_USERNAME, AIO_KEY);

/****************************** Feeds ***************************************/

#define NODE_ID "DH11NODE"

#define PUBLISH_TEMPERATURE_DATA   "things/" NODE_ID "/testthing/data" 
#define PUBLISH_HUMIDITY_DATA   "things/" NODE_ID "/testthing/data"

Adafruit_MQTT_Publish publishData = Adafruit_MQTT_Publish(&mqtt, PUBLISH_HUMIDITY_DATA, MQTT_QOS_1);


// Setup a feed called 'ledBrightness' for subscribing to changes.
Adafruit_MQTT_Subscribe ledBrightness = Adafruit_MQTT_Subscribe(&mqtt, AIO_USERNAME "/feeds/ledBrightness");

/*************************** Sketch Code ************************************/

// Bug workaround for Arduino 1.6.6, it seems to need a function declaration
// for some reason (only affects ESP8266, likely an arduino-builder bug).

#define dht_apin D0 

void MQTT_connect();

dht DHT;

void setup() {
  Serial.begin(9600);
  delay(10);

  Serial.println(F("Adafruit MQTT Test"));

  // Connect to WiFi access point.
  Serial.println(); Serial.println();
  Serial.print("Connecting to ");
  Serial.println(WLAN_SSID);

  WiFi.begin(WLAN_SSID, WLAN_PASS);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println();

  Serial.println("WiFi connected");
  Serial.println("IP address: "); Serial.println(WiFi.localIP());

  delay(1000);
}

void loop() {
  
  MQTT_connect();

  DHT.read11(dht_apin);
  
  Serial.print("Current humidity = ");
 
  Serial.print(DHT.humidity);

  Serial.print("%  ");

  Serial.print("temperature = ");

  Serial.print(DHT.temperature); 

  Serial.println("C  ");

  char data[100];
  sprintf(data, "Humidity: %.2f, Tempereture: %.2f", DHT.humidity, DHT.temperature);

  humData.publish(data);
  

  delay(5000);

  
  // ping the server to keep the mqtt connection alive
  // NOT required if you are publishing once every KEEPALIVE seconds
  /*
  if(! mqtt.ping()) {
    mqtt.disconnect();
  }
  */
}


// Function to connect and reconnect as necessary to the MQTT server.
// Should be called in the loop function and it will take care if connecting.
void MQTT_connect() {
  int8_t ret;

  // Stop if already connected.
  if (mqtt.connected()) {
    return;
  }

  Serial.print("Connecting to MQTT... ");

  uint8_t retries = 3;
  while ((ret = mqtt.connect()) != 0) { // connect will return 0 for connected
       Serial.println(mqtt.connectErrorString(ret));
       Serial.println("Retrying MQTT connection in 5 seconds...");
       mqtt.disconnect();
       delay(5000);  // wait 5 seconds
       retries--;
       if (retries == 0) {
         // basically die and wait for WDT to reset me
         while (1);
       }
  }
  Serial.println("MQTT Connected!");
}

