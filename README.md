# 🔐 Keylogger Detector for Windows

A simple Java-based tool that scans active Windows processes to detect known keyloggers. If any are found, an alert email is sent automatically to the user.

---

## 📌 Features

✅ Scans running Windows processes  
✅ Compares with a suspicious process list  
✅ Sends email alerts for any detection  
✅ Logs detected processes to a file  
✅ Easy to run from terminal (VS Code or CMD)

---

## 💻 Technologies Used

- Java (JDK 17)  
- JavaMail API (`javax.mail`)  
- Java Activation Framework (`javax.activation`)  
- Windows `tasklist` command  
- VS Code

---

## 📂 Project Structure
```
KeyloggerDetectorJava/
├── src/
│   ├── Main.java
│   ├── ProcessScanner.java
│   ├── EmailSender.java
│   └── SuspiciousListLoader.java
├── lib/                     # JAR files for email support
├── suspicious_list.txt      # List of known keyloggers
├── config.properties         # Stores email credentials
├── logs/
│   └── detections.txt
```

---

## ⚙️ How to Run

1. ✅ Install **Java 17**  
2. ✅ Clone this repository  
3. ✅ Add your Gmail & app-password in `config.properties`

## config.properties

gmail.username=your-email@gmail.com
gmail.password=your-app-password
gmail.to=receiver-email@gmail.com


---

### 🔧 Compile & Run

```bash
cd src
javac -cp ".;../lib/javax.mail-1.6.2.jar;../lib/javax.activation-1.2.0.jar" *.java
java -cp ".;../lib/javax.mail-1.6.2.jar;../lib/javax.activation-1.2.0.jar" Main

## Sample output

=== Keylogger Detector for Windows ===
Detected: chrome.exe
Detected: explorer.exe
Email sent successfully.

##  👩‍💻 Created By
Sivapriya A
Final Year – B.Tech
GitHub: @Sivapriya22112004
