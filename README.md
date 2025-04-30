# 소리담 백엔드 서버

> **소리담(Soridam)**은 사용자가 측정한 소음 데이터를 기반으로 조용한 장소를 탐색할 수 있도록 돕는 위치 기반 소음 관리 서비스입니다.

이 저장소는 `소리담`의 **백엔드 시스템**을 위한 레포지토리로, `Spring Boot`, `PostGIS`, `Redis`, `QueryDSL`, `WebClient(OpenAI)` 등을 활용하여 기능을 제공합니다.

---

## 📦 프로젝트 구조

```
soridam-server/
├── api        # Presentation Layer (API, Facade)
├── domain     # Domain Layer (Entity, Repository Interface, Business Logic)
├── infra      # Infrastructure Layer (JPA/QueryDSL 구현체, Redis, 외부 API)
├── common     # 공통 유틸, 에러 처리, 커스텀 예외 등
```

---

## 🚀 주요 기능

### 📍 장소 기반 소음 데이터 관리
- 실시간 소음 등록 및 조회
- 소음 측정값(평균/최대 데시벨) 저장
- 시간순, 평균순 정렬 및 필터링 지원

### 🗺️ 소음 지도 기능
- 위치 기반 반경 검색 (ex. 500m 이내)
- PostGIS 기반 거리 계산 및 필터 적용
- 장소 유형, 소음 수준 필터링 가능

### ✍️ 리뷰 기능
- 소음 데이터에 대한 사용자 리뷰 등록
- 장소 단위 리뷰 요약(GPT) 및 캐싱 제공

### 🧠 GPT 기반 요약
- 장소 리뷰를 분석하여 요약 문구 생성
- OpenAI GPT-3.5-turbo 사용
- 장소별 최신 50개 소음 리뷰 기반으로 생성
- Redis 캐시에 저장하여 조회 최적화

### 🕐 스케줄러
- 매주 월요일 자동 리뷰 요약 실행
- 요약 실패 시 개별 로깅 처리 후 다음 장소로 계속 진행

---

## ⚙️ 기술 스택

| 분류            | 기술                         |
|----------------|------------------------------|
| Language       | Java 17                      |
| Framework      | Spring Boot 3.x              |
| ORM            | Spring Data JPA, QueryDSL    |
| DB             | PostgreSQL + PostGIS         |
| Caching        | Redis                        |
| Messaging      | (추후 확장) Kafka 예정        |
| External API   | OpenAI API (GPT-3.5-turbo)   |
| Infra          | Docker, GitHub Actions       |
| Architecture   | CQRS, 멀티모듈               |

---

## 📌 향후 계획

- WebSocket 기반 실시간 알림 기능 추가
- 데이터 기반 추천 기능 시범 도입
- 사용자 위치 기반 실시간 소음 알림 기능 확장

---

## 👨‍💻 팀 소개

- 본 프로젝트는 대규모 서비스를 설계 및 운영하는 백엔드 기술 학습을 목적으로 구성되었으며, 실제 소음 환경 문제 해결에도 기여할 수 있는 방향으로 발전 중입니다.

---
