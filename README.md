# 🏠 Property Tax Calculator App

A modern **Property Tax Calculator Android Application** built using **Kotlin + Jetpack Compose + SQLite Database**.

This application helps users to calculate estimated property tax based on property details like area, property type, building age, occupancy status, and zone. The app stores calculation history locally for future reference.

---

## 📱 App Preview

Property Tax Calculator provides:

- Modern Jetpack Compose UI
- Simple tax calculation process
- Local SQLite storage
- Tax history management
- Statistics dashboard

---

## ✨ Features

### 🧮 Property Tax Calculation

Users can calculate property tax by entering:

- Owner Name
- Property Type
  - Residential
  - Commercial
  - Industrial

- Property Area (sq.ft)
- Building Age
- Occupancy Type
- Location Zone


---

### 📊 Tax Calculation Logic

The application follows a Unit Area based calculation method.


### Formula:

---

## 💰 Tax Rules Used

### Unit Rate

| Property Type | Rate |
|---|---|
| Residential | ₹1.5 |
| Commercial | ₹3.5 |
| Industrial | ₹2.5 |


### Building Age Factor

| Age | Factor |
|---|---|
| 0-5 Years | 1.0 |
| 5-10 Years | 0.9 |
| 10-20 Years | 0.8 |
| Above 20 Years | 0.7 |


### Occupancy Factor

| Type | Factor |
|---|---|
| Self Occupied | 1.0 |
| Rented | 1.2 |


### Zone Factor

| Zone | Factor |
|---|---|
| Urban | 1.5 |
| Semi Urban | 1.2 |
| Rural | 1.0 |


Tax Rate:

---

## 🛠️ Tech Stack

### Android

- Kotlin
- Jetpack Compose
- Material 3 UI
- MVVM Architecture

### Database

- SQLite

### Tools

- Android Studio
- Gradle
- Git & GitHub


---

## 🗂️ Application Modules


---

## 💾 Database Structure


Table: property_tax


---

## 📸 Screens Included

- Splash Screen
- Home Dashboard
- Property Tax Form
- Calculation Result
- Saved Records
- Tax Statistics
- About Developer


---

## 🚀 Future Enhancements

- PDF Tax Receipt Generation
- Cloud Backup
- User Login System
- Online Property Tax Payment Integration
- Dark Mode Improvements
- Graph Based Tax Analytics


---

## 👨‍💻 Developer

**Jeren Dev**

Android Developer


GitHub:

https://github.com/jeren-dev


LinkedIn:

Add your LinkedIn profile


---

## 📦 Installation


1. Clone the repository


2. Open project in Android Studio


3. Sync Gradle


4. Run application


---

## 📄 License

This project is developed for educational and learning purposes.

---

⭐ If you like this project, give it a star!
