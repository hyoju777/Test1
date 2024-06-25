package member.service;

import member.dao.MemberRepository;
import member.dto.MemberDTO;
import member.dto.MemberInsertDTO;

import java.util.ArrayList;
public class Service {

        private MemberRepository memberRepository;

        public Service() {
            this.memberRepository = new MemberRepository();
        }

        // service
        // 비즈니스의 도메인과 관련된 역할을 수행한다.
        // 데이터베이스와 연결된 작업을 수행한다.
        public ArrayList memberViewAll() throws Exception {
            ArrayList member = memberRepository.memberViewAll();

            if(member == null){
                throw new Exception("사원정보 조회실패");
            }

            return member;
        }

        public MemberDTO memberFindByName(String name) throws Exception {
            // name이 입력되지 않은 경우
            if(name == null && name.equals("")){
                return null;
            }

            MemberDTO member = memberRepository.memberFindByName(name);

            if(member == null){
                throw new Exception("사원정보 조회실패");
            }
            return member;
        }

        public String memberInsert(MemberInsertDTO member) throws Exception {
            // 서비스는 아래와 같이 우리의 비즈니스 로직에 맞는 유효성을 검사한다.
            // 아래는 사원의 번호가 중복되는 것을 확인하고 만약 중복이라면 등록을 취소해야한다.
            MemberDTO findmember = memberRepository.memberFindById(member.getMemberNum());
            if(findmember != null){
                throw new Exception("중복회원");
            }
            int result = memberRepository.memberInsert(member);
            if(result < 0){
                throw new Exception("등록실패");
            }

            return (result > 0) ? "등록성공" : "등록실패";
        }

        public MemberDTO memberFindById(String index) {
            MemberDTO findMember = memberRepository.memberFindById(index);
            if(findMember != null){
                return findMember;
            }else{
                return null;
            }
        }

        public MemberDTO memeberModify(String name, String index) throws Exception {
            if(name.equals("") || name == null){
                throw new Exception("빈값 입력");
            }

            int result = memberRepository.memberModify(name,index);
            if(result < 0){
                throw new Exception("변경실패");
            }

            MemberDTO modifyMember = memberRepository.memberFindById(index);
            return modifyMember;
        }

    public MemberDTO memeberDelete(String index) throws Exception
    {

        int result = memberRepository.memberDelete(index);
        if(result < 0){
            throw new Exception("변경실패");
        }

        MemberDTO deleteMember = memberRepository.memberFindById(index);
        return deleteMember;
    }
    public ArrayList bookRegistration(int bookQuantity, String[] bookName) {
        return memberRepository.bookRegistration(bookQuantity, bookName);
    }

    public ArrayList bookNameSearch(String bookName) {
        return memberRepository.bookNameSearch(bookName);
    }

    public ArrayList bookModify(String bookName, String newBookName) {
        return memberRepository.bookModify(bookName, newBookName);
    }

    public ArrayList bookDelete(String bookName) {
        return memberRepository.bookDelete(bookName);
    }

    public ArrayList registeredBookList() throws Exception {
        ArrayList bookList = memberRepository.registeredBookList();
        if (bookList.isEmpty()) {
            throw new Exception("도서 조회실패");
        }
        return bookList;
    }




}
