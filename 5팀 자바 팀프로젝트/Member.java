import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Member{
	public FileWriter fw;
	public File file = new File("회원정보.txt");
	public Scanner input = new Scanner(System.in);
	public ArrayList<String> name = new ArrayList<>();
	public ArrayList<Integer> birthDate = new ArrayList<>();
	public ArrayList<String> id = new ArrayList<>();
	public ArrayList<String> password = new ArrayList<>();
	public ArrayList<String> email = new ArrayList<>();
	public ArrayList<String> phone = new ArrayList<>();
	public ArrayList<String> time = new ArrayList<>();
	public ArrayList<String> total = new ArrayList<>();
	public int index = -1; //로그인할 때  해당 회원정보를 가지고 있는 ArrayList의 배열 위치를 index값으로 설정하여 활용
	
	public Member() throws IOException{
		Scanner fs = new Scanner(new FileReader(file));
		int i=0;
		while(fs.hasNextLine()){
			String a = fs.nextLine();
			String[] str = a.split(" ");
			name.add(str[0]); //코드번호 저장
			birthDate.add(Integer.parseInt(str[1]));
			id.add(str[2]);  //상품명 저장
			password.add(str[3]); // 단가 저장
			email.add(str[4]); // 수량 저장
			phone.add(str[5]); //합계 저장
			time.add(str[6]);
			total.add(name.get(i)+" "+birthDate.get(i)+" "+id.get(i)+" "+password.get(i)+" "+email.get(i)+" "+phone.get(i)+" "+time.get(i));
			i++;
		}
		fs.close();
	}
	
	public int getIndex(){
		return index;
	}
	
	//현재시간(또는 실행시간)을 yy-MM-dd[HH:mm:ss]형태로 저장해서 리턴해주는 메소드
	public static String getRunTime(){
		SimpleDateFormat setDate= new SimpleDateFormat("yy-MM-dd[HH:mm:ss]");
		Date currentTime = new Date();
		String runTime = setDate.format(currentTime);
		return runTime;
	}
		
	//입력받은 정보를 txt파일에 입력하여 회원 정보 등록
	public void inputDate(String name,Integer birthDate,String id,String password,String email,String phone)throws IOException{
		fw = new FileWriter(file,true);
		fw.write(name+" ");
		fw.write(birthDate+" ");
		fw.write(id+" ");
		fw.write(password+" ");
		fw.write(email + " ");
		fw.write(phone+" ");
		fw.write(getRunTime()+"\n");
		fw.flush();
		fw.close();
	}
	
	//파일 덮어쓰기 메소드(변경된 정보를 txt파일에 덮어씀, 밑에서 자주 쓰여서 메소드로 만듬)
	public void overWrite() throws IOException{
		fw = new FileWriter(file);
		for(int i=0; i<total.size(); i++)
			fw.write(total.get(i)+"\n");
		fw.flush();
		fw.close();
	}
	
	//가입된 회원이 맞는지 아이디와 비밀번호를 확인하는 메소드
	public boolean checkId(String checkId,String checkPw) throws IOException{
		for(int i=0; i<id.size(); i++ ){
			if((id.get(i)).equals(checkId)&&password.get(i).equals(checkPw)){
				return true;
			}
		}
		return false;
		
	}
	
	//메인문의 "1-1.로그인" 코너 실행을 위한 메소드
	public void printMemberLogin()throws IOException{
		int n=0;
		System.out.println("-------------------------------------");
		System.out.println("[로그인]");
		while(true){
			System.out.print("\nID:");
			String id = input.next();
			System.out.print("비밀번호:");
			String password = input.next();
			if(checkId(id, password)==true){
				index = this.id.indexOf(id); //로그인한 회원정보를 가지고 있는 배열의 위치를 index에 집어넣음
				System.out.println("\n"+id+"님 환영합니다.");
				break;
			}else{
				n++;
				System.out.println("잘못 입력하셨습니다. 남은횟수:"+(4-n));
				if(n==4){
					System.out.println("입력 횟수를 초과했습니다.\n프로그램을 종료합니다");
					System.exit(0);
				}
			}	
		}
		
	}
	
	//메인문의 "1-2.회원가입" 코너 실행을 위한 메소드
	public void printMembership()throws IOException{
		System.out.println("-------------------------------------");
		System.out.println("[회원 가입]");
		System.out.print("\n성함을 입력하세요:");
		String name = input.next();
		System.out.print("생년월일을 입력하세요:(ex.950101)");
		int birthDate = input.nextInt();
		System.out.print("사용하실 ID를 입력하세요:");
		String id = input.next();
		System.out.print("사용하실 비밀번호를 입력하세요:");
		String password = input.next();
		System.out.print("e-mail을 입력하세요:");
		String email = input.next();
		System.out.print("휴대폰 번호를 입력하세요:");
		String phone = input.next();

		inputDate(name,birthDate,id,password,email,phone);
		System.out.println("\n환영합니다! "+name+"님 회원가입이 완료되었습니다.");
		System.out.println("\n->초기 화면으로 돌아갑니다.");
	}
	
	//메인문의 "1-1-5.회원정보 확인" 코너 실행 메소드
	public void printMemberInformation(){
		System.out.println("-------------------------------------");
		System.out.println("[회원 정보 확인]");
		System.out.println("\n이름: "+name.get(index));
		System.out.println("생년월일: "+birthDate.get(index));
		System.out.println("ID: "+id.get(index));
		System.out.print("비밀번호: ");
		for(int i=0;i<password.get(index).length();i++) //비밀번호 보안을 위해서 비밀번호 개수만큼 *로 대신 표현
			System.out.print("*");
		System.out.println("("+password.get(index).length()+"자리)");
		System.out.println("이메일: "+email.get(index));
		System.out.println("휴대폰 번호: "+phone.get(index));
		System.out.println("가입한 시간: "+time.get(index));
	}
	
	//메인문의 "1-1-5-1.회원정보 수정" 코너 실행 메소드
	public void modifyMeberInformation() throws IOException{
		boolean a = true;
		System.out.println("-------------------------------------");
		System.out.println("[회원정보 수정]");
		while(a){
			System.out.println("\n0.이전 화면  1.비밀번호  2.이메일  3.휴대폰 번호");
			System.out.print("선택하세요: ");
			int choice = input.nextInt();
			switch(choice){
				case 0:
					System.out.println("\n->이전 화면으로 돌아갑니다.");
					a=false;
					break;
				case 1:
					System.out.print("\n변경할 비밀번호를 입력하세요 : ");
					String newPassword = input.next();
					password.set(index, newPassword);
					total.set(index,name.get(index)+" "+birthDate.get(index)+" "+id.get(index)+" "+password.get(index)+" "+email.get(index)+" "+phone.get(index)+" "+time.get(index));
					overWrite();
					System.out.println("\n비밀번호 변경이 완료되었습니다.");
					break;
				case 2:
					System.out.print("\n변경할 이메일 주소를 입력하세요: ");
					String newEmail = input.next();
					email.set(index, newEmail);
					total.set(index,name.get(index)+" "+birthDate.get(index)+" "+id.get(index)+" "+password.get(index)+" "+email.get(index)+" "+phone.get(index)+" "+time.get(index));
					overWrite();
					System.out.println("\n이메일 주소 변경이 완료되었습니다.");
					break;
				case 3:
					System.out.print("\n변경할 휴대폰 번호를 입력하세요: ");
					String newPhone = input.next();
					phone.set(index, newPhone);
					total.set(index,name.get(index)+" "+birthDate.get(index)+" "+id.get(index)+" "+password.get(index)+" "+email.get(index)+" "+phone.get(index)+" "+time.get(index));
					overWrite();
					System.out.println("\n휴대폰 번호 변경이 완료되었습니다.");
					break;
			}
		}
	}
		
	//메인문의 "1-1-5-2.회원탈퇴" 코너 실행을 위한 메소드
	public void printDeleteMember()throws Exception{
		System.out.println("-------------------------------------");
		System.out.println("[회원 탈퇴]");
		System.out.print("\nID:");
		String deleteId = input.next();
		System.out.print("PW:");
		String deletePw = input.next();
		if(checkId(deleteId,deletePw)==true){ //아이디와 패스워드가 일치할 경우 진행
			System.out.print("회원탈퇴 하시려면 '회원탈퇴'를 적어주세요:"); //실수로 회원탈퇴하는 것을 방지하기 위한 단계 ("회원탈퇴"를 적어야 탈퇴됨)
			String check = input.next();
			if(check.equals("회원탈퇴")){
				for(int i=0;i<id.size();i++){
					if(id.get(i).equals(deleteId)&&password.get(i).equals(deletePw)){
						name.remove(i);
						total.remove(i);						
						break;
					}
				}						
				overWrite();
				index = -1;
				System.out.println("\n회원탈퇴가 완료되었습니다.");
				System.out.println("\n->초기 화면으로 돌아갑니다.");
			}else{
				System.out.println("\n회원탈퇴에 실패했습니다.");
				System.out.println("\n->이전 화면으로 돌아갑니다.");
			}
		}else{
			System.out.println("\n회원정보가 일치하지 않습니다.");
			System.out.println("\n->이전 화면으로 돌아갑니다.");
			}
	}
	
	//메인문의 "2-5.소비자회원 관리" 코너 실행을 위한 메소드
	public void printManegeMember(){
		boolean a = true;
		while(a){
			System.out.println("-------------------------------------");
			System.out.println("[소비자회원 관리]");
			System.out.println("\n0.이전 화면\n1.회원 수 확인\n2.회원 개인정보");
			System.out.print("선택 : ");
			int choice = input.nextInt();
			switch(choice){
				case 0: //이전 화면
					System.out.println("\n->이전 화면으로 돌아갑니다.");
					System.out.println("-------------------------------------");
					a=false;
					break;
				case 1: //2-5-1.회원 수 확인
					System.out.println("-------------------------------------");
					System.out.println("[회원 수 확인]");
					System.out.println("\n현재 회원 수: "+name.size()+"명 입니다.");
					System.out.print("\n0.이전 화면: ");
					int select1 = input.nextInt();
					if(select1==0)
						System.out.println("\n->이전 화면으로 돌아갑니다.");
					break;
				case 2: //2-5-2.회원 개인정보
					System.out.println("-------------------------------------");
					System.out.println("[회원 개인정보]");
					System.out.format("\n%-6s%-8s%-10s%-10s\n","이름","생년월일","ID","휴대폰 번호");
					for(int i=0;i<name.size();i++)
						System.out.format("%-6s%-9d%-12s%-10s\n",name.get(i),birthDate.get(i),id.get(i),phone.get(i));
					System.out.print("\n0.이전 화면: ");
					int select2 = input.nextInt();
					if(select2==0)
						System.out.println("\n->이전 화면으로 돌아갑니다.");
					break;
			}	

		}
		
	}
	
	
}
