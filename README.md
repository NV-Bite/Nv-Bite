<div align="center">
  <img src="https://github.com/NV-Bite/Nv-Bite/blob/master/Group%20236.png" style="width: 20%;">
  <h1>Mobile Application</h1>
  <p>Nv-Bite Apps was created by mobile development member using upload imaage, Retrofit, Room, and Firebase to build features such as authentication feature, custom food feature, check food nutrition feature, favorite food feature, and profile edit feature. Beside that, mobile development member also build another features such as splash screen, localization, etc.
</p>
</div>


<div align="center">
  <img src="https://github.com/NV-Bite/Nv-Bite/blob/master/NV-Bite.png" alt="Result App" style="width: 100%;">
</div>

## Tools & Libraries
- [Android Studio](https://developer.android.com/studio)
- Retrofit
- [Camera](https://developer.android.com/training/camerax)
- [Figma](https://www.figma.com/)
- [Firebase](https://firebase.google.com/)

## Demo Apps
[Nv-Bite Demo Apps](https://drive.google.com/drive/folders/1DfzBSgjT7R15S7XegnUYdNYHs7T4s_sP)

## Download APK
Download the APK here:<br>
[Nv-Bite APK](https://drive.google.com/drive/folders/1_irbazW6Y5N4acEu76stJgbow7ho8mbp)

## Installation
#### Prerequisites
- Android Studio
#### Steps
- Clone project
 ```bash
 $ git clone https://github.com/NV-Bite/Nv-Bite.git
 ```
- Run the Application


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
