<div align="center">
  <img src="https://github.com/NV-Bite/Nv-Bite-App/blob/master/util/logo%20nvbite.png" style="width: 10%;" alt="Nv-Bite Logo">
  <h1>Mobile Application Nv-Bite</h1>
  <p>
NV-Bite is a mobile application developed by the Mobile Development Team using modern tools and libraries like Retrofit, Room, and Firebase. The app focuses on sustainability by incorporating features such as Carbon Footprint Detection, personalized sustainability tips, and the Pick-Drop Map. Additional features include a splash screen, login user, and more.
  </p>
</div>


<a href="https://www.figma.com/design/fZioLe7cr7ANnt96ztDckB/envBite?node-id=0-1&t=R3m69bsPQAWN9Cud-1"><img src="https://github.com/NV-Bite/Nv-Bite-App/blob/master/util/NV-Bite.png" alt="Nv-Bite Application Preview" style="width: 100%;"></a>


## Features

- **History**: Review and monitor your Carbon Footprint records.  
- **Profile Settings**: Customize the app's settings and preferences.  

## Tools & library
- [Android Studio](https://developer.android.com/studio)  
- [Retrofit](https://square.github.io/retrofit/)  
- [CameraX](https://developer.android.com/training/camerax)  
- [Figma](https://www.figma.com/)  
- [Firebase](https://firebase.google.com/)


## 🎥 Demo Apps
Explore the application demo here:  
[Nv-Bite Demo Apps](https://youtu.be/fdwMxzZ0uAc?si=fJ0IkGcVNDNdDTk0)

## 📱 Downloadable APK
Download and install the app using the APK file provided:  
[Nv-Bite APK](https://drive.google.com/drive/folders/1_irbazW6Y5N4acEu76stJgbow7ho8mbp)

## Requirements

- **Android Studio**: Use Android Studio for building and running the project.
- **Android SDK 24+**: Minimum SDK version is 24.
- **Gradle**: For building and managing project dependencies.

## Setup Instructions

1. **Clone the Repository**  
   Begin by cloning the repository to your local machine:  
   ```bash
   https://github.com/NV-Bite/Nv-Bite-App.git
   ```  

2. **Launch Android Studio**  
   Open Android Studio and select the option **Open an existing project**, then navigate to the directory where the project is located.  

3. **Sync with Gradle**  
   Allow Android Studio to sync the project with Gradle automatically. Ensure that all required dependencies are downloaded during the sync process.  

4. **Run the Application**  
   - Connect your Android device to your computer or set up an Android emulator.  
   - In Android Studio, click the **Run** button to build and install the application on your chosen device or emulator.  

# Mobile Development Architecture: Nv-Bite

The architecture of the **Nv-Bite** application is designed with two primary layers:  
**UI Layer** and **Data Layer**.

### UI Layer
In the **UI Layer**, a **ViewModel** is integrated with **LiveData** to supply data that can be observed by components like **Activity** and **Fragment**.

### Data Layer
The **Data Layer** is responsible for managing data sources through a **Repository**, which acts as a bridge between the local database and remote web services.

### Communication with Remote APIs
Communication with remote APIs is handled via **Retrofit**, which streamlines the process of parsing JSON data.

### Data Flow
Data flow in this application follows a structured approach: it starts from the **UI Component**, which subscribes to **LiveData** provided by the **ViewModel**, and then the **ViewModel** retrieves the data from the **Repository**.

This **Repository** can access data from either local or remote sources based on the need. This architecture ensures optimal data management and simplifies testing, making the app efficient and robust.

> **Note**: This architecture follows the best practices in mobile development, focusing on maintainability and scalability.

---

## 🌐 Social Media

### 🧑‍💻 Teguh Aldianto
<div align="left">
  <ul>
    <li>🌟 <strong>Instagram</strong>: <a href="https://www.instagram.com/aalditgh_/" target="_blank">@aalditgh_</a></li>
    <li>🔗 <strong>LinkedIn</strong>: <a href="https://www.linkedin.com/in/teguh-aldianto-705b53298/" target="_blank">Teguh Aldianto</a></li>
    <li>🎓 <strong>Pendidikan</strong>: D4 Manajemen Informatika</li>
    <li>🏫 <strong>Universitas</strong>: Universitas Negeri Surabaya (UNESA)</li>
  </ul>
</div>

---

### 🧑‍💻 Evy Nur Octaviani
<div align="left">
  <ul>
    <li>🌟 <strong>Instagram</strong>: <a href="https://www.instagram.com/evy.vyoca/" target="_blank">@evy.vyoca</a></li>
    <li>🔗 <strong>LinkedIn</strong>: <a href="http://www.linkedin.com/in/evy-nur-octaviani-b38a87257" target="_blank">Evy Nur Octaviani</a></li>
    <li>🎓 <strong>Pendidikan</strong>: S1 Teknik Elektro</li>
    <li>🏫 <strong>Universitas</strong>: Universitas Negeri Semarang (UNNES)</li>
  </ul>
</div>
