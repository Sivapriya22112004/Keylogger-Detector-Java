# 🛡️ Keylogger Detector - Java

A Java-based desktop application that detects suspicious processes such as keyloggers or data stealers running in the background of a Windows system.

## 🎯 Project Objective

To build a simple, effective, and email-alert-enabled **Keylogger Detection Tool** for Windows as a part of a final year cybersecurity project. It helps identify suspicious processes and notifies the user immediately.

---

## ⚙️ Features

✅ Scans running processes using Windows `tasklist`  
✅ Compares process names with a list of known suspicious executables  
✅ Logs detections in a file  
✅ Sends alert emails to user (via Gmail SMTP)  
✅ Ignores sensitive files using `.gitignore`  

---

## 🖥️ Tech Stack

- **Java 11+**
- **JavaMail API (javax.mail)**
- **Windows OS (uses `tasklist` command)**

---

## 📁 Project Structure

```bash
Keylogger-Detector-Java/
│
├── src/                       # Java source files
│   ├── Main.java
│   ├── ProcessScanner.java
│   ├── SuspiciousListLoader.java
│   └── EmailSender.java
│
├── logs/                     # Output logs (e.g., detections.txt)
├── lib/                      # JavaMail + Activation libraries (JARs)
├── suspicious_list.txt       # List of suspicious process names
├── config.properties         # (Ignored) Gmail credentials - NOT pushed to GitHub
├── .gitignore                # Specifies which files/folders to exclude
└── README.md                 # Project documentation (this file)
