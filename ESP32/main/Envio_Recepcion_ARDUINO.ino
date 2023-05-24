void recepcion_ARDUINO(){
  
  if (SerialESP32.available()) {
    String inputString = SerialESP32.readStringUntil('\n'); // Leer una línea completa de datos del puerto serial
    char* token = strtok(const_cast<char*>(inputString.c_str()), ","); // Obtener el primer token
    int i = 0;
    while (token != NULL && i < NUM_SENSORS) {
      Valores_sensores_ARDUINO[i] = String(token); // Convertir el token actual a un valor entero y almacenarlo en el arreglo
      //Serial.println(Valores_sensores_ARDUINO[i]);
      token = strtok(NULL, ","); // Obtener el siguiente token
      i++;
    }
  }
}
