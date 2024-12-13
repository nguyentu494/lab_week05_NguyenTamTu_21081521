
# Lab Week 05 - Spring Boot Project

## **Nguyễn Tâm Tú**

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

## **Yêu Cầu Kỹ Thuật**

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

### 3. **Cài Đặt Security**
Cấu hình bảo mật với Spring Security để phân quyền vai trò (công ty hoặc ứng viên).

### 4. **Service Layer**
Cung cấp các chức năng:
- Xử lý logic tìm kiếm, đề xuất, gửi email mời tuyển.
- Kết nối repository và controller.

### 5. **Giao Diện**

Giao diện được xây dựng bằng **Thymeleaf** để đảm bảo tính tương tác và thân thiện với người dùng. Các trang chính bao gồm:

- **Trang Công Ty**:
  - **Quản lý tin tuyển dụng**: Công ty có thể đăng tin tuyển dụng với các kỹ năng yêu cầu và theo dõi trạng thái của các tin đăng.
  - **Tìm kiếm ứng viên**: Hiển thị danh sách ứng viên phù hợp với kỹ năng của công việc đã đăng.
  - **Gửi email mời ứng tuyển**: Với một cú nhấp chuột, công ty có thể gửi email mời ứng viên tham gia ứng tuyển.

- **Trang Ứng Viên**:
  - **Gợi ý công việc**: Hiển thị danh sách công việc phù hợp dựa trên kỹ năng hiện có của ứng viên.
  - **Học thêm kỹ năng**: Đưa ra danh sách kỹ năng cần học để tăng cơ hội được tuyển dụng.
  - **Chatbot hỗ trợ**: Ứng viên có thể tìm hiểu thêm thông tin và nhận gợi ý từ chatbot tích hợp.

- **Trang Đăng Nhập/Đăng Ký**:
  - **Đăng ký linh hoạt**: Hỗ trợ đăng ký tài khoản với vai trò ứng viên hoặc công ty.
  - **Quy trình rõ ràng**: Người dùng điền thông tin cá nhân sau khi đăng ký và nhận thông báo xác nhận thành công.

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
   ```bash
   ./gradlew bootRun
   ```
   Truy cập tại: `http://localhost:8080/`

---

## **Đề Xuất Phát Triển Thêm**

1. Thêm tính năng chat giữa công ty và ứng viên.
2. Phân tích và thống kê xu hướng tuyển dụng và kỹ năng phổ biến.
