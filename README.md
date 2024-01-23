UML CLASS DIAGRAM

The following diagram illustrates the UML Class representation of the project:![image](https://github.com/celikmehmetfatihh/BASIC-Bed-And-breakfaSt-In-Cyprus-/assets/78351594/20e77df6-b76e-4287-b564-066254de9281)

GUI PART


Upon running the code, the main screen reveals various functionalities:


![image](https://github.com/celikmehmetfatihh/BASIC-Bed-And-breakfaSt-In-Cyprus-/assets/78351594/70f94048-b188-4e51-8d49-5a5530230e78)

As an illustrative instance, the user display section demonstrates the graphical user interfaces (GUIs) developed for each functionality, implemented using the JAVA SWING FRAMEWORK:

  ![image](https://github.com/celikmehmetfatihh/BASIC-Bed-And-breakfaSt-In-Cyprus-/assets/78351594/db8ef066-bdfe-44a9-b181-380c20c060b0)

Another example is the add property section, which represents one of the numerous GUIs integrated into the project:

  ![image](https://github.com/celikmehmetfatihh/BASIC-Bed-And-breakfaSt-In-Cyprus-/assets/78351594/59f89986-70a8-4952-a971-3b0bd8d7c1c9)



DATABASE CONNECTIVITY

Database connectivity within the project is managed through two distinct approaches:

Working with Binary Files:
Users or properties are updated, deleted, or added through the program flow. Upon clicking the terminate button, user and property details are appended to binary files such as host.dat, standard.dat, gold.dat, sharedproperty.dat, fullproperty.dat. During application startup, these binary files are read using pipelining fileInputStream -> BufferedInputStream -> DataInputStream.

Working with a Database (XAMPP Control Panel and MySQL):
The project offers the flexibility of utilizing the XAMPP control panel for connecting to the MySQL database, as depicted in the provided image:
  
![image](https://github.com/celikmehmetfatihh/BASIC-Bed-And-breakfaSt-In-Cyprus-/assets/78351594/1236ebe0-59f5-44a5-b93b-8a400712bae4)

Users can choose between binary files and a database to store property and user details.

OBJECT SERIALIZATION AND SECURITY

User objects are serialized into an external file to enhance security measures against potential attacks on user data. When the application is closed, User objects are persistently stored in a file, and an MD5 hash is generated for that file. The MD5 hash is then written into another external file. Upon reloading the application, a thread is created to read the serialized user objects, regenerate the MD5 for the serialized objects, and compare it with the previously stored MD5 hash. If they match, it indicates that the serialized objects remain unmodified. In the case of a mismatch, the user is promptly alerted about the updated data.
