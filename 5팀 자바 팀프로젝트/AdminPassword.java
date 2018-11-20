
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AdminPassword {
	private String psw;
	
	
	public AdminPassword() throws IOException{
		String fp = "관리자 비밀번호.txt";
		Scanner fs = new Scanner(new FileReader(fp));
		this.psw = fs.nextLine();
		fs.close();
	}
	
	public AdminPassword(String psw){
		this.psw=psw;
	}
	
	public String getPsw(){
		return psw;
	}
	
	public void setPsw(String psw) throws IOException{
		this.psw=psw;
		FileWriter fw = new FileWriter("관리자 비밀번호.txt");
		fw.write(psw); //변경한 비밀번호를 "관리자 비밀번호" 텍스트 파일에 저장
		fw.flush();
		fw.close();
	}
	
	//메인문의 "2.관리자모드" 실행시 비밀번호로 관리자임을 확인하기 위한 메소드 
	public void printCheckAdmin() throws IOException{
		Scanner input = new Scanner(System.in);
		int n=0;
		System.out.println("-------------------------------------");
		System.out.println("[관리자 확인]");
		while(true){
			
			System.out.print("\n암호를 입력하세요: ");
			String password = input.next();
			if(password.equals(psw)){
				System.out.println("\n관리자님 환영합니다.");
				System.out.println("-------------------------------------");
				break;
			}else
				System.out.println("암호가 일치하지 않습니다.(남은 입력횟수:"+(3-n)+")");
			n++;
			if(n==4){
				System.out.println("입력 횟수를 초과했습니다.\n프로그램을 종료합니다.");
				System.exit(0);
			}	
		}
	}
	
	//메인문의 "2-4.관리자 암호 변경" 실행을 위한 메소드 
	public void printChangePsw() throws IOException{
		Scanner input = new Scanner(System.in);
		int n=0;
		System.out.println("-------------------------------------");
		System.out.println("[암호 변경]");
		while(true){
			System.out.println("\n0.이전 화면으로\n1.암호 변경하기");
			System.out.print("선택:");
			int mode = input.nextInt();
			if(mode==0){
				System.out.println("\n->이전 화면으로 돌아갑니다.");
				System.out.println("-------------------------------------");
				break;
			}
			System.out.print("현재 암호를 입력하세요: ");
			String password = input.next();
			if(password.equals(psw)){
				System.out.print("새로운 암호를 입력하세요: ");
				String psw1 = input.next();
				System.out.print("암호를 다시 입력하세요: ");
				String psw2 = input.next();
				if(psw2.equals(psw1)){
					psw = psw1;
					setPsw(psw);
					System.out.println("\n[암호가 변경되었습니다.]");
					System.out.println("\n->이전 화면으로 돌아갑니다.");
					System.out.println("-------------------------------------");
					
					break;
				}else{
					System.out.println("변경한 암호가 일치하지 않습니다.(남은 입력횟수:"+(4-n)+")");
					System.out.println("\n[암호 변경에 실패하였습니다.]");
					if(n==4){
						System.out.println("[입력 횟수를 초과했습니다.]\n프로그램을 종료합니다.");
						System.exit(0);
					}
	
				}
			}else
				System.out.println("암호가 일치하지 않습니다.(남은 입력횟수:"+(4-n)+")");
			n++;
			if(n==5){
				System.out.println("[입력 횟수를 초과했습니다.]\n프로그램을 종료합니다.");
				System.exit(0);
			}

		}
	}
}
