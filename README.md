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
 $ git clone https://github.com/Bangkit-2023-Capstone-CH2-PS307/MD.git
 ```
- Run the Application


## Mobile Development Architecture [Model-View-ViewModel Architecture]
<div align="center">
  <p>
    The application structure consists of two main layers, namely the UI layer and the data layer. In the UI layer, a ViewModel is used that is integrated with LiveData to provide data that can be observed by components such as Activity and Fragment. Meanwhile, the data layer is responsible for managing data sources through a repository that functions as a liaison between the local database and remote web services. Communication with remote APIs is done using Retrofit, which supports the process of parsing JSON data directly. The data flow in this application starts from the UI component that subscribes to LiveData from the ViewModel, then the ViewModel retrieves data from the repository. This repository can access data from local or remote sources as needed. This approach aims to ensure optimal data management and facilitate testing
</p>
  <img src="https://raw.githubusercontent.com/Bangkit-2023-Capstone-CH2-PS307/.github/main/profile/assets/MD%20Architecture.png" alt="Model-View-ViewModel Architecture" style="width: 100%;">
</div>
