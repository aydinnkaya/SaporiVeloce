# 🍝 **Sapori Veloce** 

**Sapori Veloce** is a modern food ordering application developed using **Kotlin** and **Jetpack Compose**. The app follows the **MVVM architecture** and fetches real-time data from a backend API via **Retrofit**, providing a seamless and user-friendly experience for browsing food items, adding them to the cart, and placing orders.

---

## 🚀 **Key Features**

### 🔄 **Modular MVVM Architecture**
The project is built on the **Model-View-ViewModel (MVVM)** architecture, ensuring a clean separation between UI, business logic, and data management. This results in:
- **Model**: Handles data operations and API communication.
- **View**: Displays the UI using **Jetpack Compose**.
- **ViewModel**: Connects the data with the UI, ensuring smooth data flow.

### 🍕 **Food Ordering System**
- **Browse food items**: Fetch real-time data from the backend API.
- **Detailed view**: View information about individual food items.
- **Cart management**: Add items to the cart and manage them in real-time.
- **Order placement**: Place orders with real-time confirmation.

### 🌐 **API Communication with Retrofit**
- Uses **Retrofit** for handling API requests.
- Ensures up-to-date information on available food items, prices, and details.
- Robust error handling ensures smooth operation even with connectivity issues.

### 💉 **Dependency Injection with Hilt**
- **Hilt** is used for clean and scalable dependency management, injecting services such as **Retrofit**, repositories, and data sources into the **ViewModels**.

### 🎨 **Modern UI with Jetpack Compose**
- **Jetpack Compose** provides a responsive and adaptive UI across devices.
- Features smooth animations, modern UI components, and sleek transitions for a delightful user experience.

---

## 🛠️ **Technologies Used**

- **Kotlin**: Main language for Android development.
- **Jetpack Compose**: Modern toolkit for creating responsive UIs.
- **MVVM Architecture**: Separation of concerns for cleaner code.
- **Retrofit**: Handles networking and API communication.
- **Hilt**: For managing dependencies and improving modularity.
- **REST API**: Interacts with the backend to fetch food data.
- **Coroutines**: Efficiently manages network requests asynchronously.
- **Room Database**: For local data caching and offline capabilities.

---

## 🎥 Demo Video

<a href="video_link_here" target="_blank"> <img src="https://github.com/user-attachments/assets/91de398c-c354-45a5-a59e-686201f338b6" alt="Watch the video" width="400" /> </a>

---
<img width="250" alt="Screenshot 2024-10-18 at 15 39 50" src="https://github.com/user-attachments/assets/54f31398-b08a-4a06-b303-37e9c75ef13d"> | | <img width="250" alt="Screenshot 2024-10-18 at 15 37 34" src="https://github.com/user-attachments/assets/18832eb0-c4e3-433f-acda-7d5b447aee5f">


## 📸 Screenshots (Dark Mode)


| Home Screen | My Screen | My Orders |
|:-------------:|:------------:|:------------:|
| ![Home Screen](https://github.com/user-attachments/assets/c47b1b7a-c63e-4028-8365-1d066a66ce4a) | <img width="730" alt="Screenshot 2024-10-18 at 21 50 52" src="https://github.com/user-attachments/assets/2075fd5f-0882-4850-9ca1-13f91567ccf5">  | ![My Orders](https://github.com/user-attachments/assets/d555c10b-7ebc-42b5-9509-70d9e263ec55) |

| Card | Favorites | Empty Cart |
|:------------------:|:------------------:|:------------------:|
| ![Favorites](https://github.com/user-attachments/assets/b618d88b-615c-4b5f-968f-0a3505ad8c16) | ![Settings](https://github.com/user-attachments/assets/f8fcf8a4-7c0b-4283-8ced-41177aa0bbfd) | ![Empty Cart](https://github.com/user-attachments/assets/57f975a4-e5f1-4424-8b6d-432590dbd0b2) |

---

## 📸 Screenshots (Light Mode)

| Card | Detail  |Favorites |
|:-------------:|:------------:|:------------:|
| ![Home Screen](https://github.com/user-attachments/assets/f6794d31-182d-4da9-991b-488f00d90050) | ![My Orders](https://github.com/user-attachments/assets/89f52614-276f-4d30-b9d6-06ba522b9644) | ![My Orders](https://github.com/user-attachments/assets/ed43a5a9-6565-4dab-8dde-cece9308eacd) |

| My Orders | Settings | Empty Cart |
|:------------------:|:------------------:|:------------------:|
| ![Favorites](https://github.com/user-attachments/assets/abc905b9-0eef-4059-9b76-174dce4aa696) | ![Settings](https://github.com/user-attachments/assets/e53494e9-170d-4a1d-9837-1043d6ef2e46) | ![Empty Cart](https://github.com/user-attachments/assets/c9e0d8f2-7840-4292-8569-f7a838164352) |

---


## 🔧 **Installation and Setup**

To run **Sapori Veloce** on your local machine, follow these steps:

### ⚙️ Prerequisites
- **Android Studio** (latest version).
- An Android device or emulator.
- Working internet for API communication.

### 📝 Step-by-Step Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/aydinnkaya/SaporiVeloce.git
   ```

2. **Navigate to the project directory**:
   ```bash
   cd SaporiVeloce
   ```

3. **Open the project in Android Studio**:  
   Import and sync dependencies.

4. **Configure API settings**:  
   Set the base URL and API keys in configuration files.

5. **Run the app**:  
   Select a device/emulator and press `Run`.

---

## 🤝 **Contributing**

We welcome contributions! Follow these steps to contribute:

1. **Fork the repository**.
2. **Create a feature branch**: `git checkout -b feature-branch-name`.
3. **Make your changes** and commit them: `git commit -m 'Added feature XYZ'`.
4. **Push to your branch**: `git push origin feature-branch-name`.
5. **Open a pull request**.

---
