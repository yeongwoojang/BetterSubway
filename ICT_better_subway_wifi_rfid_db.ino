#include <SPI.h>
#include <MFRC522.h>  //RFID 를 위한 라이브러리 

#include <ESP8266WiFi.h>
#include <WiFiClient.h>
#include <ESP8266WebServer.h>
#include <ESP8266mDNS.h>  // Wifi 연결을 위한 라이브러리
#include <ESP8266HTTPClient.h>


#define RST_PIN   D9                            // reset핀은 9번으로 설정
#define SS_PIN    D10                           // SS핀은 10번으로 설정
                                               // SS핀은 데이터를 주고받는 역할의 핀( SS = Slave Selector )
#define LED  D3
#define BUTTON  D2  // 의자버튼
#define BUTTON2 D8  // 하차버튼

#define RST2 D4
const char* ssid = "iPhone";   // wifi ssid 와 password 입력
const char* password = "1q2w3e4r5t";
int Switch;
int cnt = 0;                        

MFRC522 mfrc(SS_PIN, RST_PIN);                 // MFR522를 이용하기 위해 mfrc객체를 생성

void setup(){

  pinMode(LED, OUTPUT);
  pinMode(BUTTON, INPUT);
  pinMode(BUTTON2, INPUT);
  digitalWrite(RST2, HIGH);
  pinMode(RST2, OUTPUT);
  
  Serial.begin(115200);                        
  SPI.begin();                               
  mfrc.PCD_Init();  
  
  WiFi.mode(WIFI_OFF);
  delay(1000);
  WiFi.mode(WIFI_STA);
  
  WiFi.begin(ssid, password);

  // Wait for connection
  while (WiFi.status() != WL_CONNECTED) {  // 와이파이 잡힐때까지 기다림
    delay(500);
    Serial.print(".1");
  }

    if (MDNS.begin("esp8266")) {
    Serial.println("MDNS responder started");
    }


}

void loop(){
  
  HTTPClient http;    
  HTTPClient http2;
  HTTPClient http3;

  WiFiClient client;
  
  String user_uid;  // 카드 uid 값을 위한 변수
  String getData = "";  // uid값을 aws로 보내기 위해 임시저장 변수
  String Link = ""; //aws 주소 저장을 위한 변수
  String payload = "fail";  //웹에서의 피드백 값 저장 변수

  
 /*LOW가 눌린거 0*/
    Switch = digitalRead(BUTTON);
    if(Switch == 0){  //카드 인식 없이 의자버튼이 눌리면 led on
      digitalWrite(LED,HIGH);
    }
    if(Switch == 1){
      digitalWrite(LED,LOW);
      
    }
  
    if(cnt > 15){
      cnt = 0;
      Switch = 0;
      digitalWrite(LED,LOW);
      digitalWrite(RST2, LOW);  // 카드 인식 15루프 안되면 보드 리셋
    }
  
  if ( !mfrc.PICC_IsNewCardPresent() || !mfrc.PICC_ReadCardSerial() ) {   
                                               // 태그 접촉이 되지 않았을때 또는 ID가 읽혀지지 않았을때
    delay(500);                                // 0.5초 딜레이 
    cnt++;
    Serial.println(cnt);
    return;                                    
  } 

    
  
  for (byte i = 0; i < 4; i++) {               // 태그의 ID출력하는 반복문.태그의 ID사이즈(4)까지
    user_uid = user_uid+mfrc.uid.uidByte[i];
  }

  getData = "?card_uid=" + user_uid;
  Link = "http://ec2-15-164-129-208.ap-northeast-2.compute.amazonaws.com:3000/" + getData;  // aws + user_uid 값 

  http.begin(client, Link); //HTTP 개통
  int httpCode = http.GET(); // get방식으로 위의 값 전송
  payload = http.getString(); // 피드백 값 저장
  payload = payload.substring(0, 4);
  Serial.println(payload);

  http.end();


  delay(5000);
  Switch = digitalRead(BUTTON);
  Serial.println(Switch);
  if(Switch == 0){  // 의자 눌림

    
    if(payload == "pass"){
    String returnData = "http://ec2-15-164-129-208.ap-northeast-2.compute.amazonaws.com:3000/arduino";  // pass피드백이 오고 의자가 눌렸으면 서버에 정상 착석 전송
    http2.begin(client, returnData);
    int httpCode2 = http2.POST("isOk=pass");
    String resultPayload = http2.getString();
    http2.end();
    Serial.println(resultPayload);
    }
  
    
    while(true){
      if(payload != "pass"){
        digitalWrite(LED,HIGH); // pass가 아니면 led on
      }else{
        digitalWrite(LED,LOW);
      }
      delay(1000);
    Serial.println(digitalRead(BUTTON2));
      if(digitalRead(BUTTON2) == 0){  // 하차 버튼 눌릴 시 서버에 하차 정보전송
        String seatOFFData = "http://ec2-15-164-129-208.ap-northeast-2.compute.amazonaws.com:3000/out";
        http3.begin(client, seatOFFData);
        int httpCode = http3.POST("out=1");
        http3.end();
        Serial.println("seat off");
        break;
      }
     
    }}

  Serial.println("end");
}



