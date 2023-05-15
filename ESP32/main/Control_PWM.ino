const int canalPWM = 0;                                         //  Propiedades señal PWM.
const int frecuencia = 5000;                                   //   Frecuencia en Hz.
const int resolucion = 8;                                     //    8-16 bits de resolución.
int Duty, Set_rpm;

const int portPin = 35;                              // Entrada análoga del potenciómetro.                                // Led que sirva para visualizar si se está ejecutando la interrupción por Timer.
int POT = 0;                                       // Variable que recibe lo que llega del potenciómetro.


void Control_PWM(){

      POT = analogRead(portPin);
      Set_rpm = map(POT,0,4095,0,255);
      Duty = Set_rpm; 

      Serial.println("Velocidad: ");
      Serial.println(Duty);

      //ledcWrite(canalPWM, Duty);

      delay(1000);
}
