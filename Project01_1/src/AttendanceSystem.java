import java.util.*;

public class AttendanceSystem {
	private static Scanner scan = new Scanner(System.in);
	static List<Student> st = new ArrayList<Student>();

	public static void main(String[] args) {
		int num = 0; // 내가 입력할 출석번호
		int fail = 0; // 출석번호입력 실패횟수
		boolean run = true; // 프로그램종료
		final int ATTENDENCE = 15; // 총 수업수

		// 학생생성
		st.add(new Student("id1", "ps1", "김모범"));
		st.add(new Student("id2", "ps2", "김불량"));
		st.add(new Student("id3", "ps3", "김평범"));

		// 학생마다 수강과목생성
		st.get(0).add(101, "철학", 0, 4);
		st.get(0).add(102, "물리학", 0, 4);
		st.get(1).add(103, "과학", 0, 1);
		st.get(1).add(102, "물리학", 0, 0);
		st.get(2).add(104, "철학", 0, 3);

		// 프로그램시작
		while (run) {
			try {
				System.out.println("-------------------");
				System.out.println("전자출결시스템");
				System.out.println("-------------------");
				System.out.println("1.학생 | 2.교수 | 3.종료");
				System.out.println("-------------------");
				System.out.print("선택> ");
				int select = scan.nextInt();

				// 학생로그인
				if (select == 1) {
					boolean login = false;
					boolean subselect = false;
					System.out.print("id: ");
					String b = scan.next();
					System.out.print("passward: ");
					String c = scan.next();

					// id,ps일치
					for (int i = 0; i < st.size(); i++) {
						if (b.equals(st.get(i).getId()) && c.equals(st.get(i).getPassward())) {
							System.out.println("로그인성공");
							login = true;

							// 강의목록+강의선택
							System.out.println(st.get(i).getSubject());
							System.out.println("-------------------");
							System.out.print("강의번호입력> ");
							select = scan.nextInt();

							// 출석체크
							for (int k = 0; k < st.get(i).getSubject().size(); k++)
								if (select == st.get(i).getSubject().get(k).getSubjectcode()) {
									subselect = true;

									while (fail < 3 && run) {
										// 출석번호출력
										int answer;
										answer = st.get(i).getSubject().get(k)
												.setNumber((int) (Math.random() * 100) + 1);
										System.out.println(answer);

										// 출석번호입력
										System.out.print("출석번호> ");
										num = scan.nextInt();

										// 출석성공
										if (num != 0 && num == st.get(i).getSubject().get(k).getNumber()) {
											System.out.println("출석이 완료되었습니다.");
											int tmp = st.get(i).getSubject().get(k)
													.setAttendance(st.get(i).getSubject().get(k).getAttendance() + 1);
											System.out.println("출석률: " + tmp + "/" + ATTENDENCE);
											run = false;

										// 출석실패
										} else {
											fail++;
											System.out.println("출석번호가 일치하지않습니다. " + fail + "번실패");
										}
									} // 출석 while 끝
									
									fail = 0; // 출석실패횟수 초기화
									
								} // 강의선택성공끝
							
							if (subselect == false) {
								System.out.println("수강중인 강의가 아닙니다.");
							}
						}
					} // 맨위for

					// id,ps불일치
					if (login == false)
						System.out.println("id 또는 password가 일치하지 않습니다.");

					// 교수
				} else if (select == 2) {

					// 종료
				} else if (select == 3) {
					run = false;

					// 그외번호입력
				} else
					System.out.println("올바른 번호를 선택하세요.");

				// 선택할때 문자예외처리
			} catch (InputMismatchException e) {
				System.out.println("숫자를 입력해주세요.");
				scan.next();
			}
		} // 전체while
	}// main
}// AttendanceSystem