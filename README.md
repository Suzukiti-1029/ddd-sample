## データベースの設定
1. `mysql -u root -p`
	- パスワード
2. `create database ddd_sample;`
3. `create user 'ddd_user'@'localhost' identified by 'ddd-password';`
4. `grant all privileges on ddd_sample.* to 'ddd_user'@'localhost';`
5. `flush privileges;`
6. `alter database ddd_sample character set utf8mb4 collate utf8mb4_unicode_ci;`
