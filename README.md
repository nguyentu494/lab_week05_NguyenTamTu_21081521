# Lab Week 05 - Spring Boot Project

# Nguyễn Tâm Tú

## Mục Tiêu Dự Án
Xây dựng một ứng dụng Spring Boot hỗ trợ:

1. **Quản lý dữ liệu tuyển dụng**:
   - Công ty có thể đăng tin tuyển dụng với các kỹ năng (skills) yêu cầu.
   - Ứng viên có thể đăng nhập để được gợi ý công việc phù hợp.

2. **Tìm kiếm và đề xuất**:
   - Hỗ trợ công ty tìm ứng viên có kỹ năng phù hợp.
   - Gửi email mời ứng viên phù hợp tham gia ứng tuyển.
   - Đề xuất các kỹ năng mà ứng viên có thể học để tăng cơ hội việc làm.

---

## Chức Năng Chính
- Công ty đăng bài tuyển dụng với kỹ năng mong muốn.
- Ứng viên nhận gợi ý công việc phù hợp.
- Công ty tìm ứng viên và gửi email mời ứng tuyển.
- Đề xuất kỹ năng học tập cho ứng viên.

---

## Yêu Cầu Kỹ Thuật

### 1. **Tạo Entities và Cơ Sở Dữ Liệu**
Khi thực thi, ứng dụng sẽ tạo ra các bảng trong cơ sở dữ liệu với cấu trúc như sau:

#### Các bảng chính:
- **User**: Lưu thông tin ứng viên.
- **Company**: Lưu thông tin công ty.
- **JobPost**: Lưu thông tin công việc do công ty đăng.
- **Skill**: Lưu danh sách các kỹ năng.
- **UserSkill**: Liên kết ứng viên với các kỹ năng họ có.
- **JobSkill**: Liên kết công việc với các kỹ năng yêu cầu.

Sơ đồ lớp:


![image](https://github.com/user-attachments/assets/d67b5325-5577-4fea-8cea-1e1fc6fdc44b)


#### Ghi chú:
Sử dụng các mối quan hệ **@OneToMany**, **@ManyToOne**, và **@ManyToMany** để liên kết các thực thể.

### 2. **Cấu hình Security**

![image](https://github.com/user-attachments/assets/923eab7b-fe39-4fb7-aec1-3ef900d7d777)


### 3. **Repositories Interface**
Tạo các repository để thao tác với cơ sở dữ liệu:

- **AddressRepository**: Quản lý các thao tác trên bảng Address.
- **AccountRepository**: Quản lý các thao tác trên bảng Account.
- **CandidateRepository**: Quản lý các thao tác trên bảng Candidate
- **ExperienceRepository**: Quản lý các thao tác trên bảng Experience
- **CompanyRepository**: Quản lý các thao tác trên bảng Company.
- **JobRepository**: Quản lý các thao tác trên bảng Job.
- **SkillRepository**: Quản lý các thao tác trên bảng Skill.
- **CandidateSkillRepository**: Quản lý liên kết kỹ năng của ứng viên.
- **JobSkillRepository**: Quản lý liên kết kỹ năng của công việc.


![image](https://github.com/user-attachments/assets/f86bc5ac-4b15-4c53-b87e-5742ec7b7879)

### 4. **Lớp Services**
Cung cấp các chức năng chính:


![image](https://github.com/user-attachments/assets/0fbb1e62-930b-4444-a012-f04085507931)


### 5. **Giao Diện Người Dùng**
- **Công ty**:
- Đây là giao diện trang chủ khi đăng nhập vào với tài khoản role company
 ![image](https://github.com/user-attachments/assets/356c3187-b028-475a-a9b5-d31cf5d20fe8)

- Ở trang của company có thể đăng tin tuyển dụng với các kỹ năng yêu cầu
 ![image](https://github.com/user-attachments/assets/48344b5c-2257-4f55-a8d7-8748f8d837cc)

- Sau khi đăng tải người dùng có thể xem các công việc công ty mình đăng ở trang quản lý tin tuyển dụng
 ![image](https://github.com/user-attachments/assets/d7d6ed66-8777-47e6-a5b3-3c309d77aba2)

- Người dùng có thể tìm các ứng cử viên phù hợp với công việc mà công ty đăng tuyển
  ![image](https://github.com/user-attachments/assets/bee76fa9-61da-4084-8d38-e433c5059c11)

- Khi click vào "Mời ứng tuyển" thì sẽ gửi mail mời đến mail của ứng cử viên phù hợp
  ![image](https://github.com/user-attachments/assets/54f56428-e629-4b73-bca8-ceb7be409967)
   Mail mời ứng cử viên nhận
  ![image](https://github.com/user-attachments/assets/41847223-77eb-4626-9b13-b673e9c45784)



- **Ứng viên**:
  - Trang gợi ý danh sách công việc phù hợp với kỹ năng của ứng cử viên. Ngoài ra còn hiện các skill mà người dùng chưa có để có thể học thêm giúp phù hợp với công việc.
    ![image](https://github.com/user-attachments/assets/b4dc4b18-08ee-4968-bc36-3a26769e3dba)
  - Người dùng có thể sử dụng chatbot để tìm hiểu thêm thông tin để có thể dễ tìm được việc hơn
    ![image](https://github.com/user-attachments/assets/80df4774-6523-48c2-ad25-65e0e9cc027b)

- **Người dùng**
  - Có thể đăng ký tài khoản với role ứng viên hoặc công ty
    ![image](https://github.com/user-attachments/assets/15f30d5b-06c6-4c72-a7a8-3428cf0e63e4)
  - Tiếp theo, cần điền thông tin cá nhân của bản thân
    ![image](https://github.com/user-attachments/assets/c42ddf2e-685a-45d1-8f0b-5b2bf040b70b)
  - Sau khi đăng ký thành công thì sẽ được hiển thị thông báo và có thể chuyển sang trang đăng nhập
    ![image](https://github.com/user-attachments/assets/af635180-92e2-4768-9055-053b43816f13)
  - Account 46 đã được thêm thành công vào db với password được encode
    ![image](https://github.com/user-attachments/assets/7b485df1-d6f2-457e-9139-3d296b4688b8)

---

## Hướng Dẫn Cài Đặt

### 1. **Yêu Cầu Hệ Thống**
- Java 17
- Gradle 8.7
- Heidi SQL

### 2. **Cài Đặt Dự Án**
1. Clone repository:
   ```bash
   git clone https://github.com/nguyentu494/lab_week05_NguyenTamTu_21081521.git
   ```
2. Cấu hình database trong file `application.properties`:
   ```properties
   spring.application.name=lab_week_05_NguyenTamTu_21081521

   spring.config.import=optional:file:.env[.properties]

   spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
   spring.datasource.url=jdbc:mariadb://localhost:3306/works?createDatabaseIfNotExist=true
   spring.datasource.username=${user_my_sql}
   spring.datasource.password=${password_my_sql}
   
   spring.ai.mistralai.api-key=${api_key}
   spring.ai.mistralai.chat.options.model=mistral-small
   spring.ai.mistralai.chat.options.temperature=0.7
   
   spring.jpa.show-sql=true
   spring.jpa.generate-ddl=true
   spring.jpa.hibernate.ddl-auto=update
   
   spring.mail.host=smtp.gmail.com
   spring.mail.port=587
   spring.mail.username=${mail}
   spring.mail.password=${mailpass}
   spring.mail.properties.mail.smtp.auth=true
   spring.mail.properties.mail.smtp.starttls.enable=true
   ```
3. Chạy ứng dụng:
   ```bash
   Chạy ứng dụng
   Truy cập ứng dụng tại địa chỉ http://localhost:8080/
   ```

---

## File Structure
```plaintext
src
├── main
│   ├── java
│   │   └── com.example.labweek05
|   |         ├── backend
│   │         │    ├── configs
│   │         │    ├── dto
│   │         │    ├── enums
│   │         │    ├── ids
│   │         │    ├── models
│   │         │    ├── repositories
│   │         │    └── services
│   │         │    
│   │         └── frontend
│   │           └──controller
│   └── resources
│       ├── templates
│       ├── static
│       └── application.properties
```

---

## Đề Xuất Phát Triển Thêm
- Thêm tính năng chat giữa công ty và ứng viên.
- Phân tích và thống kê nhu cầu kỹ năng phổ biến.
