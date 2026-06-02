# OTA予約管理アプリ（ota-app）

OTAサイト（じゃらん・楽天トラベル等）からの予約を一元管理するシステム。

## 技術スタック

| 項目 | 内容 |
|------|------|
| フレームワーク | Spring Boot 3.5.11 |
| テンプレート | Thymeleaf |
| データベース | H2（ファイルモード） |
| ORM | Spring Data JPA |
| ポート | 8080 |

## 起動方法

```bash
cd C:/Users/user/Documents/java/ota-app
./mvnw spring-boot:run
```

## ログイン

| 項目 | 値 |
|------|------|
| URL | http://localhost:8080/login |
| ユーザー名 | admin |
| パスワード | password |

## 画面一覧

| URL | 画面名 | 説明 |
|-----|--------|------|
| /login | ログイン画面 | 管理者ログイン |
| /dashboard | ダッシュボード | 予約状況の概要 |
| /reservations | 予約一覧 | 予約の確認・キャンセル |
| /reservations/{id} | 予約詳細 | 予約の詳細表示 |
| /h2-console | H2コンソール | データベース管理 |

## H2データベース

| 項目 | 値 |
|------|------|
| URL | http://localhost:8080/h2-console |
| JDBC URL | jdbc:h2:file:C:/Users/user/Documents/java/ota-app/otadb |
| ユーザー名 | sa |
| パスワード | （空欄） |

## プロジェクト構成

```
ota-app/
├── src/main/java/com/example/ota_app/
│   ├── OtaAppApplication.java        # メインクラス
│   ├── controller/
│   │   ├── AuthController.java        # ログイン認証
│   │   ├── DashboardController.java   # ダッシュボード
│   │   └── ReservationController.java # 予約管理
│   ├── entity/
│   │   ├── Reservation.java           # 予約エンティティ
│   │   ├── TimeSlot.java              # 時間枠エンティティ
│   │   └── Availability.java          # 空き状況エンティティ
│   ├── repository/
│   │   ├── ReservationRepository.java
│   │   ├── TimeSlotRepository.java
│   │   └── AvailabilityRepository.java
│   └── service/
│       ├── ReservationService.java    # 予約ビジネスロジック
│       ├── DashboardService.java      # ダッシュボードロジック
│       └── OtaSyncService.java        # OTAサイトへのAPI連携
├── src/main/resources/
│   ├── application.properties         # アプリ設定
│   └── templates/                     # Thymeleafテンプレート
│       ├── login.html
│       ├── dashboard.html
│       ├── fragments/
│       │   ├── header.html
│       │   └── footer.html
│       └── reservations/
│           ├── list.html
│           └── detail.html
└── pom.xml                            # Maven設定
```

## API連携（売止機能）

ota-appはOTAサイトに対して売止・売止解除のAPIを送信する。

| 連携先 | URL | ポート |
|--------|-----|--------|
| OTAサイトA | http://localhost:8081/api/availability | 8081 |
| OTAサイトB | http://localhost:8082/api/availability | 8082 |

### 売止の流れ

1. OTAサイトから予約が入る
2. ota-appが予約を受け付け、各OTAサイトに売止を送信
3. OTAサイト側で該当日が売止になる

### キャンセル時の流れ

1. ota-appで予約をキャンセル
2. 該当日の売止を解除するAPIを各OTAサイトに送信
3. OTAサイト側で該当日が再び予約可能になる

## 関連アプリ

| アプリ | ポート | 説明 |
|--------|--------|------|
| ota-app | 8080 | 予約管理（このアプリ） |
| ota-site-a | 8081 | OTA予約サイトA |
| ota-site-b | 8082 | OTA予約サイトB（未作成） |

## 注意事項

- `ddl-auto=create` のため、再起動するとデータがリセットされます
- 本番環境では `ddl-auto=update` または `validate` に変更してください
