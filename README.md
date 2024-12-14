
# Lab Week 05 - Spring Boot Project

---

# **Thông tin**

### **Họ tên**: Nguyễn Tâm Tú
 
### **Mã số sinh viên**: 21081521
 
### **GVHD**: Võ Văn Hải

---

## **Mục Tiêu Dự Án**
Xây dựng một ứng dụng Spring Boot hỗ trợ:

1. **Quản lý dữ liệu tuyển dụng**:
   - Công ty có thể đăng tin tuyển dụng với các kỹ năng (skills) yêu cầu.
   - Ứng viên có thể đăng nhập để nhận gợi ý công việc phù hợp.

2. **Tìm kiếm và đề xuất**:
   - Hỗ trợ công ty tìm ứng viên có kỹ năng phù hợp.
   - Gửi email mời ứng viên phù hợp tham gia ứng tuyển.
   - Đề xuất các kỹ năng mà ứng viên có thể học để tăng cơ hội việc làm.

---

## **Chức Năng Nổi Bật**

1. **Công ty**:
   - Đăng tin tuyển dụng với các kỹ năng yêu cầu.
   - Xem và quản lý danh sách các công việc đã đăng.
   - Tìm kiếm ứng viên phù hợp với tin tuyển dụng.
   - Gửi email mời ứng viên tham gia ứng tuyển trực tiếp từ trang quản lý.

2. **Ứng viên**:
   - Nhận gợi ý các công việc phù hợp với kỹ năng hiện có.
   - Xem danh sách kỹ năng chưa đầy đủ để nâng cao cơ hội tuyển dụng.
   - Sử dụng chatbot để hỗ trợ tìm hiểu thông tin tuyển dụng.

3. **Người dùng**:
   - Đăng ký tài khoản với vai trò công ty hoặc ứng viên.
   - Điền thông tin cá nhân sau khi đăng ký.
   - Quản lý thông tin tài khoản một cách bảo mật.

---

## **Nội dung**

### 1. **Cơ Sở Dữ Liệu**
- **Entities**:
  - **User**: Lưu thông tin ứng viên.
  - **Company**: Lưu thông tin công ty.
  - **JobPost**: Lưu thông tin tuyển dụng.
  - **Skill**: Lưu danh sách kỹ năng.
  - **UserSkill**: Liên kết kỹ năng với ứng viên.
  - **JobSkill**: Liên kết kỹ năng với công việc.

- Sử dụng quan hệ **@OneToMany**, **@ManyToOne**, và **@ManyToMany** trong Spring Boot.

### 2. **Repositories**
Tạo repository cho từng bảng để thao tác với cơ sở dữ liệu:
- AddressRepository, AccountRepository, CandidateRepository, ExperienceRepository, CompanyRepository, JobRepository, SkillRepository, CandidateSkillRepository, JobSkillRepository.

### 3. **Phân quyền đăng nhập với Security**
Cấu hình bảo mật với Spring Security để phân quyền vai trò (công ty hoặc ứng viên).

### 4. **Service Layer**
Cung cấp các chức năng:
- Xử lý logic tìm kiếm, đề xuất, gửi email mời tuyển.
- Kết nối repository và controller.

---

### **Demo**

Giao diện được xây dựng bằng **Thymeleaf** để đảm bảo tính tương tác và thân thiện với người dùng. Các trang chính bao gồm:

- **Trang Công Ty**:
  - **Trang chủ**: Công ty có thể xem các bài đăng vừa được đăng tải của các công ty

    ![image](https://github.com/user-attachments/assets/28d8382e-b566-458f-a038-33a099d50c7d)

  - **Quản lý tin tuyển dụng**: Công ty có thể xem các bài đăng tuyển dụng của công ty đã đăng.
    
    ![image](https://github.com/user-attachments/assets/809ceb92-60c7-493c-bae9-5d140e3627c0)

  - **Đăng tin tuyển dụng**: Công ty có thể đăng bài tuyển dụng để tìm kiếm ứng viên phù hợp

    ![image](https://github.com/user-attachments/assets/b5b23280-c697-4a41-87a6-1bd361698b02)

    ![image](https://github.com/user-attachments/assets/4becd379-b71c-4a67-b847-ade7e6b0c8ef)

  - **Tìm kiếm ứng viên**: Hiển thị danh sách ứng viên phù hợp với kỹ năng của công việc đã đăng.
       
    ![image](https://github.com/user-attachments/assets/6c7facad-718d-44cb-9a39-d46991a54777)

  - **Gửi email mời ứng tuyển**: Với một cú nhấp chuột, công ty có thể gửi email mời ứng viên tham gia ứng tuyển.

    ![image](https://github.com/user-attachments/assets/6630eaf2-8f3a-44ba-9d0a-c5f668f39f94)

    ![image](https://github.com/user-attachments/assets/d818351e-187b-4add-80f0-5694b0bd1f4a)

- **Trang Ứng Viên**: Tiếp theo sẽ đăng nhập bằng account candidate
  - **Gợi ý công việc**: Hiển thị danh sách công việc phù hợp dựa trên kỹ năng hiện có của ứng viên.

    ![image](https://github.com/user-attachments/assets/33aa5ba2-e9a2-4a13-b214-8bef0dca934f)

  - **Học thêm kỹ năng**: Trong công việc được gợi ý sẽ có thêm các skill phù hợp, đồng thời sẽ đề cập các skill còn thiếu để học thêm phù hợp với công việc
 
    ![image](https://github.com/user-attachments/assets/e76d066b-2eba-4782-b3d3-614e95b1c8cd)

  - **Chi tiết công việc**: Khi click vào View Details sẽ show thông tin chi tiết của job đó để người dùng có thể đọc và ứng tuyển
 
    ![image](https://github.com/user-attachments/assets/8b77bac1-d4df-48f7-ad84-ae53cfa922cd)

  - **Chatbot hỗ trợ**: Ứng viên có thể tìm hiểu thêm thông tin và nhận gợi ý từ chatbot tích hợp.
 
    ![image](https://github.com/user-attachments/assets/640e6151-6abb-443f-b493-c9e1ba53063b)

- **Trang Đăng Nhập/Đăng Ký**:
  - **Đăng ký linh hoạt**: Hỗ trợ đăng ký tài khoản với vai trò ứng viên hoặc công ty.

    ![image](https://github.com/user-attachments/assets/40d62446-b137-4740-b863-ec70fa441a38)

  - **Quy trình rõ ràng**: Người dùng điền thông tin cá nhân sau khi đăng ký và nhận thông báo xác nhận thành công.

    ![image](https://github.com/user-attachments/assets/a81e2e2e-4d0b-463d-a937-4d586db3f11d)
    
    ![image](https://github.com/user-attachments/assets/3b525afe-0126-4a8e-be77-68cc4836d146)

---

## **Hướng Dẫn Cài Đặt**

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
   spring.datasource.url=jdbc:mariadb://localhost:3306/works?createDatabaseIfNotExist=true
   spring.datasource.username=${user_my_sql}
   spring.datasource.password=${password_my_sql}
   spring.mail.username=${mail}
   spring.mail.password=${mailpass}
   ```
3. Chạy ứng dụng:
   Truy cập tại: `http://localhost:8080/`

---

## **Đề Xuất Phát Triển Thêm**

1. Thêm tính năng chat giữa công ty và ứng viên.
2. Phân tích và thống kê xu hướng tuyển dụng và kỹ năng phổ biến.
