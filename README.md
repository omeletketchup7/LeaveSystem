# LeaveSystem

this project is about leaving system.

version
- anglar CLI 18.2.20
- Node 20.19.4
- npm 10.8.2
- Java 21.0.8 (Recommended)
- Spring Framework 3.3.9
- Maven 3.9.11

INSTALL
backend sql
``` 
CREATE TABLE users (
    id VARCHAR(50) PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL ,
    department VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE leave_types (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(50) NOT NULL ,
    description TEXT,
    max_days INT NOT NULL 
);

CREATE TABLE leave_requests (
    id VARCHAR(50) PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL,
    leave_type_id VARCHAR(50) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    reason TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_leave_type FOREIGN KEY (leave_type_id) REFERENCES leave_types(id) ON DELETE CASCADE
);

CREATE TABLE leave_balances (
    id VARCHAR(50) PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL,
    leave_type_id VARCHAR(50) NOT NULL,
    year INT NOT NULL,
    remaining_days INT NOT NULL,

    CONSTRAINT fk_balance_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_balance_type FOREIGN KEY (leave_type_id) REFERENCES leave_types(id) ON DELETE CASCADE
);


```

```
ALTER TABLE leave_requests
ADD comment_request TEXT;

```
fronend
```cd /frontend```
```npm i```
```ng serve```


<img width="1047" height="559" alt="image" src="https://github.com/user-attachments/assets/f96f9ac6-9b70-4b42-a368-da364e7f0d58" />

