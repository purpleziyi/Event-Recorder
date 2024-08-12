
  Development Language: Java  
  Framework: SpringBoot  
  Runtime Environment:  IntelliJ IDEA + JDK 17 + Maven 3

<br/>

 **Key Technical Challenges** 
- **Database Operations**: Utilized **MyBatis** for efficient database operations, enabling **dynamic SQL** generation and seamless object-relational mapping.
- **Authentication**: Implemented **JWT (JSON Web Token)** for secure user authentication and session management. JWTs are issued upon successful login and validated in subsequent requests to ensure security.
- **Caching**: Leveraged **Redis** for caching purposes, enhancing application performance. Implemented a Redis **Token Revocation Mechanism** to manage token invalidation and ensure security upon changes like password updates.
- **Validation**: Utilized Bean Validation for parameter validation. Employed standard constraints such as @NotEmpty and @Pattern to ensure data integrity. Implemented **custom validation** for specific fields to enforce business rules.
- **Interceptor**: Registered an interceptor to ensure that all incoming requests are processed uniformly. This allows for centralized validation, logging, and other cross-cutting concerns to be handled efficiently.
- **ThreadLocal**: Used ThreadLocal to manage thread-local variables, reducing the need for parameter passing and facilitating data sharing within the same thread. This improves performance and ensures thread safety.

<br/>

**Overview of Functionality/Modules**
1. **Login and Registration**
2. **User Profile Management:**
   - Allows users to edit basic profile information, change avatars, and update passwords.
3. **Article Categories:**
   - Displays all article categories, includes querying and filtering functionality.
   - Supports adding, deleting, and editing categories.
4. **Article Management:**
   - Displays a table of articles with search and pagination functionality.
   - Allows publishing, deleting, and editing articles.
5. **File Upload to Cloud Platform:**
   - Enables users to upload files to a cloud platform.

<br/>

**Function display/operation screenshots**
![big-event login](https://github.com/user-attachments/assets/d7f203e2-79b4-486d-b281-f794743ad094)
![big-event register](https://github.com/user-attachments/assets/252c50e7-9f0c-4f6b-a0d9-7bb24fb1b70a)
![big-event article_category](https://github.com/user-attachments/assets/6b760774-316a-4895-89a7-fde307f3fc55)
![big-event add_categoty](https://github.com/purpleziyi/BigEvent/assets/161695864/1b9801ed-2410-437e-8a0b-4b97b820c144)
![big-event article_management](https://github.com/user-attachments/assets/3de888cd-e3a9-4319-9d72-e3a034d13e01)
![big-event add article](https://github.com/user-attachments/assets/51469868-fead-4743-b881-f8a14e874fd4)
![big-event userInfo](https://github.com/user-attachments/assets/28c9f8a4-56c0-4736-bb3f-213b6e0c849e)
![big-event modify_info](https://github.com/user-attachments/assets/ff3dc914-510b-4da1-9afb-f400de456d4d)

![big-event change_avatar](https://github.com/user-attachments/assets/b4f402e6-17e8-44ea-ae46-50c21cb253d7)
![big-event change_password](https://github.com/user-attachments/assets/ef8a1bc3-d1a8-4b2c-92f7-2ce2b04d5eca)
![big-event logout](https://github.com/purpleziyi/BigEvent/assets/161695864/22f3a938-dff5-4bde-9dbd-e27a44cbe3d1)




