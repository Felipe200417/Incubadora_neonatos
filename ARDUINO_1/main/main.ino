#include <SoftwareSerial.h>

#define RX_PIN 0
#define TX_PIN 1

SoftwareSerial espSerial(RX_PIN, TX_PIN);
String dato;

float temperatura;


String tempCStr;

void setup(){

  espSerial.begin(9600);
  setup_ECG();
  setup_Temperatura();
}

void loop(){

  Ejecutar_temperatura();
  tempCStr = String(temperatura);
  dato = tempCStr+",40,30,";
  espSerial.println(dato);
  Serial.println(dato);
  delay(1000);
  //Ejecutar_temperatura();
  //ECG();
}
