package view;

import member.dto.*;
import member.service.Service;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class View {
    private static Service service = new Service();
    private static boolean state = true;



    public static void run() {
        Scanner scanner = new Scanner(System.in);

        while (state) {
            // 메뉴를 출력한다.
            System.out.println("================================= 도서 대여 프로그램 ================================");
           // System.out.println("1. 대출메뉴");
            System.out.println("1. 도서관리메뉴");
            System.out.println("2. 회원관리메뉴");
           // System.out.println("4. 반납메뉴");
            System.out.println("9. 프로그램 종료");
            System.out.print("원하는 메뉴를 입력해주세요: ");
            int menuChoice = scanner.nextInt();
            scanner.nextLine();

            switch (menuChoice) {
                case 1 -> bookManagement();
                case 2 -> memberManagement();
                case 3 -> memberManagement();
                case 4 -> bookManagement();
                case 9 -> {
                    // 프로그램 종료
                    System.out.println("프로그램을 종료합니다.");
                    state = false;
                }
                default -> System.out.println("잘못된 입력입니다.");
            }
        }

    }









    public static void memberManagement() {
        while (state) {
            System.out.println("화면 번호를 입력해주세요 : ");
            System.out.println("1. 화면 전체보기");
            System.out.println("2. 멤버 이름으로 조회하기 ");
            System.out.println("3. 멤버 정보 등록하기");
            System.out.println("4. 멤버 정보 수정하기");
            System.out.println("5. 멤버 정보 삭제 하기");
            System.out.println("9. 이전 메뉴");
            Scanner sc = new Scanner(System.in);
            int index = Integer.parseInt(sc.nextLine());

            switch (index) {
                case 1:
                    memberViewAll();
                    break;
                case 2:
                    memberFindByName();
                    break;
                case 3:
                    memberInsert();
                    break;
                case 4:
                    memberUpdate();
                    break;
                case 5:
                    memberDelete();
                case 9 :
                        return;

            }
            System.out.print("종료를 하시겠습니까? 말해 (yes Or no) 오타x 소문자만 : ");
            String result = sc.nextLine();

            if (result.equals("yes")) {
                state = false;
                sc.close();
            }
        }

    }

    private static void memberDelete()
    {

            Scanner sc = new Scanner(System.in);
            System.out.println("삭제할 회원 번호를 입력하세요");
            String index = sc.nextLine();
            MemberDTO member = service.memberFindById(index);

            if (member == null) {
                System.out.println("삭제할 회원이 존재하지 않습니다.");
                return;
            }
            System.out.println(member);

            try {
                MemberDTO modifyMember = service.memeberDelete( index);
                System.out.println(modifyMember);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }




    }

    // 현재 html의 화면을 암시하고 만든 것이다.
    // view는 사용자에게 데이터를 입력받고 서버에 전달하며, 결과를 사용자에게 보여주기 위한 용도로 사용된다.
    public static void memberViewAll() {
        System.out.println("멤 정보 전체 조회");

        try {
            ArrayList member = service.memberViewAll();
            System.out.println(member);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void memberFindByName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 사원의 이름을 입력하세요 : ");
        String name = sc.nextLine();
        MemberDTO member = null;

        try {
            member = service.memberFindByName(name);
            System.out.println(member);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void memberInsert() {
        Scanner sc = new Scanner(System.in);
        MemberInsertDTO member = new MemberInsertDTO();

        System.out.println("등록할 회원의 정보를 입력해주세요 ");
        System.out.print("회원의 번호를 입력해주세요 : ");
        member.setMemberNum(sc.nextLine());
        System.out.print("회원의 이름을 입력해주세요 : ");
        member.setMemberName(sc.nextLine());

        try {
            String result = service.memberInsert(member);

            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void memberUpdate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("변경할 회원을 입력하세요");
        String index = sc.nextLine();
        MemberDTO member = service.memberFindById(index);

        if (member == null) {
            System.out.println("변경할 회원이 존재하지 않습니다.");
            return;
        }
        System.out.println(member);
        System.out.println("변경할 이름을 입력해주세요");
        String name = sc.nextLine();
        try {
            MemberDTO modifyMember = service.memeberModify(name, index);
            System.out.println(modifyMember);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    public static void bookManagement() {
        Scanner scanner = new Scanner(System.in);
        while (state) {
            System.out.println("================================== 도서 관리 메뉴 ==================================");
            System.out.println("1. 도서 등록");
            System.out.println("2. 도서명 검색");
            System.out.println("3. 도서 수정");
            System.out.println("4. 도서 삭제");
            System.out.println("5. 전체 도서 검색");
            System.out.println("9. 이전 메뉴");
            System.out.print("메뉴를 선택해 주세요: ");

            try {
                int menuChoice = scanner.nextInt();
                scanner.nextLine();

                switch (menuChoice) {
                    case 1 -> bookRegistration();
                    case 2 -> bookNameSearch();
                    case 3 -> bookModify();
                    case 4 -> bookDelete();
                    case 5 -> service.registeredBookList();
                    case 9 -> {
                        return;
                    }
                    default -> System.out.println("잘못된 입력입니다.");
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                scanner.nextLine();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 1. 도서등록
    public static void bookRegistration() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("등록할 도서의 수량을 입력하세요: ");
        int bookQuantity = scanner.nextInt();
        scanner.nextLine();
        String[] books = new String[bookQuantity];
        String result = "";
        for (int i = 0; i < bookQuantity; i++) {
            System.out.print("등록할 도서명을 입력해주세요: ");
            String bookName = scanner.nextLine();
            books[i] = bookName;
        }
        service.bookRegistration(bookQuantity, books);
    }

    // 2. 도서명 검색
    public static void bookNameSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("검색할 도서명을 입력해주세요: ");
        String bookName = scanner.nextLine();
        service.bookNameSearch(bookName);

    }

    // 3. 도서 수정
    public static void bookModify() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("수정할 도서명을 입력해주세요: ");
        String bookName = scanner.nextLine();
        System.out.print("새로운 도서명을 입력해주세요: ");
        String newBookName = scanner.nextLine();
        service.bookModify(bookName, newBookName);
    }

    // 4. 도서 삭제
    public static void bookDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("삭제할 도서명을 입력해주세요: ");
        String bookName = scanner.nextLine();
        service.bookDelete(bookName);
    }



}
