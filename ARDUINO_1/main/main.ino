#include <SoftwareSerial.h>

#define RX_PIN 0
#define TX_PIN 1

SoftwareSerial espSerial(RX_PIN, TX_PIN);
String dato;

void setup(){

  espSerial.begin(9600);
  //setup_ECG();
  //setup_Temperatura();
}

void loop(){

  dato = "160,2,3,";
  espSerial.println(dato);
  delay(1000);
  //Ejecutar_temperatura();
  //ECG();
}
