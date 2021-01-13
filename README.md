# to-do-sample

### mysqlにlogin
```bash
# dockerで立てたmysqlサーバーにログイン
$ mysql -udocker -h127.0.0.1 -P33306 -p
Password: docker
```

#### mysqlにサンプルデータを挿入
```sql
## `to_do_category` テーブル作成
mysql> CREATE TABLE `to_do_category` (
         `id`         BIGINT(20)   unsigned     NOT NULL AUTO_INCREMENT,
         `name`       VARCHAR(255)              NOT NULL,
         `slug`       VARCHAR(64) CHARSET ascii NOT NULL,
         `color`      TINYINT UNSIGNED          NOT NULL,
         `updated_at` timestamp                 NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
         `created_at` timestamp                 NOT NULL DEFAULT CURRENT_TIMESTAMP,
         PRIMARY KEY (`id`)
       ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

## 'to_do_category'テーブルのサンプルデータ挿入
mysql> INSERT INTO to_do_category(name,slug,color) values('フロントエンド','front',1);
mysql> INSERT INTO to_do_category(name,slug,color) values('バックエンド','back',2);
mysql> INSERT INTO to_do_category(name,slug,color) values('インフラ','infra',3);

## 'to_do'テーブルを作成
mysql> CREATE TABLE `to_do` (
         `id`          BIGINT(20) unsigned NOT NULL AUTO_INCREMENT,
         `category_id` BIGINT(20) unsigned NOT NULL,
         `title`       VARCHAR(255)        NOT NULL,
         `body`        TEXT,
         `state`       TINYINT UNSIGNED    NOT NULL,
         `updated_at`  TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
         `created_at`  TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
         PRIMARY KEY (`id`)
       ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

## 'to_do'テーブルのサンプルデータ挿入
mysql> INSERT INTO `to_do`(category_id,title,body,state) values(1, 'デザインをいい感じにする','ヘッダーのデザインをもっといい感じに',0);
mysql> INSERT INTO `to_do`(category_id,title,body,state) values(2, 'Controllerの修正','Controller名をもっといい感じに',1);
mysql> INSERT INTO `to_do`(category_id,title,body,state) values(3, '新しいDB環境の作成','タイトル通り',2);
```
```
$ sbt run   // サーバーが起動したらlocalhost:9000にアクセス
```
