# 📱 NIT3213 Final Android Application

This project is my final Android app submission for the NIT3213 subject at Victoria University.

---

## 🔍 Overview

This app includes:
- ✅ Login screen that authenticates using the API
- ✅ Dashboard screen that fetches and displays data dynamically from the API
- ✅ Details screen showing complete information for each item
- ✅ ViewModel, Retrofit, and StateFlow used for clean architecture
- ✅ Fully API-based, no hardcoded data
- ✅ Compatible with any student ID and dynamically displays personalized data

---

## 🔗 API Details

- **Base URL**: `https://nit3213api.onrender.com/`
- **Login Endpoint**: `POST /sydney/auth`
- **Dashboard Endpoint**: `GET /dashboard/{keypass}`

---

## 🚀 How to Run This App

1. Open this project in Android Studio.
2. Make sure internet connection is active for API requests.
3. Click **Run** to launch on an emulator or device.
4. Use your **first name** and **student ID** (e.g. `Pardeep` / `s8093702`) to log in.
5. Navigate through dashboard and view detailed info.

---

## 📦 Dependencies Used

- Retrofit2 (Networking)
- Moshi (JSON parsing)
- StateFlow & ViewModel (Live data management)
- Kotlin Coroutines (Async tasks)
- (Optional) Hilt (for dependency injection)

---

## 🧪 Unit Testing

Includes:
- ✅ RetrofitClient test
- ✅ DashboardActivity test
- ✅ LoginActivity test

---

## 👩‍💻 Developer

**Pardeep Kaur**  
Bachelor of IT – Victoria University  
Student ID: s8093702

---

## 🧑‍🏫 For Teacher

📁 GitHub Repo Link:  
[https://github.com/pardeep383/NIT3213FinalApp](https://github.com/pardeep383/NIT3213FinalApp)
