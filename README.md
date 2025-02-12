# 🏭 Foundry Shop Floor Data Capturing Software  

A secure authentication and data submission system built with **ASP.NET Core**, **Entity Framework**, and **Microsoft SQL Server**. This project enables role-based access control, JWT authentication, and real-time data entry via an **Android application**. Swagger integration allows easy API testing and documentation.  

---

## 📋 **Features**  
✔️ Secure **JWT-based authentication** (Access & Refresh Tokens)  
✔️ **Role-based access control** for users  
✔️ **Android app** for real-time data submission  
✔️ **Microsoft SQL Server** for efficient data management  
✔️ **Swagger integration** for API documentation  

---

## 🛠 **Prerequisites**  
Ensure you have the following installed before running the project:  

1️⃣ **.NET SDK** (Version `6.0` or later) → [Download](https://dotnet.microsoft.com/en-us/download)  
2️⃣ **Microsoft SQL Server** → [Download](https://www.microsoft.com/en-us/sql-server/sql-server-downloads)  
3️⃣ **SQL Server Management Studio (SSMS)** → [Download](https://aka.ms/ssmsfullsetup)  
4️⃣ **Visual Studio** (with `.NET & ASP.NET` workload installed)  
5️⃣ **Android Studio** (For running the Android app)  
6️⃣ **Postman** (Optional, for API testing) → [Download](https://www.postman.com/downloads/)  

---

## 🚀 **Installation & Setup**  

### 🔹 **Step 1: Clone the Repository**  
```sh
git clone https://github.com/Sandeep-G-S/Foundry-Shop-Floor-Data-Capturing-Software.git
cd Foundry-Shop-Floor-Data-Capturing-Software
```

### 🔹 Step 2: Configure the Database**
1️⃣ Open SQL Server Management Studio (SSMS)
2️⃣ Create a new database named: FoundryDB
3️⃣ Run the provided SQL script (database_script.sql) to set up tables

###🔹 Step 3: Configure Environment Variables**
Create a .env file in the root directory and add:
```sh
DB_CONNECTION_STRING="Server=YOUR_SERVER;Database=FoundryDB;User Id=sa;Password=your_password;"
JWT_SECRET="your-secret-key"
```

###🔹 Step 4: Run the Backend**
```sh
cd backend
dotnet restore
dotnet run
```

The API will start at http://localhost:5000 🚀

###🔹 Step 5: Run the Android App**
1️⃣ Open the android-app folder in Android Studio
2️⃣ Connect an emulator or physical device
3️⃣ Click Run ▶ to launch the app




