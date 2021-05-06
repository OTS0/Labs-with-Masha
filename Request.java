public class Request {
    private final String command;
    int id=0;
    HumanBeing human=null;
    String path="";
    String WeaponType="";
    String SoundTrackNme="";
    public Request(Integer id, HumanBeing human, String path, String command, String WeaponType, String SoundTrackName){
        this.id=id;
        this.human=human;
        this.path=path;
        this.command=command;
        this.WeaponType=WeaponType;
        this.SoundTrackNme=SoundTrackName;
    }
}
