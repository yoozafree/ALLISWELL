package feedbackDemo;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FeedBackDemo {

    // 상태값
    enum Status {
        DRAFT, EDITED, CONFIRMED
    }

    // 피드백 객체
    static class FeedBack {
        long id;
        String memberName;
        String inputSummary;
        int avgResponseSec;
        double completionRate;
        int lateTasks;

        String content;      // 피드백 본문
        Status status;       // DRAFT/EDITED/CONFIRMED
        LocalDateTime createdAt;
        LocalDateTime updatedAt;

        public FeedBack(long id) {
            this.id = id;
        }
    }

    // DB 대신 메모리에 일단 저장하는 방식으로 
    static Map<Long, FeedBack> store = new HashMap<>();
    static long seq = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== Feedback Demo (AI 생성 + 등록/수정/확정) =====");

        while (true) {
            System.out.println("\n메뉴 선택:");
            System.out.println("1) 피드백 생성(=등록)");
            System.out.println("2) 피드백 조회");
            System.out.println("3) 피드백 수정");
            System.out.println("4) 피드백 확정");
            System.out.println("0) 종료");
            System.out.print("> ");

            String menu = sc.nextLine().trim();

            if (menu.equals("0")) {
                System.out.println("종료");
                break;
            } else if (menu.equals("1")) {
                createFeedBack(sc);
            } else if (menu.equals("2")) {
                readFeedback(sc);
            } else if (menu.equals("3")) {
                editFeedback(sc);
            } else if (menu.equals("4")) {
                confirmFeedback(sc);
            } else {
                System.out.println("메뉴를 다시 선택하십시오.");
            }
        }

        sc.close();
    }

    static void createFeedBack(Scanner sc) {
        FeedBack fb = new FeedBack(seq++);

        System.out.print("팀원 이름: ");
        fb.memberName = sc.nextLine().trim();

        System.out.print("회의 요약(한 줄): ");
        fb.inputSummary = sc.nextLine().trim();

        System.out.print("평균 응답 시간(초): ");
        fb.avgResponseSec = parseIntSafe(sc.nextLine());

        System.out.print("작업 완료율(0~1): ");
        fb.completionRate = parseDoubleSafe(sc.nextLine());

        System.out.print("지연 작업 수: ");
        fb.lateTasks = parseIntSafe(sc.nextLine());

        // AI가 피드백을 생성해주었
        fb.content = fakeAiGenerate(fb.inputSummary, fb.avgResponseSec, fb.completionRate, fb.lateTasks);

        fb.status = Status.DRAFT;
        fb.createdAt = LocalDateTime.now();
        fb.updatedAt = fb.createdAt;

        store.put(fb.id, fb);

        System.out.println("\n[생성 완료]");
        System.out.println("피드백 ID = " + fb.id);
        System.out.println("상태 = " + fb.status);
        System.out.println("출력(피드백) = " + fb.content);
    }

    static void readFeedback(Scanner sc) {
        System.out.print("조회할 피드백 ID: ");
        long id = parseLongSafe(sc.nextLine());

        FeedBack fb = store.get(id);
        if (fb == null) {
            System.out.println("해당 ID의 피드백이 없습니다.");
            return;
        }

        System.out.println("\n[피드백 조회]");
        System.out.println("ID: " + fb.id);
        System.out.println("팀원: " + fb.memberName);
        System.out.println("상태: " + fb.status);
        System.out.println("회의요약: " + fb.inputSummary);
        System.out.println("지표: avg=" + fb.avgResponseSec + "s, rate=" + fb.completionRate + ", late=" + fb.lateTasks);
        System.out.println("내용: " + fb.content);
        System.out.println("생성: " + fb.createdAt);
        System.out.println("수정: " + fb.updatedAt);
    }

    static void editFeedback(Scanner sc) {
        System.out.print("수정할 피드백 ID: ");
        long id = parseLongSafe(sc.nextLine());

        FeedBack fb = store.get(id);
        if (fb == null) {
            System.out.println("해당 ID의 피드백이 존재하지 않습니다.");
            return;
        }

        if (fb.status == Status.CONFIRMED) {
            System.out.println("이미 확정(CONFIRMED)된 피드백은 수정할 수 없습니다.");
            return;
        }

        System.out.println("현재 내용: " + fb.content);
        System.out.print("새 내용 입력: ");
        String newText = sc.nextLine();

        if (newText == null || newText.trim().isEmpty()) {
            System.out.println("입력이 비어있어 수정 작업을 취소합니다.");
            return;
        }

        fb.content = newText.trim();
        fb.status = Status.EDITED;
        fb.updatedAt = LocalDateTime.now();

        System.out.println("[수정 완료] 상태 = " + fb.status);
    }

    static void confirmFeedback(Scanner sc) {
        System.out.print("확정할 피드백 ID: ");
        long id = parseLongSafe(sc.nextLine());

        FeedBack fb = store.get(id);
        if (fb == null) {
            System.out.println("해당 ID의 피드백이 없습니다.");
            return;
        }

        if (fb.status == Status.CONFIRMED) {
            System.out.println("이미 확정된 상태입니다.");
            return;
        }

        fb.status = Status.CONFIRMED;
        fb.updatedAt = LocalDateTime.now();

        System.out.println("[확정 완료] 확정 완료되어 수정이 불가한 상태 = " + fb.status);
    }

    // 데모용 생성 함수
    static String fakeAiGenerate(String summary, int avgSec, double rate, int late) {
        String s1 = "회의 내용(" + summary + ") 기준으로 작업 흐름은 전체적으로 정돈되어 있습니다.";
        String s2;
        if (rate >= 0.85) s2 = "작업 완료율이 높은 것으로 보아 맡은 부분을 꾸준히 처리한 점이 확인됩니다.";
        else if (rate >= 0.7) s2 = "작업 완료율은 보통 수준이며, 일부 작업의 점검이 필요해 보입니다.";
        else s2 = "작업 완료율이 낮은 편이기에 일정 관리 방식 조정이 필요해 보입니다.";

        String s3;
        if (avgSec <= 60) s3 = "응답 속도도 비교적 빠른 편이라 협업 진행이 매끄럽습니다.";
        else s3 = "응답이 다소 늦어질 때가 있어, 진행 상황 공유를 조금 더 자주 하면 좋겠습니다.";

        String s4;
        if (late == 0) s4 = "지연 작업이 없어 일정 준수 측면에서 안정적입니다.";
        else s4 = "지연 작업(" + late + "건)이 발생했으니, 작업을 더 작은 단위로 나눠 중간 체크할 것을 권장합니다.";

        // 3~4문장 정도로 맞추기
        return s1 + " " + s2 + " " + s3 + " " + s4;
    }

    // 입력 파싱.. 
    static int parseIntSafe(String v) {
        try { return Integer.parseInt(v.trim()); } catch (Exception e) { return 0; }
    }
    static long parseLongSafe(String v) {
        try { return Long.parseLong(v.trim()); } catch (Exception e) { return -1; }
    }
    static double parseDoubleSafe(String v) {
        try { return Double.parseDouble(v.trim()); } catch (Exception e) { return 0.0; }
    }
}
