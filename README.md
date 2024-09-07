# 메모리 버켓

![image.png](https://postfiles.pstatic.net/MjAyNDA5MDdfMjE5/MDAxNzI1NzIwNTc0NDA3.fo9SUGi61-q2iWZ5bXoPKg-5QhziaC_Eqf1gmek8CiQg.1otIhhPZXemN2IQoYQYXGXeGPPeqvCke51H_enab-3og.PNG/image.png?type=w966)
메모리 버켓은 일상의 순간부터 인생의 특별한 순간까지 사진과 영상으로 기록하고 공유할 수 있는 애플리케이션입니다. 사용자가 간편하고 직관적으로 소중한 추억을 만들 수 있도록 최적화된 경험을 제공합니다.

`Spring Boot`로 RESTful API 서버를 개발했으며, `Spring Batch`를 통해 대용량 데이터를 처리하고, `Docker`를 사용해 일관된 환경 제공과 배포를 간소화했습니다.

## 프로젝트 구조

![image2.png](https://postfiles.pstatic.net/MjAyNDA5MDdfMjg3/MDAxNzI1NzIwNTc0MzQ1.QIdPDpjIRN570-YTP2hZoz6y-aC10Ak7Elg2veOWsLUg.QbrG-VgCXTCUYSv2wO_SCBt_lJ7c4AKSJXuEIrYKqgMg.PNG/image2.png?type=w966)

### 코어 모듈

코어 모듈은 프로젝트의 주요 도메인 엔티티와 리포지토리를 포함하며, 다른 모듈에서 공통적으로 사용되는 핵심 로직과 데이터 접근 계층을 담당합니다. 이 모듈은 도메인 로직의 기초를 이루며, 다른 모듈이 이를 의존성으로
가져와 비즈니스 로직을 처리합니다.

### 오브젝트 스토리지 모듈

오브젝트 스토리지 모듈은 객체 지향적인 설계 원칙을 적용하여 인터페이스와 구현체를 분리한 구조입니다. `ObjectStorageService` 인터페이스는 오브젝트 업로드 및 삭제 기능을 추상화하여, 스토리지 기술의
변경이 용이하도록 설계되었습니다. 현재는 AWS S3를 사용한 `S3Service` 구현체가 존재하며, S3 클라이언트와 프리사이너를 통해 객체를 관리합니다. 향후 다른 스토리지로의 교체도 인터페이스 기반의 구조
덕분에 쉽게 이루어질 수 있습니다.

### API 모듈

API 모듈은 RESTful API를 제공하는 애플리케이션의 핵심 요소로, 사용자 요청을 처리하는 역할을 합니다. 이 모듈은 코어 모듈의 비즈니스 로직과 오브젝트 스토리지 모듈의 기능을 활용하여 사용자 요청을 적절히
처리하고 응답을 반환합니다.

### 배치 모듈

배치 모듈은 대규모 데이터 작업을 처리하기 위해 독립적으로 설계되었습니다. 이 모듈은 `Spring Batch`와 통합되어 정기적인 배치 작업을 자동화하고 관리합니다. 코어 및 오브젝트 스토리지 모듈과의 연계를 통해
데이터의 일괄 처리와 스케줄링 작업을 효율적으로 처리합니다.

## API 스펙

### 앨범

`Album` 엔티티는 사용자가 생성한 사진 및 비디오 앨범을 관리하는 핵심 도메인 객체입니다. 각 앨범은 사용자별로 소유권을 가지며, 타이틀, 설명, 썸네일 URL 등 앨범의 세부 정보를 포함합니다.

| 메소드    | 경로                | 설명          |
|--------|-------------------|-------------|
| POST   | /albums           | 앨범 생성       |
| GET    | /albums/{albumId} | 앨범 단건 조회    |
| GET    | /albums           | 앨범 목록 조회    |
| PATCH  | /albums/{albumId} | 앨범 상세 정보 수정 |
| DELETE | /albums/{albumId} | 앨범 삭제       |

### 포스트

`Post` 엔티티는 사용자가 특정 앨범에 기록한 기억을 관리하는 도메인 객체입니다. 각 포스트는 제목, 설명, 기억 날짜 등을 포함하며, 여러 메모리(Memory) 항목을 통해 관련된 사진이나 영상을 저장할 수
있습니다. 또한, 특정 앨범 및 시리즈, 커버 메모리와 연관됩니다.

| 메소드    | 경로                       | 설명        |
|--------|--------------------------|-----------|
| POST   | /albums/{albumId}/posts  | 포스트 생성    |
| GET    | /albums/{albumId}/posts  | 포스트 목록 조회 |
| GET    | /posts/{postId}          | 포스트 단건 조회 |
| POST   | /posts/{postId}          | 포스트 수정    |
| DELETE | /posts/{postId}          | 포스트 삭제    |
| POST   | /posts/{postId}/memories | 메모리 수정    |

### 시리즈

`Series` 엔티티는 사용자가 앨범 내에서 포스트를 묶어 관리할 수 있는 시리즈 개념을 제공합니다. 사용자는 특정 앨범 내에서 주제나 목적에 따라 포스트를 그룹화하고, 이를 시리즈로 관리할 수 있습니다. 각
시리즈는 하나의 앨범에 속하며, 시리즈 이름을 통해 식별됩니다.

| 메소드    | 경로                               | 설명        |
|--------|----------------------------------|-----------|
| POST   | /albums/{albumId}/series         | 시리즈 생성    |
| GET    | /albums/{albumId}/series         | 시리즈 목록 조회 |
| GET    | /albums/{albumId}/series/summary | 시리즈 집계 조회 |
| GET    | /series/{seriesId}               | 시리즈 단건 조회 |
| PATCH  | /series/{seriesId}               | 시리즈 수정    |
| DELETE | /series/{seriesId}               | 시리즈 삭제    |

### 파일

| 메소드  | 경로     | 설명               |
|------|--------|------------------|
| POST | /files | presigned url 생성 |

## 배치 서버

![impage3.png](https://postfiles.pstatic.net/MjAyNDA5MDdfMTkw/MDAxNzI1NzIwNTc0MzM0.ayk7qfmqmeknhL0WQdU90HQkhdbFJwiwS_y_Zg9EMuUg.mBnXZkLp21nJK4NszjTH3zIDzDuInEnHWmUn6SjmaZ4g.PNG/%EC%A0%9C%EB%AA%A9_%EC%97%86%EB%8A%94_%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8.drawio.png?type=w966)

포스트 삭제 시, 연관된 이미지와 영상 파일들이 Amazon S3에서 삭제되어야 합니다. 그러나 즉각적으로 S3 오브젝트 삭제를 요청하면 성능 저하 문제가 발생할 수 있습니다. 이를 해결하기 위해, 삭제할 오브젝트의
키 값을 별도의 테이블에 저장하고, **Spring Batch**를 사용하여 일괄적으로 삭제하는 방식을 도입했습니다.

## CI/CD

![image4.png](https://postfiles.pstatic.net/MjAyNDA5MDdfMTUg/MDAxNzI1NzIwNTc0MzUw.8Uq7xNOhkfPQrd9ZVQyg6jdxG4ZGEnLfJKJ8_Q0ulCsg.nW3eXBhW_72uR_c9AYtK-kGAh_nyjj_fFCHQmP6Gl4gg.PNG/image3.png?type=w966)
CI/CD 파이프라인 자동화를 통해 수동 배포 과정에서 발생하던 시간 소모와 실수 위험을 해결하기 위해 GitHub Actions를 도입했습니다. 초기에는 코드 체크아웃 후 Docker Compose 설정 파일을
원격 서버로 전송하고 SSH를 통해 배포 작업을 수행했으나, .env 파일 전송으로 인한 보안 문제와 복잡한 관리 이슈가 있었습니다. 이를 개선하기 위해 Docker Context 기능을 활용해 민감한 정보 전송
없이 원격 서버에서 직접 배포 작업을 수행하도록 변경했고, 환경 변수는 GitHub Actions 내에서 안전하게 관리되었습니다. 이로써 보안과 효율성이 모두 향상된 신뢰할 수 있는 CI/CD 파이프라인을 구축할 수
있었습니다.

포스트 삭제 시, 연관된 이미지와 영상 파일들이 Amazon S3에서 삭제되어야 합니다. 그러나 즉각적으로 S3 오브젝트 삭제를 요청하면 성능 저하 문제가 발생할 수 있습니다. 이를 해결하기 위해, 삭제할 오브젝트의
키 값을 별도의 테이블에 저장하고, **Spring Batch**를 사용하여 일괄적으로 삭제하는 방식을 도입했습니다.


