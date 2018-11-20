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
	public File file = new File("ȸ������.txt");
	public Scanner input = new Scanner(System.in);
	public ArrayList<String> name = new ArrayList<>();
	public ArrayList<Integer> birthDate = new ArrayList<>();
	public ArrayList<String> id = new ArrayList<>();
	public ArrayList<String> password = new ArrayList<>();
	public ArrayList<String> email = new ArrayList<>();
	public ArrayList<String> phone = new ArrayList<>();
	public ArrayList<String> time = new ArrayList<>();
	public ArrayList<String> total = new ArrayList<>();
	public int index = -1; //�α����� ��  �ش� ȸ�������� ������ �ִ� ArrayList�� �迭 ��ġ�� index������ �����Ͽ� Ȱ��
	
	public Member() throws IOException{
		Scanner fs = new Scanner(new FileReader(file));
		int i=0;
		while(fs.hasNextLine()){
			String a = fs.nextLine();
			String[] str = a.split(" ");
			name.add(str[0]); //�ڵ��ȣ ����
			birthDate.add(Integer.parseInt(str[1]));
			id.add(str[2]);  //��ǰ�� ����
			password.add(str[3]); // �ܰ� ����
			email.add(str[4]); // ���� ����
			phone.add(str[5]); //�հ� ����
			time.add(str[6]);
			total.add(name.get(i)+" "+birthDate.get(i)+" "+id.get(i)+" "+password.get(i)+" "+email.get(i)+" "+phone.get(i)+" "+time.get(i));
			i++;
		}
		fs.close();
	}
	
	public int getIndex(){
		return index;
	}
	
	//����ð�(�Ǵ� ����ð�)�� yy-MM-dd[HH:mm:ss]���·� �����ؼ� �������ִ� �޼ҵ�
	public static String getRunTime(){
		SimpleDateFormat setDate= new SimpleDateFormat("yy-MM-dd[HH:mm:ss]");
		Date currentTime = new Date();
		String runTime = setDate.format(currentTime);
		return runTime;
	}
		
	//�Է¹��� ������ txt���Ͽ� �Է��Ͽ� ȸ�� ���� ���
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
	
	//���� ����� �޼ҵ�(����� ������ txt���Ͽ� ���, �ؿ��� ���� ������ �޼ҵ�� ����)
	public void overWrite() throws IOException{
		fw = new FileWriter(file);
		for(int i=0; i<total.size(); i++)
			fw.write(total.get(i)+"\n");
		fw.flush();
		fw.close();
	}
	
	//���Ե� ȸ���� �´��� ���̵�� ��й�ȣ�� Ȯ���ϴ� �޼ҵ�
	public boolean checkId(String checkId,String checkPw) throws IOException{
		for(int i=0; i<id.size(); i++ ){
			if((id.get(i)).equals(checkId)&&password.get(i).equals(checkPw)){
				return true;
			}
		}
		return false;
		
	}
	
	//���ι��� "1-1.�α���" �ڳ� ������ ���� �޼ҵ�
	public void printMemberLogin()throws IOException{
		int n=0;
		System.out.println("-------------------------------------");
		System.out.println("[�α���]");
		while(true){
			System.out.print("\nID:");
			String id = input.next();
			System.out.print("��й�ȣ:");
			String password = input.next();
			if(checkId(id, password)==true){
				index = this.id.indexOf(id); //�α����� ȸ�������� ������ �ִ� �迭�� ��ġ�� index�� �������
				System.out.println("\n"+id+"�� ȯ���մϴ�.");
				break;
			}else{
				n++;
				System.out.println("�߸� �Է��ϼ̽��ϴ�. ����Ƚ��:"+(4-n));
				if(n==4){
					System.out.println("�Է� Ƚ���� �ʰ��߽��ϴ�.\n���α׷��� �����մϴ�");
					System.exit(0);
				}
			}	
		}
		
	}
	
	//���ι��� "1-2.ȸ������" �ڳ� ������ ���� �޼ҵ�
	public void printMembership()throws IOException{
		System.out.println("-------------------------------------");
		System.out.println("[ȸ�� ����]");
		System.out.print("\n������ �Է��ϼ���:");
		String name = input.next();
		System.out.print("��������� �Է��ϼ���:(ex.950101)");
		int birthDate = input.nextInt();
		System.out.print("����Ͻ� ID�� �Է��ϼ���:");
		String id = input.next();
		System.out.print("����Ͻ� ��й�ȣ�� �Է��ϼ���:");
		String password = input.next();
		System.out.print("e-mail�� �Է��ϼ���:");
		String email = input.next();
		System.out.print("�޴��� ��ȣ�� �Է��ϼ���:");
		String phone = input.next();

		inputDate(name,birthDate,id,password,email,phone);
		System.out.println("\nȯ���մϴ�! "+name+"�� ȸ�������� �Ϸ�Ǿ����ϴ�.");
		System.out.println("\n->�ʱ� ȭ������ ���ư��ϴ�.");
	}
	
	//���ι��� "1-1-5.ȸ������ Ȯ��" �ڳ� ���� �޼ҵ�
	public void printMemberInformation(){
		System.out.println("-------------------------------------");
		System.out.println("[ȸ�� ���� Ȯ��]");
		System.out.println("\n�̸�: "+name.get(index));
		System.out.println("�������: "+birthDate.get(index));
		System.out.println("ID: "+id.get(index));
		System.out.print("��й�ȣ: ");
		for(int i=0;i<password.get(index).length();i++) //��й�ȣ ������ ���ؼ� ��й�ȣ ������ŭ *�� ��� ǥ��
			System.out.print("*");
		System.out.println("("+password.get(index).length()+"�ڸ�)");
		System.out.println("�̸���: "+email.get(index));
		System.out.println("�޴��� ��ȣ: "+phone.get(index));
		System.out.println("������ �ð�: "+time.get(index));
	}
	
	//���ι��� "1-1-5-1.ȸ������ ����" �ڳ� ���� �޼ҵ�
	public void modifyMeberInformation() throws IOException{
		boolean a = true;
		System.out.println("-------------------------------------");
		System.out.println("[ȸ������ ����]");
		while(a){
			System.out.println("\n0.���� ȭ��  1.��й�ȣ  2.�̸���  3.�޴��� ��ȣ");
			System.out.print("�����ϼ���: ");
			int choice = input.nextInt();
			switch(choice){
				case 0:
					System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
					a=false;
					break;
				case 1:
					System.out.print("\n������ ��й�ȣ�� �Է��ϼ��� : ");
					String newPassword = input.next();
					password.set(index, newPassword);
					total.set(index,name.get(index)+" "+birthDate.get(index)+" "+id.get(index)+" "+password.get(index)+" "+email.get(index)+" "+phone.get(index)+" "+time.get(index));
					overWrite();
					System.out.println("\n��й�ȣ ������ �Ϸ�Ǿ����ϴ�.");
					break;
				case 2:
					System.out.print("\n������ �̸��� �ּҸ� �Է��ϼ���: ");
					String newEmail = input.next();
					email.set(index, newEmail);
					total.set(index,name.get(index)+" "+birthDate.get(index)+" "+id.get(index)+" "+password.get(index)+" "+email.get(index)+" "+phone.get(index)+" "+time.get(index));
					overWrite();
					System.out.println("\n�̸��� �ּ� ������ �Ϸ�Ǿ����ϴ�.");
					break;
				case 3:
					System.out.print("\n������ �޴��� ��ȣ�� �Է��ϼ���: ");
					String newPhone = input.next();
					phone.set(index, newPhone);
					total.set(index,name.get(index)+" "+birthDate.get(index)+" "+id.get(index)+" "+password.get(index)+" "+email.get(index)+" "+phone.get(index)+" "+time.get(index));
					overWrite();
					System.out.println("\n�޴��� ��ȣ ������ �Ϸ�Ǿ����ϴ�.");
					break;
			}
		}
	}
		
	//���ι��� "1-1-5-2.ȸ��Ż��" �ڳ� ������ ���� �޼ҵ�
	public void printDeleteMember()throws Exception{
		System.out.println("-------------------------------------");
		System.out.println("[ȸ�� Ż��]");
		System.out.print("\nID:");
		String deleteId = input.next();
		System.out.print("PW:");
		String deletePw = input.next();
		if(checkId(deleteId,deletePw)==true){ //���̵�� �н����尡 ��ġ�� ��� ����
			System.out.print("ȸ��Ż�� �Ͻ÷��� 'ȸ��Ż��'�� �����ּ���:"); //�Ǽ��� ȸ��Ż���ϴ� ���� �����ϱ� ���� �ܰ� ("ȸ��Ż��"�� ����� Ż���)
			String check = input.next();
			if(check.equals("ȸ��Ż��")){
				for(int i=0;i<id.size();i++){
					if(id.get(i).equals(deleteId)&&password.get(i).equals(deletePw)){
						name.remove(i);
						total.remove(i);						
						break;
					}
				}						
				overWrite();
				index = -1;
				System.out.println("\nȸ��Ż�� �Ϸ�Ǿ����ϴ�.");
				System.out.println("\n->�ʱ� ȭ������ ���ư��ϴ�.");
			}else{
				System.out.println("\nȸ��Ż�� �����߽��ϴ�.");
				System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
			}
		}else{
			System.out.println("\nȸ�������� ��ġ���� �ʽ��ϴ�.");
			System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
			}
	}
	
	//���ι��� "2-5.�Һ���ȸ�� ����" �ڳ� ������ ���� �޼ҵ�
	public void printManegeMember(){
		boolean a = true;
		while(a){
			System.out.println("-------------------------------------");
			System.out.println("[�Һ���ȸ�� ����]");
			System.out.println("\n0.���� ȭ��\n1.ȸ�� �� Ȯ��\n2.ȸ�� ��������");
			System.out.print("���� : ");
			int choice = input.nextInt();
			switch(choice){
				case 0: //���� ȭ��
					System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
					System.out.println("-------------------------------------");
					a=false;
					break;
				case 1: //2-5-1.ȸ�� �� Ȯ��
					System.out.println("-------------------------------------");
					System.out.println("[ȸ�� �� Ȯ��]");
					System.out.println("\n���� ȸ�� ��: "+name.size()+"�� �Դϴ�.");
					System.out.print("\n0.���� ȭ��: ");
					int select1 = input.nextInt();
					if(select1==0)
						System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
					break;
				case 2: //2-5-2.ȸ�� ��������
					System.out.println("-------------------------------------");
					System.out.println("[ȸ�� ��������]");
					System.out.format("\n%-6s%-8s%-10s%-10s\n","�̸�","�������","ID","�޴��� ��ȣ");
					for(int i=0;i<name.size();i++)
						System.out.format("%-6s%-9d%-12s%-10s\n",name.get(i),birthDate.get(i),id.get(i),phone.get(i));
					System.out.print("\n0.���� ȭ��: ");
					int select2 = input.nextInt();
					if(select2==0)
						System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
					break;
			}	

		}
		
	}
	
	
}
