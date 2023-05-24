#include <Wire.h>
#include "MAX30100_PulseOximeter.h"

#define REPORTING_PERIOD_MS 1000

PulseOximeter pox;
uint32_t tsLastReport = 0;


void Retorno(){
  
}


void setup_Oximetro() {
  Wire.begin();
  pox.setIRLedCurrent(MAX30100_LED_CURR_7_6MA);
  pox.setOnBeatDetectedCallback(Retorno);
}

void Oximetro() {
  pox.update();
  if (millis() - tsLastReport > REPORTING_PERIOD_MS) {
    Oxigeno_sangre = pox.getSpO2();
    tsLastReport = millis();
  }
}
