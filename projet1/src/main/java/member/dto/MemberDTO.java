package member.dto;

public class MemberDTO
{
    private String memberNum;
    private String memberName;
    private String memberRentList;
    private String memberStatus;



    public MemberDTO() {

    }

    public MemberDTO(String memberNum, String memberName) {
        this.memberNum = memberNum;
        this.memberName = memberName;
    }



    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberRentList() {
        return memberRentList;
    }

    public void setMemberRentList(String memberRentList) {
        this.memberRentList = memberRentList;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "memberNum=" + memberNum +
                ", memberName='" + memberName + '\'' +
                ", memberRentList='" + memberRentList + '\'' +
                '}'+"\n";
    }
}
