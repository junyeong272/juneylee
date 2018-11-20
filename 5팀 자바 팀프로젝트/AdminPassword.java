
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AdminPassword {
	private String psw;
	
	
	public AdminPassword() throws IOException{
		String fp = "������ ��й�ȣ.txt";
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
		FileWriter fw = new FileWriter("������ ��й�ȣ.txt");
		fw.write(psw); //������ ��й�ȣ�� "������ ��й�ȣ" �ؽ�Ʈ ���Ͽ� ����
		fw.flush();
		fw.close();
	}
	
	//���ι��� "2.�����ڸ��" ����� ��й�ȣ�� ���������� Ȯ���ϱ� ���� �޼ҵ� 
	public void printCheckAdmin() throws IOException{
		Scanner input = new Scanner(System.in);
		int n=0;
		System.out.println("-------------------------------------");
		System.out.println("[������ Ȯ��]");
		while(true){
			
			System.out.print("\n��ȣ�� �Է��ϼ���: ");
			String password = input.next();
			if(password.equals(psw)){
				System.out.println("\n�����ڴ� ȯ���մϴ�.");
				System.out.println("-------------------------------------");
				break;
			}else
				System.out.println("��ȣ�� ��ġ���� �ʽ��ϴ�.(���� �Է�Ƚ��:"+(3-n)+")");
			n++;
			if(n==4){
				System.out.println("�Է� Ƚ���� �ʰ��߽��ϴ�.\n���α׷��� �����մϴ�.");
				System.exit(0);
			}	
		}
	}
	
	//���ι��� "2-4.������ ��ȣ ����" ������ ���� �޼ҵ� 
	public void printChangePsw() throws IOException{
		Scanner input = new Scanner(System.in);
		int n=0;
		System.out.println("-------------------------------------");
		System.out.println("[��ȣ ����]");
		while(true){
			System.out.println("\n0.���� ȭ������\n1.��ȣ �����ϱ�");
			System.out.print("����:");
			int mode = input.nextInt();
			if(mode==0){
				System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
				System.out.println("-------------------------------------");
				break;
			}
			System.out.print("���� ��ȣ�� �Է��ϼ���: ");
			String password = input.next();
			if(password.equals(psw)){
				System.out.print("���ο� ��ȣ�� �Է��ϼ���: ");
				String psw1 = input.next();
				System.out.print("��ȣ�� �ٽ� �Է��ϼ���: ");
				String psw2 = input.next();
				if(psw2.equals(psw1)){
					psw = psw1;
					setPsw(psw);
					System.out.println("\n[��ȣ�� ����Ǿ����ϴ�.]");
					System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
					System.out.println("-------------------------------------");
					
					break;
				}else{
					System.out.println("������ ��ȣ�� ��ġ���� �ʽ��ϴ�.(���� �Է�Ƚ��:"+(4-n)+")");
					System.out.println("\n[��ȣ ���濡 �����Ͽ����ϴ�.]");
					if(n==4){
						System.out.println("[�Է� Ƚ���� �ʰ��߽��ϴ�.]\n���α׷��� �����մϴ�.");
						System.exit(0);
					}
	
				}
			}else
				System.out.println("��ȣ�� ��ġ���� �ʽ��ϴ�.(���� �Է�Ƚ��:"+(4-n)+")");
			n++;
			if(n==5){
				System.out.println("[�Է� Ƚ���� �ʰ��߽��ϴ�.]\n���α׷��� �����մϴ�.");
				System.exit(0);
			}

		}
	}
}
