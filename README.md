
# Lab Week05 - Spring Boot Project

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

Sơ đồ bảng:

```plaintext
User       Company        JobPost         Skill
| id       | id           | id            | id
| name     | name         | title         | name
| email    | email        | description   |
| skills   | jobPosts     | company_id    |
```

#### Ghi chú:
Sử dụng các mối quan hệ **@OneToMany**, **@ManyToOne**, và **@ManyToMany** để liên kết các thực thể.

### 2. **Repositories Interface**
Tạo các repository để thao tác với cơ sở dữ liệu:

- **UserRepository**: Quản lý các thao tác trên bảng User.
- **CompanyRepository**: Quản lý các thao tác trên bảng Company.
- **JobPostRepository**: Quản lý các thao tác trên bảng JobPost.
- **SkillRepository**: Quản lý các thao tác trên bảng Skill.
- **UserSkillRepository**: Quản lý liên kết kỹ năng của ứng viên.
- **JobSkillRepository**: Quản lý liên kết kỹ năng của công việc.

### 3. **Lớp Services**
Cung cấp các chức năng chính:

- **UserService**: Quản lý logic liên quan đến ứng viên.
- **CompanyService**: Quản lý logic liên quan đến công ty.
- **JobPostService**: Quản lý logic liên quan đến bài đăng công việc.
- **SkillService**: Quản lý danh sách và xử lý kỹ năng.
- **RecommendationService**: Logic gợi ý công việc phù hợp và kỹ năng cần học.

### 4. **Giao Diện Người Dùng**
- **Công ty**:
  - Form tạo bài đăng công việc với các kỹ năng mong muốn.
  - Trang quản lý danh sách các bài đăng.

- **Ứng viên**:
  - Giao diện đăng nhập.
  - Trang gợi ý danh sách công việc phù hợp với kỹ năng.

### 5. **Tìm Kiếm và Gửi Email**
- **Tìm ứng viên**:
  - Công ty có thể tìm kiếm ứng viên phù hợp với yêu cầu kỹ năng của mình.

- **Gửi email**:
  - Sử dụng thư viện **JavaMailSender** để gửi email mời ứng viên tham gia ứng tuyển.

### 6. **Đề Xuất Kỹ Năng**
- Sử dụng thuật toán so sánh giữa kỹ năng yêu cầu của công việc và kỹ năng hiện tại của ứng viên để đề xuất các kỹ năng cần học thêm.

---

## Hướng Dẫn Cài Đặt

### 1. **Yêu Cầu Hệ Thống**
- Java 17
- Maven 3.8+
- MySQL (hoặc PostgreSQL)

### 2. **Cài Đặt Dự Án**
1. Clone repository:
   ```bash
   git clone <repository-url>
   cd lab-week05
   ```
2. Cấu hình database trong file `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/lab_week05
   spring.datasource.username=<username>
   spring.datasource.password=<password>
   ```
3. Chạy ứng dụng:
   ```bash
   mvn spring-boot:run
   ```

---

## File Structure
```plaintext
src
├── main
│   ├── java
│   │   └── com.example.labweek05
│   │       ├── entity
│   │       ├── repository
│   │       ├── service
│   │       ├── controller
│   └── resources
│       ├── templates
│       ├── static
│       └── application.properties
```

---

## Chức Năng Chính
- Công ty đăng bài tuyển dụng với kỹ năng mong muốn.
- Ứng viên nhận gợi ý công việc phù hợp.
- Công ty tìm ứng viên và gửi email mời ứng tuyển.
- Đề xuất kỹ năng học tập cho ứng viên.

---

## Đề Xuất Phát Triển Thêm
- Thêm tính năng chat giữa công ty và ứng viên.
- Phân tích và thống kê nhu cầu kỹ năng phổ biến.
