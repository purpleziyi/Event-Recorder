
  Development Language: Java  
  Framework: SpringBoot  
  Runtime Environment:  IntelliJ IDEA + JDK 17 + Maven 3

<br/>

 **Main Technologies** 
- **Database Operations**: Utilized **MyBatis** for efficient database operations, enabling **dynamic SQL** generation and seamless object-relational mapping.
- **Authentication**: Implemented **JWT (JSON Web Token)** for secure user authentication and session management. JWTs are issued upon successful login and validated in subsequent requests to ensure security.
- **Caching**: Leveraged **Redis** for caching purposes, enhancing application performance. Implemented a Redis **Token Revocation Mechanism** to manage token invalidation and ensure security upon changes like password updates.
- **Validation**: Utilized Bean Validation for parameter validation. Employed standard constraints such as @NotEmpty and @Pattern to ensure data integrity. Implemented **custom validation** for specific fields to enforce business rules.
- **Interceptor**: Registered an interceptor to ensure that all incoming requests are processed uniformly. This allows for centralized validation, logging, and other cross-cutting concerns to be handled efficiently.
- **ThreadLocal**: Used ThreadLocal to manage thread-local variables, reducing the need for parameter passing and facilitating data sharing within the same thread. This improves performance and ensures thread safety.

<br/>

**Overview of Functionality/Modules**
1. **Login and Registration:**
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
![big-event login](https://github.com/purpleziyi/BigEvent/assets/161695864/6c28934e-4faf-472f-8039-758596037605)
![big-event article_category](https://github.com/purpleziyi/BigEvent/assets/161695864/557a9fe3-2454-4042-ade9-7a48653d3034)
![big-event add_categoty](https://github.com/purpleziyi/BigEvent/assets/161695864/1b9801ed-2410-437e-8a0b-4b97b820c144)
![big-event article_management](https://github.com/purpleziyi/BigEvent/assets/161695864/ba3f50e0-bdce-4f3d-b903-d326c7007b92)
![big-event add article](https://github.com/purpleziyi/BigEvent/assets/161695864/96b2e6ba-635c-48cd-acf6-a3a13c93cc24)
![big-event userInfo](https://github.com/purpleziyi/BigEvent/assets/161695864/c66e8ef8-f937-4f1a-9054-bddaa46032ee)
![big-event modify_info](https://github.com/user-attachments/assets/3983000b-5c6c-4724-9f2f-318614497efc)
![big-event change_avatar](https://github.com/user-attachments/assets/07ecef93-3fab-4045-8985-d0569783054c)
![big-event logout](https://github.com/purpleziyi/BigEvent/assets/161695864/22f3a938-dff5-4bde-9dbd-e27a44cbe3d1)





