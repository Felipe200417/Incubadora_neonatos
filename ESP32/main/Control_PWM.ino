const int canalPWM = 0;                                         //  Propiedades señal PWM.
const int frecuencia = 5000;                                   //   Frecuencia en Hz.
const int resolucion = 8;                                     //    8-16 bits de resolución.
int Duty, Set_rpm;

                                   // Variable que recibe lo que llega del potenciómetro.


void Control_PWM(){
  
      Duty = Set_rpm; 

      Serial.println("Velocidad: ");
      Serial.println(Duty);

      //ledcWrite(canalPWM, Duty);

      delay(1000);
}
