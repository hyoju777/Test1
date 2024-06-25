package member.dto;

public class MemberInsertDTO
{
    private String memberNum;
    private String memberName;


    public MemberInsertDTO() {
    }

    public MemberInsertDTO(String memberNum, String memberName) {
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

    @Override
    public String toString() {
        return "MemberInsertDTO{" +
                "memberNum=" + memberNum +
                ", memberName='" + memberName + '\'' +
                '}';
    }
}
