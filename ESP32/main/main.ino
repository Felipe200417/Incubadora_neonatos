#include <HardwareSerial.h>
#include <DHT.h>                                                  // Librería sensor de temperatura y humedad.
#include <DHT_U.h>
//Incluye las librerías
#include <HX711.h>
#include <Wire.h> 
//#include <LiquidCrystal_I2C.h>
#include <EEPROM.h>

// Crea un objeto HardwareSerial para la comunicación serial
HardwareSerial SerialESP32(1);
const int RX_PIN = 16;                             // Define los pines RX y TX para la comunicación serial
const int TX_PIN = 17;

const int NUM_SENSORS = 3; // Número de sensores
String Valores_sensores_ARDUINO[NUM_SENSORS];

const int NUM_DATOS_JAVA = 3; // Número de sensores
int Valores_JAVA[NUM_DATOS_JAVA];
String inputString;

int temp, hum;                                              // Variables que guardan la temperatura y la humedad respectivamente.

int bandera_java;                                 //Variable proporcionada por la GUI de JAVA
int bandera_alarmas;
int bandera_modo = 0;

int indicador_dato_java = 2;

int variador_envio = 1;
int variador_envio2 = 1;



                      
void setup() {
  SerialESP32.begin(9600, SERIAL_8N1, RX_PIN, TX_PIN);
  Serial.begin(9600);
  pinMode(indicador_dato_java,OUTPUT);
  setup_JAVA();
  setup_PID();
  setup_Peso();
  setup_alarmas();
  setup_Manual();

}

void loop() {

  
  recepcion_ARDUINO();
  //recepcion_JAVA();
  //envio_JAVA();
  
  switch (Valores_JAVA[0]) {
    
     // Enviar datos tecnicos por puerto serial para mostrar en ventana datos tecnicos.
    case 0: 

        detener();
        break;
      

    case 1:

        
        digitalWrite(indicador_dato_java,HIGH);
        bandera_modo = 1;
        //Serial.println("modo manual");
        //modo_manual();
        break;
      

    case 2:


        digitalWrite(indicador_dato_java,LOW);
        bandera_modo = 2;
        //Serial.println("modo automatico");
        //modo_automatico();
        break;

    default: break;
  }
  
}
