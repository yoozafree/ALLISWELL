# 🤟 TEAM ALL_IS_WELL
<br>
**업데이트 일자: 2025.12.16**

<br>

### 📌 개요
해당 레포지토리는 **2025년 2학기 이화여자대학교 컴퓨터공학과**  
**‘캡스톤디자인과창업프로젝트A’** 과목의 **3팀, ‘세얼간이’** 가 웹서비스 구현을 목표로 수행하는 프로젝트입니다.

본 프로젝트는 대학생 개발 팀 프로젝트 환경에서 반복적으로 발생하는 협업 비효율—
회의 기록 누락, 참여 불균형, 피드백의 실행력 부족—을 해결하기 위한
AI 기반 협업 지원 웹 서비스를 목표로 합니다

---

### 🎯 프로젝트 목표
- 회의 기록 자동화를 통해 **회의록 작성·관리의 수동 부담 감소**
- 팀원별 **참여도·기여도 가시화**를 통한 협업 불균형 완화
- AI 기반 피드백을 통해 **직접 말하기 어려운 피드백을 간접·객관적으로 전달**
- 회의 결과를 To-Do와 연결하여 **실행 중심의 프로젝트 관리 구조 구현**
- 팀 리더의 관리 부담을 줄이고, **공정하고 지속 가능한 협업 환경 조성**

---
### 🔑 주요 기능

1. **STT 기반 회의 기록 및 타임라인 생성**
    - 회의 음성을 자동으로 텍스트 변환(STT)
    - 발화자·시간 단위로 세그먼트화된 타임라인 제공
    - 이후 요약, 참여도 분석, To-Do 생성의 **공통 기반 데이터**로 활용
2. **AI 기반 회의 요약 & 액션 아이템 자동 추출**
    - 회의 요약본을 입력으로 받아
    - **결정·지시 중심의 실행 가능한 Action Item**만 구조화하여 추출
    - JSON 형태 출력으로 프로젝트 관리 기능과 직접 연동
3. **AI 협업 피드백 자동 생성 및 확정 프로세스**
    - 회의 요약 + 팀원 활동 지표 기반 AI 피드백 초안 생성
    - **AI 초안 → 사용자 수정 → 최종 확정**의 명확한 책임 구조
    - 피드백 이력 관리 및 회고 자료로 활용 가능

---



### ⚙️ Tech Stack

<div align="center">

**Frontend**  
<img src="https://img.shields.io/badge/React-61DAFB?logo=react&logoColor=white&style=for-the-badge"/> 
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?logo=javascript&logoColor=black&style=for-the-badge"/>

**Backend**  
<img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=white&style=for-the-badge"/>
<img src="https://img.shields.io/badge/Java-007396?logo=java&logoColor=white&style=for-the-badge"/>

**Database**  
<img src="https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white&style=for-the-badge"/>

**AI / NLP**  
<img src="https://img.shields.io/badge/OpenAI-412991?logo=openai&logoColor=white&style=for-the-badge"/>
<img src="https://img.shields.io/badge/Whisper_STT-412991?logo=openai&logoColor=white&style=for-the-badge"/>


**Collaboration Tools**  
<img src="https://img.shields.io/badge/GitHub-181717?logo=github&logoColor=white&style=for-the-badge"/> 
<img src="https://img.shields.io/badge/Slack-4A154B?logo=slack&logoColor=white&style=for-the-badge"/> 
<img src="https://img.shields.io/badge/Notion-000000?logo=notion&logoColor=white&style=for-the-badge"/> 
<img src="https://img.shields.io/badge/Discord-5865F2?logo=discord&logoColor=white&style=for-the-badge"/>
</div>


---
### 👽 팀원 소개

<table>
  <tbody>
    <tr>
      <td align="center">
        <img src="https://img.shields.io/badge/Team%20Member-fb69a3" /><br> <a href="https://github.com/monshelle"><img src="https://github.com/monshelle.png" width="100px;" alt="강민지"/></a> </td>
      <td align="center">
        <img src="https://img.shields.io/badge/Team%20Member-04e0d5" /><br> <a href="https://github.com/WHITENOISE523"><img src="https://github.com/WHITENOISE523.png" width="100px;" alt="임이랑"/></a> </td>
            <td align="center">
        <img src="https://img.shields.io/badge/Team%20Leader-8629f9" /><br> <a href="https://github.com/yoozafree"><img src="https://github.com/yoozafree.png" width="100px;" alt="정서윤"/></a> </td>
    </tr>
    <tr>
      <td align="center"><b>강민지</b></td> 
      <td align="center"><b>임이랑</b></td> 
      <td align="center"><b>정서윤</b></td>
    </tr>
    <tr>
      <td align="center">BE</td>
      <td align="center">FE</td>
      <td align="center">BE</td>
    </tr>
  </tbody>
</table>

---

### 🗓 진행 일정
- **2025.09 ~ 2025.12.21**: 기획 및 프로토타입

---
### 📂 레포지토리 구조
```
aiw/
├── backend/ # Spring 서버 코드
├── frontend/ # React 프론트엔드 코드
├── docs/ # 회의록, 설계 문서
├── .github/ # 워크플로우 및 이슈 템플릿
└── README.md
```

---

### 🧾 코드 컨벤션

커밋 메시지는 다음 규칙에 맞춰 작성합니다

> 📌 예시: **`✨ feat: sign up complete`**

---

| 태그                | 설명                                      |
|-------------------|-----------------------------------------|
| ✨ **feat**        | 새로운 기능 추가                               |
| 🐛 **fix**        | 버그 수정                                   |
| 📝 **docs**       | 문서 수정 (README 등)                        |
| 💄 **style**      | 코드 포맷팅, 세미콜론 누락, 코드 변경 없음               |
| ♻️ **refactor**   | 코드 리팩토링 (기능 변화 없이 구조 개선)                |
| ✅ **test**        | 테스트 코드 추가, 기존 테스트 리팩토링                  |
| 🔧 **chore**      | 빌드 설정 변경, 패키지 매니저 설정 등                  |
| 🔀 **merge**      | 브랜치 병합 (예: `merge: main` → main과 병합했음) |
| 📍 **checkpoint** | 진행중(체크포인트)                              |
| 🎨 **design**     | 뷰 디자인 변경                                |

---
