#include <OneWire.h>
#include <DallasTemperature.h>

#define Sensor_DS18B20 2  //Se asigno el pin de entrada


 
OneWire oneWire(Sensor_DS18B20);
DallasTemperature SensorTemp(&oneWire);

float tempT;
float* tempC = &tempT;


float Temperatura(){
  
  SensorTemp.requestTemperatures();
  tempT =  SensorTemp.getTempCByIndex(0); 
  tempC = &tempT;
  return *tempC;
  
}

void Ejecutar_temperatura(){
  temperatura = Temperatura();
      
}


void setup_Temperatura() {
  pinMode(INPUT,Sensor_DS18B20);
  SensorTemp.begin();
}
